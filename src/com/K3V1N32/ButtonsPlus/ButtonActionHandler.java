package com.K3V1N32.ButtonsPlus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Player;

import com.iConomy.iConomy;
import com.iConomy.system.Account;

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
			.replace("&m", iConomy.getAccount(p.getName()).getHoldings().balance() + "");
		return after;
	}
	
	public String rainbow(String message) {
		char[] charMessage = message.toCharArray();
		String finalMessage = "";
		int color = 1;
		for (int i = 0; i < charMessage.length; i++) {
		finalMessage = finalMessage + "§" + Integer.toHexString(color);
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
			.replace("&0", "§0")
			.replace("&1", "§1")
			.replace("&2", "§2")
			.replace("&3", "§3")
			.replace("&4", "§4")
			.replace("&5", "§5")
			.replace("&6", "§6")
			.replace("&7", "§7")
			.replace("&8", "§8")
			.replace("&9", "§9")
			.replace("&a", "§a")
			.replace("&b", "§b")
			.replace("&c", "§c")
			.replace("&d", "§d")
			.replace("&e", "§e")
			.replace("&f", "§f");
		return finale;
	}
	
	public List<String> getPlayerMobsPush(Player player) {
		List<String> perList = new ArrayList<String>();
		if(ButtonPermissionHandler.permission(player, "buttonsplus.cow.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.cow", player.isOp())) {
			perList.add("Cow");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.enderman.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.enderman", player.isOp())) {
			perList.add("Enderman");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.chicken.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.chicken", player.isOp())) {
			perList.add("Chicken");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.ghast.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.ghast", player.isOp())) {
			perList.add("Ghast");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.creeper.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.creeper", player.isOp())) {
			perList.add("Creeper");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.sheep.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.sheep", player.isOp())) {
			perList.add("Sheep");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.pigzombie.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.pigzombie", player.isOp())) {
			perList.add("PigZombie");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.zombie.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.zombie", player.isOp())) {
			perList.add("Zombie");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.giant.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.giant", player.isOp())) {
			perList.add("Giant");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.skeleton.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.skeleton", player.isOp())) {
			perList.add("Skeleton");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.slime.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.slime", player.isOp())) {
			perList.add("Slime");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.spider.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.spider", player.isOp())) {
			perList.add("Spider");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.squid.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.squid", player.isOp())) {
			perList.add("Squid");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.cavespider.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.cavespider", player.isOp())) {
			perList.add("CaveSpider");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.wolf.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.wolf", player.isOp())) {
			perList.add("Wolf");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.silverfish.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.silverfish", player.isOp())) {
			perList.add("Silverfish");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.blaze.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.blaze", player.isOp())) {
			perList.add("Blaze");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.snowman.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.snowman", player.isOp())) {
			perList.add("Snowman");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.villager.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.villager", player.isOp())) {
			perList.add("Villager");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.mushroomcow.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.mushroomcow", player.isOp())) {
			perList.add("MushroomCow");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.magmacube.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.magmacube", player.isOp())) {
			perList.add("MagmaCube");
		}
		
		return perList;
	}
	
	public String getPlayerActions(Player player) {
		List<String> perList = new ArrayList<String>();
		if(ButtonPermissionHandler.permission(player, "buttonsplus.charge.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.charge", player.isOp())) {
			perList.add("charge");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.command.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.command", player.isOp())) {
			perList.add("command");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.tutorial.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.tutorial", player.isOp())) {
			perList.add("tutorial");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.text.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.text", player.isOp())) {
			perList.add("text");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.death.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.death", player.isOp())) {
			perList.add("death");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.teleport.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.teleport", player.isOp())) {
			perList.add("teleport");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.mobspawn.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.mobspawn", player.isOp())) {
			perList.add("mob");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.globalmessage.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.globalmessage", player.isOp())) {
			perList.add("gmessage");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.heal.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.heal", player.isOp())) {
			perList.add("heal");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.burn.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.burn", player.isOp())) {
			perList.add("burn");
		}
		if(ButtonPermissionHandler.permission(player, "buttonsplus.lightning.push", player.isOp()) || ButtonPermissionHandler.permission(player, "buttonsplus.lightning", player.isOp())) {
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
		CreatureType ct = CreatureType.fromName(name);
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
		Account a = iConomy.getAccount(p.getName());
		Account b = iConomy.getAccount(owner);
		if(a.getHoldings().hasEnough((double)amount)) {
			a.getHoldings().subtract((double)amount);
			b.getHoldings().add((double)amount);
			return true;
		} else {
			return false;
		}
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
				if(button.getActionName(i).equalsIgnoreCase("mob") && ButtonPermissionHandler.permission(p, "buttonsplus.mob.*", p.isOp())) {
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
