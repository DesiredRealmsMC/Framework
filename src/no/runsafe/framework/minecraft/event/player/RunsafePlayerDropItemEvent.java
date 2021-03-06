package no.runsafe.framework.minecraft.event.player;

import no.runsafe.framework.internal.wrapper.ObjectWrapper;
import no.runsafe.framework.api.event.CancellableEvent;
import no.runsafe.framework.minecraft.entity.RunsafeItem;
import org.bukkit.event.player.PlayerDropItemEvent;

public class RunsafePlayerDropItemEvent extends RunsafePlayerEvent implements CancellableEvent
{
	public RunsafePlayerDropItemEvent(PlayerDropItemEvent toWrap)
	{
		super(toWrap);
		event = toWrap;
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

	public RunsafeItem getItem()
	{
		return ObjectWrapper.convert(event.getItemDrop());
	}

	private final PlayerDropItemEvent event;
}
