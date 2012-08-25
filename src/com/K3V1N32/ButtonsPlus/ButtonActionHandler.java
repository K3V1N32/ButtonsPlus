package com.K3V1N32.ButtonsPlus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
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


public class ButtonActionHandler{
	ButtonsPlus plugin;
	ButtonConfig config;
	Logger log = Logger.getLogger("Minecraft");
	String econName = ButtonsPlus.econ.currencyNamePlural();
	
	
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
			.replace("&bold", ChatColor.BOLD.toString())
			.replace("&under", ChatColor.UNDERLINE.toString())
			.replace("&strike", ChatColor.STRIKETHROUGH.toString())
			.replace("&italic", ChatColor.ITALIC.toString())
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
			.replace("&f", "§f")
			.replace("&z", "§k");
		return finale;
	}
	
	public List<String> getPlayerMobsPush(Player player) {
		List<String> perList = new ArrayList<String>();
		if(ButtonsPlus.perms.has(player, "buttonsplus.cow.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Cow");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.pig.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Pig");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.enderman.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Enderman");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.chicken.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Chicken");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.ghast.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Ghast");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.creeper.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Creeper");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.sheep.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Sheep");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.pigzombie.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("PigZombie");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.zombie.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Zombie");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.giant.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")){
			perList.add("Giant");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.skeleton.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Skeleton");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.slime.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Slime");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.spider.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Spider");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.squid.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Squid");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.cavespider.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("CaveSpider");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.wolf.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Wolf");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.silverfish.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Silverfish");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.blaze.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Blaze");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.snowman.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Snowman");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.villager.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Villager");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.mushroomcow.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("MushroomCow");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.magmacube.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("MagmaCube");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.ocelot.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("Ocelot");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.irongolem.push") || ButtonsPlus.perms.has(player, "buttonsplus.allmobs")) {
			perList.add("IronGolem");
		}		
		return perList;
	}
	
	public String getPlayerActions(Player player) {
		List<String> perList = new ArrayList<String>();
		if(ButtonsPlus.perms.has(player, "buttonsplus.charge.push")) {
			perList.add("charge");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.command.push")) {
			perList.add("command");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.tutorial.push")) {
			perList.add("tutorial");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.text.push")) {
			perList.add("text");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.death.push")) {
			perList.add("death");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.teleport.push")) {
			perList.add("teleport");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.mob.push")) {
			perList.add("mob");
		}		
		if(ButtonsPlus.perms.has(player, "buttonsplus.globalmessage.push")) {
			perList.add("gmessage");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.heal.push")) {
			perList.add("heal");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.burn.push")) {
			perList.add("burn");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.lightning.push")) {
			perList.add("lightning");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.console.push")) {
			perList.add("console");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.item.push")) {
			perList.add("item");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.effect.push")) {
			perList.add("effect");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.sound.push")) {
			perList.add("sound");
		}
		if(ButtonsPlus.perms.has(player, "buttonsplus.cooldowna.push")) {
			perList.add("cooldown");
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
        p.sendMessage(ChatColor.GREEN + "You Spawned a: " + ct.getName());
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
	
	
	public String doActions(Block b, Player p) {
		config = new ButtonConfig(plugin);
		String buttonloc = ButtonsPlus.saveLocation(b.getLocation());
		int cooldownTime = ButtonsPlus.cooldownTimeInSeconds * 1000;
		Button button = config.loadButton(b.getLocation());
		String owner = button.getOwner();
		Calendar calendar = new GregorianCalendar();
		int newTime = (int)calendar.getTimeInMillis();
		int time = 0;
		int time1 = 0;
		if(Bukkit.getPlayer(owner).equals(p)) {
			time = newTime - 100;
			time1 = newTime - 100;
		} else {
			time = ButtonsPlus.cooldown.get(p.getName());
			if(ButtonsPlus.buttoncooldown.containsKey(buttonloc)) {
				time1 = ButtonsPlus.buttoncooldown.get(buttonloc);
			}
		}		
		if(newTime >= time || ButtonsPlus.perms.has(p, "buttonsplus.cooldown.bypass")) {
			//go forward
		} else {
			p.sendMessage("Nope, you need to wait " + ((time - newTime)) / 1000 + " seconds more to use a button");
			return "false";
		}
		if(newTime >= time1 || ButtonsPlus.perms.has(p, "buttonsplus.cooldown.bypass")) {
			//go
		} else {
			p.sendMessage("Nope, you need to wait " + ((time1 - newTime)) / 1000 + " seconds more to use this button");
			return "false";
		}
		ButtonsPlus.cooldown.put(p.getName(), newTime + cooldownTime);
		if(button.getActionName(0).equalsIgnoreCase("charge")) {
			if(!ButtonsPlus.perms.has(p, "buttonsplus.charge.push")) {
				p.sendMessage("You do not have permission to press buttons that charge money!");
				return "false";
			}
			if(ButtonsPlus.confirmed.get(p.getName()) != null) {
				if(ButtonsPlus.confirmed.get(p.getName()).equalsIgnoreCase(button.getLoc())) {
					p.sendMessage("You just pressed a button for: $" + button.getActionArgs(0)[0] + " " + econName);
					ButtonsPlus.confirmed.remove(p.getName());
				} else {
					if(charge(p, owner, Integer.parseInt(button.getActionArgs(0)[0]))) {
						final String playername = p.getName();
						p.sendMessage("Press button again to confirm payment of: $" + button.getActionArgs(0)[0] + " " + econName);
						ButtonsPlus.confirmed.put(p.getName(), "false");
						plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							 public void run() {ButtonsPlus.confirmed.remove(playername);}}, 600L);
					} else {
						p.sendMessage("You do not have enough money to push that button!");
						return "false";
					}
				}
			}
		} else if(button.getActionName(0).equalsIgnoreCase("rewardall")) {
			if(button.getrewardedPlayers().contains(p.getName())) {
				p.sendMessage(ChatColor.RED + "You already Pressed this reward button.");
				return "reward";
			} else {
				// good for this round
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
					p.performCommand(convertToGM(button.getActionArgs(i)[0], p));
					continue;
				}
				if(button.getActionName(i).equalsIgnoreCase("console")) {
					plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), convertToGM(button.getActionArgs(i)[0], p));
					continue;
				}
				if(button.getActionName(i).equalsIgnoreCase("effect")) {
					if(button.getActionArgs(i)[0].equalsIgnoreCase("blind")) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.parseInt(button.getActionArgs(i)[1]), 1));
						p.sendMessage(ChatColor.GREEN + "FLASH, a blinding light shines in your eyes!");
						continue;
					} else if(button.getActionArgs(i)[0].equalsIgnoreCase("confuse")) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, Integer.parseInt(button.getActionArgs(i)[1]), 1));
						p.sendMessage(ChatColor.GREEN + "BAM, you now feel confused!");
						continue;
					} else if(button.getActionArgs(i)[0].equalsIgnoreCase("jump")) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.parseInt(button.getActionArgs(i)[1]), 1));
						p.sendMessage(ChatColor.GREEN + "Here, borrow my moon shoes for a while!");
						continue;
					} else if(button.getActionArgs(i)[0].equalsIgnoreCase("speed")) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.parseInt(button.getActionArgs(i)[1]), 1));
						p.sendMessage(ChatColor.GREEN + "Adrenaline is pumping! you feel faster than ever!");
						continue;
					} else if(button.getActionArgs(i)[0].equalsIgnoreCase("slow")) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.parseInt(button.getActionArgs(i)[1]), 1));
						p.sendMessage(ChatColor.GREEN + "You feel tired, you can no longer move as fast...");
						continue;
					} else if(button.getActionArgs(i)[0].equalsIgnoreCase("detox")) {
						p.removePotionEffect(PotionEffectType.BLINDNESS);
						p.removePotionEffect(PotionEffectType.CONFUSION);
						p.removePotionEffect(PotionEffectType.JUMP);
						p.removePotionEffect(PotionEffectType.SLOW);
						p.removePotionEffect(PotionEffectType.SPEED);
						p.sendMessage(ChatColor.RED + "Your effects have been cleared.");
					}
				}
				if(button.getActionName(i).equalsIgnoreCase("sound")) {
					p.getWorld().playEffect(p.getLocation(), Effect.valueOf(button.getActionArgs(i)[0]), 300);
					continue;
				}
				if(button.getActionName(i).equalsIgnoreCase("cooldown")) {
					if(ButtonsPlus.buttoncooldown.get(buttonloc) != null) {
						ButtonsPlus.cooldown.remove(buttonloc);
					}
					int ia = (int)calendar.getTimeInMillis() + (Integer.parseInt(button.getActionArgs(i)[0]) * 1000);
					ButtonsPlus.cooldown.put(buttonloc, ia);
					p.sendMessage("This buttons cooldown is: " + button.getActionArgs(i)[0] + " Seconds from now");
					continue;
				}
				if(button.getActionName(i).equalsIgnoreCase("item")) {
					ItemStack item = new ItemStack(Material.getMaterial(button.getActionArgs(i)[0]), Integer.parseInt(button.getActionArgs(i)[1]));
					p.getInventory().addItem(item);
					p.sendMessage("You have been given: " + button.getActionArgs(i)[1] + " " + button.getActionArgs(i)[0]);
					continue;
				}
				if(button.getActionName(i).equalsIgnoreCase("teleport")) {
					p.teleport(ButtonsPlus.getLocation(button.getActionArgs(i)[0], Bukkit.getServer().getWorld(button.getActionArgs(i)[1])));
					continue;
				}
				if(button.getActionName(i).equalsIgnoreCase("mob")) {
					if(getPlayerMobsPush(p).contains(button.getActionArgs(i)[0])) {
						Location loca = ButtonsPlus.getLocation(button.getActionArgs(i)[1], Bukkit.getServer().getWorld(button.getWorld()));
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
		if(button.getActionName(0).equalsIgnoreCase("rewardone")) {
			ButtonConfig config = new ButtonConfig(plugin);
			if(config.deleteButton(button.getLoc(), Bukkit.getWorld(button.getWorld()))) {
				p.sendMessage("Congrats! You Got to the button first!");
				return "false";
			}			
		}
		return "true";
	}
	
	
	
	
}
