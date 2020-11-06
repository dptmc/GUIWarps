package io.dpteam.GUIWarps.commands;

import java.util.Iterator;
import io.dpteam.GUIWarps.Main;
import io.dpteam.GUIWarps.inventory.PageInv;
import io.dpteam.GUIWarps.utils.ConfigUtilities;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandWarps implements CommandExecutor {
	FileConfiguration config;

	public CommandWarps() {
		super();
		this.config = Main.instance.getConfig();
	}

	private ItemStack getItemStackWithName(String name, Material mat) {
		ItemStack it = new ItemStack(mat);
		ItemMeta meta = it.getItemMeta();
		meta.setDisplayName(name);
		it.setItemMeta(meta);
		return it;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		int page = 1;

		for(Iterator iter = Main.ess.getWarps().getList().iterator(); iter.hasNext(); ++page) {
			int maxItems = 54;
			int limit = 0;
			PageInv pageI = new PageInv(page);
			String warp = (String)iter.next();
			if (page == 1 && Main.ess.getWarps().getCount() > 54) {
				pageI.getInventory().setItem(53, this.getItemStackWithName(this.config.getString("nextPage"), Material.getMaterial(this.config.getInt("GUIWarps.nextID"))));
			} else if (page != 1 && Main.ess.getWarps().getCount() > 53) {
				pageI.getInventory().setItem(53, this.getItemStackWithName(this.config.getString("nextPage"), Material.getMaterial(this.config.getInt("GUIWarps.nextID"))));
				pageI.getInventory().setItem(45, this.getItemStackWithName(this.config.getString("prevPage"), Material.getMaterial(this.config.getInt("GUIWarps.prevID"))));
			} else if (page != 1 && Main.ess.getWarps().getCount() <= 53) {
				pageI.getInventory().setItem(45, this.getItemStackWithName(this.config.getString("prevPage"), Material.getMaterial(this.config.getInt("GUIWarps.nextID"))));
			}

			Iterator var12 = Main.ess.getWarps().getList().iterator();

			while(var12.hasNext()) {
				String warpName = (String)var12.next();
				if (limit != maxItems) {
					if (pageI.getInventory().getItem(limit) == null) {
						pageI.getInventory().setItem(limit, this.getItemStackWithName("§a>§c>§r " + warpName + " §c<§a<", Material.GLASS));
					}

					++limit;
					if (!this.isBadWarp(warp)) {
						Main.ess.getWarps().getList().remove(warp);
					}
				}
			}

			InvClick.warps.put(page, pageI);
		}

		if (!(sender instanceof Player)) {
			sender.sendMessage("This command can only be used by players!");
			return true;
		} else if (cmd.getName().equalsIgnoreCase("warps")) {
			Player p = (Player)sender;
			p.updateInventory();
			if (p.hasPermission("guiwarps.open")) {
				if (Main.ess.getWarps().getCount() > 0) {
					p.openInventory(((PageInv)InvClick.warps.get(1)).getInventory());
				} else {
					ConfigUtilities.sendNoWarps(p);
				}
			}

			return true;
		} else {
			return false;
		}
	}

	private boolean isBadWarp(String warp) {
		return false;
	}
}
