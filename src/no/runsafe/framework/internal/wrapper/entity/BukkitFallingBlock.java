package no.runsafe.framework.internal.wrapper.entity;

import no.runsafe.framework.minecraft.entity.RunsafeEntity;
import org.bukkit.entity.FallingBlock;

public abstract class BukkitFallingBlock extends RunsafeEntity
{
	protected BukkitFallingBlock(FallingBlock toWrap)
	{
		super(toWrap);
		this.block = toWrap;
	}

	public Byte getBlockData()
	{
		return this.block.getBlockData();
	}

	public int getBlockId()
	{
		return this.block.getBlockId();
	}

	public boolean getDropItem()
	{
		return this.block.getDropItem();
	}

	public void setDropItem(boolean drop)
	{
		this.block.setDropItem(drop);
	}

	@Override
	public FallingBlock getRaw()
	{
		return block;
	}

	protected final FallingBlock block;
}
