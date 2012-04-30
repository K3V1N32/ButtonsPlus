package com.K3V1N32.ButtonsPlus;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class ButtonBListener implements Listener{
	ButtonConfig bConfig;
	ButtonsPlus plugin;
	
	public ButtonBListener(ButtonsPlus instance) {
		plugin = instance;
	}
	
	
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		bConfig = new ButtonConfig(plugin);
		Block block = event.getBlock();
		Player player = event.getPlayer();
		if(bConfig.buttonExists(block)) {
			if(bConfig.loadButton(block.getLocation()).getOwner().equals(player.getName()) || ButtonsPlus.perms.has(player, "buttonplus.break")) {
				event.getPlayer().sendMessage(ChatColor.RED + "Deleted button that belonged to: " + bConfig.loadButton(block.getLocation()).getOwner());
				bConfig.deleteButton(bConfig.saveLocation(block.getLocation()), block.getWorld());
			} else {
				event.getPlayer().sendMessage(ChatColor.RED + "This button is not owned by you! You can't break it!");
				event.setCancelled(true);
			}
		}
	}
}
