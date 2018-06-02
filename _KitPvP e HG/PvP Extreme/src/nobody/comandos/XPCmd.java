package nobody.comandos;

import nobody.eventos.Money;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class XPCmd implements CommandExecutor {
	
	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("xp")) {
			if (!p.hasPermission("tk.xp")) {
				p.sendMessage("�6{�a�lXP�6} Voc� n�o tem permiss�o apra isso!!");
			} else {
				if (args.length == 0) {
					sender.sendMessage("�6{�a�lXP�6} /xp [player] [quantia]");
					return true;
				}
				Player target = Bukkit.getPlayerExact(args[0]);
				if ((target == null) || (!(target instanceof Player))) {
					sender.sendMessage("�6{�a�lXP�6} Jogador n�o encontrado!");
					return true;
				}
				if (isNumeric(args[1])) {
					int xps = Integer.parseInt(args[1]);
					Money.addMoney(xps, target);
					p.sendMessage("�6{�a�lXP�6} �eVoc� deu ao player �c" + target.getName() + "�c " + xps + "" + "�7XP");
					target.sendMessage("�6{�a�lXP�6} �eVoc� recebeu do player �c" + p.getName() + "�c " + xps + "" + "�7XP");

				}
			}
		}
		return false;
	}
     }