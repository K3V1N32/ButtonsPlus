package com.K3R3P0.ButtonsPlus;

import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.K3R3P0.ButtonsPlus.Handlers.IOHandler;
import com.K3R3P0.ButtonsPlus.Listeners.BlockListener;
import com.K3R3P0.ButtonsPlus.Listeners.PlayerListener;

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
		logger.info("[ButtonsPlus] has been Disabled");
	}
	
	public void onEnable() {
		/** plugin management **/
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerListener(this), this);
		pm.registerEvents(new BlockListener(this), this);
		/** Vault Init **/
		setupVault();
		/** Config Setup **/
		IOHandler io = new IOHandler();
		if(!io.readConfig()) {
			logger.info("[ButtonsPlus] No config.yml found so a config.yml was created.");
		}
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
        	logger.info("[ButtonsPlus] No Economy Plugin found, disabling...");
        	this.getServer().getPluginManager().disablePlugin(this);
        }
    }
}
