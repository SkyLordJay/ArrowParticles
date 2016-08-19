package me.skylordjay_.arrowtrails;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.skylordjay_.arrowtrails.commands.TrailCommand;
import me.skylordjay_.arrowtrails.listeners.ArrowShoot;
import me.skylordjay_.arrowtrails.listeners.PlayerQuit;
import net.minecraft.server.v1_8_R3.EnumParticle;

/**
 * 
 * @author SkyLordJay_
 * @date 8/18/16
 *
 */
public class ArrowTrails extends JavaPlugin {

	private Map<UUID, Trail> players = new HashMap<>();

	private static ArrowTrails arrowTrails;

	@Override
	public void onEnable() {
		arrowTrails = this;
		registerCommands();
		registerListeners();
		new BukkitRunnable() {

			@Override
			public void run() {
				for (Trail t : players.values()) {
					t.tick();
				}
			}
		}.runTaskTimerAsynchronously(this, 0, 1);
	}

	@Override
	public void onDisable() {

	}

	private void registerCommands() {
		getCommand("trail").setExecutor(new TrailCommand());
	}

	Listener[] listeners = { new ArrowShoot(), new PlayerQuit() };

	private void registerListeners() {
		PluginManager pm = Bukkit.getPluginManager();
		for (Listener l : listeners) {
			pm.registerEvents(l, this);
		}
	}

	public void addTrail(Player p, EnumParticle e) {
		players.put(p.getUniqueId(), new Trail(e));
	}

	public Trail getTrail(Player p) {
		return players.get(p.getUniqueId());
	}

	public boolean hasTrail(Player p) {
		return players.containsKey(p.getUniqueId());
	}

	public static ArrowTrails getArrowTrails() {
		return arrowTrails;
	}

	public void removeTrail(Player p) {
		players.remove(p.getUniqueId());
	}

}
