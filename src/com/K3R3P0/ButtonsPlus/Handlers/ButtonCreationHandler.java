package com.K3R3P0.ButtonsPlus.Handlers;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.K3R3P0.ButtonsPlus.ButtonsPlus;
import com.K3R3P0.ButtonsPlus.Button.Button;
import com.K3R3P0.ButtonsPlus.Settings.Settings;
import com.K3R3P0.ButtonsPlus.Utils.Utils;

public class ButtonCreationHandler {
	IOHandler io = new IOHandler();
	Utils utils = new Utils();
	
	/** Main Chat Handler process. Input player+chat Output nothing **/
	public void handleChat(Player p, String chat) {
		String currencyName = ButtonsPlus.econ.currencyNamePlural();
		IOHandler io = new IOHandler();
		//A string that displays after an action is added
		String nextDisplay = "Type an action name to continue, type done to complete button setup, or type cancel to stop setup." + ChatColor.GOLD + "Actions: " + ChatColor.DARK_GREEN + utils.formatList(utils.getAllowed(p, Utils.actionlist, ".create")) + " " + ChatColor.GOLD;
		//Permission to get out of being charged for a button
		if(!p.hasPermission("buttonsplus.nocharge") || !(Utils.buttonCost.get(p.getName()) == null)) {
			nextDisplay = nextDisplay + "Total charge for your current button: " + ChatColor.BLUE + "$" + Utils.buttonCost.get(p.getName()) + " " + currencyName;
		}
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
		//begin the long and slow decent into if() statement hell :P
		
		//return to sender if not correct message, just a backup in case its not called from the chat :P
		if(Utils.modes.get(p.getName()).equalsIgnoreCase("none")) {
			return;
		}
		//if the button is cancelled then delete temp data and enable the regular chat
		if(chat.equalsIgnoreCase("cancel")) {
			p.sendMessage(ChatColor.GOLD + "Stoped Setup! Regular chat enabled!");
			p.sendMessage(ChatColor.RED + "-------------------------------------------------");
			Utils.modes.put(p.getName(), "none");
			if(Utils.tempButtons.containsKey(p.getName())) {
				Utils.tempButtons.remove(p.getName());
			}
			if(Utils.tempLoc.containsKey(p.getName())) {
				Utils.tempLoc.remove(p.getName());
			}
			if(Utils.increment.containsKey(p.getName())) {
				Utils.increment.remove(p.getName());
			}
			if(Utils.buttonCost.containsKey(p.getName())) {
				Utils.buttonCost.remove(p.getName());
			}
			return;
		}
		//Display the mobs that the player has access to create
		if(chat.equalsIgnoreCase("mobs")) {
			p.sendMessage(ChatColor.GOLD + "Mobs: " + ChatColor.WHITE + utils.formatList(utils.getAllowed(p, Utils.mobnames, ".create")));
			return;
		}
		//Display the actions that the player has access to
		if(chat.equalsIgnoreCase("actions")) {
			p.sendMessage(ChatColor.GOLD + "Actions: " + ChatColor.WHITE + utils.formatList(utils.getAllowed(p, Utils.actionlist, ".create")));
			return;
		}
		//Display the list of effects
		if(chat.equalsIgnoreCase("effects")) {
			p.sendMessage(ChatColor.GOLD + "Effects: " + ChatColor.WHITE + utils.formatList(utils.getAllowed(p, Utils.effectnames, ".create")));
			return;
		}
		//Display a list of sounds
		if(chat.equalsIgnoreCase("sounds")) {
			p.sendMessage(ChatColor.GOLD + "Sounds: " + ChatColor.WHITE + utils.formatList(Utils.getSounds()));
			return;
		}

		//createStart means that this button needs to be defined still! so the chat message SHOULD
		//BE a mode for the button[Basic, Charge, RewardOne, RewardAll]
		if(Utils.modes.get(p.getName()).equalsIgnoreCase("createStart")) {
			//If the mode is charge...
			if(chat.equalsIgnoreCase("charge") && p.hasPermission("buttonsplus.charge.create")) {
				Utils.tempButtons.put(p.getName(), new Button(Utils.tempLoc.get(p.getName())));
				Utils.tempButtons.get(p.getName()).setIsCharge(true);
				Utils.tempButtons.get(p.getName()).setOwner(p.getName());
				Utils.increment.put(p.getName(), 0);
				
				p.sendMessage(ChatColor.GOLD + "How much will your button charge?");
				Utils.modes.put(p.getName(), "charge1");
				return;
			}
			//If the mode is basic...
			else if(chat.equalsIgnoreCase("basic") && p.hasPermission("buttonsplus.basic.create")) {
				Utils.tempButtons.put(p.getName(), new Button(Utils.tempLoc.get(p.getName())));
				Utils.tempButtons.get(p.getName()).setIsCharge(false);
				Utils.tempButtons.get(p.getName()).setOwner(p.getName());
				Utils.increment.put(p.getName(), 0);
				Utils.tempButtons.get(p.getName()).actionNames.put(Utils.increment.get(p.getName()), "basic");
				Utils.tempButtons.get(p.getName()).actionArgs.put(Utils.increment.get(p.getName()), new String[] {"basic"});
				Utils.increment.put(p.getName(), 1);
				p.sendMessage(nextDisplay);
				Utils.modes.put(p.getName(), "create2");
				return;
			}
			//if the mode is onetimeplayer...
			else if(chat.equalsIgnoreCase("onetimeplayer") && p.hasPermission("buttonsplus.onetimeplayer.create")) {
				Utils.tempButtons.put(p.getName(), new Button(Utils.tempLoc.get(p.getName())));
				Utils.tempButtons.get(p.getName()).setIsCharge(false);
				Utils.tempButtons.get(p.getName()).setOwner(p.getName());
				Utils.increment.put(p.getName(), 0);
				Utils.tempButtons.get(p.getName()).actionNames.put(Utils.increment.get(p.getName()), "onetimeplayer");
				Utils.tempButtons.get(p.getName()).actionArgs.put(Utils.increment.get(p.getName()), new String[] {"onetimeplayer"});
				Utils.increment.put(p.getName(), 1);
				p.sendMessage(nextDisplay);
				Utils.modes.put(p.getName(), "create2");
				return;
			}
			//if the mode is onetimeall...
			else if(chat.equalsIgnoreCase("onetimeall") && p.hasPermission("buttonsplus.onetimeall.create")) {
				Utils.tempButtons.put(p.getName(), new Button(Utils.tempLoc.get(p.getName())));
				Utils.tempButtons.get(p.getName()).setIsCharge(false);
				Utils.tempButtons.get(p.getName()).setOwner(p.getName());
				Utils.increment.put(p.getName(), 0);
				Utils.tempButtons.get(p.getName()).actionNames.put(Utils.increment.get(p.getName()), "onetimeall");
				Utils.tempButtons.get(p.getName()).actionArgs.put(Utils.increment.get(p.getName()), new String[] {"onetimeall"});
				Utils.increment.put(p.getName(), 1);
				p.sendMessage(nextDisplay);
				Utils.modes.put(p.getName(), "create2");
				return;
			} else {
				p.sendMessage(ChatColor.RED + "Please input a type or type cancel to cancel editing a button");
				return;
			}
		}
		//Seting up a charge mode button
		if(Utils.modes.get(p.getName()).equalsIgnoreCase("charge1")) {
			if(p.hasPermission("buttonsplus.charge.create")) {
				int cha_rge = 0;
				int charge2 = 0;
				try {
					cha_rge = Integer.parseInt(chat);
				} catch(Exception e) {
					p.sendMessage(ChatColor.RED + "Please enter a number! (>o_o)> --- |__|:");
					return;
				}
				charge2 = cha_rge * Settings.chargeMultiplier;
				Utils.buttonCost.put(p.getName(), charge2);
				Utils.tempButtons.get(p.getName()).actionNames.put(Utils.increment.get(p.getName()), "charge");
				Utils.tempButtons.get(p.getName()).actionArgs.put(Utils.increment.get(p.getName()), new String[] {chat});
				Utils.increment.put(p.getName(), 1);
				p.sendMessage(nextDisplay);
				Utils.modes.put(p.getName(), "create2");
				return;
			}
		}
		
		if(Utils.modes.get(p.getName()).equalsIgnoreCase("create2")) {
			if(utils.getAllowed(p, Utils.actionlist, ".create").contains(chat) && !chat.equalsIgnoreCase("charge")) {
				if(chat.equalsIgnoreCase("command")) {
					p.sendMessage(ChatColor.GOLD + "Enter your command WITHOUT the / in front");
					Utils.modes.put(p.getName(), "command1");
					return;
				}
				if(chat.equalsIgnoreCase("cooldown")) {
					p.sendMessage(ChatColor.GOLD + "Enter how long to set cooldown in seconds");
					Utils.modes.put(p.getName(), "cooldown1");
					return;
				}
				if(chat.equalsIgnoreCase("text")) {
					p.sendMessage(ChatColor.GOLD + "Enter a line of text");
					Utils.modes.put(p.getName(), "text1");
					return;
				}
				if(chat.equalsIgnoreCase("sound")) {
					p.sendMessage("Enter the name of a sound, type sounds to see a list of sounds");
					Utils.modes.put(p.getName(), "sound1");
					return;
				}
				if(chat.equalsIgnoreCase("death")) {
					Utils.buttonCost.put(p.getName(), Utils.buttonCost.get(p.getName()) + Settings.deathcost);
					Utils.tempButtons.get(p.getName()).actionNames.put(Utils.increment.get(p.getName()), "death");
					Utils.tempButtons.get(p.getName()).actionArgs.put(Utils.increment.get(p.getName()), new String[] {"none"});
					Utils.increment.put(p.getName(), Utils.increment.get(p.getName()) + 1);
					p.sendMessage(ChatColor.GREEN + "Added death to actions");
					p.sendMessage(nextDisplay);
					Utils.modes.put(p.getName(), "create2");
					return;
				}
				if(chat.equalsIgnoreCase("teleport")) {
					p.sendMessage(ChatColor.GOLD + "Stand where you want this to teleport someone and type tphere");
					Utils.modes.put(p.getName(), "teleport1");
					return;
				}
				if(chat.equalsIgnoreCase("mob")) {
					p.sendMessage(ChatColor.GOLD + "Stand where you would like to spawn the mob and Enter the name of the mob you want to spawn, type mobs to see a list of mobs that you have permission for.");
					Utils.modes.put(p.getName(), "mob1");
					return;
				}
				if(chat.equalsIgnoreCase("gmessage")) { 
					p.sendMessage(ChatColor.GOLD + "Enter the message you wish to be displayed(&p is the person who pressed button and &m is how much money they have ;))");
					Utils.modes.put(p.getName(), "gmessage1");
					return;
				}
				if(chat.equalsIgnoreCase("heal")) {
					Utils.buttonCost.put(p.getName(), Utils.buttonCost.get(p.getName()) + Settings.healcost);
					Utils.tempButtons.get(p.getName()).actionNames.put(Utils.increment.get(p.getName()), "heal");
					Utils.tempButtons.get(p.getName()).actionArgs.put(Utils.increment.get(p.getName()), new String[] {"none"});
					Utils.increment.put(p.getName(), Utils.increment.get(p.getName()) + 1);
					p.sendMessage(ChatColor.GREEN + "Added heal to actions");
					p.sendMessage(nextDisplay);
					Utils.modes.put(p.getName(), "create2");
					return;
				}
				if(chat.equalsIgnoreCase("burn")) {
					Utils.buttonCost.put(p.getName(), Utils.buttonCost.get(p.getName()) + Settings.burncost);
					Utils.tempButtons.get(p.getName()).actionNames.put(Utils.increment.get(p.getName()), "burn");
					Utils.tempButtons.get(p.getName()).actionArgs.put(Utils.increment.get(p.getName()), new String[] {"none"});
					Utils.increment.put(p.getName(), Utils.increment.get(p.getName()) + 1);
					p.sendMessage(ChatColor.GREEN+ "Added burn to actions");
					p.sendMessage(nextDisplay);
					Utils.modes.put(p.getName(), "create2");
					return;
				}
				if(chat.equalsIgnoreCase("lightning")) {
					Utils.buttonCost.put(p.getName(), Utils.buttonCost.get(p.getName()) + Settings.lightningcost);
					Utils.tempButtons.get(p.getName()).actionNames.put(Utils.increment.get(p.getName()), "lightning");
					Utils.tempButtons.get(p.getName()).actionArgs.put(Utils.increment.get(p.getName()), new String[] {"none"});
					Utils.increment.put(p.getName(), Utils.increment.get(p.getName()) + 1);
					p.sendMessage(ChatColor.GREEN + "Added lightning to button");
					p.sendMessage(nextDisplay);
					Utils.modes.put(p.getName(), "create2");
					return;
				}
				if(chat.equalsIgnoreCase("console")) {
					p.sendMessage("Enter a console command WITHOUT the / in front");
					Utils.modes.put(p.getName(), "console1");
					return;
				}
				if(chat.equalsIgnoreCase("effect")) {
					p.sendMessage("Enter an effect name and the duration in ticks seperated by a space (20 ticks = 1 second, 1200 ticks = 1 minute), type effects to see which effects you have permissions for.");
					Utils.modes.put(p.getName(), "effect1");
					return;
				}
				if(chat.equalsIgnoreCase("item")) {
					p.sendMessage("Enter the name or id of the item you want to give the player and the amount seperated by a space E.g. stone 2");
					Utils.modes.put(p.getName(), "item1");
				}
				return;
			} else if(chat.equalsIgnoreCase("done")) {
				int cost = Utils.buttonCost.get(p.getName());
				double balance = ButtonsPlus.econ.getBalance(p.getName());
				if(Utils.buttonCost.get(p.getName()) > balance) {
					p.sendMessage("The cost for this button(" + cost + ") is greater than your balance(" + balance + ")");
					p.sendMessage("Type cancel and start over, or make " + (cost - balance) + " more " + currencyName + "s.");
					return;
				} else {
					p.sendMessage("The Total Charge for this button is $" + cost + " " + currencyName);
					p.sendMessage("Type cancel to stop setup or type confirm to create the button and be charged");
					Utils.modes.put(p.getName(), "doneConfirm");
				}
			} else {
				p.sendMessage(ChatColor.RED + "Insuficiant Permissions, or not an action.");
				p.sendMessage(nextDisplay);
				return;
			}
		}
		if(Utils.modes.get(p.getName()) == "doneConfirm" && chat.equalsIgnoreCase("confirm")) {
			if(Utils.buttonCost.get(p.getName()) > ButtonsPlus.econ.getBalance(p.getName())) {
				if(p.hasPermission("buttonsplus.nocharge")) {
					p.sendMessage("Looks like you have the nocharge permission, :D");
				} else {
					p.sendMessage("Insufficient funds type cancel and remake the button to have less actions, or make more money!");
					return;
				}
			}
			if(!p.hasPermission("buttonsplus.nocharge")) {
				ButtonsPlus.econ.withdrawPlayer(p.getName(), Utils.buttonCost.get(p.getName()));
				p.sendMessage(ChatColor.GOLD + "You have been charged: $" + Utils.buttonCost.get(p.getName()));
			}
			io.saveButton(Utils.tempButtons.get(p.getName()));
			p.sendMessage(ChatColor.GREEN + "Saved Button. Setup complete!");
			p.sendMessage(ChatColor.BLUE + "==================================================");
			Utils.modes.put(p.getName(), "none");
			if(Utils.tempButtons.containsKey(p.getName())) {
				Utils.tempButtons.remove(p.getName());
			}
			if(Utils.tempLoc.containsKey(p.getName())) {
				Utils.tempLoc.remove(p.getName());
			}
			if(Utils.increment.containsKey(p.getName())) {
				Utils.increment.remove(p.getName());
			}
			if(Utils.buttonCost.containsKey(p.getName())) {
				Utils.buttonCost.remove(p.getName());
			}
			return;
		}
		//start the next round of if() statement battle X.x
		//If the player has put in a command:
		if(Utils.modes.get(p.getName()).equalsIgnoreCase("command1")) {
			Utils.tempButtons.get(p.getName()).actionNames.put(Utils.increment.get(p.getName()), "command");
			Utils.tempButtons.get(p.getName()).actionArgs.put(Utils.increment.get(p.getName()), new String[] {chat});
			Utils.increment.put(p.getName(), Utils.increment.get(p.getName()) + 1);
			p.sendMessage(ChatColor.GREEN + "Command Action added");
			Utils.buttonCost.put(p.getName(), Utils.buttonCost.get(p.getName()) + Settings.commandcost);
			p.sendMessage(nextDisplay);
			Utils.modes.put(p.getName(), "create2");
			return;
		}
		//If the player has put in a cooldown time:
		if(Utils.modes.get(p.getName()).equalsIgnoreCase("cooldown1")) {
			int i = 0;
			try {
				i = Integer.parseInt(chat);
			} catch(Exception e) {
				p.sendMessage(ChatColor.RED + "Please enter a number!");
				return;
			}
			Utils.tempButtons.get(p.getName()).actionNames.put(Utils.increment.get(p.getName()), "cooldown");
			Utils.tempButtons.get(p.getName()).actionArgs.put(Utils.increment.get(p.getName()), new String[] {i + ""});
			Utils.increment.put(p.getName(), Utils.increment.get(p.getName()) + 1);
			p.sendMessage(ChatColor.GREEN + "Cooldown Action added");
			Utils.buttonCost.put(p.getName(), Utils.buttonCost.get(p.getName()) + Settings.cooldowncost);
			p.sendMessage(nextDisplay);
			Utils.modes.put(p.getName(), "create2");
			return;
		}
		if(Utils.modes.get(p.getName()).equalsIgnoreCase("sound1")) {
			if(Utils.getSounds().contains(chat)) {
				Utils.tempButtons.get(p.getName()).actionNames.put(Utils.increment.get(p.getName()), "sound");
				Utils.tempButtons.get(p.getName()).actionArgs.put(Utils.increment.get(p.getName()), new String[] {chat});
				Utils.increment.put(p.getName(), Utils.increment.get(p.getName()) + 1);
				p.sendMessage(ChatColor.GREEN + "Sound Action added");
				Utils.buttonCost.put(p.getName(), Utils.buttonCost.get(p.getName()) + Settings.soundcost);
				p.sendMessage(nextDisplay);
				Utils.modes.put(p.getName(), "create2");
				return;
			} else {
				p.sendMessage(ChatColor.RED + "That sound is not in the list! type sounds to see a list of sounds that you can use");
				return;
			}
		}
		if(Utils.modes.get(p.getName()).equalsIgnoreCase("console1")) {
			Utils.tempButtons.get(p.getName()).actionNames.put(Utils.increment.get(p.getName()), "console");
			Utils.tempButtons.get(p.getName()).actionArgs.put(Utils.increment.get(p.getName()), new String[] {chat});
			Utils.increment.put(p.getName(), Utils.increment.get(p.getName()) + 1);
			p.sendMessage(ChatColor.GREEN + "Console Action added");
			Utils.buttonCost.put(p.getName(), Utils.buttonCost.get(p.getName()) + Settings.consolecost);
			p.sendMessage(nextDisplay);
			Utils.modes.put(p.getName(), "create2");
			return;
		}
		if(Utils.modes.get(p.getName()).equalsIgnoreCase("item1")) {
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
			Utils.tempButtons.get(p.getName()).actionNames.put(Utils.increment.get(p.getName()), "item");
			Utils.tempButtons.get(p.getName()).actionArgs.put(Utils.increment.get(p.getName()), new String[] {mat.toString(), "" + y});
			Utils.increment.put(p.getName(), Utils.increment.get(p.getName()) + 1);
			p.sendMessage(ChatColor.GREEN + "Item Action added");
			Utils.buttonCost.put(p.getName(), Utils.buttonCost.get(p.getName()) + Settings.itemcost);
			p.sendMessage(nextDisplay);
			Utils.modes.put(p.getName(), "create2");
			return;
		}
		if(Utils.modes.get(p.getName()).equalsIgnoreCase("effect1")) {
			String[] split1 = chat.split(" ");
			int i = 0;
			try {
				i = Integer.parseInt(split1[1]);
			} catch(Exception e) {
				p.sendMessage("Please enter a number!");
				return;
			}
			if(utils.formatList(utils.getAllowed(p, Utils.effectnames, ".create")).contains(split1[0])) {
				Utils.tempButtons.get(p.getName()).actionNames.put(Utils.increment.get(p.getName()), "effect");
				Utils.tempButtons.get(p.getName()).actionArgs.put(Utils.increment.get(p.getName()), new String[] {split1[0], i + ""});
				Utils.increment.put(p.getName(), Utils.increment.get(p.getName()) + 1);
				p.sendMessage(ChatColor.GREEN + "Added Effect action");
				Utils.buttonCost.put(p.getName(), Utils.buttonCost.get(p.getName()) + Settings.effectcost);
				p.sendMessage(nextDisplay);
				Utils.modes.put(p.getName(), "create2");
				return;
			} else {
				p.sendMessage(ChatColor.RED + "Invalid effect name or Invalid Permissions(Type effects to see which effects you can use!)");
				return;
			}
		}
		if(Utils.modes.get(p.getName()).equalsIgnoreCase("text1")) {
			Utils.tempButtons.get(p.getName()).actionNames.put(Utils.increment.get(p.getName()), "text");
			Utils.tempButtons.get(p.getName()).actionArgs.put(Utils.increment.get(p.getName()), new String[] {chat});
			Utils.increment.put(p.getName(), Utils.increment.get(p.getName()) + 1);
			p.sendMessage(ChatColor.GREEN + "Text Action added");
			Utils.buttonCost.put(p.getName(), Utils.buttonCost.get(p.getName()) + Settings.textcost);
			p.sendMessage(nextDisplay);
			Utils.modes.put(p.getName(), "create2");
			return;
		}
		if(Utils.modes.get(p.getName()).equalsIgnoreCase("teleport1")) {
			Utils.tempButtons.get(p.getName()).actionNames.put(Utils.increment.get(p.getName()), "teleport");
			Utils.tempButtons.get(p.getName()).actionArgs.put(Utils.increment.get(p.getName()), new String[] {Utils.convertLoc(p.getLocation()), p.getWorld().getName()});
			Utils.increment.put(p.getName(), Utils.increment.get(p.getName()) + 1);
			p.sendMessage(ChatColor.GREEN + "Teleport Action added");
			Utils.buttonCost.put(p.getName(), Utils.buttonCost.get(p.getName()) + Settings.teleportcost);
			p.sendMessage(nextDisplay);
			Utils.modes.put(p.getName(), "create2");
			return;
		}
		if(Utils.modes.get(p.getName()).equalsIgnoreCase("gmessage1")) {
			Utils.tempButtons.get(p.getName()).actionNames.put(Utils.increment.get(p.getName()), "gmessage");
			Utils.tempButtons.get(p.getName()).actionArgs.put(Utils.increment.get(p.getName()), new String[] {chat});
			Utils.increment.put(p.getName(), Utils.increment.get(p.getName()) + 1);
			p.sendMessage(ChatColor.GREEN + "Global message Action added");
			Utils.buttonCost.put(p.getName(), Utils.buttonCost.get(p.getName()) + Settings.gmessagecost);
			p.sendMessage(nextDisplay);
			Utils.modes.put(p.getName(), "create2");
			return;
		}
		if(Utils.modes.get(p.getName()).equalsIgnoreCase("mob1")) {
			if(utils.getAllowed(p, Utils.mobnames, ".create").contains(chat)) {
				Utils.tempButtons.get(p.getName()).actionNames.put(Utils.increment.get(p.getName()), "mob");
				Utils.tempButtons.get(p.getName()).actionArgs.put(Utils.increment.get(p.getName()), new String[] {chat, Utils.convertLoc(p.getLocation())});
				Utils.increment.put(p.getName(), Utils.increment.get(p.getName()) + 1);
				p.sendMessage(ChatColor.GREEN + "Added spawn mob action");
				Utils.buttonCost.put(p.getName(), Utils.buttonCost.get(p.getName()) + Settings.mobcost);
				p.sendMessage(nextDisplay);
				Utils.modes.put(p.getName(), "create2");
				return;
			} else {
				p.sendMessage(ChatColor.RED + "Invalid mob name or Invalid Permissions. Type mobs to see which mobs you can use!");
				return;
			}
		}
	}
}
