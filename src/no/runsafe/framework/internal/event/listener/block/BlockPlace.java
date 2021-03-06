package no.runsafe.framework.internal.event.listener.block;

import no.runsafe.framework.api.event.IRunsafeEvent;
import no.runsafe.framework.api.event.block.IBlockPlace;
import no.runsafe.framework.internal.event.listener.EventRouterBase;
import no.runsafe.framework.api.EventRouterFactory;
import no.runsafe.framework.api.IOutput;
import no.runsafe.framework.internal.wrapper.ObjectWrapper;
import no.runsafe.framework.api.IScheduler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public final class BlockPlace extends EventRouterBase<IBlockPlace, BlockPlaceEvent>
{
	private BlockPlace(IOutput output, IScheduler scheduler, IBlockPlace handler)
	{
		super(output, scheduler, handler);
	}

	@EventHandler
	@Override
	public void acceptEvent(BlockPlaceEvent event)
	{
		super.acceptEvent(event);
	}

	@Override
	public boolean onEvent(BlockPlaceEvent event)
	{
		return handler.OnBlockPlace(
			ObjectWrapper.convert(event.getPlayer()),
			ObjectWrapper.convert(event.getBlock())
		);
	}

	public static EventRouterFactory Factory()
	{
		return new EventRouterFactory()
		{
			@Override
			public Class<? extends IRunsafeEvent> getInterface()
			{
				return IBlockPlace.class;
			}

			@Override
			public Listener getListener(IOutput output, IScheduler scheduler, IRunsafeEvent subscriber)
			{
				return new BlockPlace(output, scheduler, (IBlockPlace) subscriber);
			}
		};
	}
}

