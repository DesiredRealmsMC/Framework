package no.runsafe.framework.internal.event.listener.entity;

import no.runsafe.framework.api.event.IRunsafeEvent;
import no.runsafe.framework.api.event.entity.ISpawnEggUsed;
import no.runsafe.framework.internal.event.listener.EventRouterBase;
import no.runsafe.framework.api.EventRouterFactory;
import no.runsafe.framework.api.IOutput;
import no.runsafe.framework.internal.wrapper.ObjectWrapper;
import no.runsafe.framework.api.IScheduler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public final class SpawnEggUsed extends EventRouterBase<ISpawnEggUsed, CreatureSpawnEvent>
{
	private SpawnEggUsed(IOutput output, IScheduler scheduler, ISpawnEggUsed handler)
	{
		super(output, scheduler, handler);
	}

	@EventHandler
	@Override
	public void acceptEvent(CreatureSpawnEvent event)
	{
		if (event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.SPAWNER_EGG))
			super.acceptEvent(event);
	}

	@Override
	public boolean onEvent(CreatureSpawnEvent event)
	{
		return handler.OnSpawnEggUsed(
			ObjectWrapper.convert(event.getEntity()),
			ObjectWrapper.convert(event.getLocation())
		);
	}

	public static EventRouterFactory Factory()
	{
		return new EventRouterFactory()
		{
			@Override
			public Class<? extends IRunsafeEvent> getInterface()
			{
				return ISpawnEggUsed.class;
			}

			@Override
			public Listener getListener(IOutput output, IScheduler scheduler, IRunsafeEvent subscriber)
			{
				return new SpawnEggUsed(output, scheduler, (ISpawnEggUsed) subscriber);
			}
		};
	}
}
