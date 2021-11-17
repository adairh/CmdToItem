package net.wdlvn.cti;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class Commands implements CommandExecutor{
	private Main plugin;
    public Commands(Main plugin){
        this.plugin = plugin;
    }
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FileConfiguration f = plugin.getConfig();
		if (cmd.getName().equals("cti"))
	    {
	      if ((sender instanceof Player))
	      {
	        Player p = (Player)sender;
	        if(args[0].equals("get")) {
	        	if (sender.hasPermission("cti.get"))
		          {
		        	ItemStack is = new ItemStack(Material.valueOf(plugin.getConfig().getString("Gem.Material")));
		        	ItemMeta im = is.getItemMeta();
		        	im.setDisplayName(plugin.color(plugin.getConfig().getString("Gem.DisplayName")));
		        	List<String> lore = new ArrayList<String>();
		        	for (String s:plugin.getConfig().getStringList("Gem.Lore")) {
		        		if (s.equals("%cmd%")) {
		        			lore.add(plugin.color("/"+args[1].replace("-", " ")));
		        			
		        		}
		        		else if (s.equals("%group%")) {
		        			if (args.length == 3) {
		        				lore.add(plugin.color(plugin.getConfig().getString("GroupLore")+args[2]));
		        			}
		        		}else {
		        			lore.add(plugin.color(s));
		        		}
		        		
		        	}
		        	im.setLore(lore);
		        	is.setItemMeta(im);
		        	p.getInventory().addItem(is);
		          }
	        }	   
	        else if (args[0].equalsIgnoreCase("Reload"))
	        {
		          if (sender.hasPermission("cti.reload"))
		          {
		            plugin.reloadConfig();
		            plugin.saveConfig();
		            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aReloaded"));
		          }
		          else
		          {
		            sender.sendMessage(
		              ChatColor.translateAlternateColorCodes('&', "&aYou don't"
		              		+ " have enough permissions to use this command"));
		          }
		        }    
	      }
	    }
		return false;
	}
	public static boolean isInt(String s) {
	    try {
	        Integer.parseInt(s);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
