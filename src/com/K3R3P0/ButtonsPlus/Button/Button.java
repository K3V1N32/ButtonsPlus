package com.K3R3P0.ButtonsPlus.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import com.K3R3P0.ButtonsPlus.Utils.Utils;

/** A class for Helping read/write buttons **/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class Button {
	
	/** A boolean that determines if a button charges money from a player **/
	public boolean isCharge = false;
	/** Gets whether or not this button charges money **/
	public boolean getIsCharge() {
		return isCharge;
	}
	
	/** Sets whether or not this button charges money **/
	public void setIsCharge(boolean charge) {
		isCharge = charge;
	}
	
	//Location Variable
	//Format: x[number]y[number]z[number] find x, then find z, in-between = x so on, so forth
	/** The string that defines the location of the button formatted as "x[xPos]y[yPos]z[zPos] The world is loaded from where the player is located **/
	public String loc = "";
	/** Gets the formatted location string from the button **/
	public String getLoc() {
		return loc;
	}
	/** Sets the location string to String newLoc **/
	public void setLoc(String newLoc) {
		loc = newLoc;
	}
	//This is an example of how the hashmap should work...
	//
	//actionArgs: 0 : <0, ["Zombie", "x100y100z100"]>
	//actionNames: 0 : <0, "SpawnMob">
	//
	//actionArgs: 1 : <1, ["x100y100z100"]>
	//actionNames: 1 : <1, "Teleport">
	//
	//attempt hashmap action data stuff
	/** Map<Integer, String[]> of action arguments **/
	public Map<Integer, String[]> actionArgs = new HashMap();
	/** Map<Integer, String> of action names **/
	public Map<Integer, String> actionNames = new HashMap();
	
	/** Get the name of an action **/
	public String getActionName(int a) {
		return actionNames.get(a);
	}
	
	/** Get the actions arguments in a string list **/
	public String[] getActionArgs(int a) {
		return actionArgs.get(a);
	}
	
	/** Get the amount of actions this button has **/
	public int getActionAmount() {
		return actionNames.size();
	}
	
	
	/** The owner of the button **/
	public String owner;
	
	/** Get the owner of this button **/
	public String getOwner() {
		return owner;
	}
	
	/** Set the owner of this button **/
	public void setOwner(String newOwner) {
		owner = newOwner;
	}
	
	/** The list of players already rewarded by this button **/
	public List<String> rewardedPlayers = new ArrayList<String>();
	
	/** Get the list of rewarded players **/
	public List<String> getrewardedPlayers() {
		return rewardedPlayers;
	}
	
	/** Add a player to the list to be saved with the button **/
	public void addPlayer(String p) {
		rewardedPlayers.add(p);
	}
	
	/** Set a whole list of players to be saved with the button **/
	public void setPlayers(List<String> list) {
		rewardedPlayers = list;
	}
	
	/** The amount of pushes this button has had **/
	public int pushes;
	
	/**Get the amount of pushes this button has had **/
	public int getPushes() {
		return pushes;
	}
	
	/** Set the total amount of pushes **/
	public void setPushes(int newPushes) {
		pushes = newPushes;
	}
	
	/** Add a push to a button **/
	public void addPush() {
		pushes++;
	}
	
	/** The world the button is in **/
	public String world = "";
	
	/** Get the name of the world that the button is located in **/
	public String getWorld() {
		return world;
	}
	
	/** Get the actual world object of the world the button is in **/
	public World getWorldWorld() {
		return Bukkit.getServer().getWorld(world);
	}
	
	/** Set the world the button is in **/
	public void setWorld(String newWorld) {
		world = newWorld;
	}
	
	public String getButtonActionsFormatted() {
		int size = getActionAmount();
		String finale = "";
		for(int i = 0; i < size; i++) {
			if(i == (size - 1)) {
				finale = finale + getActionName(i) + ".";
			} else {
				finale = finale + getActionName(i) + ", ";
			}
			
		}
		return finale;
	}
	
	/** Initialize a blank button **/
	public Button() {
		//Blank
	}
	
	/**Create a default Button Using a Location new Button(location)**/
	public Button(Location nLoc) {
		world = nLoc.getWorld().getName();
		loc = Utils.convertLoc(nLoc);
	}
}
