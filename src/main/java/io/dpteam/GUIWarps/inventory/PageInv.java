package io.dpteam.GUIWarps.inventory;

import io.dpteam.Main;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class PageInv implements InventoryHolder {
	private int page;
	private Inventory inv;

	public PageInv(int page) {
		super();
		this.inv = Bukkit.createInventory(this, 54, Main.instance.getConfig().getString("GUIWarps.Title").replace('&', '§'));
		this.page = page;
	}

	public Inventory getInventory() {
		return this.inv;
	}

	public int getPage() {
		return this.page;
	}
}
