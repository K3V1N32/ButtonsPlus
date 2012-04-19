package com.K3V1N32.ButtonsPlus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;


public class ButtonActionHandler {
	ButtonsPlus plugin;
	ButtonConfig config;
	
	
	
	public String getFormatList(List<String> oldList) {
		int l = oldList.size();
		String ret = "";
		if(l == 0) {
			ret = "";
			return ret;
		}
		if(l > 1) {
			for(int i = 0;i < l;i++) {
				if(i == (l)) {
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
	
	public String convertToGM(String s, Player p) {
		String after =
			s.replace("&p", p.getName())
			.replace("&m", ButtonsPlus.econ.getBalance(p.getName()) + "");
		return after;
	}
	
	public String rainbow(String message) {
		char[] charMessage = message.toCharArray();
		String finalMessage = "";
		int color = 1;
		for (int i = 0; i < charMessage.length; i++) {
		finalMessage = finalMessage + "�" + Integer.toHexString(color);
		finalMessage = finalMessage + charMessage[i];
		color++;
			if (color >= 16) {
				color = 1;
			}
		}
		return finalMessage;
	}
	
	public String colorFormat(String m) {
		if(m.contains(".r")) {
			m.replace(".r", "");
			return rainbow(m);
		}
		String finale;
		finale =
			m.replace("&aqua", ChatColor.AQUA.toString())
			.replace("&black", ChatColor.BLACK.toString())
			.replace("&blue", ChatColor.BLUE.toString())
			.replace("&darkaqua", ChatColor.DARK_AQUA.toString())
			.replace("&darkblue", ChatColor.DARK_BLUE.toString())
			.replace("&darkgray", ChatColor.DARK_GRAY.toString())
			.replace("&darkgreen", ChatColor.DARK_GREEN.toString())
			.replace("&darkpurple", ChatColor.DARK_PURPLE.toString())
			.replace("&darkred", ChatColor.DARK_RED.toString())
			.replace("&gold", ChatColor.GOLD.toString())
			.replace("&gray", ChatColor.GRAY.toString())
			.replace("&green", ChatColor.GREEN.toString())
			.replace("&lightpurple", ChatColor.LIGHT_PURPLE.toString())
			.replace("&red", ChatColor.RED.toString())
			.replace("&white", ChatColor.WHITE.toString())
			.replace("&yellow", ChatColor.YELLOW.toString())
			.replace("&0", "�0")
			.replace("&1", "�1")
			.replace("&2", "�2")
			.replace("&3", "�3")
			.replace("&4", "�4")
			.replace("&5", "�5")
			.replace("&6", "�6")
			.replace("&7", "�7")
			.replace("&8", "�8")
			.replace("&9", "�9")
			.replace("&a", "�a")
			.replace("&b", "�b")
			.replace("&c", "�c")
			.replace("&d", "�d")
			.replace("&e", "�e")
			.replace("&f", "�f");
		return finale;
	}
	
	public List<String> getPlayerMobsPush(Player player) {
		List<String> perList = new ArrayList<String>();
		if(ButtonsPlus.perms.has(player, "buttonsplus.cow.push") || ButtonsPlus.perms.has(player, "buttonsplus.cow")) {
			perList.add("Cow");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.enderman.push") || ButtonsPlus.perms.has(player, "buttonsplus.enderman")) {
			perList.add("Enderman");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.chicken.push") || ButtonsPlus.perms.has(player, "buttonsplus.chicken")) {
			perList.add("Chicken");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.ghast.push") || ButtonsPlus.perms.has(player, "buttonsplus.ghast")) {
			perList.add("Ghast");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.creeper.push") || ButtonsPlus.perms.has(player, "buttonsplus.creeper")) {
			perList.add("Creeper");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.sheep.push") || ButtonsPlus.perms.has(player, "buttonsplus.sheep")) {
			perList.add("Sheep");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.pigzombie.push") || ButtonsPlus.perms.has(player, "buttonsplus.pigzombie")) {
			perList.add("PigZombie");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.zombie.push") || ButtonsPlus.perms.has(player, "buttonsplus.zombie")) {
			perList.add("Zombie");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.giant.push") || ButtonsPlus.perms.has(player, "buttonsplus.giant")) {
			perList.add("Giant");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.skeleton.push") || ButtonsPlus.perms.has(player, "buttonsplus.skeleton")) {
			perList.add("Skeleton");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.slime.push") || ButtonsPlus.perms.has(player, "buttonsplus.slime")) {
			perList.add("Slime");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.spider.push") || ButtonsPlus.perms.has(player, "buttonsplus.spider")) {
			perList.add("Spider");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.squid.push") || ButtonsPlus.perms.has(player, "buttonsplus.squid")) {
			perList.add("Squid");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.cavespider.push") || ButtonsPlus.perms.has(player, "buttonsplus.cavespider")) {
			perList.add("CaveSpider");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.wolf.push") || ButtonsPlus.perms.has(player, "buttonsplus.wolf")) {
			perList.add("Wolf");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.silverfish.push") || ButtonsPlus.perms.has(player, "buttonsplus.silverfish")) {
			perList.add("Silverfish");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.blaze.push") || ButtonsPlus.perms.has(player, "buttonsplus.blaze")) {
			perList.add("Blaze");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.snowman.push") || ButtonsPlus.perms.has(player, "buttonsplus.snowman")) {
			perList.add("Snowman");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.villager.push") || ButtonsPlus.perms.has(player, "buttonsplus.villager")) {
			perList.add("Villager");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.mushroomcow.push") || ButtonsPlus.perms.has(player, "buttonsplus.mushroomcow")) {
			perList.add("MushroomCow");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.magmacube.push") || ButtonsPlus.perms.has(player, "buttonsplus.magmacube")) {
			perList.add("MagmaCube");
		}
		
		return perList;
	}
	
	public String getPlayerActions(Player player) {
		List<String> perList = new ArrayList<String>();
		if(ButtonsPlus.perms.has(player, "buttonsplus.charge.push") || ButtonsPlus.perms.has(player, "buttonsplus.charge")) {
			perList.add("charge");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.command.push") || ButtonsPlus.perms.has(player, "buttonsplus.command")) {
			perList.add("command");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.tutorial.push") || ButtonsPlus.perms.has(player, "buttonsplus.tutorial")) {
			perList.add("tutorial");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.text.push") || ButtonsPlus.perms.has(player, "buttonsplus.text")) {
			perList.add("text");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.death.push") || ButtonsPlus.perms.has(player, "buttonsplus.death")) {
			perList.add("death");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.teleport.push") || ButtonsPlus.perms.has(player, "buttonsplus.teleport")) {
			perList.add("teleport");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.mobspawn.push") || ButtonsPlus.perms.has(player, "buttonsplus.mobspawn")) {
			perList.add("mob");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.globalmessage.push") || ButtonsPlus.perms.has(player, "buttonsplus.globalmessage")) {
			perList.add("gmessage");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.heal.push") || ButtonsPlus.perms.has(player, "buttonsplus.heal")) {
			perList.add("heal");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.burn.push") || ButtonsPlus.perms.has(player, "buttonsplus.burn")) {
			perList.add("burn");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.lightning.push") || ButtonsPlus.perms.has(player, "buttonsplus.lightning")) {
			perList.add("lightning");
		}
		return getFormatList(perList);
	}
	
	//List Of Actions:
	/*
	 * -Basic = Set price for button push, costs money to create, set in config .-=buttonsplus.charge=-.
	 * -Tutorial = dummy button for tutorial purposes .-=buttonsplus.tutorial=-. ##
	 * -Text = displays text on press .-=buttonsplus.text=-.##
	 * -Death = kills player when they push button .-=buttonsplus.death=-.##
	 * -Command = Sets a command to run when button pressed .-=buttonsplus.command=-.##
	 * -Teleport = Teleports a player to a specified spot .-=buttonsplus.teleport=-.##
	 * -MobSpawning = Spawns a mob on press .-=buttonsplus.mob=-.##
	 * -Global Message = sends a global message .-=buttonsplus.gmessage=-.##
	 * -Heal = heals player on press .-=buttonsplus.heal=-.##
	 * -Burn = burns a player on press .-=buttonsplus.burn=-.##
	 */
	
	public void spawnMob(String name, Location location) {
		EntityType ct = EntityType.fromName(name);
        if (ct == null) {
        	return;
        }
        location.getWorld().spawnCreature(location, ct);
	}
	
	public ButtonActionHandler(ButtonsPlus instance) {
		plugin = instance;
	}
	
	public void heal(Player p) {
		p.setHealth(20);
	}
	
	public void lightning(Player p) {
		p.getWorld().strikeLightning(p.getLocation());
	}
	
	public void burn(Player p) {
		p.setFireTicks(1000);
	}
	
	public void kill(Player p) {
		p.damage(1000);
	}
	
	public void message(String m, Player p) {
		plugin.getServer().broadcastMessage(colorFormat(convertToGM(m, p)));
	}
	
	public void tutorial(Player p, String m) {
		p.sendMessage(m);
	}
	
	public void text(Player p, String string) {
		p.sendMessage(colorFormat(convertToGM(string, p)));
	}
	
	public boolean charge(Player p, String owner, int amount) {
		
		if(ButtonsPlus.econ.getBalance(p.getName()) < amount) {
			return false;
		}
		ButtonsPlus.econ.withdrawPlayer(p.getName(), amount);
		ButtonsPlus.econ.depositPlayer(owner, amount);
		return true;
	}
	
	//command is easy handle
	//teleport is easy handle
	
	
	
	public boolean doActions(Block b, Player p) {
		config = new ButtonConfig(plugin);
		Button button = config.loadButton(b.getLocation());
		String owner = button.getOwner();
		Calendar calendar = new GregorianCalendar();
		int newTime = (int)calendar.getTimeInMillis();
		int time = ButtonsPlus.cooldown.get(p.getName());
		if(newTime >= time) {
			//:3
		} else {
			p.sendMessage("Nope, you need to wait " + ((time - newTime)) / 1000 + " seconds more to use a button");
			return false;
		}
		ButtonsPlus.cooldown.put(p.getName(), newTime + 10000);
		if(button.isCharge) {
			if(charge(p, owner, Integer.parseInt(button.getActionArgs(0)[0]))) {
				p.sendMessage("You have been Charged: $" + button.getActionArgs(0)[0]);
			} else {
				p.sendMessage("You do not have enough to push that button!");
				return false;
			}
		}
		for(int i = 1;i <= (button.getActionAmount() - 1);i++) {
			if(getPlayerActions(p).contains(button.getActionName(i))){
				if(button.getActionName(i).equalsIgnoreCase("death")) {
					kill(p);
					continue;
				}
				if(button.getActionName(i).equalsIgnoreCase("burn")) {
					burn(p);
					continue;
				}
				if(button.getActionName(i).equalsIgnoreCase("tutorial")) {
					tutorial(p, button.getActionArgs(i)[0]);
					continue;
				}
				if(button.getActionName(i).equalsIgnoreCase("gmessage")) {
					message(button.getActionArgs(i)[0], p);
					continue;
				}
				if(button.getActionName(i).equalsIgnoreCase("heal")) {
					heal(p);
					continue;
				}
				if(button.getActionName(i).equalsIgnoreCase("lightning")) {
					lightning(p);
					continue;
				}
				if(button.getActionName(i).equalsIgnoreCase("text")) {
					text(p, button.getActionArgs(i)[0]);
					continue;
				}
				if(button.getActionName(i).equalsIgnoreCase("command")) {
					p.performCommand(button.getActionArgs(i)[0]);
					continue;
				}
				if(button.getActionName(i).equalsIgnoreCase("teleport")) {
					p.teleport(ButtonsPlus.getLocation(button.getActionArgs(i)[0], Bukkit.getServer().getWorld(button.getActionArgs(i)[1])));
					continue;
				}
				if(button.getActionName(i).equalsIgnoreCase("mob") && ButtonsPlus.perms.has(p, "buttonsplus.mob.*")) {
					if(getPlayerMobsPush(p).contains(button.getActionArgs(i)[0])) {
						Location loca = ButtonsPlus.getLocation(button.getActionArgs(i)[1], Bukkit.getServer().getWorld(button.getWorld()));
						spawnMob(button.getActionArgs(i)[0], loca);
						continue;
					} else {
						p.sendMessage(ChatColor.RED + "Insufficient Permissions");
						continue;
					}
				}
			} else {
				p.sendMessage(ChatColor.RED + "Insufficient Permissions");
			}
		}
		return true;
	}
	
	
	
	
}
