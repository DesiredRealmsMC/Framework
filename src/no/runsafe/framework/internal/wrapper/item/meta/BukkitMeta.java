package no.runsafe.framework.internal.wrapper.item.meta;

import no.runsafe.framework.minecraft.enchantment.RunsafeEnchantment;
import no.runsafe.framework.minecraft.item.RunsafeItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public abstract class BukkitMeta extends RunsafeItemStack
{
	protected BukkitMeta(ItemStack stack)
	{
		super(stack);
	}

	public ItemMeta getRawMeta()
	{
		return itemStack.getItemMeta();
	}

	public boolean hasEnchants()
	{
		ItemMeta meta = getRawMeta();
		return meta != null && meta.hasEnchants();
	}

	public boolean hasEnchant(RunsafeEnchantment ench)
	{
		ItemMeta meta = getRawMeta();
		return meta != null && meta.hasEnchant(ench.getRaw());
	}

	public int getEnchantLevel(RunsafeEnchantment ench)
	{
		ItemMeta meta = getRawMeta();
		return meta == null ? -1 : meta.getEnchantLevel(ench.getRaw());
	}

	public boolean addEnchant(RunsafeEnchantment ench, int level, boolean ignoreLevelRestriction)
	{
		ItemMeta meta = getRawMeta();
		boolean success = false;
		if (meta != null)
		{
			success = meta.addEnchant(ench.getRaw(), level, ignoreLevelRestriction);
			if (success)
				itemStack.setItemMeta(meta);
		}
		return success;
	}

	public boolean removeEnchant(RunsafeEnchantment ench)
	{
		ItemMeta meta = getRawMeta();
		boolean success = false;
		if (meta != null)
		{
			success = meta.removeEnchant(ench.getRaw());
			if (success)
				itemStack.setItemMeta(meta);
		}
		return success;
	}

	public boolean hasDisplayName()
	{
		ItemMeta meta = getRawMeta();
		return meta != null && meta.hasDisplayName();
	}

	public String getDisplayName()
	{
		ItemMeta meta = getRawMeta();
		return meta == null ? null : meta.getDisplayName();
	}

	public BukkitMeta setDisplayName(String name)
	{
		ItemMeta meta = getRawMeta();
		if (meta != null)
		{
			meta.setDisplayName(name);
			itemStack.setItemMeta(meta);
		}
		return this;
	}

	public boolean hasLore()
	{
		ItemMeta meta = getRawMeta();
		return meta != null && meta.hasLore();
	}

	public List<String> getLore()
	{
		ItemMeta meta = getRawMeta();
		return meta == null ? null : meta.getLore();
	}

	public BukkitMeta addLore(String lore)
	{
		ItemMeta meta = getRawMeta();
		if (meta != null)
		{
			List<String> currentLore = meta.getLore();
			if (currentLore == null)
				currentLore = new ArrayList<String>();
			currentLore.add(lore);
			meta.setLore(currentLore);
			itemStack.setItemMeta(meta);
		}
		return this;
	}

	public BukkitMeta setLore(List<String> lore)
	{
		ItemMeta meta = getRawMeta();
		if (meta != null)
		{
			meta.setLore(lore);
			itemStack.setItemMeta(meta);
		}
		return this;
	}
}
