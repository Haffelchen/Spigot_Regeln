package me.haffel.regeln;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
public static Main instance;
	
	@Override
	public void onEnable()
	{
		instance = this;
		System.out.println("<--=-=-=-=-=-=-=-=-==Regel Plugin==-=-=-=-=-=-=-=-=-->");
		System.out.println("| Version: 1.0                                       |");
		System.out.println("| Beschreibung: Spieler muessen erst die Regeln      |");
		System.out.println("|               akzeptieren bevor sie spielen koennen|");
		Config.loadConfig();
		System.out.println("<--=-=-=-=-=-=-=-=-==Aufgestartet==-=-=-=-=-=-=-=-=-->");
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Commands(), this);
		pm.registerEvents(new Events(), this);
		getCommand("rules").setExecutor(new Commands());
	}
	
	@Override
	public void onDisable()
	{
		System.out.println("<--=-=-=-=-=-=-=-=-==Regel Plugin==-=-=-=-=-=-=-=-=-->");
		System.out.println("| Version: 1.0                                       |");
		System.out.println("| Beschreibung: Spieler muessen erst die Regeln      |");
		System.out.println("|               akzeptieren bevor sie spielen koennen|");
		System.out.println("<--=-=-=-=-=-=-=-=-=-==Gestoppt==-=-=-=-=-=-=-=-=-=-->");
	}
	
	public static Main getInstance()
	{
		return instance;
	}
}
