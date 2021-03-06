package no.runsafe.framework.internal.event.listener.inventory;

import no.runsafe.framework.api.event.IRunsafeEvent;
import no.runsafe.framework.api.event.inventory.IInventoryClosed;
import no.runsafe.framework.internal.event.listener.EventRouterBase;
import no.runsafe.framework.api.EventRouterFactory;
import no.runsafe.framework.api.IOutput;
import no.runsafe.framework.minecraft.event.inventory.RunsafeInventoryCloseEvent;
import no.runsafe.framework.api.IScheduler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public final class InventoryClose extends EventRouterBase<IInventoryClosed, InventoryCloseEvent>
{
	private InventoryClose(IOutput output, IScheduler scheduler, IInventoryClosed handler)
	{
		super(output, scheduler, handler);
	}

	@EventHandler
	@Override
	public void acceptEvent(InventoryCloseEvent event)
	{
		super.acceptEvent(event);
	}

	@Override
	public boolean onEvent(InventoryCloseEvent event)
	{
		handler.OnInventoryClosed(new RunsafeInventoryCloseEvent(event));
		return true;
	}

	public static EventRouterFactory Factory()
	{
		return new EventRouterFactory()
		{
			@Override
			public Class<? extends IInventoryClosed> getInterface()
			{
				return IInventoryClosed.class;
			}

			@Override
			public Listener getListener(IOutput output, IScheduler scheduler, IRunsafeEvent subscriber)
			{
				return new InventoryClose(output, scheduler, (IInventoryClosed) subscriber);
			}
		};
	}
}
