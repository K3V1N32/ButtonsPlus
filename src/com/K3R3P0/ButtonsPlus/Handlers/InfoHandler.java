package com.K3R3P0.ButtonsPlus.Handlers;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.K3R3P0.ButtonsPlus.ButtonsPlus;
import com.K3R3P0.ButtonsPlus.Button.Button;
import com.K3R3P0.ButtonsPlus.Utils.Utils;

public class InfoHandler extends BukkitRunnable{
	ButtonsPlus plugin;
	ScoreboardManager SBMan = Bukkit.getScoreboardManager();
	Utils util = new Utils();
	IOHandler io = new IOHandler();
	
	public InfoHandler(ButtonsPlus theP) {
		plugin = theP;
	}
	
	public void run() {
		for(final String s: Utils.inforemoveFlag) {
			plugin.getServer().getPlayer(s).setScoreboard(SBMan.getNewScoreboard());
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
				public void run() {Utils.inforemoveFlag.remove(s);}
			}, 5L);
		}
		if(Utils.haveInfo.isEmpty()) {
			return;
		}
		//Run through a list of players
		for(Player p: plugin.getServer().getOnlinePlayers()) {
			//If the player can see info then update the list
			if(Utils.haveInfo.contains(p.getName())) {
				Block pb = util.getPlayerBlock(p);
				if(pb != null) {
					if(io.buttonExists(pb)) {
						Button pbutton = io.loadButton(pb.getLocation());
						Scoreboard playerboard;
						playerboard = SBMan.getNewScoreboard();
						Objective obj = playerboard.registerNewObjective("BPInfo", "bpinfo");
						obj.setDisplaySlot(DisplaySlot.SIDEBAR);
						obj.setDisplayName("Owner: " + pbutton.getOwner());
						Score pushes = obj.getScore(Bukkit.getOfflinePlayer("Pushes:"));
						Score cost = obj.getScore(Bukkit.getOfflinePlayer("Cost:"));
						pushes.setScore(pbutton.getPushes());
						if(pbutton.getActionName(0).equalsIgnoreCase("charge")) {
							String bcost = pbutton.getActionArgs(0)[0];
							int ccost = 0;
							try {
								ccost = Integer.parseInt(bcost);
							} catch(Exception e) {
								//if this happens... i will give up...
							}
							cost.setScore(ccost);
						} else {
							cost.setScore(0);
						}
						p.setScoreboard(playerboard);
					} else {
						p.setScoreboard(SBMan.getNewScoreboard());
					}
				}
			} else {
				p.setScoreboard(SBMan.getNewScoreboard());
			}
		}
	}
	
}
