
package com.K3V1N32.ButtonsPlus;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.vehicle.VehicleListener;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.iConomy.iConomy;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

@SuppressWarnings({"unused", "unchecked", "rawtypes" })
public class ButtonsPlus extends JavaPlugin{
	//logger
	public final Logger logger = Logger.getLogger("Minecraft");
	
	//Listeners
	public final PlayerListener ButtonPListener = new ButtonPListener(this);
	public final BlockListener ButtonBListener = new ButtonBListener(this);
	
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
	
	//Iconomy
	public iConomy iConomy = null;
	
	//Permissions
	public PermissionHandler permissionHandler;
	
	//onDisable
	public void onDisable() {
		logger.info("[ButtonsPlus] has been Disabled");
	}
	
	//onEnable
	public void onEnable() {
		//plugin manager and event registration
		PluginManager pm = getServer().getPluginManager();
    	pm.registerEvent(Type.PLAYER_INTERACT, ButtonPListener, Priority.Normal, this);
    	pm.registerEvent(Type.PLAYER_CHAT, ButtonPListener, Priority.Normal, this);
    	pm.registerEvent(Type.PLAYER_JOIN, ButtonPListener, Priority.Normal, this);
		pm.registerEvent(Type.BLOCK_BREAK, ButtonBListener, Priority.Normal, this);
		pm.registerEvent(Type.BLOCK_PLACE, ButtonBListener, Priority.Normal, this);
		pm.registerEvent(Type.PLUGIN_ENABLE, new Server(this), Priority.Monitor, this);
        pm.registerEvent(Type.PLUGIN_DISABLE, new Server(this), Priority.Monitor, this);
        		
		//more stuff
		ButtonPermissionHandler.initialize(this);
		
		//Hello
		PluginDescriptionFile pdfFile = this.getDescription();
        logger.info("[ButtonsPlus] version " + pdfFile.getVersion() + " is Enabled!" );
	}
    
}
