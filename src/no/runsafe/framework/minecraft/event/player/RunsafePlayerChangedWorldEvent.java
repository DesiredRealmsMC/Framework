package no.runsafe.framework.minecraft.event.player;

import no.runsafe.framework.internal.wrapper.ObjectWrapper;
import no.runsafe.framework.minecraft.RunsafeWorld;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class RunsafePlayerChangedWorldEvent extends RunsafePlayerEvent
{
	public RunsafePlayerChangedWorldEvent(PlayerChangedWorldEvent toWrap)
	{
		super(toWrap);
		event = toWrap;
	}

	public RunsafeWorld getSourceWorld()
	{
		return ObjectWrapper.convert(event.getFrom());
	}

	private final PlayerChangedWorldEvent event;
}
