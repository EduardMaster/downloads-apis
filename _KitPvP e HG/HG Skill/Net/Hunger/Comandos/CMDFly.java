package Net.Hunger.Comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import Net.Hunger.Main;

public class CMDFly implements CommandExecutor, Listener {
	
  public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
    if (!(sender instanceof Player)) {
     sender.sendMessage("�7[�a!�7] �bComandos apenas no servidor!");
     return true;
     }
     if (cmd.getName().equalsIgnoreCase("fly")) {
       Player player = (Player)sender;
       if (args.length == 0) {
         if (sender.hasPermission("Tag.vip")) {
          if(Main.PreGame) {
           if (!player.getAllowFlight()) {
             player.sendMessage("�7[�a!�7] Voc� ativou o �aFly!");
             player.setAllowFlight(true);
           } else {
             sender.sendMessage("�7[�a!�7] Voc� desativou o �aFly!");
             player.setAllowFlight(false);
           }
         } else {
          player.sendMessage("�7[�a?�7] Voc� so pode usar isso no �bPreGame!");	
          return true;
         }
       } else {
       player.sendMessage("�7[�a?�7] Voc� n�o possue permiss�o para isso!");
       return true;
       }
      }
     }
	return true;
  }
}