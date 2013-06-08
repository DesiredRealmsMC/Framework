package no.runsafe.framework.wrapper.entity;

import no.runsafe.framework.server.entity.RunsafeHanging;
import no.runsafe.framework.server.item.RunsafeItemStack;
import no.runsafe.framework.wrapper.ObjectWrapper;
import org.bukkit.entity.ItemFrame;

public class BukkitItemFrame extends RunsafeHanging
{
	public BukkitItemFrame(ItemFrame toWrap)
	{
		super(toWrap);
		itemFrame = toWrap;
	}

	public RunsafeItemStack getItem()
	{
		return ObjectWrapper.convert(itemFrame.getItem());
	}

	public void setItem(RunsafeItemStack item)
	{
		itemFrame.setItem(item.getRaw());
	}

	protected final ItemFrame itemFrame;
}