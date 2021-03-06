package no.runsafe.framework.minecraft.event.player;

import no.runsafe.framework.api.IKernel;
import no.runsafe.framework.RunsafePlugin;
import no.runsafe.framework.api.event.player.IPlayerQuitEvent;
import no.runsafe.framework.api.event.IFakeableEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class RunsafePlayerQuitEvent extends RunsafePlayerEvent implements IFakeableEvent
{
	public RunsafePlayerQuitEvent(PlayerQuitEvent toWrap)
	{
		super(toWrap);
		event = toWrap;
	}

	public String getQuitMessage()
	{
		return event.getQuitMessage();
	}

	public void setQuitMessage(String message)
	{
		event.setQuitMessage(message);
	}

	public void Fire()
	{
		isFake = true;
		for (IKernel plugin : RunsafePlugin.Instances.values())
			for (IPlayerQuitEvent listener : plugin.getComponents(IPlayerQuitEvent.class))
				listener.OnPlayerQuit(this);
	}

	@Override
	public boolean isFake()
	{
		return isFake;
	}

	private final PlayerQuitEvent event;
	private boolean isFake = false;
}
