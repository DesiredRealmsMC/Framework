package no.runsafe.framework.minecraft.event.block;

import no.runsafe.framework.internal.wrapper.ObjectWrapper;
import no.runsafe.framework.minecraft.block.RunsafeBlock;
import no.runsafe.framework.api.event.CancellableEvent;
import no.runsafe.framework.minecraft.player.RunsafePlayer;
import org.bukkit.event.block.BlockPlaceEvent;

public class RunsafeBlockPlaceEvent extends RunsafeBlockEvent implements CancellableEvent
{
	public RunsafeBlockPlaceEvent(BlockPlaceEvent toWrap)
	{
		super(toWrap);
		event = toWrap;
	}

	//Used in default MC spawn region, not linked to WorldGuard
	public boolean canBuild()
	{
		return event.canBuild();
	}

	public void setBuild(boolean canBuild)
	{
		event.setBuild(canBuild);
	}

	public RunsafeBlock getBlock()
	{
		return ObjectWrapper.convert(event.getBlock());
	}

	public RunsafeBlock getBlockAgainst()
	{
		return ObjectWrapper.convert(event.getBlockAgainst());
	}

	public RunsafeBlock getBlockPlaced()
	{
		return ObjectWrapper.convert(event.getBlockPlaced());
	}

	public RunsafePlayer getPlayer()
	{
		return ObjectWrapper.convert(event.getPlayer());
	}

	@Override
	public boolean getCancelled()
	{
		return event.isCancelled();
	}

	@Override
	public void setCancelled(boolean cancel)
	{
		event.setCancelled(cancel);
	}

	private final BlockPlaceEvent event;
}
