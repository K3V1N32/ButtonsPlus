package com.K3V1N32.ButtonsPlus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.fusesource.jansi.Ansi.Color;

public class ButtonCreationHandler {
	ButtonsPlus plugin;
	ButtonConfig config;
	/** The name of the currency from the server **/
	String currencyName = ButtonsPlus.econ.currencyNamePlural();
	
	/** Formats a list so it can be displayed in game **/
	public String getFormatList(List<String> oldList) {
		int l = oldList.size();
		String ret = "";
		if(l > 1) {
			for(int i = 0;i < l;i++) {
				if(i > (l - 1)) {
					ret = ret + oldList.get(i) + ".";
				} else {
					ret = ret + oldList.get(i) + ", ";
				}
			}
		} else {
			if(oldList.isEmpty()) {
				ret = "No Permissions.";
			} else {
				ret = oldList.get(0);
			}
		}
		return ret;
	}
	
	/** Gets a list of the actions a player can CREATE **/
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
	
	/** Gets a list of effects a player can CREATE **/
	public String getPlayerEffects(Player player) {
		List<String> perList = new ArrayList<String>();
		if(ButtonsPlus.perms.has(player, "buttonsplus.blind.create")) {
			perList.add("blind");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.confuse.create")) {
			perList.add("confuse");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.jump.create")) {
			perList.add("jump");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.speed.create")) {
			perList.add("speed");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.slow.create")) {
			perList.add("slow");
		}
		return getFormatList(perList);
	}
	
	/** A list of sounds **/
	public String getPlayerSounds(Player player) {
		List<String> perList = new ArrayList<String>();
		//Effect.BOW_FIRE;
		//Effect.CLICK1;
		//Effect.CLICK2;
		//Effect.DOOR_TOGGLE;
		//Effect.ENDER_SIGNAL;
		//Effect.EXTINGUISH;
		//Effect.GHAST_SHOOT;
		//Effect.GHAST_SHRIEK;
		//Effect.ZOMBIE_CHEW_IRON_DOOR;
		//Effect.ZOMBIE_CHEW_WOODEN_DOOR;
		//Effect.ZOMBIE_DESTROY_DOOR;
		perList.add("BOW_FIRE");
		perList.add("CLICK1");
		perList.add("CLICK2");
		perList.add("DOOR_TOGGLE");
		perList.add("EXTINGUISH");
		perList.add("GHAST_SHOOT");
		perList.add("GHAST_SHRIEK");
		perList.add("ZOMBIE_CHEW_IRON_DOOR");
		perList.add("ZOMBIE_CHEW_WOODEN_DOOR");
		perList.add("ZOMBIE_DESTROY_DOOR");
		return getFormatList(perList);
	}
	
	/** Gets the mobs a player has perms for **/
	public String getPlayerMobs(Player player) {
		List<String> perList = new ArrayList<String>();
		if(ButtonsPlus.perms.has(player, "buttonsplus.cow.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Cow");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.pig.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Pig");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.enderman.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Enderman");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.chicken.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Chicken");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.ghast.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Ghast");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.creeper.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Creeper");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.sheep.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Sheep");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.pigzombie.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("PigZombie");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.zombie.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Zombie");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.giant.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Giant");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.skeleton.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Skeleton");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.slime.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Slime");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.spider.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Spider");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.squid.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Squid");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.cavespider.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("CaveSpider");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.wolf.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Wolf");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.silverfish.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Silverfish");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.blaze.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Blaze");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.mushroomcow.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("MushroomCow");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.villager.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Villager");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.snowman.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Snowman");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.magmacube.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("MagmaCube");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.ocelot.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Ocelot");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.irongolem.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("IronGolem");
		}
		return getFormatList(perList);
	}
	
	/** Initilize creation handler **/
	public ButtonCreationHandler(ButtonsPlus instance) {
		plugin = instance;
	}
	
	
	/** Main Chat Handler process. Input player+chat Output nothing **/
	public void handleChat(Player p, String chat) {
		//A string that displays after an action is added
		String nextDisplay = "Type an action name to continue, type done to complete button setup, or type cancel to stop setup." + ChatColor.GOLD + "Actions: " + ChatColor.DARK_GREEN + getPlayerActions(p) + " " + ChatColor.GOLD;
		//Permission to get out of being charged for a button
		if(!ButtonsPlus.perms.has(p, "buttonsplus.nocharge") || !(ButtonsPlus.buttonCost.get(p.getName()) == 0) || !(ButtonsPlus.buttonCost.get(p.getName()) == null)) {
			nextDisplay = nextDisplay + "Total charge for your current button: " + ChatColor.BLUE + "$" + ButtonsPlus.buttonCost.get(p.getName());
		}
		//init config
		config = new ButtonConfig(plugin);
		/*
		 * Button Types: Basic(no charge), Charge(charges money), RewardPlayer(only one use per player), RewardAll(one use and the button deletes itself)
		 * Ok so, lets take this step by step
		 * Step 1. Player crouch + Right Click (createStart)
		 * Step 2. <dialog popup(Will this button charge money?), setMode(create1), create blank button w/ loc/world and save it only to a hashmap(initButtons<String, Button>)>
		 * Step 3. <player input, if no(mode = create2) skip to [7], if yes, keep going(no player mode change)>
		 * Step 4. <player wants to charge(response=yes mode=createStart), set player mode to (charge1)>
		 * Step 5. <dialog popup(How much money?)[if not integer no mode change + extra dialog], setMode(charge2)>
		 * Step 6. <player input, set button actionName<0, charge> + actionArgs<0, ####>, set mode (create2)>
		 * Step 7. <if charge var = false then set actionName<0, charge> actionArgs<0, 0>, else continue>
		 * Step 8. <popup(nextDisplay) (mode=create2 || create3), player input>
		 * Step 9. <if<mode=create2> and message = available action then continue>
		 * Step 10. <action + "0" = mode, go through actions dialogs(next dialogs will be action + "#ofdialog">
		 * Step 11. when the current action dialogs are complete, saving actionName + actionArgs hashMap for the button and mode = create3
		 * Step 12. if "continue" goto 8 if "done" saveButton if "cancel" delete tempButton and set playerMode = "none"
		 * Step 13. Repeat as needed.[8]
		 */
		//begin the long and slow decent into if() hell :P
		
		//return to sender if not correct message, just a backup incase its not called from the chat :P
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("none")) {
			return;
		}
		//if the button is cancelled then delete temp data and enable the regular chat
		if(chat.equalsIgnoreCase("cancel")) {
			p.sendMessage(ChatColor.GOLD + "Stoped Setup! Regular chat enabled!");
			p.sendMessage(ChatColor.RED + "-------------------------------------------------");
			ButtonsPlus.modes.put(p.getName(), "none");
			if(ButtonsPlus.tempButtons.containsKey(p.getName())) {
				ButtonsPlus.tempButtons.remove(p.getName());
			}
			if(ButtonsPlus.tempLoc.containsKey(p.getName())) {
				ButtonsPlus.tempLoc.remove(p.getName());
			}
			if(ButtonsPlus.increment.containsKey(p.getName())) {
				ButtonsPlus.increment.remove(p.getName());
			}
			if(ButtonsPlus.buttonCost.containsKey(p.getName())) {
				ButtonsPlus.buttonCost.remove(p.getName());
			}
			return;
		}
		//Display the mobs that the player has access to
		if(chat.equalsIgnoreCase("mobs")) {
			p.sendMessage(ChatColor.GOLD + "Mobs: " + ChatColor.WHITE + getPlayerMobs(p));
			return;
		}
		//Display the actions that the player has access to
		if(chat.equalsIgnoreCase("actions")) {
			p.sendMessage(ChatColor.GOLD + "Actions: " + ChatColor.WHITE + getPlayerActions(p));
			return;
		}
		//Display the list of effects
		if(chat.equalsIgnoreCase("effects")) {
			p.sendMessage(ChatColor.GOLD + "Effects: " + ChatColor.WHITE + getPlayerEffects(p));
			return;
		}
		//Display a list of sounds
		if(chat.equalsIgnoreCase("sounds")) {
			p.sendMessage(ChatColor.GOLD + "Sounds: " + ChatColor.WHITE + getPlayerSounds(p));
			return;
		}

		//createStart means that this button needs to be defined still! so the chat message SHOULD
		//BE a mode for the button[Basic, Charge, RewardOne, RewardAll]
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("createStart")) {
			//If the mode is charge...
			if(chat.equalsIgnoreCase("charge") && ButtonsPlus.perms.has(p, "buttonsplus.charge.create")) {
				ButtonsPlus.tempButtons.put(p.getName(), new Button(ButtonsPlus.tempLoc.get(p.getName())));
				ButtonsPlus.tempButtons.get(p.getName()).setIsCharge(true);
				ButtonsPlus.tempButtons.get(p.getName()).setOwner(p.getName());
				ButtonsPlus.increment.put(p.getName(), 0);
				
				p.sendMessage(ChatColor.GOLD + "How much will your button charge?");
				ButtonsPlus.modes.put(p.getName(), "charge1");
				return;
			}
			//If the mode is basic...
			else if(chat.equalsIgnoreCase("basic")) {
				ButtonsPlus.tempButtons.put(p.getName(), new Button(ButtonsPlus.tempLoc.get(p.getName())));
				ButtonsPlus.tempButtons.get(p.getName()).setIsCharge(false);
				ButtonsPlus.tempButtons.get(p.getName()).setOwner(p.getName());
				ButtonsPlus.increment.put(p.getName(), 0);
				ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "basic");
				ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {"basic"});
				ButtonsPlus.increment.put(p.getName(), 1);
				p.sendMessage(nextDisplay);
				ButtonsPlus.modes.put(p.getName(), "create2");
				return;
			}
			//if the mode is rewardone...
			else if(chat.equalsIgnoreCase("rewardone")) {
				ButtonsPlus.tempButtons.put(p.getName(), new Button(ButtonsPlus.tempLoc.get(p.getName())));
				ButtonsPlus.tempButtons.get(p.getName()).setIsCharge(false);
				ButtonsPlus.tempButtons.get(p.getName()).setOwner(p.getName());
				ButtonsPlus.increment.put(p.getName(), 0);
				ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "rewardone");
				ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {"rewardOne"});
				ButtonsPlus.increment.put(p.getName(), 1);
				p.sendMessage(nextDisplay);
				ButtonsPlus.modes.put(p.getName(), "create2");
				return;
			}
			//if the mode is rewardall...
			else if(chat.equalsIgnoreCase("rewardall")) {
				ButtonsPlus.tempButtons.put(p.getName(), new Button(ButtonsPlus.tempLoc.get(p.getName())));
				ButtonsPlus.tempButtons.get(p.getName()).setIsCharge(false);
				ButtonsPlus.tempButtons.get(p.getName()).setOwner(p.getName());
				ButtonsPlus.increment.put(p.getName(), 0);
				ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "rewardall");
				ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {"rewardAll"});
				ButtonsPlus.increment.put(p.getName(), 1);
				p.sendMessage(nextDisplay);
				ButtonsPlus.modes.put(p.getName(), "create2");
				return;
			} else {
				p.sendMessage(Color.RED + "Please input a type or type cancel to cancel editing a button");
				return;
			}
		}
		//Seting up a charge mode button
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("charge1")) {
			if(ButtonsPlus.perms.has(p, "buttonsplus.charge.create")) {
				int balance = (int)ButtonsPlus.econ.getBalance(p.getName());
				int cha_rge = 0;
				int charge2 = 0;
				try {
					cha_rge = Integer.parseInt(chat);
				} catch(Exception e) {
					p.sendMessage(ChatColor.RED + "Please enter a number! (>o_o)> --- |__|:");
					return;
				}
				if(ButtonsPlus.charge) {
					charge2 = ButtonsPlus.chargePrice;
				} else {
					charge2 = cha_rge * ButtonsPlus.multiplier;
				}
				if(charge2 > balance) {
					p.sendMessage(ChatColor.RED + "Insufficient funds! Please enter a price you can afford, or type cancel to stop making this button");
					return;
				}
				ButtonsPlus.econ.withdrawPlayer(p.getName(), charge2);
				p.sendMessage(ChatColor.DARK_BLUE + "You have been charged $" + charge2 + " " + currencyName + " for making this button!");
				ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "charge");
				ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {chat});
				ButtonsPlus.increment.put(p.getName(), 1);
				p.sendMessage(nextDisplay);
				ButtonsPlus.modes.put(p.getName(), "create2");
				return;
			}
		}
		
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("create2")) {
			if(getPlayerActions(p).contains(chat) && !chat.equalsIgnoreCase("charge")) {
				if(chat.equalsIgnoreCase("command")) {
					p.sendMessage(ChatColor.GOLD + "Enter your command WITHOUT the / in front");
					ButtonsPlus.modes.put(p.getName(), "command1");
					return;
				}
				if(chat.equalsIgnoreCase("tutorial")) {
					p.sendMessage(ChatColor.GOLD + "Enter some text");
					ButtonsPlus.modes.put(p.getName(), "tutorial1");
					return;
				}
				if(chat.equalsIgnoreCase("cooldown")) {
					p.sendMessage(ChatColor.GOLD + "Enter how long to set cooldown in seconds");
					ButtonsPlus.modes.put(p.getName(), "cooldown1");
					return;
				}
				if(chat.equalsIgnoreCase("text")) {
					p.sendMessage(ChatColor.GOLD + "Enter a line of text");
					ButtonsPlus.modes.put(p.getName(), "text1");
					return;
				}
				if(chat.equalsIgnoreCase("sound")) {
					p.sendMessage("Enter the name of a sound, type sounds to see a list of sounds");
					ButtonsPlus.modes.put(p.getName(), "sound1");
					return;
				}
				if(chat.equalsIgnoreCase("death")) {
					ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "death");
					ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {"none"});
					ButtonsPlus.increment.put(p.getName(), ButtonsPlus.increment.get(p.getName()) + 1);
					p.sendMessage(ChatColor.GREEN + "Added death to actions");
					p.sendMessage(nextDisplay);
					ButtonsPlus.modes.put(p.getName(), "create2");
					return;
				}
				if(chat.equalsIgnoreCase("teleport")) {
					p.sendMessage(ChatColor.GOLD + "Stand where you want this to teleport someone and type tphere");
					ButtonsPlus.modes.put(p.getName(), "teleport1");
					return;
				}
				if(chat.equalsIgnoreCase("mob")) {
					p.sendMessage(ChatColor.GOLD + "Stand where you would like to spawn the mob and Enter the name of the mob you want to spawn, type mobs to see a list of mobs that you have permission for.");
					ButtonsPlus.modes.put(p.getName(), "mob1");
					return;
				}
				if(chat.equalsIgnoreCase("gmessage")) { 
					p.sendMessage(ChatColor.GOLD + "Enter the message you wish to be displayed(&p is the person who pressed button and &m is how much money they have ;))");
					ButtonsPlus.modes.put(p.getName(), "gmessage1");
					return;
				}
				if(chat.equalsIgnoreCase("heal")) {
					ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "heal");
					ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {"none"});
					ButtonsPlus.increment.put(p.getName(), ButtonsPlus.increment.get(p.getName()) + 1);
					p.sendMessage(ChatColor.GREEN + "Added heal to actions");
					p.sendMessage(nextDisplay);
					ButtonsPlus.modes.put(p.getName(), "create2");
					return;
				}
				if(chat.equalsIgnoreCase("burn")) {
					ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "burn");
					ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {"none"});
					ButtonsPlus.increment.put(p.getName(), ButtonsPlus.increment.get(p.getName()) + 1);
					p.sendMessage(ChatColor.GREEN+ "Added burn to actions");
					p.sendMessage(nextDisplay);
					ButtonsPlus.modes.put(p.getName(), "create2");
					return;
				}
				if(chat.equalsIgnoreCase("lightning")) {
					ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "lightning");
					ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {"none"});
					ButtonsPlus.increment.put(p.getName(), ButtonsPlus.increment.get(p.getName()) + 1);
					p.sendMessage(ChatColor.GREEN + "Added lightning to button");
					p.sendMessage(nextDisplay);
					ButtonsPlus.modes.put(p.getName(), "create2");
					return;
				}
				if(chat.equalsIgnoreCase("console")) {
					p.sendMessage("Enter a console command WITHOUT the / in front");
					ButtonsPlus.modes.put(p.getName(), "console1");
					return;
				}
				if(chat.equalsIgnoreCase("effect")) {
					p.sendMessage("Enter an effect name and the duration in ticks seperated by a space (20 ticks = 1 second, 1200 ticks = 1 minute), type effects to see which effects you have permissions for.");
					ButtonsPlus.modes.put(p.getName(), "effect1");
					return;
				}
				if(chat.equalsIgnoreCase("item")) {
					p.sendMessage("Enter the name or id of the item you want to give the player and the amount seperated by a space E.g. stone 2");
					ButtonsPlus.modes.put(p.getName(), "item1");
				}
				return;
			} else if(chat.equalsIgnoreCase("done")) {
				p.sendMessage("The Total Charge for this button is $" + ButtonsPlus.buttonCost.get(p.getName()));
				p.sendMessage("Type cancel to stop setup or type confirm to create the button and be charged");
				ButtonsPlus.modes.put(p.getName(), "doneConfirm");
			} else {
				p.sendMessage(ChatColor.RED + "Insuficiant Permissions, or not an action, retry.");
				p.sendMessage(nextDisplay);
				return;
			}
		}
		if(ButtonsPlus.modes.get(p.getName()) == "doneConfirm") {
			if(ButtonsPlus.buttonCost.get(p.getName()) > ButtonsPlus.econ.getBalance(p.getName())) {
				p.sendMessage("Insufficient funds type cancel and remake the button to have less commands or make more money!");
				return;
			}
			ButtonsPlus.econ.withdrawPlayer(p.getName(), ButtonsPlus.buttonCost.get(p.getName()));
			p.sendMessage(ChatColor.GOLD + "You have been charged: $" + ButtonsPlus.buttonCost.get(p.getName()));
			config.saveButton(ButtonsPlus.tempButtons.get(p.getName()));
			p.sendMessage(ChatColor.GREEN + "Saved Button. Setup complete");
			p.sendMessage(ChatColor.BLUE + "==================================================");
			ButtonsPlus.modes.put(p.getName(), "none");
			if(ButtonsPlus.tempButtons.containsKey(p.getName())) {
				ButtonsPlus.tempButtons.remove(p.getName());
			}
			if(ButtonsPlus.tempLoc.containsKey(p.getName())) {
				ButtonsPlus.tempLoc.remove(p.getName());
			}
			if(ButtonsPlus.increment.containsKey(p.getName())) {
				ButtonsPlus.increment.remove(p.getName());
			}
			if(ButtonsPlus.buttonCost.containsKey(p.getName())) {
				ButtonsPlus.buttonCost.remove(p.getName());
			}
			return;
		}
		//start the next round of if() battle X.x
		//If the player has put in a command:
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("command1")) {
			ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "command");
			ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {chat});
			ButtonsPlus.increment.put(p.getName(), ButtonsPlus.increment.get(p.getName()) + 1);
			p.sendMessage(ChatColor.GREEN + "Command Action added");
			ButtonsPlus.buttonCost.put(p.getName(), ButtonsPlus.buttonCost.get(p.getName()) + ButtonsPlus.commandcost);
			p.sendMessage(nextDisplay);
			ButtonsPlus.modes.put(p.getName(), "create2");
			return;
		}
		//If the player has put in a cooldown time:
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("cooldown1")) {
			int i = 0;
			try {
				i = Integer.parseInt(chat);
			} catch(Exception e) {
				p.sendMessage(ChatColor.RED + "Please enter a number!");
				return;
			}
			ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "cooldown");
			ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {i + ""});
			ButtonsPlus.increment.put(p.getName(), ButtonsPlus.increment.get(p.getName()) + 1);
			p.sendMessage(ChatColor.GREEN + "Cooldown Action added");
			ButtonsPlus.buttonCost.put(p.getName(), ButtonsPlus.buttonCost.get(p.getName()) + ButtonsPlus.cooldowncost);
			p.sendMessage(nextDisplay);
			ButtonsPlus.modes.put(p.getName(), "create2");
			return;
		}
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("sound1")) {
			if(getPlayerSounds(p).contains(chat)) {
				ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "sound");
				ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {chat});
				ButtonsPlus.increment.put(p.getName(), ButtonsPlus.increment.get(p.getName()) + 1);
				p.sendMessage(ChatColor.GREEN + "Sound Action added");
				ButtonsPlus.buttonCost.put(p.getName(), ButtonsPlus.buttonCost.get(p.getName()) + ButtonsPlus.soundcost);
				p.sendMessage(nextDisplay);
				ButtonsPlus.modes.put(p.getName(), "create2");
				return;
			} else {
				p.sendMessage(ChatColor.RED + "That sound is not in the list! type sounds to see a list of sounds that you can use");
				return;
			}
		}
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("console1")) {
			ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "console");
			ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {chat});
			ButtonsPlus.increment.put(p.getName(), ButtonsPlus.increment.get(p.getName()) + 1);
			p.sendMessage(ChatColor.GREEN + "Console Action added");
			ButtonsPlus.buttonCost.put(p.getName(), ButtonsPlus.buttonCost.get(p.getName()) + ButtonsPlus.consolecost);
			p.sendMessage(nextDisplay);
			ButtonsPlus.modes.put(p.getName(), "create2");
			return;
		}
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("item1")) {
			String[] split = chat.split(" ");
			Material mat = null;
			String item = split[0].toUpperCase();
			int i = 0;
			int y = 0;
			try {
				i = Integer.parseInt(split[0]);
				mat = Material.getMaterial(i);
			} catch (Exception e) {
				mat = Material.getMaterial(item);
			}
			
			if(mat == null) {
				p.sendMessage(ChatColor.RED + "Please Enter a Proper Item ID or name!");
				return;
			}
			try {
				y = Integer.parseInt(split[1]);
			} catch (Exception ex) {
				p.sendMessage(ChatColor.RED + "Please Enter an amount of the Item to give!");
				return;
			}
			ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "item");
			ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {mat.toString(), "" + y});
			ButtonsPlus.increment.put(p.getName(), ButtonsPlus.increment.get(p.getName()) + 1);
			p.sendMessage(ChatColor.GREEN + "Item Action added");
			ButtonsPlus.buttonCost.put(p.getName(), ButtonsPlus.buttonCost.get(p.getName()) + ButtonsPlus.itemcost);
			p.sendMessage(nextDisplay);
			ButtonsPlus.modes.put(p.getName(), "create2");
			return;
		}
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("effect1")) {
			String[] split1 = chat.split(" ");
			int i = 0;
			try {
				i = Integer.parseInt(split1[1]);
			} catch(Exception e) {
				p.sendMessage("Please enter a number!");
				return;
			}
			if(getPlayerEffects(p).contains(split1[0])) {
				ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "effect");
				ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {split1[0], i + ""});
				ButtonsPlus.increment.put(p.getName(), ButtonsPlus.increment.get(p.getName()) + 1);
				p.sendMessage(ChatColor.GREEN + "Added Effect action");
				ButtonsPlus.buttonCost.put(p.getName(), ButtonsPlus.buttonCost.get(p.getName()) + ButtonsPlus.effectcost);
				p.sendMessage(nextDisplay);
				ButtonsPlus.modes.put(p.getName(), "create2");
				return;
			} else {
				p.sendMessage(ChatColor.RED + "Invalid effect name or Invalid Permissions(Type effects to see which effects you can use!)");
				return;
			}
		}
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("tutorial1")) {
			ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "tutorial");
			ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {chat});
			ButtonsPlus.increment.put(p.getName(), ButtonsPlus.increment.get(p.getName()) + 1);
			p.sendMessage(ChatColor.GREEN + "Tutorial Action added");
			ButtonsPlus.buttonCost.put(p.getName(), ButtonsPlus.buttonCost.get(p.getName()) + ButtonsPlus.tutorialcost);
			p.sendMessage(nextDisplay);
			ButtonsPlus.modes.put(p.getName(), "create2");
			return;
		}
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("text1")) {
			ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "text");
			ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {chat});
			ButtonsPlus.increment.put(p.getName(), ButtonsPlus.increment.get(p.getName()) + 1);
			p.sendMessage(ChatColor.GREEN + "Text Action added");
			ButtonsPlus.buttonCost.put(p.getName(), ButtonsPlus.buttonCost.get(p.getName()) + ButtonsPlus.textcost);
			p.sendMessage(nextDisplay);
			ButtonsPlus.modes.put(p.getName(), "create2");
			return;
		}
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("teleport1")) {
			ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "teleport");
			ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {ButtonsPlus.saveLocation(p.getLocation()), p.getWorld().getName()});
			ButtonsPlus.increment.put(p.getName(), ButtonsPlus.increment.get(p.getName()) + 1);
			p.sendMessage(ChatColor.GREEN + "Teleport Action added");
			ButtonsPlus.buttonCost.put(p.getName(), ButtonsPlus.buttonCost.get(p.getName()) + ButtonsPlus.teleportcost);
			p.sendMessage(nextDisplay);
			ButtonsPlus.modes.put(p.getName(), "create2");
			return;
		}
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("gmessage1")) {
			ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "gmessage");
			ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {chat});
			ButtonsPlus.increment.put(p.getName(), ButtonsPlus.increment.get(p.getName()) + 1);
			p.sendMessage(ChatColor.GREEN + "Global message Action added");
			ButtonsPlus.buttonCost.put(p.getName(), ButtonsPlus.buttonCost.get(p.getName()) + ButtonsPlus.globalmessagecost);
			p.sendMessage(nextDisplay);
			ButtonsPlus.modes.put(p.getName(), "create2");
			return;
		}
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("mob1")) {
			if(getPlayerMobs(p).contains(chat)) {
				ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "mob");
				ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {chat, ButtonsPlus.saveLocation(p.getLocation())});
				ButtonsPlus.increment.put(p.getName(), ButtonsPlus.increment.get(p.getName()) + 1);
				p.sendMessage(ChatColor.GREEN + "Added spawn mob action");
				ButtonsPlus.buttonCost.put(p.getName(), ButtonsPlus.buttonCost.get(p.getName()) + ButtonsPlus.mobcost);
				p.sendMessage(nextDisplay);
				ButtonsPlus.modes.put(p.getName(), "create2");
				return;
			} else {
				p.sendMessage(ChatColor.RED + "Invalid mob name or Invalid Permissions(Type mobs to see which mobs you can use!)");
				return;
			}
		}
	}
}
