package com.emiv.antixray;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class joinListener implements Listener {

	Main plugin;
	public joinListener(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerMine(PlayerJoinEvent e) {
		if (!plugin.isPlayerMining.containsKey(e.getPlayer().getName())) {
			plugin.isPlayerMining.put(e.getPlayer().getName(), false);
		}
	}
	
}
