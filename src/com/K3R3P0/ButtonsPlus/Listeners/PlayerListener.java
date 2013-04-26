package com.K3R3P0.ButtonsPlus.Listeners;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import com.K3R3P0.ButtonsPlus.ButtonsPlus;
import com.K3R3P0.ButtonsPlus.Button.Button;
import com.K3R3P0.ButtonsPlus.Handlers.ButtonActionHandler;
import com.K3R3P0.ButtonsPlus.Handlers.ButtonCreationHandler;
import com.K3R3P0.ButtonsPlus.Handlers.IOHandler;
import com.K3R3P0.ButtonsPlus.Settings.Settings;
import com.K3R3P0.ButtonsPlus.Utils.Utils;

public class PlayerListener implements Listener{
	IOHandler io = new IOHandler();
	ButtonsPlus plugin;
	Logger logger = Logger.getLogger("Minecraft");
	ButtonCreationHandler bch;
	Utils utils = new Utils();
	
	public PlayerListener(ButtonsPlus plugina) {
		plugin = plugina;
		bch = new ButtonCreationHandler(plugin);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(!Utils.cooldown.containsKey(player.getName())) {
			Utils.cooldown.put(player.getName(), 0);
		}
		Utils.modes.put(player.getName(), "none");
		Utils.confirmed.put(player.getName(), false);
		io.loadMoney();
		if(Utils.playerOwed.containsKey(player.getName())) {
			if(Settings.econmode.equalsIgnoreCase("money")) {
				ButtonsPlus.econ.depositPlayer(player.getName(), Utils.playerOwed.get(player.getName()));
				player.sendMessage(ChatColor.GOLD + "[BP] You were given $" + Utils.playerOwed.get(player.getName()) + " from button pushes");
			}
			if(Settings.econmode.equalsIgnoreCase("xp")) {
				player.setLevel(player.getLevel() + Utils.playerOwed.get(player.getName()));
				player.sendMessage(ChatColor.GOLD + "[BP] You were given " + Utils.playerOwed.get(player.getName()) + " Experience levels from button pushes");
			}
			if(Settings.econmode.equalsIgnoreCase("item")) {
				ItemStack give = new ItemStack(Settings.itemid, Utils.playerOwed.get(player.getName()));
				player.getInventory().addItem(give);
				player.sendMessage(ChatColor.GOLD + "[BP] You were given " + Utils.playerOwed.get(player.getName()) + " " + give.getType().toString() + " from button pushes");
			}
			Utils.playerOwed.remove(player.getName());
			io.saveMoney();
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		String player = event.getPlayer().getName();
		Utils.buttonCost.remove(player);
		Utils.modes.remove(player);
		Utils.confirmed.remove(player);
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		//if the player is editing then eat up their chat
		if(!Utils.modes.get(event.getPlayer().getName()).equalsIgnoreCase("none")) {
			bch.handleChat(event.getPlayer(), event.getMessage());
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onEntityInteract(EntityInteractEvent event) {
		Entity ent = event.getEntity();
		Block block = event.getBlock();
		if(ent instanceof Arrow) {
			Arrow arrow = (Arrow)ent;
			if(block.getType() == Material.WOOD_BUTTON) {
				if(arrow.getShooter() instanceof Player) {
					Player player = (Player)arrow.getShooter();
					if(player.hasPermission("buttonsplus.WOOD_BUTTON.push")) {
						if(io.buttonExists(block)) {
							Button button = io.loadButton(block.getLocation());
							ButtonActionHandler bah = new ButtonActionHandler(plugin);
							//This string is the return for the handler and tests to see if everything went smoothly
							//If the player was not able to activate the block, then we have to cancel the event so that the redstone is not activated
							String test = bah.doActions(block, player);
							//Case = "true"
							if(test == "true") {
								//If true, then add a push to the button :D
								button.addPush();
								//Add the player to the list of rewarded players
								if(!button.getrewardedPlayers().contains(player.getName())) {
									button.addPlayer(player.getName());
								}
								//Save the button
								io.saveButton(button);
								return;
							  //If false then cancel event
							} else if(test == "false"){
								event.setCancelled(true);
								return;
							} else if(test == "reward") {
								event.setCancelled(true);
								return;
							}
						}
					} else {
						event.setCancelled(true);
						return;
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		//If there is no block there... then do nothing.
		if(event.getClickedBlock() == null) {
			return;
		}
		//The player who triggered the event
		Player player = event.getPlayer();
		//The block that was interacted with
		Block block = event.getClickedBlock();
		//Name of the player
		String playername = player.getName();
		//Return if this block isnt on the type list
		if(!utils.listtolist(Utils.buttontypes).contains(block.getType())) {
			return;
		}
		
		//If the button exists then...
		if(io.buttonExists(block)) {
			Button button = io.loadButton(block.getLocation());
			//If the player is editing another button
			if(!Utils.modes.get(playername).equalsIgnoreCase("none")) {
				player.sendMessage(ChatColor.RED + "Your creating another button!");
				event.setCancelled(true);
				return;
			}
			//If the player has permission to see info and is sneaking, show info
			if((button.getOwner().equals(playername) || player.hasPermission("buttonsplus.info")) && player.isSneaking() && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				player.sendMessage(ChatColor.BLUE + "=======================.-=Info=-.=====================");
				player.sendMessage(ChatColor.GOLD + "Owner: " + button.getOwner());
				player.sendMessage(ChatColor.GOLD + "Total Pushes: " + button.getPushes());
				//if the button is a charge button then show how much it will cost to push
				if(button.getActionName(0).equalsIgnoreCase("charge")) {
					String cost = Settings.econmode;
					if(cost.equalsIgnoreCase("item")) {
						cost = Material.getMaterial(Settings.itemid).toString() + "s";
					}
					player.sendMessage(ChatColor.GOLD + "This will cost: " + button.getActionArgs(0)[0] + " " + cost);
				} else {
					player.sendMessage(ChatColor.GOLD + "This button will not charge.");
				}
				//Send the player a list of the actions this button will perform
				player.sendMessage(ChatColor.GOLD + "Actions: " + button.getButtonActionsFormatted());
				player.sendMessage(ChatColor.BLUE + "=======================.-=End=-.======================");
				//Don't activate the button
				event.setCancelled(true);
				return;
			//If the player does not have INFO but has costinfo permission, then the infoshort is displayed
			} else if(player.hasPermission("buttonsplus.costinfo") && player.isSneaking() && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				player.sendMessage(ChatColor.BLUE + "====================.-=InfoShort=-.===================");
				player.sendMessage(ChatColor.GOLD + "Owner: " + button.getOwner());
				//Show the cost of the button
				String cost = Settings.econmode;
				if(cost.equalsIgnoreCase("item")) {
					cost = Material.getMaterial(Settings.itemid).toString() + "s";
				}
				if(button.getActionName(0).equalsIgnoreCase("charge")) {
					player.sendMessage(ChatColor.GOLD + "This will cost: " + button.getActionArgs(0)[0] + " " + cost);
				} else {
					player.sendMessage(ChatColor.GOLD + "This button will not charge.");
				}
				player.sendMessage(ChatColor.BLUE + "=======================.-=End=-.======================");
				//Don't activate the button.
				event.setCancelled(true);
				return;
			}
			//If the player has permission to interact with that type of block
			if(utils.getAllowed(player, Utils.buttontypes, ".push").contains(block.getType())) {
				//If the player does a regular non-crouch action to the block and activates it successfully
				if(((event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK) && block.getType().equals(Material.STONE_BUTTON) && !player.isSneaking())
						|| ((event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK) && block.getType() == Material.WOOD_BUTTON && !player.isSneaking())
						|| (event.getAction() == Action.PHYSICAL && block.getType() == Material.WOOD_PLATE)
						|| ((event.getAction() == Action.LEFT_CLICK_BLOCK || (event.getAction() == Action.RIGHT_CLICK_BLOCK && !player.isSneaking())) && block.getType() == Material.LEVER)
						|| (event.getAction() == Action.PHYSICAL && block.getType() == Material.STONE_PLATE)
						|| (block.getType().equals(Material.STONE_BUTTON) && event.getAction() == Action.RIGHT_CLICK_BLOCK && !player.isSneaking())) {
					if(block.getType() == Material.LEVER) {
						if((block.getData() & 0x8) > 0) {
							return;
						}
					}
					//The action handler for this event is created, if we get this far that is
					ButtonActionHandler bah = new ButtonActionHandler(plugin);
					//This string is the return for the handler and tests to see if everything went smoothly
					//If the player was not able to activate the block, then we have to cancel the event so that the redstone is not activated
					String test = bah.doActions(block, player);
					//Case = "true"
					if(test == "true") {
						//If true, then add a push to the button :D
						button.addPush();
						//If the button is a reward button then add the player to the list so they cannot press it again
						if(!button.getrewardedPlayers().contains(playername)) {
							button.addPlayer(playername);
						}
						//Save the button
						io.saveButton(button);
						return;
					  //If false then cancel event
					} else if(test == "false"){
						event.setCancelled(true);
						return;
					} else if(test == "reward") {
						event.setCancelled(true);
						return;
					}
				} else {
					return;
				}
			//If a player does not have perms for the block type, then deny them the redstone going off
			} else {
				//Send the player a message
				player.sendMessage(ChatColor.RED + "You do not have permission to press this " + block.getType().toString());
				//Cancel the event
				event.setCancelled(true);
				return;
			}
		}
		//If the player is sneaking and performs the creation action then
		if((block.getType() == Material.STONE_BUTTON
				|| block.getType() == Material.WOOD_BUTTON
				|| block.getType() == Material.WOOD_PLATE
				|| block.getType() == Material.STONE_PLATE
				|| block.getType() == Material.LEVER)
				&& (event.getAction() == Action.RIGHT_CLICK_BLOCK && player.isSneaking())) {
			//If the button exists already... 
			if(io.buttonExists(block)) {
			//If the button does not exist and the player is editing a button then
			}else if(!Utils.modes.get(playername).equalsIgnoreCase("none")) {
				player.sendMessage(ChatColor.RED + "Your creating another button!");
			//If the button can be created then
			} else if(utils.getAllowed(player, Utils.buttontypes, ".create").contains(block.getType())) {
				//Set the players mode to start the creation and stop the players chat messages going through
				Utils.modes.put(playername, "createStart");
				//Get the players location and store it for later use
				Utils.tempLoc.put(playername, block.getLocation());
				//Send the create message
				player.sendMessage(ChatColor.BLUE + "=====================.-=Create=-.=====================");
				player.sendMessage(ChatColor.GOLD + "You are now Setting up a ButtonsPlus button!");
				player.sendMessage(ChatColor.GOLD + "You will not be able to chat to other players during creation!");
				player.sendMessage(ChatColor.GOLD + "Type cancel at any time during setup to cancel setup");
				//Set the cost for the creation to 0
				Utils.buttonCost.put(playername, 0);
				player.sendMessage(ChatColor.BLUE + "----------------------------------------------------");
				//Show the player what modes they can use
				if(!utils.getAllowed(player, Utils.buttonmodes, ".create").isEmpty()) {
					player.sendMessage(ChatColor.GOLD + "What type of button do you want this to be? Type in one of the modes from this list:");
					player.sendMessage(ChatColor.BLUE + "======================.-=Modes=-.=====================");
					player.sendMessage(ChatColor.GOLD + utils.formatList(utils.getAllowed(player, Utils.buttonmodes, ".create")));
					player.sendMessage(ChatColor.BLUE + "---------------------------------------------------");
				//If they have no permissions for it, then tell them
				} else if(utils.getAllowed(player, Utils.actionlist, ".create").isEmpty()) {
					//lets just make sure their mode is none >:P
					Utils.modes.put(playername, "none");
				}
			}
		}
	}
}
