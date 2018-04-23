package me.haffel.regeln;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener
{
	public static HashMap<Player, Location> dontMove = new HashMap<>();

	@EventHandler
	public void joinEvent(PlayerJoinEvent event)
	{
		Player p = event.getPlayer();

		if (getConfig().isSet("Accepted"))
		{
			if (!getConfig().getConfigurationSection("Accepted").contains(event.getPlayer().getUniqueId().toString()))
			{
				if (!getConfig().getBoolean("MoveBeforeAccepted"))
				{
					dontMove.put(p, p.getLocation());
				}
				
				Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable()
				{
					@Override
					public void run()
					{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Rules")).replace("\\n", "\n"));
					}
				}, 20L);
			}
		} else
		{
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("SomethingWentWrong")).replace("\\n", "\n"));
			dontMove.put(p, p.getLocation());
		}
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event)
	{
		Player p = event.getPlayer();

		if (dontMove.containsKey(p))
		{
			if (dontMove.get(p).getBlockX() != p.getLocation().getBlockX()
					|| dontMove.get(p).getBlockZ() != p.getLocation().getBlockZ())
			{
				p.teleport(new Location(p.getWorld(), dontMove.get(p).getBlockX(), p.getLocation().getBlockY(),
						dontMove.get(p).getBlockZ(), p.getLocation().getYaw(), p.getLocation().getPitch()));
				p.sendMessage(
						ChatColor.translateAlternateColorCodes('&', getConfig().getString("RulesNotAcceptedMsg")).replace("\\n", "\n"));
			}
		}
	}

	@EventHandler
	public void quitGameEvent(PlayerQuitEvent event)
	{
		Player p = event.getPlayer();
		dontMove.remove(p);
	}

	public static FileConfiguration getConfig()
	{
		return Main.getInstance().getConfig();
	}

	public static void saveConfig()
	{
		Main.getInstance().saveConfig();
	}
}
