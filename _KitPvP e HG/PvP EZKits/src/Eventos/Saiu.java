package Eventos;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import Utils.KitAPI;

public class Saiu implements Listener{
	
	@EventHandler
	private void aoSair(PlayerQuitEvent e){
		Player p = e.getPlayer();
		
		e.setQuitMessage("�7[�4ا7]" + p.getDisplayName());
		KitAPI.RemoveKit(p);
	}

}
