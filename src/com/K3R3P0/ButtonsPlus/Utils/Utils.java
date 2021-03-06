package com.K3R3P0.ButtonsPlus.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.K3R3P0.ButtonsPlus.ButtonsPlus;
import com.K3R3P0.ButtonsPlus.Button.Button;
/**
 * A compiled group of utilities that my plugin uses
 * @author K3V1N32
 *
 */
@SuppressWarnings({"unchecked", "unused", "rawtypes"})
public class Utils {
	//HashMaps
	/** [buttonlocation{String}, cooldowntime{int}] **/
	public static HashMap<String, Integer> buttoncooldown = new HashMap();
	/** [playername{String}, playermode{String}] **/
	public static HashMap<String, String> modes = new HashMap();
	/** [playername{String}, Button] **/
	public static HashMap<String, Button> tempButtons = new HashMap();
	/** [playername{String}, location{Location}] **/
	public static HashMap<String, Location> tempLoc = new HashMap();
	/** [playername{String}, TotalActionsOnButton{int}] **/
	public static HashMap<String, Integer> increment = new HashMap();
	/** [playername{String}, TimeWhenCanUseButtonAgain{int}] **/
	public static HashMap<String, Integer> cooldown = new HashMap();
	/** [PlayerName{String}, TempButtonCost{int}] **/
	public static HashMap<String, Integer> buttonCost = new HashMap();
	/** [PlayerName{String}, buttontrue{boolean}] **/
	public static HashMap<String, Boolean> confirmed = new HashMap();
	/** [PlayerName{String}, Money{int}] **/
	public static HashMap<String, Integer> playerOwed = new HashMap();
	/** If a playername is in this list, they will be shown info **/
	public static ArrayList<String> haveInfo = new ArrayList<String>();
	/** If a playername is in this list, they will have thier scoreboard removed **/
	public static ArrayList<String> inforemoveFlag = new ArrayList<String>();
	
	//Variables
	/**
	 * A list of all the actions currently implemented in buttonsplus
	 * in simple 1 word form
	 */
	public static String[] actionlist = {
		"burn",
		"command",
		"console",
		"cooldown",
		"death",
		"effect",
		"gmessage",
		"heal",
		"item",
		"lightning",
		"mob",
		"sound",
		"take",
		"teleport",
		"text"
	};
	
	/** A list of current button types **/
	public static Material[] buttontypes = {
		Material.LEVER,
		Material.STONE_BUTTON,
		Material.STONE_PLATE,
		Material.WOOD_PLATE,
		Material.WOOD_BUTTON
	};
	
	/** A list of current button modes  **/
	public static String[] buttonmodes = {
		"basic",
		"charge",
		"onetimeall",
		"onetimeplayer"
		
	};
	
	/** A static list of effects in buttonsplus **/
	public static String[] effectnames = {
		"blind",
		"confuse",
		"detox",
		"jump",
		"slow",
		"speed"
	};
	
	/** A static list of the mobs in minecraft **/
	public static String[] mobnames = {
		"bat",
		"blaze",
		"cavespider",
		"chicken",
		"cow",
		"creeper",
		"enderdragon",
		"enderman",
		"ghast",
		"giant",
		"lavaslime",
		"mushroomcow",
		"ozelot",
		"pig",
		"pigzombie",
		"sheep",
		"silverfish",
		"skeleton",
		"slime",
		"snowman",
		"spider",
		"squid",
		"villager",
		"villagergolem",
		"witch",
		"wither",
		"wolf",
		"zombie"
	};
	
	//Functions
	/**
	 * Converts a location to a store-able string that can be deciphered later [Does not store world!]
	 * @param oldLocation - The Location Object
	 * @return - A string format of a location
	 */
	public static String convertLoc(Location oldLocation) {
		return ("x" + oldLocation.getBlockX() + 
				"y" + oldLocation.getBlockY() + 
				"z" + oldLocation.getBlockZ()).replace(".", "_").replace("-", "N");
	}
	
	/**
	 * Get the block a player is looking at.
	 * @param p - The player who you want to get the block from
	 * @return - The block the player is looking at
	 */
	public Block getPlayerBlock(Player p) {
		Block b = p.getTargetBlock(null, 200);
		return b;
	}
	
	/**
	 * RAINBOWSSSSSS!
	 * @param message
	 * @return a message that is colorful :P
	 */
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
			String s = m.replace(".r", "");
			return rainbow(s);
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
	
	/**
	 * Converts a string to a Global message String
	 * @param s
	 * @param p
	 * @return
	 */
	public String convertToGM(String s, Player p) {
		String after = s.replace("&p", p.getName());
		return after;
	}
	
	public List<Material> listtolist(Material[] mats) {
		List<Material> mat2 = new ArrayList<Material>();
		for(int i=0;i < mats.length;i++) {
			mat2.add(mats[i]);
		}
		return mat2;
	}
	
	/**
	 * Gets a location from a saveLocation string
	 * @param loc - The ciphered location string [using convertLoc()]
	 * @param world - The world object for this location
	 * @return - A Location Object
	 */
	public Location unconvertLoc(String loc, World world) {
		String[] one = loc.split("x");
		String[] two = one[1].split("y");
		String x = two[0];
		x = x.replace("_", ".").replace("N", "-");
		String[] three = two[1].split("z");
		String y = three[0];
		y = y.replace("_", ".").replace("N", "-");
		String z = three[1];
		z = z.replace("_", ".").replace("N", "-");
		int x1 = Integer.parseInt(x);
		int y1 = Integer.parseInt(y);
		int z1 = Integer.parseInt(z);
		Location oldLoc = new Location(world, x1, y1, z1);
		return oldLoc;
	}
	
	/**
	 * Formats a list so that it can be displayed to a user
	 * @param oldList - The list that you want to format
	 * @return 
	 */
	public String formatList(List<String> oldList) {
		int l = oldList.size();
		String ret = "";
		if(l == 0) {
			ret = "Nothing Here!";
			return ret;
		}
		if(l > 1) {
			for(int i = 0;i < l;i++) {
				if(i == l-1) {
					ret = ret + oldList.get(i) + ".";
				} else {
					ret = ret + oldList.get(i) + ", ";
				}
			}
		} else {
			ret = oldList.get(0) + ".";
		}
		return ret;
	}
	
	/** A list of sounds **/
	public static List<String> getSounds() {
		List<String> perList = new ArrayList<String>();
		perList.add("BOW_FIRE");
		perList.add("CLICK1");
		perList.add("CLICK2");
		perList.add("DOOR_TOGGLE");
		perList.add("EXTINGUISH");
		perList.add("GHAST_SHOOT");
		perList.add("GHAST_SHRIEK");
		perList.add("ZOMBIE_CHEW_IRON_DOOR");
		perList.add("ZOMBIE_CHEW_WOODEN_DOOR");
		perList.add("ZOMBIE_DESTROY_DOOR");
		return perList;
	}
	
	/**
	 * Gets the allowed thing from a list for a player
	 * @param player - The player we are checking perms for
	 * @param list - the list we are checking
	 * @param suffix "", ".push", or ".create"
	 * @return A list of allowed things for a player
	 */
	public List<String> getAllowed(Player player, String[] list, String suffix) {
		List<String> allowed = new ArrayList<String>();
		for(int i=0;i < list.length;i++) {
			if(player.hasPermission("buttonsplus." + list[i] + suffix)) {
				allowed.add(list[i]);
			}
		}
		return allowed;
	}
	
	public List<String> getAllowedMobs(Player player, String suffix) {
		List<String> allowed = new ArrayList<String>();
		for(int i=0;i < mobnames.length;i++) {
			if(player.hasPermission("buttonsplus." + mobnames[i] + suffix) || player.hasPermission("buttonsplus.mob.allmobs" + suffix)) {
				allowed.add(mobnames[i]);
			}
		}
		return allowed;
	}
	
	/**
	 * Gets the allowed thing from a list for a player
	 * @param player - The player we are checking perms for
	 * @param list - the list we are checking
	 * @param suffix "", ".push", or ".create"
	 * @return A list of allowed things for a player
	 */
	public List<Material> getAllowed(Player player, Material[] list, String suffix) {
		List<Material> allowed = new ArrayList<Material>();
		for(int i=0;i < list.length;i++) {
			if(player.hasPermission("buttonsplus." + list[i].toString() + suffix)) {
				allowed.add(list[i]);
			}
		}
		return allowed;
	}
}
