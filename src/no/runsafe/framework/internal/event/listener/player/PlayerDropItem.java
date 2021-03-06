package no.runsafe.framework.internal.event.listener.player;

import no.runsafe.framework.api.event.IRunsafeEvent;
import no.runsafe.framework.internal.event.listener.EventRouterBase;
import no.runsafe.framework.api.EventRouterFactory;
import no.runsafe.framework.api.event.player.IPlayerDropItemEvent;
import no.runsafe.framework.api.IOutput;
import no.runsafe.framework.minecraft.event.player.RunsafePlayerDropItemEvent;
import no.runsafe.framework.api.IScheduler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public final class PlayerDropItem extends EventRouterBase<IPlayerDropItemEvent, PlayerDropItemEvent>
{
	private PlayerDropItem(IOutput output, IScheduler scheduler, IPlayerDropItemEvent handler)
	{
		super(output, scheduler, handler);
	}

	@EventHandler
	@Override
	public void acceptEvent(PlayerDropItemEvent event)
	{
		super.acceptEvent(event);
	}

	@Override
	public boolean onEvent(PlayerDropItemEvent event)
	{
		handler.OnPlayerDropItem(new RunsafePlayerDropItemEvent(event));
		return true;
	}

	public static EventRouterFactory Factory()
	{
		return new EventRouterFactory()
		{
			@Override
			public Class<? extends IRunsafeEvent> getInterface()
			{
				return IPlayerDropItemEvent.class;
			}

			@Override
			public Listener getListener(IOutput output, IScheduler scheduler, IRunsafeEvent subscriber)
			{
				return new PlayerDropItem(output, scheduler, (IPlayerDropItemEvent) subscriber);
			}
		};
	}
}
