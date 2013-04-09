package com.K3R3P0.ButtonsPlus.Handlers;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.K3R3P0.ButtonsPlus.ButtonsPlus;
import com.K3R3P0.ButtonsPlus.Button.Button;
import com.K3R3P0.ButtonsPlus.Settings.Settings;
import com.K3R3P0.ButtonsPlus.Utils.Utils;

public class ButtonActionHandler{
	ButtonsPlus plugin;
	IOHandler io = new IOHandler();
	Utils utils = new Utils();
	Logger log = Logger.getLogger("Minecraft");
	String econName = "";
	
	public void spawnMob(String name, Location location, Player p) {
		EntityType ct = EntityType.fromName(name);
        if (ct == null) {
        	return;
        }
        try {
        	location.getWorld().spawnEntity(location, ct);
        } catch(Exception e) {
        	log.info("[ButtonsPlus]Well dammit... : " + e.toString());
        }
        p.sendMessage(ChatColor.GREEN + "You Spawned a " + ct.getName());
	}
	
	public ButtonActionHandler(ButtonsPlus plugina) {
		plugin = plugina;
		if(Settings.econmode.equalsIgnoreCase("money")) {
			econName = ButtonsPlus.econ.currencyNamePlural();
		}
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
		plugin.getServer().broadcastMessage(utils.colorFormat(utils.convertToGM(m, p)));
	}
	
	public void text(Player p, String string) {
		p.sendMessage(utils.colorFormat(utils.convertToGM(string, p)));
	}
	
	public boolean charge(Player p, String owner, int amount) {
		if(Settings.econmode.equalsIgnoreCase("money")) {
			if(ButtonsPlus.econ.getBalance(p.getName()) < amount) {
				return false;
			}
			ButtonsPlus.econ.withdrawPlayer(p.getName(), amount);
			ButtonsPlus.econ.depositPlayer(owner, amount);
			return true;
		}
		if(Settings.econmode.equalsIgnoreCase("item")) {
			ItemStack require = new ItemStack(Settings.itemid, amount);
			Material check = require.getType();
			final Player pf = p;
			if(p.getInventory().contains(check)) {
				final int slot = p.getInventory().first(check);
				final ItemStack stack = p.getInventory().getItem(slot);
				if(amount > stack.getAmount()) {
					return false;
				} else {
					int amountnew = stack.getAmount() - amount;
					stack.setAmount(amountnew);
					plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						public void run() {pf.getInventory().setItem(slot, stack);}}, 10L);
					Player[] plist = plugin.getServer().getOnlinePlayers();
					for(int i=0;i < plist.length;i++) {
						final Player pl = plist[i];
						if(pl.getName().equalsIgnoreCase(owner)) {
							plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								public void run() {pl.getInventory().setItem(slot, stack);}}, 10L);
						}
					}
					return true;
				}
			} else {
				return false;
			}
		}
		if(Settings.econmode.equalsIgnoreCase("xp")) {
			if(p.getLevel() >= amount) {
				p.setLevel(p.getLevel() - amount);
				Player[] plist = plugin.getServer().getOnlinePlayers();
				for(int i=0;i < plist.length;i++) {
					Player pl = plist[i];
					if(pl.getName().equalsIgnoreCase(owner)) {
						pl.setLevel(pl.getLevel() + amount);
					}
				}
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public boolean take(Player p, int amount, String what) {
		if(what.equalsIgnoreCase("money")) {
			if(ButtonsPlus.econ.getBalance(p.getName()) < amount) {
				return false;
			}
			ButtonsPlus.econ.withdrawPlayer(p.getName(), amount);
			p.sendMessage(ChatColor.GOLD + "You've lost $" + amount + ".");
			return true;
		}
		if(what.equalsIgnoreCase("xp")) {
			if(p.getLevel() >= amount) {
				p.setLevel(p.getLevel() - amount);
				p.sendMessage(ChatColor.GOLD + "You've lost " + amount + " Experience Levels.");
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public boolean takeItem(Player p, ItemStack item) {
		int amount = item.getAmount();
		final Player pf = p;
		Material check = item.getType();
		if(p.getInventory().contains(check)) {
			final int slot = p.getInventory().first(check);
			final ItemStack stack = p.getInventory().getItem(slot);
			if(amount > stack.getAmount()) {
				return false;
			} else {
				int amountnew = stack.getAmount() - amount;
				stack.setAmount(amountnew);
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {pf.getInventory().setItem(slot, stack);}}, 10L);
				p.sendMessage("You've lost " + amount + " " + item.getType().toString() + "s");
				return true;
			}
		} else {
			return false;
		}
	}
	
	public String doActions(Block b, Player p) {
		String buttonloc = Utils.convertLoc(b.getLocation());
		int cooldownTime = Settings.cooldownTimeInSeconds * 1000;
		Button button = io.loadButton(b.getLocation());
		String owner = button.getOwner();
		Calendar calendar = new GregorianCalendar();
		int newTime = (int)calendar.getTimeInMillis();
		int time = 0;
		int time1 = 0;
		if(p.getName().equalsIgnoreCase(owner)) {
			time = newTime - 100;
			time1 = newTime - 100;
		} else {
			time = Utils.cooldown.get(p.getName());
			if(Utils.buttoncooldown.containsKey(buttonloc)) {
				time1 = Utils.buttoncooldown.get(buttonloc);
			}
		}
		if(newTime >= time || p.hasPermission("buttonsplus.cooldown.bypass")) {
			//go forward
		} else {
			p.sendMessage(ChatColor.GOLD + "Nope, you need to wait " + ((time - newTime)) / 1000 + " seconds more to use a button");
			return "false";
		}
		if(newTime >= time1 || p.hasPermission("buttonsplus.cooldown.bypass")) {
			//go
		} else {
			p.sendMessage(ChatColor.GOLD + "Nope, you need to wait " + (((time1 - newTime)) / 1000) + " seconds more to use this button");
			return "false";
		}
		Utils.cooldown.put(p.getName(), newTime + cooldownTime);
		if(button.getActionName(0).equalsIgnoreCase("charge")) {
			if(!p.hasPermission("buttonsplus.charge.push")) {
				p.sendMessage(ChatColor.GOLD + "You do not have permission to press buttons that charge money!");
				return "false";
			}
			if(Utils.confirmed.get(p.getName())) {
				if(charge(p, owner, Integer.parseInt(button.getActionArgs(0)[0]))) {
					p.sendMessage(ChatColor.GOLD + "You pressed a button for: " + button.getActionArgs(0)[0] + " " + Settings.econmode);
					Utils.confirmed.put(p.getName(), false);
				} else {
					p.sendMessage(ChatColor.RED + "Insufficient Funds.");
					return "false";
				}
			} else {
				final String playername = p.getName();
				if(Settings.econmode.equalsIgnoreCase("money")) {
					p.sendMessage(ChatColor.GOLD + "Press button again to confirm payment of: $" + button.getActionArgs(0)[0] + " " + econName);
				}
				if(Settings.econmode.equalsIgnoreCase("item")) {
					p.sendMessage(ChatColor.GOLD + "Press button again to confirm payment of " + button.getActionArgs(0)[0] + Material.getMaterial(Settings.itemid).toString() + "s");
				}
				if(Settings.econmode.equalsIgnoreCase("xp")) {
					p.sendMessage(ChatColor.GOLD + "Press button again to confirm payment of: " + button.getActionArgs(0)[0] + " levels");
				}
				Utils.confirmed.put(p.getName(), true);
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {Utils.confirmed.put(playername, false);}}, 60L);
				return "false";
			}
		} else if(button.getActionName(0).equalsIgnoreCase("onetimeplayer")) {
			if(button.getrewardedPlayers().contains(p.getName())) {
				p.sendMessage(ChatColor.RED + "You already Pressed this one-time-press button.");
				return "reward";
			} else {
				// good for this round
			}
		}
		for(int i = 1;i <= (button.getActionAmount() - 1);i++) {
			if(utils.getAllowed(p, Utils.actionlist, ".push").contains(button.getActionName(i))){
				if(button.getActionName(i).equalsIgnoreCase("death")) {
					kill(p);
					continue;
				}
				if(button.getActionName(i).equalsIgnoreCase("burn")) {
					burn(p);
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
					p.performCommand(utils.convertToGM(button.getActionArgs(i)[0], p));
					continue;
				}
				if(button.getActionName(i).equalsIgnoreCase("console")) {
					plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), utils.convertToGM(button.getActionArgs(i)[0], p));
					continue;
				}
				if(button.getActionName(i).equalsIgnoreCase("take")) {
					if(button.getActionArgs(i)[0].equalsIgnoreCase("item")) {
						takeItem(p, new ItemStack(Integer.parseInt(button.getActionArgs(i)[1]), Integer.parseInt(button.getActionArgs(i)[2]), (byte) Integer.parseInt(button.getActionArgs(i)[3])));
					} else {
						take(p, Integer.parseInt(button.getActionArgs(i)[1]), button.getActionArgs(i)[0]);
						continue;
					}
				}
				if(button.getActionName(i).equalsIgnoreCase("effect")) {
					if(button.getActionArgs(i)[0].equalsIgnoreCase("blind")) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.parseInt(button.getActionArgs(i)[1]), 1));
						if(Settings.effectMessage) {p.sendMessage(ChatColor.GREEN + "FLASH! A blinding light shines in your eyes!");}
						continue;
					} else if(button.getActionArgs(i)[0].equalsIgnoreCase("confuse")) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, Integer.parseInt(button.getActionArgs(i)[1]), 1));
						if(Settings.effectMessage) {p.sendMessage(ChatColor.GREEN + "BAM! You now feel confused!");}
						continue;
					} else if(button.getActionArgs(i)[0].equalsIgnoreCase("jump")) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.parseInt(button.getActionArgs(i)[1]), 1));
						if(Settings.effectMessage) {p.sendMessage(ChatColor.GREEN + "HERE! Borrow my moon shoes for a while!");}
						continue;
					} else if(button.getActionArgs(i)[0].equalsIgnoreCase("speed")) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.parseInt(button.getActionArgs(i)[1]), 1));
						if(Settings.effectMessage) {p.sendMessage(ChatColor.GREEN + "Adrenaline is pumping! you feel faster than ever!");}
						continue;
					} else if(button.getActionArgs(i)[0].equalsIgnoreCase("slow")) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.parseInt(button.getActionArgs(i)[1]), 1));
						if(Settings.effectMessage) {p.sendMessage(ChatColor.GREEN + "You feel tired, you can no longer move as fast...");}
						continue;
					} else if(button.getActionArgs(i)[0].equalsIgnoreCase("detox")) {
						p.removePotionEffect(PotionEffectType.BLINDNESS);
						p.removePotionEffect(PotionEffectType.CONFUSION);
						p.removePotionEffect(PotionEffectType.JUMP);
						p.removePotionEffect(PotionEffectType.SLOW);
						p.removePotionEffect(PotionEffectType.SPEED);
						if(Settings.effectMessage) {p.sendMessage(ChatColor.GOLD + "Your effects have been cleared.");}
					}
				}
				if(button.getActionName(i).equalsIgnoreCase("sound")) {
					p.getWorld().playEffect(p.getLocation(), Effect.valueOf(button.getActionArgs(i)[0]), 300);
					continue;
				}
				if(button.getActionName(i).equalsIgnoreCase("cooldown")) {
					if(Utils.buttoncooldown.get(buttonloc) != null) {
						Utils.cooldown.remove(buttonloc);
					}
					int ia = (int)calendar.getTimeInMillis() + (Integer.parseInt(button.getActionArgs(i)[0]) * 1000);
					Utils.cooldown.put(buttonloc, ia);
					p.sendMessage(ChatColor.GOLD + "This buttons cooldown is: " + button.getActionArgs(i)[0] + " Seconds from now");
					continue;
				}
				if(button.getActionName(i).equalsIgnoreCase("item")) {
					final Player pf = p;
					final ItemStack item = new ItemStack(Material.getMaterial(Integer.parseInt(button.getActionArgs(i)[0])), Integer.parseInt(button.getActionArgs(i)[1]),(byte) Integer.parseInt(button.getActionArgs(i)[2]));
					plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						public void run() {pf.getInventory().addItem(item);}}, 5L);
					p.sendMessage(ChatColor.GOLD + "You have been given: " + button.getActionArgs(i)[1] + " " + item.getType().toString());
					continue;
				}
				if(button.getActionName(i).equalsIgnoreCase("teleport")) {
					p.teleport(utils.unconvertLoc(button.getActionArgs(i)[0], Bukkit.getServer().getWorld(button.getActionArgs(i)[1])));
					continue;
				}
				if(button.getActionName(i).equalsIgnoreCase("mob")) {
					if(utils.getAllowedMobs(p, ".push").contains(button.getActionArgs(i)[0])) {
						Location loca = utils.unconvertLoc(button.getActionArgs(i)[1], Bukkit.getServer().getWorld(button.getWorld()));
						spawnMob(button.getActionArgs(i)[0], loca, p);
						continue;
					} else {
						p.sendMessage(ChatColor.RED + "Insufficient Permissions");
						continue;
					}
				}
			} else {
				p.sendMessage(ChatColor.RED + "Insufficient Permissions for action");
			}
			
		}
		if(button.getActionName(0).equalsIgnoreCase("onetimeall")) {
			if(io.deleteButton(button.getLoc(), Bukkit.getWorld(button.getWorld()))) {
				p.sendMessage(ChatColor.GOLD + "Congrats! You Got to the button first!");
				return "false";
			}
		}
		return "true";
	}
	
	
	
	
}
