package com.K3V1N32.ButtonsPlus;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.*;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;



@SuppressWarnings({"unused", "unchecked", "rawtypes" })
public class ButtonsPlus extends JavaPlugin{
	//logger
	public final Logger logger = Logger.getLogger("Minecraft");
	
	public static Economy econ = null;
	public static Permission perms = null;
	
	//<playername, mode>
	//default == null/"none"
	public static HashMap<String, String> modes = new HashMap();
	//<playername, Button>
	public static HashMap<String, Button> tempButtons = new HashMap();
	//<playername, location>
	public static HashMap<String, Location> tempLoc = new HashMap();
	//<playername, Integer>
	public static HashMap<String, Integer> increment = new HashMap();
	//<playername, TimeWhenCanUseButtonAgain(Integer)>
	public static HashMap<String, Integer> cooldown = new HashMap();
	
	
	//Config
	ButtonConfig config;
	
	//making/saving a location is a piece of cake
	public static String saveLocation(Location oldLocation) {
		return "x" + oldLocation.getBlockX() + "y" + oldLocation.getBlockY() + "z" + oldLocation.getBlockZ() + "".replace(".", "_").replace("-", "N");
	}
	
	//getLocation
	public static Location getLocation(String loc, World world) {
		String[] one = loc.split("x");
		String[] two = one[1].split("y");
		String x = two[0];
		x.replace("_", ".").replace("N", "-");
		String[] three = two[1].split("z");
		String y = three[0];
		y.replace("_", ".").replace("N", "-");
		String z = three[1];
		z.replace("_", ".").replace("N", "-");
		int x1 = Integer.parseInt(x);
		int y1 = Integer.parseInt(y);
		int z1 = Integer.parseInt(z);
		Location oldLoc = new Location(world, x1, y1, z1);
		return oldLoc;
	}
	
	//onDisable
	public void onDisable() {
		logger.info("[ButtonsPlus] has been Disabled");
	}
	
	//onEnable
	public void onEnable() {
		//plugin manager and event registration
		PluginManager pm = getServer().getPluginManager();
    	pm.registerEvents(new ButtonPListener(this), this);
        pm.registerEvents(new ButtonBListener(this), this);
    	
        //Vault Init
        if(!setupEconomy()) {
        	 logger.info(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
             getServer().getPluginManager().disablePlugin(this);
             return;
        }
        setupPermissions();
		//Hello thar :3
		PluginDescriptionFile pdfFile = this.getDescription();
        logger.info("[ButtonsPlus] version " + pdfFile.getVersion() + " is Enabled!" );
	}
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
	
	private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
}
