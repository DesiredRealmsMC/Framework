package no.runsafe.framework.internal.event.listener.block;

import no.runsafe.framework.api.EventRouterFactory;
import no.runsafe.framework.api.IOutput;
import no.runsafe.framework.api.IScheduler;
import no.runsafe.framework.api.event.IRunsafeEvent;
import no.runsafe.framework.api.event.block.IBlockDispense;
import no.runsafe.framework.api.event.block.IItemDispensed;
import no.runsafe.framework.api.event.block.IItemDropped;
import no.runsafe.framework.internal.event.listener.EventRouterBase;
import no.runsafe.framework.internal.wrapper.ObjectWrapper;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;

public final class BlockDispense extends EventRouterBase<IBlockDispense, BlockDispenseEvent>
{
	private BlockDispense(IOutput output, IScheduler scheduler, IBlockDispense handler)
	{
		super(output, scheduler, handler);
	}

	@EventHandler
	@Override
	public void acceptEvent(BlockDispenseEvent event)
	{
		if (handler instanceof IItemDropped && event.getBlock().getType() != Material.DROPPER)
			return;

		if (handler instanceof IItemDispensed && event.getBlock().getType() != Material.DISPENSER)
			return;

		super.acceptEvent(event);
	}

	@Override
	public boolean onEvent(BlockDispenseEvent event)
	{
		return handler.OnBlockDispense(
			ObjectWrapper.convert(event.getBlock()),
			ObjectWrapper.convert(event.getItem())
		);
	}

	public static EventRouterFactory Factory()
	{
		return new EventRouterFactory()
		{
			@Override
			public Class<? extends IRunsafeEvent> getInterface()
			{
				return IBlockDispense.class;
			}

			@Override
			public Listener getListener(IOutput output, IScheduler scheduler, IRunsafeEvent subscriber)
			{
				return new BlockDispense(output, scheduler, (IBlockDispense) subscriber);
			}
		};
	}
}

