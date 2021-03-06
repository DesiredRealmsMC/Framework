package no.runsafe.framework.minecraft.event.player;

import no.runsafe.framework.api.IKernel;
import no.runsafe.framework.RunsafePlugin;
import no.runsafe.framework.api.event.player.IPlayerJoinEvent;
import no.runsafe.framework.api.event.IFakeableEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class RunsafePlayerJoinEvent extends RunsafePlayerEvent implements IFakeableEvent
{
	public RunsafePlayerJoinEvent(PlayerJoinEvent toWrap)
	{
		super(toWrap);
		event = toWrap;
	}

	public String getJoinMessage()
	{
		return event.getJoinMessage();
	}

	public void setJoinMessage(String message)
	{
		event.setJoinMessage(message);
	}

	public void Fire()
	{
		isFake = true;
		for (IKernel plugin : RunsafePlugin.Instances.values())
			for (IPlayerJoinEvent listener : plugin.getComponents(IPlayerJoinEvent.class))
				listener.OnPlayerJoinEvent(this);
	}

	@Override
	public boolean isFake()
	{
		return isFake;
	}

	private final PlayerJoinEvent event;
	private boolean isFake;
}
