package net.wdlvn.cti;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;


public class Listen implements Listener{
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		ItemStack is1 = e.getCursor();
		ItemStack is2 = e.getCurrentItem();
		int slotcu = e.getSlot();
		Player p = (Player) e.getWhoClicked();
		if ((is1.getType() != null) && (is2.getType() != null)) {
			if (is2.hasItemMeta()) {
				if (is2.getItemMeta().hasDisplayName()) {
					if (is2.getItemMeta().getDisplayName().equals(plugin.color(plugin.getConfig().getString("Gem.DisplayName")))) {
						return;
					}
				}
			}
			if (e.getClick() == ClickType.LEFT) {
				if (p.getGameMode() == GameMode.SURVIVAL || p.getGameMode() == GameMode.ADVENTURE) {
					if (is1.hasItemMeta()) {
						if (is1.getItemMeta().hasLore()) {
							if (is1.getItemMeta().hasDisplayName()) {
								if (p.hasPermission("cti.use")) {
									if (is1.getItemMeta().getDisplayName().equals(plugin.color(plugin.getConfig().getString("Gem.DisplayName")))) {
										if (is1.getType() == Material.valueOf(plugin.getConfig().getString("Gem.Material"))) {
											
											String gr = null;
											
											
											String cmd = null;
											for (String s:is1.getItemMeta().getLore()) {
												if (s.contains("/")) {
													cmd = s.replace("/", "");
												}
											}
											boolean ikrank = false;
											for (String l:is1.getItemMeta().getLore()) {
												String st = plugin.getConfig().getString("GroupLore");
												int start = 0;
												int end = st.length();
												String sub = l.substring(start,end);
												if (plugin.color(sub).equals(plugin.color(st))) {
													ikrank = true;	
													String g = l.substring(end,l.length());
													gr = g;
	
														
												}
												else ikrank = false;
													
											}
											if (ikrank) {
												if (isThat(gr,is2)) {
													
												p.sendMessage(gr);
													if (p.getItemOnCursor().getAmount() >1) {
														p.getItemOnCursor().setAmount(p.getItemOnCursor().getAmount()-1);
													}else {
													p.setItemOnCursor(null);
													}
													//p.closeInventory();
													
													int slot = p.getInventory().getHeldItemSlot();
													ItemStack is3 = p.getInventory().getItem(slot);
													p.getInventory().setItem(slot, is2);
													if (p.isOp()) {
														Bukkit.getServer().dispatchCommand(p, cmd);
													}
													else {
														p.setOp(true);
														Bukkit.getServer().dispatchCommand(p, cmd);
														p.setOp(false);
													}
													
													is2 = p.getInventory().getItem(slot);
													p.getInventory().setItem(slot, is3);
				
													p.getInventory().setItem(slotcu, is2);
												
												}
											}
											else {
	
												if (p.getItemOnCursor().getAmount() >1) {
													p.getItemOnCursor().setAmount(p.getItemOnCursor().getAmount()-1);
												}else {
												p.setItemOnCursor(null);
												}
												//p.closeInventory();
												
												int slot = p.getInventory().getHeldItemSlot();
												ItemStack is3 = p.getInventory().getItem(slot);
												p.getInventory().setItem(slot, is2);
												if (p.isOp()) {
													Bukkit.getServer().dispatchCommand(p, cmd);
												}
												else {
													p.setOp(true);
													Bukkit.getServer().dispatchCommand(p, cmd);
													p.setOp(false);
												}
												
												is2 = p.getInventory().getItem(slot);
												p.getInventory().setItem(slot, is3);
			
												p.getInventory().setItem(slotcu, is2);
											
											
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	public boolean isThat(String gr,ItemStack is2) {

					if (is2.hasItemMeta()) {
						if (is2.getItemMeta().hasLore()) {
							if (is2.getItemMeta().getLore().contains(plugin.color(gr))) {
								return true;
							}
							else return false;
						}
						else return false;
					}
					else return false;
				
			
	}
	private static Main plugin;
    public Listen(Main plugin){
        this.plugin = plugin;
    }
}
