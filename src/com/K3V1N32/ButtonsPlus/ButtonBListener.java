package com.K3V1N32.ButtonsPlus;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;

public class ButtonBListener extends BlockListener {
	ButtonConfig bConfig;
	ButtonsPlus plugin;
	
	public ButtonBListener(ButtonsPlus instance) {
		plugin = instance;
	}
	
	public void onBlockBreak(BlockBreakEvent event) {
		bConfig = new ButtonConfig(plugin);
		Block block = event.getBlock();
		Player player = event.getPlayer();
		if(bConfig.buttonExists(block)) {
			if(bConfig.loadButton(block.getLocation()).getOwner().equals(player.getName()) || ButtonPermissionHandler.permission(player, "buttonplus.break", player.isOp())) {
				event.getPlayer().sendMessage(ChatColor.RED + "Deleted button that belonged to: " + bConfig.loadButton(block.getLocation()).getOwner());
				bConfig.deleteButton(bConfig.saveLocation(block.getLocation()), block.getWorld());
			} else {
				event.getPlayer().sendMessage(ChatColor.RED + "This button is owned! You can't break it!");
				event.setCancelled(true);
			}
		}
	}
}
