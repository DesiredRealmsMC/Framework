package no.runsafe.framework.internal.wrapper.block;

import no.runsafe.framework.minecraft.block.RunsafeBlockState;
import no.runsafe.framework.api.minecraft.IInventoryHolder;
import no.runsafe.framework.minecraft.inventory.RunsafeInventory;
import no.runsafe.framework.internal.wrapper.ObjectWrapper;
import org.bukkit.block.Furnace;

public abstract class BukkitFurnace extends RunsafeBlockState implements IInventoryHolder
{
	protected BukkitFurnace(Furnace toWrap)
	{
		super(toWrap);
		furnace = toWrap;
	}

	public short getBurnTime()
	{
		return furnace.getBurnTime();
	}

	public void setBurnTime(short i)
	{
		furnace.setBurnTime(i);
	}

	public short getCookTime()
	{
		return furnace.getCookTime();
	}

	public void setCookTime(short i)
	{
		furnace.setCookTime(i);
	}

	public RunsafeInventory getInventory()
	{
		return ObjectWrapper.convert(furnace.getInventory());
	}

	@Override
	public Furnace getRaw()
	{
		return furnace;
	}

	protected final Furnace furnace;
}
