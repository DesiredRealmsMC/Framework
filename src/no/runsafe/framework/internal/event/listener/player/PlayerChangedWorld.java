package no.runsafe.framework.internal.event.listener.player;

import no.runsafe.framework.api.event.IRunsafeEvent;
import no.runsafe.framework.internal.event.listener.EventRouterBase;
import no.runsafe.framework.api.EventRouterFactory;
import no.runsafe.framework.api.event.player.IPlayerChangedWorldEvent;
import no.runsafe.framework.api.IOutput;
import no.runsafe.framework.minecraft.event.player.RunsafePlayerChangedWorldEvent;
import no.runsafe.framework.api.IScheduler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public final class PlayerChangedWorld extends EventRouterBase<IPlayerChangedWorldEvent, PlayerChangedWorldEvent>
{
	private PlayerChangedWorld(IOutput output, IScheduler scheduler, IPlayerChangedWorldEvent handler)
	{
		super(output, scheduler, handler);
	}

	@EventHandler
	@Override
	public void acceptEvent(PlayerChangedWorldEvent event)
	{
		super.acceptEvent(event);
	}

	@Override
	public boolean onEvent(PlayerChangedWorldEvent event)
	{
		handler.OnPlayerChangedWorld(new RunsafePlayerChangedWorldEvent(event));
		return true;
	}

	public static EventRouterFactory Factory()
	{
		return new EventRouterFactory()
		{
			@Override
			public Class<? extends IRunsafeEvent> getInterface()
			{
				return IPlayerChangedWorldEvent.class;
			}

			@Override
			public Listener getListener(IOutput output, IScheduler scheduler, IRunsafeEvent subscriber)
			{
				return new PlayerChangedWorld(output, scheduler, (IPlayerChangedWorldEvent) subscriber);
			}
		};
	}
}
