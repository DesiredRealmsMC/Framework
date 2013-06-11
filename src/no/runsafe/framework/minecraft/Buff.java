package no.runsafe.framework.minecraft;

import no.runsafe.framework.minecraft.entity.RunsafeLivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Buff
{
	public static class Combat
	{
		public static final Buff Confusion = new Buff(PotionEffectType.CONFUSION, true);
		public static final Buff Blindness = new Buff(PotionEffectType.BLINDNESS, true);

		public static class Damage
		{
			public static final Buff Increase = new Buff(PotionEffectType.INCREASE_DAMAGE, true);
			public static final Buff Decrease = new Buff(PotionEffectType.WEAKNESS, true);
			public static final Buff Instant = new Buff(PotionEffectType.HARM, true);
			public static final Buff Poison = new Buff(PotionEffectType.POISON, true);
			public static final Buff Wither = new Buff(PotionEffectType.WITHER, true);
		}
	}

	public static class Healing
	{
		public static final Buff Instant = new Buff(PotionEffectType.HEAL, true);
		public static final Buff Regeneration = new Buff(PotionEffectType.REGENERATION, true);
	}

	public static class Resistance
	{
		public static final Buff Damage = new Buff(PotionEffectType.DAMAGE_RESISTANCE, true);
		public static final Buff Fire = new Buff(PotionEffectType.FIRE_RESISTANCE, true);
	}

	public static class Utility
	{
		public static final Buff Invisibility = new Buff(PotionEffectType.INVISIBILITY, true);
		public static final Buff NightVision = new Buff(PotionEffectType.NIGHT_VISION, true);
		public static final Buff WaterBreathing = new Buff(PotionEffectType.WATER_BREATHING, true);

		public static class Movement
		{
			public static final Buff IncreaseSpeed = new Buff(PotionEffectType.SPEED, true);
			public static final Buff DecreaseSpeed = new Buff(PotionEffectType.SLOW, true);
			public static final Buff JumpHeight = new Buff(PotionEffectType.JUMP, true);
		}

		public static class DigSpeed
		{
			public static final Buff Increase = new Buff(PotionEffectType.FAST_DIGGING, true);
			public static final Buff Decrease = new Buff(PotionEffectType.SLOW_DIGGING, true);
		}
	}

	public static class Disease
	{
		public static final Buff Hunger = new Buff(PotionEffectType.HUNGER, true);
	}

	public PotionEffect getEffect()
	{
		if (root)
			return convertToBuff().getEffect();

		return new PotionEffect(type, duration, amplification, ambient);
	}

	public PotionEffectType getType()
	{
		return type;
	}

	public Buff duration(int value)
	{
		if (root)
			return convertToBuff().duration(value);

		duration = value * 20;
		return this;
	}

	public Buff amplification(int value)
	{
		if (root)
			return convertToBuff().amplification(value);

		amplification = value;
		return this;
	}

	public Buff ambient(boolean value)
	{
		if (root)
			return convertToBuff().ambient(value);

		ambient = value;
		return this;
	}

	public Buff applyTo(RunsafeLivingEntity entity)
	{
		getEffect().apply(entity.getRaw());
		return this;
	}

	private Buff(PotionEffectType type, boolean root)
	{
		this.type = type;
		this.root = root;
		this.duration = 1;
		this.amplification = 1;
	}

	private Buff convertToBuff()
	{
		return new Buff(type, false);
	}

	private final PotionEffectType type;
	private final boolean root;
	private int duration;
	private int amplification;
	private boolean ambient;
}
