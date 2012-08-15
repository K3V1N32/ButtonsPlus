package com.K3V1N32.ButtonsPlus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.PluginDescriptionFile;

public class ButtonConfig {
	public Configuration buttonConfig;
	public String configDir = "plugins" + File.separator + "ButtonsPlus" + File.separator;
	public List<String> moneyList = new ArrayList<String>();
	public List<String> itemList = new ArrayList<String>();
	public Logger log = Logger.getLogger("Minecraft");
	ButtonsPlus plugin;
	public PluginDescriptionFile pdfFile;

	public ButtonConfig(ButtonsPlus instance) {
		plugin = instance;
		pdfFile = plugin.getDescription();
	}

	public boolean readConfig() {
		buttonConfig = new Configuration(new File(configDir + "config.yml"));
		File finder = new File(configDir + "config.yml");
		if(!finder.exists()) {
			if(buttonConfig.getString("configver") == pdfFile.getVersion()) {
				buttonConfig.set("configver", pdfFile.getVersion());
				buttonConfig.set("cooldownInSeconds", 5);
				buttonConfig.set("charge.setCost", false);
				buttonConfig.set("charge.cost", 10);
				buttonConfig.set("charge.multiplier", 2);
				buttonConfig.set("cost.command", 0);
				buttonConfig.set("cost.sound", 0);
				buttonConfig.set("cost.effect", 0);
				buttonConfig.set("cost.text", 0);
				buttonConfig.set("cost.item", 0);
				buttonConfig.set("cost.tutorial", 0);
				buttonConfig.set("cost.death", 0);
				buttonConfig.set("cost.heal", 0);
				buttonConfig.set("cost.lightning", 0);
				buttonConfig.set("cost.teleport", 0);
				buttonConfig.set("cost.globalmessage", 0);
				buttonConfig.set("cost.burn", 0);
				buttonConfig.save();
				ButtonsPlus.cooldownTimeInSeconds = 5;
				ButtonsPlus.charge = false;
				ButtonsPlus.chargePrice = 0;
				ButtonsPlus.multiplier = 2;
				ButtonsPlus.burncost = 0;
				ButtonsPlus.commandcost = 0;
				ButtonsPlus.soundcost = 0;
				ButtonsPlus.healcost = 0;
				ButtonsPlus.deathcost = 0;
				ButtonsPlus.lightningcost = 0;
				ButtonsPlus.globalmessagecost = 0;
				ButtonsPlus.teleportcost = 0;
				ButtonsPlus.textcost = 0;
				ButtonsPlus.itemcost = 0;
				ButtonsPlus.effectcost = 0;
				ButtonsPlus.tutorialcost = 0;
				return false;
			} else {
				readConfig();
				return true;
			}
		}
		buttonConfig.load();
		if(buttonConfig.get("charge.enabled") != null) {
			log.info("[ButtonsPlus] OLD CONFIG FOUND! rewriting");
			finder.delete();
			readConfig();
			return false;
		}
		buttonConfig.load();
		ButtonsPlus.cooldownTimeInSeconds = buttonConfig.getInt("cooldownInSeconds");
		ButtonsPlus.charge = buttonConfig.getBoolean("charge.setCost");
		ButtonsPlus.chargePrice = buttonConfig.getInt("charge.cost");
		ButtonsPlus.multiplier = buttonConfig.getInt("charge.multiplier");
		ButtonsPlus.burncost = buttonConfig.getInt("cost.burn");
		ButtonsPlus.commandcost = buttonConfig.getInt("cost.command");
		ButtonsPlus.soundcost = buttonConfig.getInt("cost.sound");
		ButtonsPlus.healcost = buttonConfig.getInt("cost.heal");
		ButtonsPlus.deathcost = buttonConfig.getInt("cost.death");
		ButtonsPlus.lightningcost = buttonConfig.getInt("cost.lightning");
		ButtonsPlus.globalmessagecost = buttonConfig.getInt("cost.globalmessage");
		ButtonsPlus.teleportcost = buttonConfig.getInt("cost.teleport");
		ButtonsPlus.textcost = buttonConfig.getInt("cost.text");
		ButtonsPlus.itemcost = buttonConfig.getInt("cost.item");
		ButtonsPlus.effectcost = buttonConfig.getInt("cost.effect");
		ButtonsPlus.tutorialcost = buttonConfig.getInt("cost.tutorial");
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
