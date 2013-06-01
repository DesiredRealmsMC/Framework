package no.runsafe.framework.event.listener.hanging;

import no.runsafe.framework.event.IRunsafeEvent;
import no.runsafe.framework.event.hanging.IItemFramePlaced;
import no.runsafe.framework.event.listener.EventRouterBase;
import no.runsafe.framework.event.listener.EventRouterFactory;
import no.runsafe.framework.output.IOutput;
import no.runsafe.framework.server.ObjectWrapper;
import no.runsafe.framework.timer.IScheduler;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingPlaceEvent;

public class ItemFramePlace extends EventRouterBase<IItemFramePlaced, HangingPlaceEvent>
{
	protected ItemFramePlace(IOutput output, IScheduler scheduler, IItemFramePlaced handler)
	{
		super(output, scheduler, handler);
	}

	@EventHandler
	@Override
	public void AcceptEvent(HangingPlaceEvent event)
	{
		if (event.getEntity().getType() == EntityType.ITEM_FRAME)
			super.AcceptEvent(event);
	}

	@Override
	public boolean OnEvent(HangingPlaceEvent event)
	{
		return handler.OnItemFramePlaced(
			ObjectWrapper.convert(event.getPlayer()),
			ObjectWrapper.convert((ItemFrame) event.getEntity())
		);
	}

	public static EventRouterFactory Factory()
	{
		return new EventRouterFactory()
		{
			@Override
			public Class<? extends IRunsafeEvent> getInterface()
			{
				return IItemFramePlaced.class;
			}

			@Override
			public Listener getListener(IOutput output, IScheduler scheduler, IRunsafeEvent subscriber)
			{
				return new ItemFramePlace(output, scheduler, (IItemFramePlaced) subscriber);
			}
		};
	}
}
