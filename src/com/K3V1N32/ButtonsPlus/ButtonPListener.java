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
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.Listener;
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
public class ButtonPListener implements Listener{
	ButtonsPlus plugin;
	ButtonConfig bConfig;
	Logger logger = Logger.getLogger("Minecraft");
	
	public ButtonPListener(ButtonsPlus instance) {
		plugin = instance;
	}
	
	
	
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
		Calendar calendar = new GregorianCalendar();
		int thisTime = (int)calendar.getTimeInMillis() + 5000;
		ButtonsPlus.modes.put(event.getPlayer().getName(), "none");
		ButtonsPlus.cooldown.put(event.getPlayer().getName(), thisTime);
	}
	
	@EventHandler
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
		if(event.getClickedBlock() == null) {
			return;
		}
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		String playername = player.getName();
		bConfig = new ButtonConfig(plugin);
		if((block.getType().equals(Material.STONE_BUTTON) || block.getType().equals(Material.WOOD_PLATE) || block.getType().equals(Material.STONE_PLATE) || block.getType().equals(Material.LEVER)) && bConfig.buttonExists(block)) {
			Button button = bConfig.loadButton(block.getLocation());
			if(!ButtonsPlus.modes.get(playername).equalsIgnoreCase("none")) {
				player.sendMessage(ChatColor.RED + "Your creating another button!");
				event.setCancelled(true);
				return;
			}
			if(button.getOwner().equalsIgnoreCase(playername) && event.getAction() == Action.RIGHT_CLICK_BLOCK && !player.isSneaking()) {
				player.sendMessage(ChatColor.DARK_GREEN + "You own this button!");
			}
			if(buttonTypes(player).contains(block.getType())) {
				if((event.getAction() == Action.LEFT_CLICK_BLOCK && block.getType().equals(Material.STONE_BUTTON))
						|| (event.getAction() == Action.PHYSICAL && block.getType() == Material.WOOD_PLATE)
						|| ((event.getAction().equals(Action.LEFT_CLICK_BLOCK) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !player.isSneaking())) && block.getType() == Material.LEVER)
						|| (event.getAction() == Action.PHYSICAL && block.getType() == Material.STONE_PLATE)
						|| (block.getType().equals(Material.STONE_BUTTON) && event.getAction() == Action.RIGHT_CLICK_BLOCK && !player.isSneaking())) {
					ButtonActionHandler bah = new ButtonActionHandler(plugin);
					String test = bah.doActions(block, player);
					if(test == "true") {
						button.addPush();
						if(!button.getrewardedPlayers().contains(playername)) {
							button.addPlayer(playername);
						}
						bConfig.saveButton(button);
						return;
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
			} else {
				player.sendMessage(ChatColor.RED + "You do not have permission to press: " + block.getType().toString());
				event.setCancelled(true);
				return;
			}
		}
		if((block.getType().equals(Material.STONE_BUTTON) || block.getType().equals(Material.WOOD_PLATE) || block.getType().equals(Material.STONE_PLATE) || block.getType().equals(Material.LEVER)) && (event.getAction() == Action.RIGHT_CLICK_BLOCK && player.isSneaking())) {
			if(bConfig.buttonExists(block)) {
				Button button = bConfig.loadButton(block.getLocation());
				if(button.getOwner().equals(playername) || ButtonsPlus.perms.has(player, "buttonsplus.info")) {
					player.sendMessage(ChatColor.BLUE + "=======================.-=Info=-.=====================");
					player.sendMessage(ChatColor.GOLD + "Owner: " + button.getOwner());
					player.sendMessage(ChatColor.GOLD + "Total Pushes: " + button.getPushes());
					if(button.getActionName(0).equalsIgnoreCase("charge")) {
						player.sendMessage(ChatColor.GOLD + "This will cost: $" + button.getActionArgs(0)[0]);
					} else {
						player.sendMessage(ChatColor.GOLD + "This button will not charge money");
					}
					player.sendMessage(ChatColor.GOLD + "Actions: " + getButtonActionsFormatted(button));
					player.sendMessage(ChatColor.BLUE + "=======================.-=End=-.======================");
					event.setCancelled(true);
				} else if(ButtonsPlus.perms.has(player, "buttonsplus.costinfo")) {
					player.sendMessage(ChatColor.BLUE + "====================.-=InfoShort=-.===================");
					player.sendMessage(ChatColor.GOLD + "Owner: " + button.getOwner());
					if(button.getActionName(0).equalsIgnoreCase("charge")) {
						player.sendMessage(ChatColor.GOLD + "This will cost: $" + button.getActionArgs(0)[0]);
					} else {
						player.sendMessage(ChatColor.GOLD + "This button will not charge money");
					}
					player.sendMessage(ChatColor.BLUE + "=======================.-=End=-.======================");
					event.setCancelled(true);
				}
			}else if(!ButtonsPlus.modes.get(playername).equalsIgnoreCase("none")) {
				player.sendMessage(ChatColor.RED + "Your creating another button! type cancel now if you want to stop!");
			} else if((block.getType().equals(Material.STONE_BUTTON) && ButtonsPlus.perms.has(player, "buttonsplus.button.create")) || (block.getType().equals(Material.STONE_PLATE) && ButtonsPlus.perms.has(player, "buttonsplus.stoneplate.create")) || (block.getType().equals(Material.WOOD_PLATE) && ButtonsPlus.perms.has(player, "buttonsplus.woodplate.create")) || (block.getType().equals(Material.LEVER) && ButtonsPlus.perms.has(player, "buttonsplus.lever.create"))) {
				ButtonsPlus.modes.put(playername, "createStart");
				ButtonsPlus.tempLoc.put(playername, block.getLocation());
				player.sendMessage(ChatColor.BLUE + "=====================.-=Create=-.=====================");
				player.sendMessage(ChatColor.GOLD + "You are now Setting up a ButtonsPlus button!");
				player.sendMessage(ChatColor.GOLD + "You will not be able to chat to other players during creation!");
				player.sendMessage(ChatColor.BLUE + "----------------------------------------------------");
				if(fetchModes(player).contains("Charge") || fetchModes(player).contains("RewardAll") || fetchModes(player).contains("RewardOne")) {
					player.sendMessage(ChatColor.GOLD + "What type of button do you want this to be? Type in one of the modes from this list:");
					player.sendMessage(ChatColor.BLUE + "======================.-=Modes=-.=====================");
					player.sendMessage(ChatColor.BLUE + getFormatList(fetchModes(player)));
					player.sendMessage(ChatColor.BLUE + "----------------------------------------------------");
				} else if(getPlayerActions(player).isEmpty()) {
					player.sendMessage("You have no creation permissions for this plugin @_@");
					ButtonsPlus.modes.put(playername, "none");
				} else {
					player.sendMessage(ChatColor.GOLD + "Now Setting Up A Basic Button!");
					player.sendMessage("Type an action name to continue, type done to complete button setup, or type cancel to stop setup." + ChatColor.GOLD + "Actions: " + ChatColor.DARK_GREEN + getPlayerActions(player));
					ButtonsPlus.tempButtons.put(player.getName(), new Button(ButtonsPlus.tempLoc.get(player.getName())));
					ButtonsPlus.tempButtons.get(player.getName()).setIsCharge(false);
					ButtonsPlus.tempButtons.get(player.getName()).setOwner(player.getName());
					ButtonsPlus.increment.put(player.getName(), 0);
					ButtonsPlus.tempButtons.get(player.getName()).actionNames.put(ButtonsPlus.increment.get(player.getName()), "basic");
					ButtonsPlus.tempButtons.get(player.getName()).actionArgs.put(ButtonsPlus.increment.get(player.getName()), new String[] {"basic"});
					ButtonsPlus.increment.put(player.getName(), 1);
					ButtonsPlus.modes.put(playername, "create2");
				}
				
			}
		}
	}
}
