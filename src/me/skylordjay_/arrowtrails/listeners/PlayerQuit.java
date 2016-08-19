package me.skylordjay_.arrowtrails.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.skylordjay_.arrowtrails.ArrowTrails;

/**
 * 
 * @author SkyLordJay_
 * @date 8/18/16
 *
 */
public class PlayerQuit implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (ArrowTrails.getArrowTrails().hasTrail(p)) {
			ArrowTrails.getArrowTrails().removeTrail(p);
		}
	}

}
