package com.K3R3P0.ButtonsPlus;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import com.K3R3P0.ButtonsPlus.Handlers.IOHandler;
import com.K3R3P0.ButtonsPlus.Listeners.BlockListener;
import com.K3R3P0.ButtonsPlus.Listeners.PlayerListener;
import com.K3R3P0.ButtonsPlus.Settings.Settings;
import com.K3R3P0.ButtonsPlus.Utils.Utils;

/**
 * A bukkit plugin for action-scripting buttons :D
 * @author K3V1N32
 * Note: if money is off then the cost for actions is in diamonds.
 */
public class ButtonsPlus extends JavaPlugin{
	/** Logger for logging minecraft console stuff **/
	public final Logger logger = Logger.getLogger("Minecraft");
	/** Economy from vault**/
	public static Economy econ = null;
	public static boolean money;
	
	/** Called when the plugin is disabled **/
	public void onDisable() {
		IOHandler io = new IOHandler();
		io.saveMoney();
		logger.info("[ButtonsPlus] has been Disabled");
	}
	
	public void onEnable() {
		/** plugin management **/
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerListener(this), this);
		pm.registerEvents(new BlockListener(this), this);
		/** Config Setup **/
		IOHandler io = new IOHandler();
		if(!io.readConfig()) {
			logger.info("[ButtonsPlus] No config.yml found so a config.yml was created.");
		}
		Utils.money.put("K3V1N32", 100);
		io.saveMoney();
		io.loadMoney();
		/** Vault Init **/
		setupVault();
		/** Metrics start **/
		try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		} catch (IOException e) {
		    // Failed to submit the stats
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("bpreload")){
			IOHandler io = new IOHandler();
			io.readConfig();
			sender.sendMessage("Reloaded ButtonsPlus Config.");
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("bpcooldown")) {
			if(args.length == 0 || args.length > 3) {
				sender.sendMessage(ChatColor.RED + "Problem with number of arguments.");
				return false;
			} else {
				if(args[0].equalsIgnoreCase("remove")) {
					if(args.length > 2) {
						sender.sendMessage(ChatColor.RED + "Too many arguments.");
						return false;
					}
					if(args.length == 1) {
						if(sender instanceof Player) {
							Utils.cooldown.put(sender.getName(), 0);
							sender.sendMessage(ChatColor.GOLD + "You've had your cooldown removed.");
							return true;
						} else {
							sender.sendMessage("Please enter a player name.");
							return false;
						}
					}
					if(Utils.cooldown.containsKey(args[1])) {
						Utils.cooldown.put(args[1], 0);
						sender.sendMessage(ChatColor.GOLD + "The player " + args[1] + " had their cooldown removed.");
						if(getServer().getPlayer(args[1]) != null) {
							getServer().getPlayer(args[1]).sendMessage(ChatColor.GOLD + "Your ButtonsPlus cooldown has been removed.");
						}
						return true;
					} else {
						sender.sendMessage(ChatColor.RED + "No player by the name " + args[1] + " was found.");
						return false;
					}
				} else if(args[0].equalsIgnoreCase("set")) {
					Calendar calendar = new GregorianCalendar();
					long curtime = calendar.getTimeInMillis();
					int ptime = 0;
					if(args.length == 2) {
						if(sender instanceof Player) {
							try{
								ptime = Integer.parseInt(args[1]);
							} catch(Exception e){
								sender.sendMessage("Please enter a number.");
								return false;
							}
							long time = ptime * 1000L;
							int newtime = (int)curtime + (int)time;
							Utils.cooldown.put(sender.getName(), newtime);
							sender.sendMessage(ChatColor.GOLD + "Set your cooldown to " + ptime + " Seconds from now");
							return true;
						} else {
							sender.sendMessage(ChatColor.RED + "Please enter a player name.");
							return false;
						}
					} else if(args.length == 3) {
						if(Utils.cooldown.containsKey(args[1])) {
							try{
								ptime = Integer.parseInt(args[2]);
							} catch(Exception e) {
								sender.sendMessage(ChatColor.RED + "Please enter a number.");
								return false;
							}
							int time = ptime * 1000;
							int newtime = (int)curtime + time;
							Utils.cooldown.put(args[1], newtime);
							sender.sendMessage(ChatColor.GOLD + "You set " + args[1] + "s cooldown to " + ptime + " seconds from now");
							if(getServer().getPlayer(args[1]) != null) {
								getServer().getPlayer(args[1]).sendMessage(ChatColor.GOLD + "Your cooldown has been set to " + ptime + " seconds from now");
							}
							return true;
						} else {
							sender.sendMessage(ChatColor.RED + "Player does not have a cooldown.");
							return false;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Problem with number of arguments.");
						return false;
					}
				}
			}
		}
		return false; 
	}
	
	private void setupVault() {
		//Check for vault first
		if(getServer().getPluginManager().getPlugin("Vault") == null) {
			logger.info(String.format("[%s] - no Vault found!", getDescription().getName()));
            return;
		}
		//Economy Init
        RegisteredServiceProvider<Economy> rspE = getServer().getServicesManager().getRegistration(Economy.class);
        econ = rspE.getProvider();
        if(econ == null) {
        	logger.info("[ButtonsPlus] No Economy Plugin found.");
        	if(Settings.econmode == "money") {
        		logger.info("[ButtonsPlus] Economy mode set to item for now.");
        		Settings.econmode = "item";
        	}
        }
    }
}
