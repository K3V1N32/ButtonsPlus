package com.K3R3P0.ButtonsPlus.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.material.Button;
import org.bukkit.material.Lever;

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
		//Getting the blocks all around the current block to check for buttons.
		Location locx1 = new Location(block.getWorld(), block.getX() + 1D, block.getY(), block.getZ());
		Location locx2 = new Location(block.getWorld(), block.getX() - 1D, block.getY(), block.getZ());
		Location locz1 = new Location(block.getWorld(), block.getX(), block.getY(), block.getZ() + 1D);
		Location locz2 = new Location(block.getWorld(), block.getX(), block.getY(), block.getZ() - 1D);
		Location locy1 = new Location(block.getWorld(), block.getX(), block.getY() + 1D, block.getZ());
		Block blockx1 = block.getWorld().getBlockAt(locx1);
		Block blockx2 = block.getWorld().getBlockAt(locx2);
		Block blockz1 = block.getWorld().getBlockAt(locz1);
		Block blockz2 = block.getWorld().getBlockAt(locz2);
		Block blocky1 = block.getWorld().getBlockAt(locy1);
		if(io.buttonExists(block)) {
			if((io.loadButton(block.getLocation()).getOwner().equals(player.getName()) || player.hasPermission("buttonplus.break"))  && player.isSneaking()) {
				event.getPlayer().sendMessage(ChatColor.RED + "Deleted button that belonged to: " + io.loadButton(block.getLocation()).getOwner());
				io.deleteButton(Utils.convertLoc(block.getLocation()), block.getWorld());
			} else {
				event.getPlayer().sendMessage(ChatColor.RED + "You can't break this button!");
				event.setCancelled(true);
			}
		} else
		if(io.buttonExists(blockx1)) {
			if(blockx1.getType().equals(Material.STONE_BUTTON) || blockx1.getType().equals(Material.WOOD_BUTTON)) {
				Button button = (Button) blockx1.getState().getData();
				if(block.equals(blockx1.getRelative(button.getAttachedFace()))) {
					player.sendMessage(ChatColor.RED + "That block has a buttonsplus button on it, if you wish to remove this block, break the button attached to it.");
					event.setCancelled(true);
				}
			} else if(blockx1.getType().equals(Material.LEVER)) {
				Lever button = (Lever) blockx1.getState().getData();
				if(block.equals(blockx1.getRelative(button.getAttachedFace()))) {
					player.sendMessage(ChatColor.RED + "That block has a buttonsplus lever on it, if you wish to remove this block, break the lever attached to it.");
					event.setCancelled(true);
				}
			}
		}
		if(io.buttonExists(blockx2)) {
			if(blockx2.getType().equals(Material.STONE_BUTTON) || blockx2.getType().equals(Material.WOOD_BUTTON)) {
				Button button = (Button) blockx2.getState().getData();
				if(block.equals(blockx2.getRelative(button.getAttachedFace()))) {
					player.sendMessage(ChatColor.RED + "That block has a buttonsplus button on it, if you wish to remove this block, break the button attached to it.");
					event.setCancelled(true);
				}
			} else if(blockx2.getType().equals(Material.LEVER)) {
				Lever button = (Lever) blockx2.getState().getData();
				if(block.equals(blockx2.getRelative(button.getAttachedFace()))) {
					player.sendMessage(ChatColor.RED + "That block has a buttonsplus lever on it, if you wish to remove this block, break the lever attached to it.");
					event.setCancelled(true);
				}
			}
		}
		if(io.buttonExists(blocky1)) {
			player.sendMessage(ChatColor.RED + "That block has a buttonsplus plate on it, if you wish to remove this block, break the plate attached to it.");
			event.setCancelled(true);
		}
		if(io.buttonExists(blockz1)) {
			if(blockz1.getType().equals(Material.STONE_BUTTON) || blockz1.getType().equals(Material.WOOD_BUTTON)) {
				Button button = (Button) blockz1.getState().getData();
				if(block.equals(blockx1.getRelative(button.getAttachedFace()))) {
					player.sendMessage(ChatColor.RED + "That block has a buttonsplus button on it, if you wish to remove this block, break the button attached to it.");
					event.setCancelled(true);
				}
			} else if(blockz1.getType().equals(Material.LEVER)) {
				Lever button = (Lever) blockz1.getState().getData();
				if(block.equals(blockz1.getRelative(button.getAttachedFace()))) {
					player.sendMessage(ChatColor.RED + "That block has a buttonsplus lever on it, if you wish to remove this block, break the lever attached to it.");
					event.setCancelled(true);
				}
			}
		}
		if(io.buttonExists(blockz2)) {
			if(blockz2.getType().equals(Material.STONE_BUTTON) || blockz2.getType().equals(Material.WOOD_BUTTON)) {
				Button button = (Button) blockz2.getState().getData();
				if(block.equals(blockz2.getRelative(button.getAttachedFace()))) {
					player.sendMessage(ChatColor.RED + "That block has a buttonsplus button on it, if you wish to remove this block, break the button attached to it.");
					event.setCancelled(true);
				}
			} else if(blockz2.getType().equals(Material.LEVER)) {
				Lever button = (Lever) blockz2.getState().getData();
				if(block.equals(blockz2.getRelative(button.getAttachedFace()))) {
					player.sendMessage(ChatColor.RED + "That block has a buttonsplus lever on it, if you wish to remove this block, break the lever attached to it.");
					event.setCancelled(true);
				}
			}
		}
	}
}
