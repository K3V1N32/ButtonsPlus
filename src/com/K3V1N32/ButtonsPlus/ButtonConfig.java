package com.K3V1N32.ButtonsPlus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class ButtonConfig {
	public Configuration buttonConfig;
	public String configDir = "plugins" + File.separator + "ButtonsPlus" + File.separator;
	public List<String> moneyList = new ArrayList<String>();
	public List<String> itemList = new ArrayList<String>();
	public Logger log = Logger.getLogger("Minecraft");
	ButtonsPlus plugin;
	
	
	public ButtonConfig(ButtonsPlus instance) {
		plugin = instance;
	}
	
	public boolean readConfig() {
		buttonConfig = new Configuration(new File(configDir + "config.yml"));
		File finder = new File(configDir + "config.yml");
		if(!finder.exists()) {
			buttonConfig.set("cooldownInSeconds", 5);
			buttonConfig.save();
			ButtonsPlus.cooldownTimeInSeconds = 5;
			return false;
		}
		buttonConfig.load();
		buttonConfig.getInt("cooldownInSeconds", ButtonsPlus.cooldownTimeInSeconds);
		return true;
	}
	
	public boolean saveButton(Button button) {
		String wName = button.getWorld();
		String saveLoc = button.getLoc();
		buttonConfig = new Configuration(new File(configDir + File.separator + "buttons" + File.separator + wName + File.separator + saveLoc + ".yml"));
		buttonConfig.load();
		buttonConfig.setProperty("owner", button.getOwner());
		buttonConfig.setProperty("pushes", button.getPushes());
		buttonConfig.setProperty("isCharge", button.getIsCharge());
		buttonConfig.setProperty("size", button.getActionAmount());
		for(int i = 0; i < button.getActionAmount(); i++) {
			List<String> a = new ArrayList<String>();
			for(int j = 0;j < button.getActionArgs(i).length;j++) {
				a.add(j, button.getActionArgs(i)[j]);
			}
			buttonConfig.setProperty("action_" + i + ".actionName", button.getActionName(i));
			buttonConfig.setProperty("action_" + i + ".actionArgs", a);
		}
		buttonConfig.save();
		return true;
	}
	
	public Button loadButton(Location loca) {
		String wName = loca.getWorld().getName();
		String saveLoc = ButtonsPlus.saveLocation(loca);
		Button button = new Button(plugin);
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
				String[] a = {"", "", ""};
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
			return button;
		} else {
			return null;
		}
	}
	
	public String saveLocation(Location oldLocation) {
		return "x" + oldLocation.getBlockX() + "y" + oldLocation.getBlockY() + "z" + oldLocation.getBlockZ() + "".replace(".", "_").replace("-", "N");
	}
	
	public boolean buttonExists(Block block) {
		String saveloc = saveLocation(block.getLocation());
		String wName = block.getWorld().getName();
		buttonConfig = new Configuration(new File(configDir + File.separator + "buttons" + File.separator + wName + File.separator + saveloc + ".yml"));
		File finder = new File(configDir + File.separator + "buttons" + File.separator + wName + File.separator + saveloc + ".yml");
		if(finder.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deleteButton(String saveloc, World world) {
		String wName = world.getName();
		buttonConfig = new Configuration(new File(configDir + File.separator + "buttons" + File.separator + wName + File.separator + saveloc + ".yml"));
		File finder = new File(configDir + File.separator + "buttons" + File.separator + wName + File.separator + saveloc + ".yml");
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
