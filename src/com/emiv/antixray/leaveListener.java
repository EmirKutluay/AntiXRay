package com.emiv.antixray;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class leaveListener implements Listener {

	Main plugin;
	public leaveListener(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerMine(PlayerQuitEvent e) {
		if (plugin.isPlayerMining.containsKey(e.getPlayer().getName())) {
			plugin.isPlayerMining.put(e.getPlayer().getName(), null);
		}
	}
	
}
