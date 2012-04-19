package com.K3V1N32.ButtonsPlus;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
//Perms:
//Viewing button info: buttonsplus.info
//Creating buttons: buttonsplus.create
//Manual override: buttonsplus.break
//Super: buttonsplus.admin
/*Button Types:
 * -Basic = Set price for button push, costs money to create, set in config .-=buttonsplus.basic=-.
 * -Tutorial = dummy button for tutorial purposes .-=buttonsplus.tutorial=-. 
 * -Text = displays text on press .-=buttonsplus.text=-.
 * -Death = kills player when they push button .-=buttonsplus.death=-.
 * -Command = Sets a command to run when button pressed .-=buttonsplus.command=-.
 * -Teleport = Teleports a player to a specified spot .-=buttonsplus.teleport=-.
 * -MobSpawning = Spawns a mob on press .-=buttonsplus.mob=-. 
 * -Global Message = sends a global message .-=buttonsplus.global=-.
 * -Heal = heals player on press .-=buttonsplus.heal=-.
 * -Reward = gives a player money ONCE when they press button. .-=buttonsplus.reward=-.
 * -Burn = burns a player on press .-=buttonsplus.burn=-.
 */
public class ButtonPListener extends PlayerListener{
	ButtonsPlus plugin;
	ButtonConfig bConfig;
	Logger logger = Logger.getLogger("Minecraft");

	public ButtonPListener(ButtonsPlus instance) {
		plugin = instance;
	}

	public void onPlayerJoin(PlayerJoinEvent event) {
		Calendar calendar = new GregorianCalendar();
		int thisTime = (int)calendar.getTimeInMillis() + 10000;
		ButtonsPlus.modes.put(event.getPlayer().getName(), "none");
		ButtonsPlus.cooldown.put(event.getPlayer().getName(), thisTime);
	}

	public void onPlayerChat(PlayerChatEvent event) {
		if(!ButtonsPlus.modes.get(event.getPlayer().getName()).equalsIgnoreCase("none")) {
			ButtonCreationHandler bch = new ButtonCreationHandler(plugin);
			bch.handleChat(event.getPlayer(), event.getMessage());
			event.setCancelled(true);
		}
	}
	
	public String getButtonActionsFormatted(Button button) {
		int size = button.getActionAmount();
		String finale = "";
		for(int i = 0; i < size; i++) {
			finale = finale + button.getActionName(i) + ", ";
		}
		return finale;
	}

	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getClickedBlock() == null) {
			return;
		}
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		String playername = player.getName();
		bConfig = new ButtonConfig(plugin);
		if(block.getType().equals(Material.STONE_BUTTON) && bConfig.buttonExists(block)) {
			Button button = bConfig.loadButton(block.getLocation());
			if(!ButtonsPlus.modes.get(playername).equalsIgnoreCase("none")) {
				player.sendMessage(ChatColor.RED + "Your creating another button!");
				event.setCancelled(true);
				return;
			}
			if(button.getOwner().equalsIgnoreCase(playername) && event.getAction() == Action.LEFT_CLICK_BLOCK) {
				player.sendMessage(ChatColor.DARK_GREEN + "You own this button!");
			}
			if(event.getAction() == Action.LEFT_CLICK_BLOCK || (event.getAction() == Action.RIGHT_CLICK_BLOCK && !player.isSneaking())) {
				ButtonActionHandler bah = new ButtonActionHandler(plugin);
				if(bah.doActions(block, player)) {
					button.addPush();
					bConfig.saveButton(button);
				} else {
					event.setCancelled(true);
				}
			} else {
				event.setCancelled(true);
			}
		}
		if(block.getType().equals(Material.STONE_BUTTON) && event.getAction() == Action.RIGHT_CLICK_BLOCK && player.isSneaking()) {
			if(bConfig.buttonExists(block)) {
				Button button = bConfig.loadButton(block.getLocation());
				if(button.getOwner().equals(playername) || ButtonPermissionHandler.permission(player, "buttonsplus.info", player.isOp())) {
					player.sendMessage(ChatColor.BLUE + "=======================.-=Info=-.=====================");
					player.sendMessage(ChatColor.GOLD + "Owner: " + button.getOwner());
					player.sendMessage(ChatColor.GOLD + "Total Pushes: " + button.getPushes());
					player.sendMessage(ChatColor.GOLD + "Cost per push: " + button.getActionArgs(0)[0]);
					player.sendMessage(ChatColor.GOLD + "Actions: " + getButtonActionsFormatted(button));
					player.sendMessage(ChatColor.BLUE + "=======================.-=End=-.======================");
				}
			}else if(!ButtonsPlus.modes.get(playername).equalsIgnoreCase("none")) {
				player.sendMessage(ChatColor.RED + "Your creating another button!");
			} else {
				ButtonsPlus.modes.put(playername, "createStart");
				ButtonsPlus.tempLoc.put(playername, block.getLocation());
				player.sendMessage(ChatColor.BLUE + "===========================.-=Create=-.===================");
				player.sendMessage(ChatColor.GOLD + "You are now Setting up a ButtonPlus button!");
				player.sendMessage(ChatColor.GOLD + "You will not be able to chat to other players during creation!");
				player.sendMessage(ChatColor.BLUE + "----------------------------------------------------------");
				player.sendMessage(ChatColor.GOLD + "Would you like this button to charge money for each press?");
				player.sendMessage(ChatColor.GOLD + "(Warning, This will cost 2x the amount it charges.");
				player.sendMessage(ChatColor.GOLD + "E.g. $10 per press = $20 total price for button.)");
				player.sendMessage(ChatColor.GOLD + "Type Yes or No now");
			}
		}
	}
}
