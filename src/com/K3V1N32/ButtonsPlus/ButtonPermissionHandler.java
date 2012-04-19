package com.K3V1N32.ButtonsPlus;

import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import com.nijiko.permissions.PermissionHandler;

public class ButtonPermissionHandler {
	public static PermissionHandler handler;
	public static Plugin PermissionPlugin;
	public static ButtonsPlus plugin;
	public static Logger log = Logger.getLogger("Minecraft");
	
	 public static void initialize(ButtonsPlus instance)
	 {
		plugin = instance;
	    PluginManager pm = plugin.getServer().getPluginManager();
	    Plugin permissions = pm.getPlugin("Permissions");
	    if (permissions != null) {
	      PermissionPlugin = permissions;
	      String version = permissions.getDescription().getVersion().substring(0, 1);
	      log.info("[Ultimate Button] Permissions version " + version + " loaded.");
	    } else {
	      log.info("[Ultimate Button] Couldn't find any permissions plugins! Defaults Enabled");
	    }
	  }
	 
	  public static boolean permission(Player player, String permission, boolean defaultPerm) {
		    return (player.hasPermission(permission) || ((com.nijikokun.bukkit.Permissions.Permissions)PermissionPlugin).getHandler().has(player, permission)) || defaultPerm || player.hasPermission("ubutton.admin");
	  }
}
