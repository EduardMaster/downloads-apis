package nobody.comandos;

import nobody.main.Main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Caixa
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!(sender instanceof Player))
    {
      sender.sendMessage("�cConsole ? Aff");
      return true;
    }
    Player p = (Player)sender;
    if (args.length == 0)
    {
      p.sendMessage("�7[�6�l!�7] �eUse: �b/doarcaixa <Player> <Caixa>");
      return true;
    }
    Player t = Bukkit.getPlayer(args[0]);
    if (t == p)
    {
      p.sendMessage("�7[�6�l!�7] �cVoc� nao pode doar para Voc� mesmo");
      return true;
    }
    if (args[1] == null) {
      p.sendMessage("�7[�6�l!�7] �cUse: /doarcaixa <Player> <Caixa>");
    }
    if (t == null)
    {
      p.sendMessage("�7[�6�l!�7] �cEsse jogador n�o existe ou est� offline");
      return true;
    }
    if (p.hasPermission("tk.caixa"))
    {
      if (args[1].equals("Gold")) {
        if (Main.getInstance().caixa.getString(t.getName().toLowerCase() + ".Gold").equals("false"))
        {
          Main.getInstance().caixa.set(t.getName().toLowerCase() + ".Gold", "true");
          p.sendMessage("�7[�6�l!�7] �aVoc� deu uma caixa �e�lGold �apara �b" + t.getName());
          t.sendMessage("�7[�6�l!�7] �e" + p.getName() + " �eDeu uma a Caixa �e�lGold �apara Voc�");
          Main.getInstance().save();
        }
        else
        {
          if (Main.getInstance().caixa.getString(t.getName().toLowerCase() + ".Gold").equals("true"))
          {
            p.sendMessage("�7[�6�l!�7] �cEsse jogador j� tem essa caixa");
            return true;
          }
          p.sendMessage("�7[�6�l!�7] �cEssa caixa n�o existe");
        }
      }
      if (args[1].equals("Diamante")) {
        if (Main.getInstance().caixa.getString(t.getName().toLowerCase() + ".Diamante").equals("false"))
        {
          Main.getInstance().caixa.set(t.getName().toLowerCase() + ".Diamante", "true");
          p.sendMessage("�7[�6�l!�7] �eVoc� deu uma caixa �b�lDiamante �epara �b" + t.getName());
          t.sendMessage("�7[�6�l!�7] �e" + p.getName() + " �eDeu uma a Caixa �b�lDiamante �apara Voc�");
          Main.getInstance().save();
        }
        else
        {
          if (Main.getInstance().caixa.getString(t.getName().toLowerCase() + ".Diamante").equals("true"))
          {
            p.sendMessage("�cEsse jogador j� tem essa caixa");
            return true;
          }
          p.sendMessage("�cEssa caixa n�o existe");
        }
      }
      if (args[1].equals("Esmeralda")) {
        if (Main.getInstance().caixa.getString(t.getName().toLowerCase() + ".Esmeralda").equals("false"))
        {
          Main.getInstance().caixa.set(t.getName().toLowerCase() + ".Esmeralda", "true");
          p.sendMessage("�7[�6�l!�7] �aVoc� deu uma caixa �a�lEsmeralda �apara �b" + t.getName());
          t.sendMessage("�7[�6�l!�7] �b" + p.getName() + " �aDeu uma a Caixa �a�lEsmeralda �apara Voc�");
          Main.getInstance().save();
        }
        else
        {
          if (Main.getInstance().caixa.getString(t.getName().toLowerCase() + ".Esmeralda").equals("true"))
          {
            p.sendMessage("�7[�6�l!�7] �cEsse jogador j� tem essa caixa");
            return true;
          }
          p.sendMessage("�7[�6�l!�7] �cEssa caixa n�o existe");
        }
      }
    }
    else
    {
      p.sendMessage("Sem permissao");
    }
    return true;
  }
}
