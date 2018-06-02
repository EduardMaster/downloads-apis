package EventosPrincipais;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import Outros.ScoreBoard;
import Utils.KillsDeathsMoney;
import Utils.KitAPI;
import Utils.WarpsAPI;

public class Matar implements Listener {
	
	@EventHandler
	public void Matan�a(PlayerDeathEvent e ) {
		Player p = e.getEntity();
		WarpsAPI.ir(p, "spawn");
		if (e.getEntity().getKiller() instanceof Player) {
			Player t = (Player)p.getKiller();
			KillsDeathsMoney.addDeaths(p, 1);
			KillsDeathsMoney.addKill(t, 1);
			KillsDeathsMoney.addMoney(t, 100);
			KillsDeathsMoney.removermoney(p, 60);
			KitAPI.RemoveKit(p);
			ScoreBoard.UpdateScore(p);
			ScoreBoard.UpdateScore(t);
			e.setDeathMessage(null);
			p.sendMessage("�c�lVoc� morreu para " + t.getDisplayName() + "�c�l- 60 Coins");
			t.sendMessage("�c�lVoc� matou " + p.getDisplayName() + "�c�b+ 100 Coins");
		} else {
			ScoreBoard.UpdateScore(p);
			KitAPI.RemoveKit(p);
			e.setDeathMessage(null);
			p.sendMessage("�c�lFly�7�lTrue �a: �c�lVoc� morreu");
		}
	}

}
