package me.skylordjay_.arrowtrails.listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import me.skylordjay_.arrowtrails.ArrowTrails;

/**
 * 
 * @author SkyLordJay_
 * @date 8/18/16
 *
 */
public class ArrowShoot implements Listener {

	@EventHandler
	public void onPlayerShootArrow(ProjectileLaunchEvent e) {
		if (e.getEntity().getShooter() != null) {
			if (e.getEntity().getShooter() instanceof Player) {
				if (e.getEntity() instanceof Arrow) {
					Player p = (Player) e.getEntity().getShooter();
					if (ArrowTrails.getArrowTrails().hasTrail(p)) {
						ArrowTrails.getArrowTrails().getTrail(p).addArrow((Arrow) e.getEntity());
					}
				}
			}
		}
	}

}
