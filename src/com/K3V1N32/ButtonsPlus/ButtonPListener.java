package com.K3V1N32.ButtonsPlus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.Listener;
//Permissions:
//Viewing button info: buttonsplus.info
//Viewing button cost info: buttonsplus.costinfo
//Creating buttons: buttonsplus.create
//Buttons:
//	-buttonsplus.button
//	-buttonsplus.stoneplate
//  -buttonsplus.woodplate
//	-buttonsplus.lever
//Manual override: buttonsplus.break
//Super: buttonsplus.admin or buttonsplus.*
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
 * -Console = runs a command through the console .-=buttonsplus.console=-.
 * -Item = gives the player some items .-=buttonsplus.item=-.
 * -Cooldown = sets a time in which the player can press that button again .-=buttonsplus.cooldown=-.
 * -Effect = Gives the player the requested effect for a given amount of time .-=buttonsplus.effect=-.
 * -Sound = Plays a sound at the players location .-=buttonsplus.sound=-.
 * -Lightning = Strikes lightning at a players location .-=buttonsplus.lightning=-.
 */
public class ButtonPListener implements Listener{
	ButtonsPlus plugin;
	ButtonConfig bConfig;
	//INIT logger
	Logger logger = Logger.getLogger("Minecraft");
	
	/** Initilize this class **/
	public ButtonPListener(ButtonsPlus instance) {
		plugin = instance;
	}
	
	
	/** Formats a list so that it can be displayed to a user **/
	public String getFormatList(List<String> oldList) {
		int l = oldList.size();
		String ret = "";
		if(l == 0) {
			ret = "";
			return ret;
		}
		if(l > 1) {
			for(int i = 0;i < l;i++) {
				if(i > (l - 1)) {
					ret = ret + oldList.get(i) + ".";
				} else {
					ret = ret + oldList.get(i) + ", ";
				}
			}
		} else {
			ret = oldList.get(0);
		}
		return ret;
	}
	
	/** Get a list of actions a player has permissions for **/
	public String getPlayerActions(Player player) {
		List<String> perList = new ArrayList<String>();
		if(ButtonsPlus.perms.has(player, "buttonsplus.command.create")) {
			perList.add("command");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.tutorial.create")) {
			perList.add("tutorial");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.text.create")) {
			perList.add("text");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.death.create")) {
			perList.add("death");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.teleport.create")) {
			perList.add("teleport");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.mob.create")) {
			perList.add("mob");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.globalmessage.create")) {
			perList.add("gmessage");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.heal.create")) {
			perList.add("heal");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.burn.create")) {
			perList.add("burn");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.lightning.create")) {
			perList.add("lightning");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.console.create")) {
			perList.add("console");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.item.create")) {
			perList.add("item");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.effect.create")) {
			perList.add("effect");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.sound.create")) {
			perList.add("sound");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.cooldowna.create")) {
			perList.add("cooldown");
		}
		return getFormatList(perList);
	}
	
	/** Gets the types of blocks a player can interact with **/
	public List<Material> buttonTypes(Player p) {
		List<Material> typeList = new ArrayList<Material>();
		if(ButtonsPlus.perms.has(p, "buttonsplus.button.push")) {
			typeList.add(Material.STONE_BUTTON);
		}
		if(ButtonsPlus.perms.has(p, "buttonsplus.lever.push")) {
			typeList.add(Material.LEVER);
		}
		if(ButtonsPlus.perms.has(p, "buttonsplus.stoneplate.push")) {
			typeList.add(Material.STONE_PLATE);
		}
		if(ButtonsPlus.perms.has(p, "buttonsplus.woodplate.push")) {
			typeList.add(Material.WOOD_PLATE);
		}
		return typeList;
	}
	
	/** Outputs a list of button modes a player can create **/
	public List<String> fetchModes(Player p) {
		List<String> mList = new ArrayList<String>();
		mList.add("Basic");
		if(ButtonsPlus.perms.has(p, "buttonsplus.charge.create")) {
			mList.add("Charge");
		}
		if(ButtonsPlus.perms.has(p, "buttonsplus.rewardone.create")) {
			mList.add("RewardOne");
		}
		if(ButtonsPlus.perms.has(p, "buttonsplus.rewardall.create")) {
			mList.add("RewardAll");
		}
		return mList;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		//Set the time when a player joins so we have them in the cooldown event, with a time of 5 seconds from the time they join
		Calendar calendar = new GregorianCalendar();
		int thisTime = (int)calendar.getTimeInMillis() + 5000;
		ButtonsPlus.modes.put(event.getPlayer().getName(), "none");
		ButtonsPlus.cooldown.put(event.getPlayer().getName(), thisTime);
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		//if the player is editing then eat up their chat :P
		if(!ButtonsPlus.modes.get(event.getPlayer().getName()).equalsIgnoreCase("none")) {
			//HMMMM... creates a new handler everytime... i could optimize this somehow... hmmm
			// TODO: OPTIMIZE THIS HANDLER!
			ButtonCreationHandler bch = new ButtonCreationHandler(plugin);
			bch.handleChat(event.getPlayer(), event.getMessage());
			event.setCancelled(true);
		}
	}
	/** Gets the actions from a button and formats them for output **/
	public String getButtonActionsFormatted(Button button) {
		int size = button.getActionAmount();
		String finale = "";
		for(int i = 0; i <= size; i++) {
			if(i == size) {
				finale = finale + button.getActionName(i) + ".";
			} else {
				finale = finale + button.getActionName(i) + ", ";
			}
			
		}
		return finale;
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
		//The config to save stuff to
		bConfig = new ButtonConfig(plugin);
		//If the button exists then...
		if((block.getType().equals(Material.STONE_BUTTON) 
				|| block.getType().equals(Material.WOOD_PLATE) 
				|| block.getType().equals(Material.STONE_PLATE) 
				|| block.getType().equals(Material.LEVER)) 
				&& bConfig.buttonExists(block)) {
			Button button = bConfig.loadButton(block.getLocation());
			//If the player is editing another button
			if(!ButtonsPlus.modes.get(playername).equalsIgnoreCase("none")) {
				player.sendMessage(ChatColor.RED + "Your creating another button!");
				event.setCancelled(true);
				return;
			}
			//If the player is the owner...
			if(button.getOwner().equalsIgnoreCase(playername) 
					&& event.getAction() == Action.RIGHT_CLICK_BLOCK && !player.isSneaking()) {
				player.sendMessage(ChatColor.DARK_GREEN + "You own this button!");
			}
			//If the player has permission to interact with that type of block
			if(buttonTypes(player).contains(block.getType())) {
				//If the player does a regular non-crouch action to the block and activates it successfully
				if((event.getAction() == Action.LEFT_CLICK_BLOCK && block.getType().equals(Material.STONE_BUTTON))
						|| (event.getAction() == Action.PHYSICAL && block.getType() == Material.WOOD_PLATE)
						|| ((event.getAction().equals(Action.LEFT_CLICK_BLOCK) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !player.isSneaking())) && block.getType() == Material.LEVER)
						|| (event.getAction() == Action.PHYSICAL && block.getType() == Material.STONE_PLATE)
						|| (block.getType().equals(Material.STONE_BUTTON) && event.getAction() == Action.RIGHT_CLICK_BLOCK && !player.isSneaking())) {
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
						bConfig.saveButton(button);
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
				player.sendMessage(ChatColor.RED + "You do not have permission to press: " + block.getType().toString());
				//Cancel the event
				event.setCancelled(true);
				return;
			}
		}
		//If the player is sneaking and preforms the creation action then
		if((block.getType().equals(Material.STONE_BUTTON) 
				|| block.getType().equals(Material.WOOD_PLATE)
				|| block.getType().equals(Material.STONE_PLATE)
				|| block.getType().equals(Material.LEVER)) 
				&& (event.getAction() == Action.RIGHT_CLICK_BLOCK && player.isSneaking())) {
			//If the button exists already then show them the INFO for that button
			if(bConfig.buttonExists(block)) {
				//Load in the info for that button
				Button button = bConfig.loadButton(block.getLocation());
				//If the player has the info perm send them the long version
				if(button.getOwner().equals(playername) || ButtonsPlus.perms.has(player, "buttonsplus.info")) {
					player.sendMessage(ChatColor.BLUE + "=======================.-=Info=-.=====================");
					player.sendMessage(ChatColor.GOLD + "Owner: " + button.getOwner());
					player.sendMessage(ChatColor.GOLD + "Total Pushes: " + button.getPushes());
					//if the button is a charge button then show how much it will cost to push
					if(button.getActionName(0).equalsIgnoreCase("charge")) {
						player.sendMessage(ChatColor.GOLD + "This will cost: $" + button.getActionArgs(0)[0]);
					} else {
						player.sendMessage(ChatColor.GOLD + "This button will not charge money");
					}
					//Send the player a list of the actions this button will perform
					player.sendMessage(ChatColor.GOLD + "Actions: " + getButtonActionsFormatted(button));
					player.sendMessage(ChatColor.BLUE + "=======================.-=End=-.======================");
					//Don't activate the button
					event.setCancelled(true);
				//If the player does not have INFO but has costinfo permission, then the infoshort is displayed
				} else if(ButtonsPlus.perms.has(player, "buttonsplus.costinfo")) {
					player.sendMessage(ChatColor.BLUE + "====================.-=InfoShort=-.===================");
					player.sendMessage(ChatColor.GOLD + "Owner: " + button.getOwner());
					//Show the cost of the button
					if(button.getActionName(0).equalsIgnoreCase("charge")) {
						player.sendMessage(ChatColor.GOLD + "This will cost: $" + button.getActionArgs(0)[0]);
					} else {
						player.sendMessage(ChatColor.GOLD + "This button will not charge money");
					}
					player.sendMessage(ChatColor.BLUE + "=======================.-=End=-.======================");
					//Don't activate the button.
					event.setCancelled(true);
				}
			//If the button does not exist and the player is editing a button then
			}else if(!ButtonsPlus.modes.get(playername).equalsIgnoreCase("none")) {
				player.sendMessage(ChatColor.RED + "Your creating another button! type cancel now if you want to stop!");
			//If the button can be created then
			} else if((block.getType().equals(Material.STONE_BUTTON) 
					&& ButtonsPlus.perms.has(player, "buttonsplus.button.create")) 
					|| (block.getType().equals(Material.STONE_PLATE) 
							&& ButtonsPlus.perms.has(player, "buttonsplus.stoneplate.create")) 
							|| (block.getType().equals(Material.WOOD_PLATE) 
									&& ButtonsPlus.perms.has(player, "buttonsplus.woodplate.create")) 
									|| (block.getType().equals(Material.LEVER) 
											&& ButtonsPlus.perms.has(player, "buttonsplus.lever.create"))) {
				//Set the players mode to start the creation and stop the players chat messages going through
				ButtonsPlus.modes.put(playername, "createStart");
				//Get the players location and store it for later use
				ButtonsPlus.tempLoc.put(playername, block.getLocation());
				//Send the create message
				player.sendMessage(ChatColor.BLUE + "=====================.-=Create=-.=====================");
				player.sendMessage(ChatColor.GOLD + "You are now Setting up a ButtonsPlus button!");
				player.sendMessage(ChatColor.GOLD + "You will not be able to chat to other players during creation!");
				//Set the cost for the creation to 0
				ButtonsPlus.buttonCost.put(playername, 0);
				player.sendMessage(ChatColor.BLUE + "----------------------------------------------------");
				//Show the player what modes they can use
				if(fetchModes(player).contains("Charge") 
						|| fetchModes(player).contains("RewardAll") 
						|| fetchModes(player).contains("RewardOne")) {
					player.sendMessage(ChatColor.GOLD + "What type of button do you want this to be? Type in one of the modes from this list:");
					player.sendMessage(ChatColor.BLUE + "======================.-=Modes=-.=====================");
					player.sendMessage(ChatColor.BLUE + getFormatList(fetchModes(player)));
					player.sendMessage(ChatColor.BLUE + "----------------------------------------------------");
				//If they have no permissions for it, then tell them
				} else if(getPlayerActions(player).isEmpty()) {
					player.sendMessage("You have no creation permissions for this plugin @_@");
					ButtonsPlus.modes.put(playername, "none");
				//If they only have basic, then go straight to basic setup
				} else {
					player.sendMessage(ChatColor.GOLD + "Now Setting Up A Basic Button!");
					player.sendMessage("Type an action name to continue, type done to complete button setup, or type cancel to stop setup." + ChatColor.GOLD + "Actions: " + ChatColor.DARK_GREEN + getPlayerActions(player));
					//setup a temp button for while the player is creating the button
					//set location
					ButtonsPlus.tempButtons.put(player.getName(), new Button(ButtonsPlus.tempLoc.get(player.getName())));
					//Set charge to false, cause this is just a basic button
					ButtonsPlus.tempButtons.get(player.getName()).setIsCharge(false);
					//Set the owner
					ButtonsPlus.tempButtons.get(player.getName()).setOwner(player.getName());
					//Set up the increment variable so that the creation handler knows how many actions were added
					ButtonsPlus.increment.put(player.getName(), 0);
					//Sets the 0 action to basic, 0 action is the first action in the list of actions
					ButtonsPlus.tempButtons.get(player.getName()).actionNames.put(ButtonsPlus.increment.get(player.getName()), "basic");
					//Sets the arguments for the action 0
					ButtonsPlus.tempButtons.get(player.getName()).actionArgs.put(ButtonsPlus.increment.get(player.getName()), new String[] {"basic"});
					//Sets the increment to 1 for the next action
					ButtonsPlus.increment.put(player.getName(), 1);
					//Sets the players mode to create2 which will allow them to keep editing this button
					ButtonsPlus.modes.put(playername, "create2");
				}
				
			}
		}
	}
}
