package me.skylordjay_.arrowtrails;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;

import me.skylordjay_.arrowtrails.particles.ParticleEffect;

/**
 * 
 * @author SkyLordJay_
 * @date 8/18/16
 *
 */
public class Trail {

	private ParticleEffect e;

	private ArrayList<Arrow> arrows = new ArrayList<>();

	public Trail(ParticleEffect e) {
		this.e = e;
	}

	public void addArrow(Arrow a) {
		arrows.add(a);
	}

	public void tick() {
		for (Arrow a : arrows) {
			if (a.isOnGround() || a.isDead() || a == null) {
				arrows.remove(a);
				return;
			} else {
				particle(a.getLocation());
			}
		}
	}
	
	private void particle(Location loc){
		e.display(0, 0, 0, 0, 15,loc, 256);
	}

}
