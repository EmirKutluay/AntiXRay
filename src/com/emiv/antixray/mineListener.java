package com.emiv.antixray;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class mineListener implements Listener {

	
	Main plugin;
	public mineListener(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerMine(BlockBreakEvent e) {
		Player p = e.getPlayer();
		for (String s : plugin.orePoints.keySet()) {
			Material m = Material.getMaterial(s);
			if (m == e.getBlock().getType()) {
				if (plugin.isPlayerMining.get(p.getName())) { //True
					plugin.miningPoints.put(p.getName(), plugin.miningPoints.get(p.getName()) + plugin.orePoints.get(s));
				} else { //False
					plugin.isPlayerMining.put(p.getName(), true);
					plugin.miningPoints.put(p.getName(), plugin.orePoints.get(s));
					new BukkitRunnable() {
						@Override
						public void run() {
							if (plugin.miningPoints.get(p.getName()) >= plugin.getConfig().getInt("WaitBetween") * 24) {
								for (Player admin : Bukkit.getOnlinePlayers()) {
									if (admin.hasPermission("antixray.admin")) {
										admin.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("PlayerSuspicious").replace("%player%", p.getName())));
									}
								}
							}
							plugin.miningPoints.put(p.getName(), 0);
							plugin.isPlayerMining.put(p.getName(), false);
						}
					}.runTaskLater(Bukkit.getServer().getPluginManager().getPlugin("AntiXRay"), (long) (plugin.getConfig().getInt("WaitBetween") * 60 * 20));
				}
			}
		}
	}
}
