package com.K3R3P0.ButtonsPlus.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.K3R3P0.ButtonsPlus.ButtonsPlus;
import com.K3R3P0.ButtonsPlus.Handlers.IOHandler;
import com.K3R3P0.ButtonsPlus.Utils.Utils;

public class BlockListener implements Listener{
	ButtonsPlus plugin;
	
	public BlockListener(ButtonsPlus plugina) {
		plugin = plugina;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		IOHandler io = new IOHandler();
		Block block = event.getBlock();
		Player player = event.getPlayer();
		if(io.buttonExists(block)) {
			if((io.loadButton(block.getLocation()).getOwner().equals(player.getName()) || player.hasPermission("buttonplus.break"))  && player.isSneaking()) {
				event.getPlayer().sendMessage(ChatColor.RED + "Deleted button that belonged to: " + io.loadButton(block.getLocation()).getOwner());
				io.deleteButton(Utils.convertLoc(block.getLocation()), block.getWorld());
			} else {
				event.getPlayer().sendMessage(ChatColor.RED + "You can't break this button!");
				event.setCancelled(true);
			}
		}
	}
}
