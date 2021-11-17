package net.wdlvn.cti;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	public String color(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new Listen(this), this);
		getConfig().options().copyDefaults(true);
	     getCommand("cti").setExecutor(new Commands(this));
		saveConfig();
	}
}
