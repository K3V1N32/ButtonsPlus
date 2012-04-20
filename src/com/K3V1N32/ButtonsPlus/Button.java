package com.K3V1N32.ButtonsPlus;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Button {
	//YAY FOR BUTTON OBJECT TO MAKE SHIT EASIER!! :P
	ButtonsPlus plugin;
	ButtonConfig config;
	public boolean isCharge = false;
	public boolean getIsCharge() {
		return isCharge;
	}
	public void setIsCharge(boolean ch_arge) {
		isCharge = ch_arge;
	}
	//Location Variable
	//Format: x[number]y[number]z[number] find x, then find z, in-between = x so on, so forth
	public String loc = "";
	public String getLoc() {
		return loc;
	}
	public void setLoc(String newLoc) {
		loc = newLoc;
	}

	//Getting the location is hard
	public Location getLocation() {
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
		Location oldLoc = new Location(Bukkit.getServer().getWorld(world), x1, y1, z1);
		return oldLoc;
	}
	
	//Not sure how I want to save actions here...\
	//This is an example of how the hashmap should work...
	//
	//actionArgs: 0 : <0, ["Zombie", "x100y100z100"]>
	//actionNames: 0 : <0, "SpawnMob">
	//
	//actionArgs: 1 : <1, ["x100y100z100"]>
	//actionNames: 1 : <1, "Teleport">
	//
	//this might be good for organization...
	//
	//multiple actions FTW
	//each button can have as many actions as a user cares to enter :D
	//
	//attempt hashmap action data stuffz
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<Integer, String[]> actionArgs = new HashMap();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<Integer, String> actionNames = new HashMap();
	public String getActionName(int a) {
		return actionNames.get(a);
	}
	public String[] getActionArgs(int a) {
		return actionArgs.get(a);
	}
	public int getActionAmount() {
		return actionNames.size();
	}
	
	//Owner...
	public String owner;
	public String getOwner() {
		return owner;
	}
	public void setOwner(String newOwner) {
		owner = newOwner;
	}
	
	//Pushes...
	public int pushes;
	public int getPushes() {
		return pushes;
	}
	public void setPushes(int newPushes) {
		pushes = newPushes;
	}
	public void addPush() {
		pushes++;
	}
	
	//save/get world that button is in
	public String world = "";
	public String getWorld() {
		return world;
	}
	public World getWorldWorld() {
		return Bukkit.getServer().getWorld(world);
	}
	public void setWorld(String newWorld) {
		world = newWorld;
	}
	
	//initilize a blank button :P
	public Button(ButtonsPlus instance) {
		plugin = instance;
	}
	
	//Create a default Button Using a Location new Button(location, plugin);
	public Button(Location nLoc) {
		world = nLoc.getWorld().getName();
		loc = ButtonsPlus.saveLocation(nLoc);
	}
}
