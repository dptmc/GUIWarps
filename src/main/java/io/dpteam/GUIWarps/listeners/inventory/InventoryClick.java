package io.dpteam.GUIWarps.inventory;

import java.util.HashMap;
import io.dpteam.Main;
import io.dpteam.inventory.PageInv;
import io.dpteam.utils.ConfigUtilities;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {
	public static HashMap warps = new HashMap();
	FileConfiguration config;

	public InventoryClick() {
		super();
		this.config = Main.instance.getConfig();
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		if (e.getInventory().getHolder() instanceof PageInv) {
			PageInv inv = (PageInv)e.getInventory().getHolder();
			e.setCancelled(true);
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.BARRIER) {
				e.getWhoClicked().openInventory(((PageInv)warps.get(inv.getPage() + 1)).getInventory());
				return;
			}

			if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.GRASS) {
				e.getWhoClicked().openInventory(((PageInv)warps.get(inv.getPage() - 1)).getInventory());
				return;
			}

			ConfigUtilities.sendWarpTp(p, e.getCurrentItem().getItemMeta().getDisplayName());
		}

	}
}
