package Zey.PvP.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Zey.PvP.Main.Main;

public class FinalizarCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		
		Player p = (Player)Sender;
		
		if(Cmd.getName().equalsIgnoreCase("finalizar")){
		
		if(!p.hasPermission("zey.pvp.finalizar")){
			p.sendMessage("�cVoc� n�o tem permiss�o para isso.");
			return true;
		}
		
		if(Args.length == 0){
			p.sendMessage(String.valueOf(Main.prefix) + " �7� �cErrado, utilize a sintaxe correta: /finalizar [Evento] [FullKit]");
			return true;
		}
		if(Args[0].equalsIgnoreCase("evento")){
			
			if(!IniciarCommand.evento){
				p.sendMessage(String.valueOf(Main.prefix) + " �7� �cO Evento [Evento] ainda n�o foi iniciado.");
			}else{
				p.sendMessage(String.valueOf(Main.prefix) + " �7� �cVoc� finalizou o Evento [Evento].");
				Bukkit.broadcastMessage(String.valueOf(Main.prefix) + " �7� �cO Evento [Evento] foi finalizado e n�o est� mais disponivel para acesso.");
				IniciarCommand.evento = false;
				return true;
			}
		}
		if(Args[0].equalsIgnoreCase("fullkit")){
			
			if(!IniciarCommand.fullkit){
				p.sendMessage(String.valueOf(Main.prefix) + " �7� �cO Benef�cio [FullKit] n�o est� liberado.");
			}else{
				p.sendMessage(String.valueOf(Main.prefix) + " �7� �cVoc� finalizou o Benef�cio [FullKit].");
				Bukkit.broadcastMessage(String.valueOf(Main.prefix) + " �7� �cO Benef�cio [FullKit] foi finalizado e n�o est� mais disponivel.");
				IniciarCommand.fullkit = false;
				return true;
			}
		}
	}
 return false;
  }
}