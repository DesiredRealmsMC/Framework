package no.runsafe.framework.minecraft;

import no.runsafe.framework.minecraft.item.meta.RunsafeFirework;
import no.runsafe.framework.minecraft.item.meta.RunsafeMeta;
import no.runsafe.framework.minecraft.player.RunsafePlayer;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FireworkBuilder
{
	public FireworkBuilder Random()
	{
		return Random(rng.nextInt(4));
	}

	public FireworkBuilder Random(int effects)
	{
		power = rng.nextInt(3);
		FireworkEffect.Type[] types = FireworkEffect.Type.values();
		for (int i = 0; i <= effects; ++i)
		{
			if (i > 0)
			{
				this.effects.add(builder.build());
				builder = FireworkEffect.builder();
			}
			builder
				.trail(rng.nextBoolean())
				.flicker(rng.nextBoolean())
				.with(types[rng.nextInt(types.length - 1)])
				.withColor(Color.fromRGB(rng.nextInt(255), rng.nextInt(255), rng.nextInt(255)))
				.withColor(Color.fromRGB(rng.nextInt(255), rng.nextInt(255), rng.nextInt(255)));
		}
		return this;
	}

	public FireworkBuilder Effect(Firework.EffectType effect)
	{
		builder = builder.with(effect.getType());
		return this;
	}

	public FireworkBuilder Ball()
	{
		builder = builder.with(FireworkEffect.Type.BALL);
		return this;
	}

	public FireworkBuilder LargeBall()
	{
		builder = builder.with(FireworkEffect.Type.BALL_LARGE);
		return this;
	}

	public FireworkBuilder Star()
	{
		builder = builder.with(FireworkEffect.Type.STAR);
		return this;
	}

	public FireworkBuilder Burst()
	{
		builder = builder.with(FireworkEffect.Type.BURST);
		return this;
	}

	public FireworkBuilder Creeper()
	{
		builder = builder.with(FireworkEffect.Type.CREEPER);
		return this;
	}

	public FireworkBuilder Trail()
	{
		return Trail(true);
	}

	public FireworkBuilder Trail(boolean trail)
	{
		builder = builder.trail(trail);
		return this;
	}

	public FireworkBuilder Flicker()
	{
		return Flicker(true);
	}

	public FireworkBuilder Flicker(boolean flicker)
	{
		builder = builder.flicker(flicker);
		return this;
	}

	public FireworkBuilder Colour(String rgb)
	{
		builder = builder.withColor(Color.fromRGB(Integer.parseInt(rgb, 16)));
		return this;
	}

	public FireworkBuilder Colour(byte r, byte g, byte b)
	{
		builder = builder.withColor(Color.fromRGB(r, g, b));
		return this;
	}

	public FireworkBuilder Fade(String rgb)
	{
		builder = builder.withFade(Color.fromRGB(Integer.parseInt(rgb, 16)));
		return this;
	}

	public FireworkBuilder Fade(byte r, byte g, byte b)
	{
		builder = builder.withFade(Color.fromRGB(r, g, b));
		return this;
	}

	public FireworkBuilder And()
	{
		effects.add(builder.build());
		builder = FireworkEffect.builder();
		return this;
	}

	public FireworkBuilder Power(int power)
	{
		this.power = power;
		return this;
	}

	public RunsafeMeta Create()
	{
		RunsafeFirework item = (RunsafeFirework) Item.Special.Crafted.Firework.getItem();
		item.setPower(power);
		((FireworkMeta) item.getRaw().getItemMeta()).addEffect(builder.build());
		return item;
	}

	public void Fire(RunsafeLocation location)
	{
		org.bukkit.entity.Firework firework = location.getWorld().getRaw()
			.spawn(location.getRaw(), org.bukkit.entity.Firework.class);
		firework.getFireworkMeta().setPower(power);
		if (!effects.isEmpty())
			firework.getFireworkMeta().addEffects(effects);
		firework.getFireworkMeta().addEffect(builder.build());
	}

	public void Give(RunsafePlayer player)
	{
		player.give(Create());
	}

	FireworkBuilder()
	{
		builder = FireworkEffect.builder();
	}

	private FireworkEffect.Builder builder;
	private final List<FireworkEffect> effects = new ArrayList<FireworkEffect>();
	private int power = 0;

	private static final Random rng = new Random();
}
