package io.dpteam.GUIWarps;

import com.earth2me.essentials.Essentials;
import java.io.File;
import io.dpteam.GUIWarps.commands.CommandWarps;
import io.dpteam.GUIWarps.listeners.inventory.InvClick;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static Main instance;
	public static Essentials ess;
	FileConfiguration config = this.getConfig();
	File configFile = new File(this.getDataFolder(), "config.yml");

	public Main() {
		super();
	}

	public void onEnable() {
		instance = this;
		ess = (Essentials)this.getServer().getPluginManager().getPlugin("Essentials");
		this.setupConfig();
		this.registerListeners();
		this.registerCommands();
		System.out.println("GUIWarps Enabled");
	}

	@Override
	public void onDisable() {
		System.out.println("GUIWarps Disabled");
	}

	public void setupConfig() {
		if (this.configFile == null) {
			this.configFile = new File(this.getDataFolder(), "config.yml");
		}

		if (!this.configFile.exists()) {
			this.saveDefaultConfig();
		}

	}

	public void registerListeners() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new InvClick(), this);
	}

	public void registerCommands() {
		this.getCommand("warps").setExecutor(new CommandWarps());
	}
}
