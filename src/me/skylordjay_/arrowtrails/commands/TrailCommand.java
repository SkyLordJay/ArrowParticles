package me.skylordjay_.arrowtrails.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.skylordjay_.arrowtrails.ArrowTrails;
import me.skylordjay_.arrowtrails.particles.ParticleEffect;

/**
 * 
 * @author SkyLordJay_
 * @date 8/18/16
 *
 */

public class TrailCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("off")) {
					if (ArrowTrails.getArrowTrails().hasTrail(p)) {
						ArrowTrails.getArrowTrails().removeTrail(p);
						p.sendMessage(ChatColor.RED + "Arrow Trail off.");
					} else {
						p.sendMessage(ChatColor.RED + "You don't currently have a trail.");
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("list")) {
					list(p);
					return true;
				}
				try {
					ParticleEffect e = ParticleEffect.valueOf(args[0].toUpperCase());
					ArrowTrails.getArrowTrails().addTrail(p, e);
					p.sendMessage(ChatColor.GREEN + "Arrow Trail set.");
				} catch (Exception e) {
					p.sendMessage(ChatColor.RED + "Invalid Particle: " + args[0].toLowerCase());
					p.sendMessage(ChatColor.GRAY + "/trail list");
				}
			} else {
				list(p);
			}
		} else {
			sender.sendMessage(ChatColor.RED + "/trail is a player command only!");
		}
		return false;
	}
	
	public void list(Player p) {
		String text = "";
		for (ParticleEffect pa : ParticleEffect.values()) {
			if (text.equals("")) {
				text = pa.name();
			} else {
				text += ", " + pa.name();
			}
		}
		text += ", OFF";
		p.sendMessage(ChatColor.GRAY + "/trail <particle>");
		p.sendMessage(ChatColor.GRAY + text);
	}

}
