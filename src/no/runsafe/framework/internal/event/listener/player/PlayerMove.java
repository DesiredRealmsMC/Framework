package no.runsafe.framework.internal.event.listener.player;

import no.runsafe.framework.api.event.IRunsafeEvent;
import no.runsafe.framework.internal.event.listener.EventRouterBase;
import no.runsafe.framework.api.EventRouterFactory;
import no.runsafe.framework.api.event.player.IPlayerMove;
import no.runsafe.framework.api.IOutput;
import no.runsafe.framework.internal.wrapper.ObjectWrapper;
import no.runsafe.framework.api.IScheduler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public final class PlayerMove extends EventRouterBase<IPlayerMove, PlayerMoveEvent>
{
	private PlayerMove(IOutput output, IScheduler scheduler, IPlayerMove handler)
	{
		super(output, scheduler, handler);
	}

	@EventHandler
	@Override
	public void acceptEvent(PlayerMoveEvent event)
	{
		super.acceptEvent(event);
	}

	@Override
	public boolean onEvent(PlayerMoveEvent event)
	{
		return handler.OnPlayerMove(ObjectWrapper.convert(event.getPlayer()), ObjectWrapper.convert(event.getFrom()), ObjectWrapper.convert(event.getTo()));
	}

	public static EventRouterFactory Factory()
	{
		return new EventRouterFactory()
		{
			@Override
			public Class<? extends IRunsafeEvent> getInterface()
			{
				return IPlayerMove.class;
			}

			@Override
			public Listener getListener(IOutput output, IScheduler scheduler, IRunsafeEvent subscriber)
			{
				return new PlayerMove(output, scheduler, (IPlayerMove) subscriber);
			}
		};
	}
}
