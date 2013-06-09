package com.K3R3P0.ButtonsPlus.Handlers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import com.K3R3P0.ButtonsPlus.Button.Button;
import com.K3R3P0.ButtonsPlus.Config.Configuration;
import com.K3R3P0.ButtonsPlus.Settings.Settings;
import com.K3R3P0.ButtonsPlus.Utils.Utils;

/**
 * Input/Output File Handler for ButtonsPlus
 * @author K3V1N32
 *
 */
public class IOHandler {
	public Configuration buttonConfig;
	public String configDir = "plugins" + File.separator + "ButtonsPlus" + File.separator;
	public List<String> moneyList = new ArrayList<String>();
	public List<String> itemList = new ArrayList<String>();
	public Logger log = Logger.getLogger("Minecraft");

	/**
	 * Read the config file from server, if the file is missing it will be created/read
	 * @return True if config is there, false if config was created
	 */
	public boolean readConfig() {
		buttonConfig = new Configuration(new File(configDir + "config.yml"));
		File finder = new File(configDir + "config.yml");
		if(!finder.exists()) {
			//Setting Defaults in the file
			buttonConfig.set("econmode.mode", "money");
			buttonConfig.set("econmode.itemid", 264);
			buttonConfig.set("metrics.enabled", true);
			buttonConfig.set("cooldownInSeconds", 5);
			buttonConfig.set("effectMessage", true);
			buttonConfig.set("bpinfo.onBYdefault", false);
			buttonConfig.set("charge.multiplier", 2);
			buttonConfig.set("action.command.type", "money");
			buttonConfig.set("action.command.cost", 10);
			buttonConfig.set("action.command.itemid", "none");
			buttonConfig.set("action.cooldown.type", "money");
			buttonConfig.set("action.cooldown.cost", 10);
			buttonConfig.set("action.cooldown.itemid", "none");
			buttonConfig.set("action.sound.type", "money");
			buttonConfig.set("action.sound.cost", 10);
			buttonConfig.set("action.sound.itemid", "none");
			buttonConfig.set("action.effect.type", "money");
			buttonConfig.set("action.effect.cost", 10);
			buttonConfig.set("action.effect.itemid", "none");
			buttonConfig.set("action.text.type", "money");
			buttonConfig.set("action.text.cost", 10);
			buttonConfig.set("action.text.itemid", "none");
			buttonConfig.set("action.item.type", "money");
			buttonConfig.set("action.item.cost", 10);
			buttonConfig.set("action.item.itemid", "none");
			buttonConfig.set("action.death.type", "money");
			buttonConfig.set("action.death.cost", 10);
			buttonConfig.set("action.death.itemid", "none");
			buttonConfig.set("action.heal.type", "money");
			buttonConfig.set("action.heal.cost", 10);
			buttonConfig.set("action.heal.itemid", "none");
			buttonConfig.set("action.lightning.type", "money");
			buttonConfig.set("action.lightning.cost", 10);
			buttonConfig.set("action.lightning.itemid", "none");
			buttonConfig.set("action.teleport.type", "money");
			buttonConfig.set("action.teleport.cost", 10);
			buttonConfig.set("action.teleport.itemid", "none");
			buttonConfig.set("action.gmessage.type", "money");
			buttonConfig.set("action.gmessage.cost", 10);
			buttonConfig.set("action.gmessage.itemid", "none");
			buttonConfig.set("action.burn.type", "money");
			buttonConfig.set("action.burn.cost", 10);
			buttonConfig.set("action.burn.itemid", "none");
			buttonConfig.set("action.console.type", "money");
			buttonConfig.set("action.console.cost", 10);
			buttonConfig.set("action.console.itemid", "none");
			buttonConfig.set("action.mob.type", "money");
			buttonConfig.set("action.mob.cost", 10);
			buttonConfig.set("action.mob.itemid", "none");
			buttonConfig.set("action.take.type", "money");
			buttonConfig.set("action.take.cost", 10);
			buttonConfig.set("action.take.itemid", "none");
			buttonConfig.save();
			readConfig();
			return false;
		}
		buttonConfig.load();
		String tmode = buttonConfig.getString("econmode.mode");
		if(tmode.equalsIgnoreCase("money") || tmode.equalsIgnoreCase("item") || tmode.equalsIgnoreCase("xp")) {
			Settings.econmode = tmode;
		} else { Settings.econmode = "xp"; log.info("[ButtonsPlus] Defaulting econmode to xp, please set a mode in the config.yml");}		
		Settings.itemid = buttonConfig.getInt("econmode.itemid");
		Settings.cooldownTimeInSeconds = buttonConfig.getInt("cooldownInSeconds");
		Settings.effectMessage = buttonConfig.getBoolean("effectMessage");
		Settings.chargeMultiplier = buttonConfig.getInt("charge.multiplier");
		Settings.burncost = buttonConfig.getInt("action.burn.cost");
		Settings.commandcost = buttonConfig.getInt("action.command.cost");
		Settings.cooldowncost = buttonConfig.getInt("action.cooldown.cost");
		Settings.soundcost = buttonConfig.getInt("action.sound.cost");
		Settings.healcost = buttonConfig.getInt("action.heal.cost");
		Settings.deathcost = buttonConfig.getInt("action.death.cost");
		Settings.lightningcost = buttonConfig.getInt("action.lightning.cost");
		Settings.gmessagecost = buttonConfig.getInt("action.gmessage.cost");
		Settings.teleportcost = buttonConfig.getInt("action.teleport.cost");
		Settings.textcost = buttonConfig.getInt("action.text.cost");
		Settings.itemcost = buttonConfig.getInt("action.item.cost");
		Settings.effectcost = buttonConfig.getInt("action.effect.cost");
		Settings.consolecost = buttonConfig.getInt("action.console.cost");
		Settings.mobcost = buttonConfig.getInt("action.mob.cost");
		Settings.takecost = buttonConfig.getInt("aciton.take.cost");
		Settings.metricson = buttonConfig.getBoolean("metrics.enabled");
		return true;
	}
	
	public void saveEcon() {
		buttonConfig = new Configuration(new File(configDir + "config.yml"));
		buttonConfig.load();
		buttonConfig.set("econmode.mode", Settings.econmode);
		buttonConfig.save();
	}
	
	public void saveMoney() {
		HashMap<String, Integer> hash = Utils.playerOwed;
		String section = "money";
		buttonConfig = new Configuration(new File(configDir + File.separator + "money.yml"));
		for(Entry<String, Integer> hash1: hash.entrySet()) {
			buttonConfig.set(section + "." + hash1.getKey(), hash1.getValue());
		}
		buttonConfig.save();
	}
	
	public void loadMoney() {
		String section = "money";
		buttonConfig = new Configuration(new File(configDir + File.separator + "money.yml"));
		buttonConfig.load();
		Utils.playerOwed.clear();
		try {
			for(String key: buttonConfig.getConfigurationSection(section).getKeys(false)) {
				int value = buttonConfig.getInt(section + "." + key);
				Utils.playerOwed.put(key, value);
			}
		} catch(Exception e) {}
	}
	
	/**
	 * Saves a button(Button.class) to a file on the server
	 * @param button - The button that is being saved to file
	 * @return whether the button was saved succesfully or not
	 */
	public boolean saveButton(Button button) {
		String wName = button.getWorld();
		String saveLoc = button.getLoc();
		buttonConfig = new Configuration(new File(configDir + File.separator + "buttons" + File.separator + wName + File.separator + saveLoc + ".yml"));
		buttonConfig.load();
		buttonConfig.setProperty("owner", button.getOwner());
		buttonConfig.setProperty("pushes", button.getPushes());
		buttonConfig.setProperty("isCharge", button.getIsCharge());
		buttonConfig.setProperty("size", button.getActionAmount());
		buttonConfig.setProperty("rSize", button.getrewardedPlayers().size());
		for(int i = 0; i < button.getActionAmount(); i++) {
			List<String> a = new ArrayList<String>();
			for(int j = 0;j < button.getActionArgs(i).length;j++) {
				a.add(j, button.getActionArgs(i)[j]);
			}
			buttonConfig.setProperty("action_" + i + ".actionName", button.getActionName(i));
			buttonConfig.setProperty("action_" + i + ".actionArgs", a);
		}
		for(int j = 0; j < button.getrewardedPlayers().size(); j++) {
			buttonConfig.setProperty("player_" + j , button.getrewardedPlayers().get(j));
		}
		buttonConfig.save();
		return true;
	}
	
	/**
	 * 
	 * @param loca
	 * @return
	 */
	public Button loadButton(Location loca) {
		String wName = loca.getWorld().getName();
		String saveLoc = Utils.convertLoc(loca);
		Button button = new Button();
		buttonConfig = new Configuration(new File(configDir + File.separator + "buttons" + File.separator + wName + File.separator + saveLoc + ".yml"));
		File finder = new File(configDir + File.separator + "buttons" + File.separator + wName + File.separator + saveLoc + ".yml");
		if(finder.exists()) {
			buttonConfig.load();
			button.setOwner(buttonConfig.getString("owner"));
			button.setWorld(wName);
			button.setLoc(saveLoc);
			button.setPushes(buttonConfig.getInt("pushes"));
			button.setIsCharge(buttonConfig.getBoolean("isCharge"));
			//Example action config FlatFile
			//size = 2
			//action_0:
			//  actionArgs:  //0
			//  -'0'  //0
			//  actionName: charge  //0
			//action_1:
			//  actionArgs:  //1
			//  -'HelloWorld'  //0
			//  actionName: text  //1
			for(int i = 0;i < buttonConfig.getInt("size");i++) {
				String[] a = {"", "", "", ""};
				for(int j = 0;j < buttonConfig.getStringList("action_" + i + ".actionArgs").size();j++) {
					try {
						a[j] = buttonConfig.getStringList("action_" + i + ".actionArgs").get(j);
					} catch (Exception e) {
						log.info(e.toString());
					}
				}
				button.actionArgs.put(i, a);
				button.actionNames.put(i, buttonConfig.getString("action_" + i + ".actionName"));
			}
			List<String> b = new ArrayList<String>();
			for(int j = 0;j < buttonConfig.getInt("rSize");j++) {
				b.add(buttonConfig.getString("player_" + j));
			}
			button.setPlayers(b);
			return button;
		} else {
			return null;
		}
	}
	
	/**
	 * Check if a button file exists
	 * @param block - the block that we are checking
	 * @return true if a button exists there
	 */
	public boolean buttonExists(Block block) {
		String saveloc = Utils.convertLoc(block.getLocation());
		String wName = block.getWorld().getName();
		buttonConfig = new Configuration(new File(configDir + File.separator + "buttons" + File.separator + wName + File.separator + saveloc + ".yml"));
		File finder = new File(configDir + File.separator + "buttons" + File.separator + wName + File.separator + saveloc + ".yml");
		if(finder.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Delete button from file
	 * @param saveloc - The string version of the button location
	 * @param world - The world that the button existed in
	 * @return true if world existed
	 */
	public boolean deleteButton(String saveloc, World world) {
		String wName = world.getName();
		final File finder = new File(configDir + File.separator + "buttons" + File.separator + wName + File.separator + saveloc + ".yml");
		if(finder.exists()) {
			finder.delete();
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Delete button from file
	 * @param button - The button that will be deleted from file
	 * @return true if file existed
	 */
	public boolean deleteButton(Button button) {
		String wName = button.getWorld();
		String saveloc = button.getLoc();
		final File finder = new File(configDir + File.separator + "buttons" + File.separator + wName + File.separator + saveloc + ".yml");
		if(finder.exists()) {
			finder.delete();
			return true;
		} else {
			return false;
		}
	}

	public void resetButtons() {
		File finder = new File(configDir + File.separator + "buttons");
		finder.delete();
	}
}
