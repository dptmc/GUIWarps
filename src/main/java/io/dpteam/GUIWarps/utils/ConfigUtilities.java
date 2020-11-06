package io.dpteam.GUIWarps.utils;

import com.earth2me.essentials.commands.WarpNotFoundException;
import me.hunterplay.Main;
import net.ess3.api.InvalidWorldException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ConfigUtilities {
	static FileConfiguration config;

	static {
		config = Main.instance.getConfig();
	}

	public ConfigUtilities() {
		super();
	}

	private static String getPrefix() {
		return config.getString("prefix").replace('&', '§');
	}

	private static String getString(String path) {
		return config.getString(path).replace('&', '§');
	}

	public static void sendNoPerm(Player p) {
		p.sendMessage(getPrefix() + getString("no-perm"));
	}

	public static void sendNoWarps(Player p) {
		p.sendMessage(getPrefix() + getString("no-warps"));
	}

	public static void sendWarpTp(Player p, String warp) {
		String[] split = warp.split(" ");
		p.sendMessage(getPrefix() + getString("warp-tp").replace("%warp%", split[1]));

		try {
			p.teleport(Main.ess.getWarps().getWarp(split[1]));
		} catch (WarpNotFoundException var4) {
			var4.printStackTrace();
		} catch (InvalidWorldException var5) {
			var5.printStackTrace();
		}

	}
}
