package no.runsafe.framework.minecraft.entity;

import no.runsafe.framework.api.minecraft.RunsafeEntityType;
import no.runsafe.framework.internal.wrapper.ObjectWrapper;
import no.runsafe.framework.minecraft.RunsafeLocation;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public enum LivingEntity implements RunsafeEntityType
{
	Bat(EntityType.BAT),
	Blaze(EntityType.BLAZE),
	CaveSpider(EntityType.CAVE_SPIDER),
	Chicken(EntityType.CHICKEN),
	Cow(EntityType.COW),
	Creeper(EntityType.CREEPER),
	EnderDragon(EntityType.ENDER_DRAGON),
	Enderman(EntityType.ENDERMAN),
	Ghast(EntityType.GHAST),
	Giant(EntityType.GIANT),
	Horse(EntityType.HORSE),
	IronGolem(EntityType.IRON_GOLEM),
	LavaSlime(EntityType.MAGMA_CUBE),
	MushroomCow(EntityType.MUSHROOM_COW),
	Ocelot(EntityType.OCELOT, "Ocelot"),
	Pig(EntityType.PIG),
	PigZombie(EntityType.PIG_ZOMBIE, "PigZombie"),
	Player(EntityType.PLAYER),
	Sheep(EntityType.SHEEP),
	Silverfish(EntityType.SILVERFISH),
	Skeleton(EntityType.SKELETON),
	Slime(EntityType.SLIME),
	Snowman(EntityType.SNOWMAN),
	Spider(EntityType.SPIDER),
	Squid(EntityType.SQUID),
	Villager(EntityType.VILLAGER),
	Witch(EntityType.WITCH),
	Wither(EntityType.WITHER),
	Wolf(EntityType.WOLF),
	Zombie(EntityType.ZOMBIE);

	LivingEntity(EntityType bukkitType, String name)
	{
		type = bukkitType;
		nameOverride = name;
	}

	LivingEntity(EntityType bukkitType)
	{
		type = bukkitType;
		nameOverride = null;
	}

	@Override
	public Class<? extends Entity> getEntityType()
	{
		return type.getEntityClass();
	}

	@Override
	public String getName()
	{
		return nameOverride == null ? type.getName() : nameOverride;
	}

	@Override
	public int getId()
	{
		return type.getTypeId();
	}

	@Override
	public boolean isAlive()
	{
		return type.isAlive();
	}

	@Override
	public boolean isSpawnable()
	{
		return type.isSpawnable();
	}

	@Override
	public EntityType getRaw()
	{
		return type;
	}

	@Override
	public RunsafeEntity spawn(RunsafeLocation location)
	{
		return ObjectWrapper.convert(location.getWorld().spawn(location, this.getEntityType()));
	}

	private final EntityType type;
	private final String nameOverride;
}
