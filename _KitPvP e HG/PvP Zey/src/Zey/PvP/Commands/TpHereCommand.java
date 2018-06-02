package Zey.PvP.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Zey.PvP.Main.Main;


public class TpHereCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("tphere") || cmd.getName().equalsIgnoreCase("here")) {
            if (!p.hasPermission("zey.pvp.tp.here")) {
			p.sendMessage("�cVoc� n�o tem permiss�o para isso.");
				return true;
			}
			if(args.length == 0) {
				p.sendMessage(String.valueOf(Main.prefix) + " �7� �cErrado, utilize a sintaxe correta: /tphere ou /here para puxar um jogador at� voc�.");
				return true;
			}
			Player k = Bukkit.getPlayer(args[0]);
			if(k == null) {
				p.sendMessage(String.valueOf(Main.prefix) + " �7� �cEste jogador(a) est� offline ou n�o existe.");
				return true;
			}
			k.teleport(p);
			p.sendMessage(String.valueOf(Main.prefix) + " �7� �7Voc� puxou o jogador: �e" + k.getName() + " �7at� voc�.");
			return true;
		}
		return false;
	}

}
