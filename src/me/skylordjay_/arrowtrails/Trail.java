package me.skylordjay_.arrowtrails;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

/**
 * 
 * @author SkyLordJay_
 * @date 8/18/16
 *
 */
public class Trail {

	private EnumParticle e;

	private ArrayList<Arrow> arrows = new ArrayList<>();

	public Trail(EnumParticle e) {
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

	private void particle(Location loc) {
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(e, true, (float) loc.getX(),
				(float) loc.getY(), (float) loc.getZ(), 0, 0, 0, 0, 15, null);
		for (Player p : Bukkit.getOnlinePlayers()) {
			((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
		}
	}

}
