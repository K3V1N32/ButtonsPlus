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
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;



@SuppressWarnings({"unused", "unchecked", "rawtypes" })
public class ButtonsPlus extends JavaPlugin{
	/** Logger for logging minecraft console stuff **/
	public final Logger logger = Logger.getLogger("Minecraft");
	/** Economy variable from vault**/
	public static Economy econ = null;
	/** Permission variable from vault **/
	public static Permission perms = null;
	/** Chat from vault **/
	public static Chat chat = null;
	
	/** HashMap<buttonloc, cooldown> Stores a button specific cooldown **/
	public static HashMap<String, Integer> buttoncooldown = new HashMap();
	/** HashMap<playername, mode> Stores a players mode **/
	public static HashMap<String, String> modes = new HashMap();
	/** HashMap<playername, Button> Stores a temporary button **/
	public static HashMap<String, Button> tempButtons = new HashMap();
	/** HashMap<playername, location> Stores a temporary location **/
	public static HashMap<String, Location> tempLoc = new HashMap();
	/** HashMap<playername, TotalActionsOnButton> Stores an action increment **/
	public static HashMap<String, Integer> increment = new HashMap();
	/** HashMap<playername, TimeWhenCanUseButtonAgain(Integer)> Stores the time when a player can use buttons again**/
	public static HashMap<String, Integer> cooldown = new HashMap();
	/** HashMap<PlayerName, TempButtonCost> Stores the cost of a button temporarily **/
	public static HashMap<String, Integer> buttonCost = new HashMap();
	/** HashMap<PlayerName, ButtonLoc> Stores whether or not a button transaction is confirmed **/
	public static HashMap<String, String> confirmed = new HashMap();
	/** Time in seconds before button can be pressed again **/
	public static int cooldownTimeInSeconds;
	/** if charging a set price, this is the cost for charge action **/
	public static int chargePrice;
	/** whether or not charge action is set price or multiplier (true = setprice, false = multiplier) **/
	public static boolean charge;
	/** The multiplier for charge action cost **/
	public static int multiplier;
	/** The cost for the command action **/
	public static int commandcost;
	/** The cost for the sound action **/
	public static int soundcost;
	/** The cost for the effect action **/
	public static int effectcost;
	/** The cost for the text action **/
	public static int textcost;
	/** The cost for the item action **/
	public static int itemcost;
	/** The cost for the tutorial action **/
	public static int tutorialcost;
	/** The cost for the death action **/
	public static int deathcost;
	/** The cost for the heal action **/
	public static int healcost;
	/** The cost for the lightning action **/
	public static int lightningcost;
	/** The cost for the teleport action **/
	public static int teleportcost;
	/** The cost for the globalmessage action **/
	public static int globalmessagecost;
	/** The cost for the burn action **/
	public static int burncost;
	
	
	/** Config Declaration **/
	ButtonConfig config;
	
	/** Converts a location to a storable string that can be decrypted later **/
	public static String saveLocation(Location oldLocation) {
		return "x" + oldLocation.getBlockX() + "y" + oldLocation.getBlockY() + "z" + oldLocation.getBlockZ() + "".replace(".", "_").replace("-", "N");
	}
	
	/** Gets a location from a saveLocation string **/
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
	
	/** Called when the plugin is disabled **/
	public void onDisable() {
		logger.info("[ButtonsPlus] has been Disabled");
	}
	
	/** Called when the plugin is enabled **/
	public void onEnable() {
		/** plugin manager and event registration **/
		PluginManager pm = getServer().getPluginManager();
    	pm.registerEvents(new ButtonPListener(this), this);
        pm.registerEvents(new ButtonBListener(this), this);
    	
        /** Vault Initialization **/
        if(!setupEconomy()) {
        	 logger.info(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
             getServer().getPluginManager().disablePlugin(this);
             return;
        }
        if(!setupChat()) {
        	logger.info("[ButtonsPlus] No chat plugin detected, using default");
        }
        setupPermissions();
        /** Setup Config **/
        ButtonConfig config = new ButtonConfig(this);
        if(!config.readConfig()) {
        	logger.info("First Time Setup Complete! Created Config in plugins/buttonsplus/config.yml");
        }
		/** Plugin Enabling Messages **/
		PluginDescriptionFile pdfFile = this.getDescription();
        logger.info("[ButtonsPlus] version " + pdfFile.getVersion() + " is Enabled!" );
	}
	
	/** Vault economy setup **/
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
	
	/** Vault permissions setup **/
	private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
	
	/** Vault Chat Setup **/
	private boolean setupChat() {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }
        return (chat != null);
    }
}
