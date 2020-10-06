package com.emiv.antixray;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	public HashMap<String, Boolean> isPlayerMining = new HashMap<String, Boolean>();
	public HashMap<String, Integer> miningPoints = new HashMap<String, Integer>();
	public HashMap<String, Integer> orePoints = new HashMap<String, Integer>();
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		
		Bukkit.getPluginManager().registerEvents(new joinListener(this), this);
		Bukkit.getPluginManager().registerEvents(new leaveListener(this), this);
		Bukkit.getPluginManager().registerEvents(new mineListener(this), this);
		
		setMining();
		setOrePoints();
	}

	private void setMining() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (!isPlayerMining.containsKey(p.getName())) {
				isPlayerMining.put(p.getName(), false);
			}
		}
	}
	
	
	private void setOrePoints() { 
		orePoints.put("COAL_ORE", 1);
		orePoints.put("IRON_ORE", 2);
		orePoints.put("GOLD_ORE", 4);
		orePoints.put("REDSTONE_ORE", 3);
		orePoints.put("DIAMOND_ORE", 6);
		orePoints.put("LAPIS_ORE", 5);
	}
}
