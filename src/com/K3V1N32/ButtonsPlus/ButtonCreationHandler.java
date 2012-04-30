package com.K3V1N32.ButtonsPlus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ButtonCreationHandler {
	ButtonsPlus plugin;
	ButtonConfig config;

	String currencyName = ButtonsPlus.econ.currencyNamePlural();
	
	public String getFormatList(List<String> oldList) {
		int l = oldList.size();
		String ret = "";
		if(l > 1) {
			for(int i = 0;i < l;i++) {
				if(i == (l)) {
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
		return getFormatList(perList);
	}
	
	public String getPlayerMobs(Player player) {
		List<String> perList = new ArrayList<String>();
		if(ButtonsPlus.perms.has(player, "buttonsplus.cow.create") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Cow");
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
	
	
	public ButtonCreationHandler(ButtonsPlus instance) {
		plugin = instance;
	}
	
	
	
	public void handleChat(Player p, String chat) {
		String nextDisplay = "Type an action name to continue, type done to complete button setup, or type cancel to stop setup." + ChatColor.GOLD + "Actions: " + ChatColor.DARK_GREEN + getPlayerActions(p);
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
		
		//return to sender if not correct message :3 just a backup :P
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("none")) {
			return;
		}
		
		if(!ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("none")) {
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
				return;
			}
			if(chat.equalsIgnoreCase("mobs")) {
				p.sendMessage(ChatColor.GOLD + "Mobs: " + ChatColor.WHITE + getPlayerMobs(p));
				return;
			}
			if(chat.equalsIgnoreCase("actions")) {
				p.sendMessage(ChatColor.GOLD + "Actions: " + ChatColor.WHITE + getPlayerActions(p));
				return;
			}
		}
		
		//
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("createStart") && ButtonsPlus.perms.has(p, "buttonsplus.charge.create")) {
			if(chat.equalsIgnoreCase("yes")) {
				ButtonsPlus.tempButtons.put(p.getName(), new Button(ButtonsPlus.tempLoc.get(p.getName())));
				ButtonsPlus.tempButtons.get(p.getName()).setIsCharge(true);
				ButtonsPlus.tempButtons.get(p.getName()).setOwner(p.getName());
				ButtonsPlus.increment.put(p.getName(), 0);
				p.sendMessage(ChatColor.GOLD + "How much will your button charge?");
				ButtonsPlus.modes.put(p.getName(), "charge1");
				return;
			}
			if(chat.equalsIgnoreCase("no")) {
				ButtonsPlus.tempButtons.put(p.getName(), new Button(ButtonsPlus.tempLoc.get(p.getName())));
				ButtonsPlus.tempButtons.get(p.getName()).setIsCharge(false);
				ButtonsPlus.tempButtons.get(p.getName()).setOwner(p.getName());
				ButtonsPlus.increment.put(p.getName(), 0);
				ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "charge");
				ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {"0"});
				ButtonsPlus.increment.put(p.getName(), 1);
				p.sendMessage(nextDisplay);
				ButtonsPlus.modes.put(p.getName(), "create2");
				return;
			}
		} else if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("createStart")) {
			ButtonsPlus.tempButtons.put(p.getName(), new Button(ButtonsPlus.tempLoc.get(p.getName())));
			ButtonsPlus.tempButtons.get(p.getName()).setIsCharge(false);
			ButtonsPlus.tempButtons.get(p.getName()).setOwner(p.getName());
			ButtonsPlus.increment.put(p.getName(), 0);
			ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "charge");
			ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {"0"});
			ButtonsPlus.increment.put(p.getName(), 1);
			p.sendMessage(nextDisplay);
			ButtonsPlus.modes.put(p.getName(), "create2");
			return;
		}
		
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
				if(chat.equalsIgnoreCase("text")) {
					p.sendMessage(ChatColor.GOLD + "Enter a line of text");
					ButtonsPlus.modes.put(p.getName(), "text1");
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
				if(chat.equalsIgnoreCase("item")) {
					p.sendMessage("Enter the name or id of the item you want to give the player and the amount seperated by a space E.g. stone 2");
					ButtonsPlus.modes.put(p.getName(), "item1");
				}
				return;
			} else if(chat.equalsIgnoreCase("done")) {
				config.saveButton(ButtonsPlus.tempButtons.get(p.getName()));
				p.sendMessage("Saved Button. Setup complete");
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
				return;
			} else {
				p.sendMessage(ChatColor.RED + "Insuficiant Permissions, or not an action, retry.");
				p.sendMessage(nextDisplay);
				return;
			}
		}
		//start the next round of if() battle X.x
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("command1")) {
			ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "command");
			ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {chat});
			ButtonsPlus.increment.put(p.getName(), ButtonsPlus.increment.get(p.getName()) + 1);
			p.sendMessage(ChatColor.GREEN + "Command Action added");
			p.sendMessage(nextDisplay);
			ButtonsPlus.modes.put(p.getName(), "create2");
			return;
		}
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("console1")) {
			ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "console");
			ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {chat});
			ButtonsPlus.increment.put(p.getName(), ButtonsPlus.increment.get(p.getName()) + 1);
			p.sendMessage(ChatColor.GREEN + "Console Action added");
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
			p.sendMessage(nextDisplay);
			ButtonsPlus.modes.put(p.getName(), "create2");
			return;
		}
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("tutorial1")) {
			ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "tutorial");
			ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {chat});
			ButtonsPlus.increment.put(p.getName(), ButtonsPlus.increment.get(p.getName()) + 1);
			p.sendMessage(ChatColor.GREEN + "Tutorial Action added");
			p.sendMessage(nextDisplay);
			ButtonsPlus.modes.put(p.getName(), "create2");
			return;
		}
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("text1")) {
			ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "text");
			ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {chat});
			ButtonsPlus.increment.put(p.getName(), ButtonsPlus.increment.get(p.getName()) + 1);
			p.sendMessage(ChatColor.GREEN + "Text Action added");
			p.sendMessage(nextDisplay);
			ButtonsPlus.modes.put(p.getName(), "create2");
			return;
		}
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("teleport1")) {
			ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "teleport");
			ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {ButtonsPlus.saveLocation(p.getLocation()), p.getWorld().getName()});
			ButtonsPlus.increment.put(p.getName(), ButtonsPlus.increment.get(p.getName()) + 1);
			p.sendMessage(ChatColor.GREEN + "Teleport Action added");
			p.sendMessage(nextDisplay);
			ButtonsPlus.modes.put(p.getName(), "create2");
			return;
		}
		if(ButtonsPlus.modes.get(p.getName()).equalsIgnoreCase("gmessage1")) {
			ButtonsPlus.tempButtons.get(p.getName()).actionNames.put(ButtonsPlus.increment.get(p.getName()), "gmessage");
			ButtonsPlus.tempButtons.get(p.getName()).actionArgs.put(ButtonsPlus.increment.get(p.getName()), new String[] {chat});
			ButtonsPlus.increment.put(p.getName(), ButtonsPlus.increment.get(p.getName()) + 1);
			p.sendMessage(ChatColor.GREEN + "Global message Action added");
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
