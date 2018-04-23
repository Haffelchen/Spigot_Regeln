package me.haffel.regeln;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.md_5.bungee.api.ChatColor;

public class Commands implements Listener, CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{

		if (sender instanceof ConsoleCommandSender)
		{
			sender.sendMessage(getConfig().getString("OnlyPlayers").replace("\\n", "\n"));

		} else if (args.length == 0)
		{
			Player p = (Player) sender;
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Help")).replace("\\n", "\n"));

		} else if (args.length >= 1)
		{
			Player p = (Player) sender;
			if (args[0].equalsIgnoreCase("help"))
			{
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Help")).replace("\\n", "\n"));

			} else if (args[0].equalsIgnoreCase("accept"))
			{
				if (getConfig().isSet("Accepted"))
				{
					if (getConfig().getConfigurationSection("Accepted").contains(p.getUniqueId().toString()))
					{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',
								getConfig().getString("RulesAlreadyAcceptedMsg")).replace("\\n", "\n"));

					} else
					{
						p.sendMessage(
								ChatColor.translateAlternateColorCodes('&', getConfig().getString("RulesAcceptedMsg")).replace("\\n", "\n"));
						Events.dontMove.remove(p);
						getConfig().getConfigurationSection("Accepted").createSection(p.getUniqueId().toString());
						getConfig().getConfigurationSection("Accepted")
								.getConfigurationSection(p.getUniqueId().toString()).set("Name", p.getName());
						saveConfig();
					}
				} else
				{
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("SomethingWentWrong")).replace("\\n", "\n"));
				}

			} else if (args[0].equalsIgnoreCase("move"))
			{
				if (p.isOp() || p.hasPermission("rules.move"))
				{
					if (args.length == 1)
					{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("IsAbleToMove").replace("{MOVE}", getConfig().getBoolean("MoveBeforeAccepted") + "")).replace("\\n", "\n"));

					} else if (args[1].equalsIgnoreCase("true"))
					{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("CanNowMove")).replace("\\n", "\n"));
						getConfig().set("MoveBeforeAccepted", true);
						saveConfig();
					} else if (args[1].equalsIgnoreCase("false"))
					{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("CanNotLongerMove")).replace("\\n", "\n"));
						getConfig().set("MoveBeforeAccepted", false);
						saveConfig();
					} else
					{
						p.sendMessage(ChatColor.RED + "Syntax: /rules move [<true|false>]");
					}
				} else
				{
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("NoPermission")).replace("\\n", "\n"));
				}
			} else if (args[0].equalsIgnoreCase("reload"))
			{
				if (p.isOp() || p.hasPermission("rules.reload"))
				{
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("ConfigIsReloading")).replace("\\n", "\n"));
					reloadConfig();
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("ConfigIsReloaded")).replace("\\n", "\n"));

				} else
				{
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("NoPermission")).replace("\\n", "\n"));
				}
			} else
			{
				p.sendMessage(ChatColor.RED + "Syntax: /rules <help|accept|move|reload>...");
			}
		}
		return false;
	}

	public static FileConfiguration getConfig()
	{
		return Main.getInstance().getConfig();
	}

	public static void saveConfig()
	{
		Main.getInstance().saveConfig();
	}

	public static void reloadConfig()
	{
		Main.getInstance().reloadConfig();
	}
}
