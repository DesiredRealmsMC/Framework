package no.runsafe.framework.internal.wrapper.metadata;

import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class BukkitMetadata
{
	public BukkitMetadata(Metadatable toWrap)
	{
		meta = toWrap;
	}

	public void setMetadata(String s, BukkitMetadataValue metadataValue)
	{
		meta.setMetadata(s, metadataValue.getRaw());
	}

	public List<BukkitMetadataValue> getMetadata(String s)
	{
		return BukkitMetadataValue.convert(meta.getMetadata(s));
	}

	public boolean hasMetadata(String s)
	{
		return meta.hasMetadata(s);
	}

	public void removeMetadata(String s, Plugin plugin)
	{
		meta.removeMetadata(s, plugin);
	}

	public Metadatable getRaw()
	{
		return meta;
	}

	private final Metadatable meta;
}
