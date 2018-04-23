package me.haffel.regeln;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

public class Config
{
	public static void loadConfig()
	{
		if (new File(Main.getInstance().getDataFolder() + "/config.yml").exists())
		{
			if (!getConfig().contains("OnlyPlayers")
					|| (getConfig().contains("OnlyPlayers") && !getConfig().isSet("Rules")))
			{
				getConfig().set("OnlyPlayers", "Du kannst die Befehle nur als Spieler ausfuehren");
			}
			
			if (!getConfig().contains("Help")
					|| (getConfig().contains("Help") && !getConfig().isSet("Rules")))
			{
				getConfig().set("Help", "&b\\nHilfe:\\n&e/rules accept\\n/rules move [<true|false>]\\n/rules reload\\n ");
			}
			
			if (!getConfig().contains("SomethingWentWrong")
					|| (getConfig().contains("SomethingWentWrong") && !getConfig().isSet("Rules")))
			{
				getConfig().set("SomethingWentWrong", "&4Etwas ist schief gelaufen, die Config ist unvollständig. Bitte melde diesen Fehler einem Admin");
			}
			
			if (!getConfig().contains("IsAbleToMove")
					|| (getConfig().contains("IsAbleToMove") && !getConfig().isSet("Rules")))
			{
				getConfig().set("IsAbleToMove", "&bSpieler können sich bewegen bevor sie die Regeln akzeptiert haben: {MOVE}");
			}
			
			if (!getConfig().contains("CanNowMove")
					|| (getConfig().contains("CanNowMove") && !getConfig().isSet("Rules")))
			{
				getConfig().set("CanNowMove", "&aSpieler können sich nun bewegen auch wenn sie die Regeln nicht akzeptiert haben");
			}
			
			if (!getConfig().contains("CanNotLongerMove")
					|| (getConfig().contains("CanNotLongerMove") && !getConfig().isSet("Rules")))
			{
				getConfig().set("CanNotLongerMove", "&cSpieler können sich erst bewegen wenn sie die Regeln akzeptiert haben");
			}
			
			if (!getConfig().contains("NoPermission")
					|| (getConfig().contains("NoPermission") && !getConfig().isSet("Rules")))
			{
				getConfig().set("NoPermission", "&cDu hast keine Berechtigung diesen Befehl zu benutzen");
			}
			
			if (!getConfig().contains("ConfigIsReloading")
					|| (getConfig().contains("ConfigIsReloading") && !getConfig().isSet("Rules")))
			{
				getConfig().set("ConfigIsReloading", "&eDie Config wird neu geladen...");
			}
			
			if (!getConfig().contains("ConfigIsReloaded")
					|| (getConfig().contains("ConfigIsReloaded") && !getConfig().isSet("Rules")))
			{
				getConfig().set("ConfigIsReloaded", "&aDie Config wurde neu geladen");
			}
			
			if (!getConfig().contains("MoveBeforeAccepted")
					|| (getConfig().contains("MoveBeforeAccepted") && !getConfig().isSet("MoveBeforeAccepted"))
					|| (getConfig().contains("MoveBeforeAccepted") && !getConfig().get("MoveBeforeAccepted").equals(true)
							&& !getConfig().get("MoveBeforeAccepted").equals(false)))
			{
				getConfig().set("MoveBeforeAccepted", false);
			}
			
			if (!getConfig().contains("Rules")
					|| (getConfig().contains("Rules") && !getConfig().isSet("Rules")))
			{
				getConfig().set("Rules", "&aEs gibt keine Regeln!");
			}
			
			if (!getConfig().contains("RulesAcceptedMsg")
					|| (getConfig().contains("RulesAcceptedMsg") && !getConfig().isSet("RulesAcceptedMsg")))
			{
				getConfig().set("RulesAcceptedMsg", "&eDu hast die Regeln akzeptiert, viel Spass");
			}
			
			if (!getConfig().contains("RulesAlreadyAcceptedMsg")
					|| (getConfig().contains("RulesAlreadyAcceptedMsg") && !getConfig().isSet("RulesAlreadyAcceptedMsg")))
			{
				getConfig().set("RulesAlreadyAcceptedMsg", "&cDu hast die Regeln bereits akzeptiert");
			}
			
			if (!getConfig().contains("RulesNotAcceptedMsg")
					|| (getConfig().contains("RulesNotAcceptedMsg") && !getConfig().isSet("RulesNotAcceptedMsg")))
			{
				getConfig().set("RulesNotAcceptedMsg", "&cBitte akzeptiere die Regeln! /rules accept");
			}
			
			getConfig().set("Version", 1.0);
			
			if(!getConfig().isSet("Accepted"))
			{
				getConfig().createSection("Accepted");
			}
			
			saveConfig();
			
			System.out.println("| Config: Erfolgreich geladen                        |");
			
		} else
		{
			getConfig().set("OnlyPlayers", "Du kannst die Befehle nur als Spieler ausfuehren");
			getConfig().set("Help", "&b\\nHilfe:\\n&e/rules accept\\n/rules move [<true|false>]\\n/rules reload\\n ");
			getConfig().set("SomethingWentWrong", "&4Etwas ist schief gelaufen, die Config ist unvollständig. Bitte melde diesen Fehler einem Admin");
			getConfig().set("IsAbleToMove", "&bSpieler können sich bewegen bevor sie die Regeln akzeptiert haben: {MOVE}");
			getConfig().set("CanNowMove", "&aSpieler können sich nun bewegen auch wenn sie die Regeln nicht akzeptiert haben");
			getConfig().set("CanNotLongerMove", "&cSpieler können sich erst bewegen wenn sie die Regeln akzeptiert haben");
			getConfig().set("NoPermission", "&cDu hast keine Berechtigung diesen Befehl zu benutzen");
			getConfig().set("ConfigIsReloading", "&eDie Config wird neu geladen...");
			getConfig().set("ConfigIsReloaded", "&aDie Config wurde neu geladen");
			getConfig().set("MoveBeforeAccepted", false);
			getConfig().set("Rules", "&aEs gibt keine Regeln!");
			getConfig().set("RulesAcceptedMsg", "&eDu hast die Regeln akzeptiert, viel Spass");
			getConfig().set("RulesAlreadyAcceptedMsg", "&cDu hast die Regeln bereits akzeptiert");
			getConfig().set("RulesNotAcceptedMsg", "&cBitte akzeptiere die Regeln! /rules accept");
			getConfig().set("Version", 1.0);
			getConfig().createSection("Accepted");
			saveConfig();
			
			System.out.println("| Config: Erfolgreich erstellt und geladen           |");
		}
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
