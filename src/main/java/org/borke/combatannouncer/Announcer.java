package org.borke.combatannouncer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.text.DecimalFormat;

public class Announcer implements Listener {
    private final Main plugin;

    public Announcer(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.getWorld().getName().contains("uhc")) {
                if (event.getDamager() instanceof Player damager) {
                    DecimalFormat df = new DecimalFormat("0.0");
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        if (online.getGameMode() == GameMode.SPECTATOR) {
                            if (online.hasPermission("announce")) {
                                online.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&6Combat&e]&c " + damager.getName() + " &7(&6" + damager.getHealth() + "&c❤&7) &4&l\uD83D\uDDE1 &a" + player.getName() + " &7(" + (player.getHealth() - event.getFinalDamage()) + "&c❤&7)"));
                            }
                        }
                    }
                }
            }
        }
    }
}
