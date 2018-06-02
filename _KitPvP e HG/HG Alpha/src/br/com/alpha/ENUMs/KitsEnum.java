package br.com.alpha.ENUMs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public enum KitsEnum {
	 ANCHOR("Anchor", null, Arrays.asList("N�o leve knockback!")),
	 ACHILLES("Achilles", null, Arrays.asList("Leve mais dano somente com itens de madeira.")),
	 BACKPACKER("BackPacker", null, Arrays.asList("Tenha uma bolsa com sopas para refill!")),
	 BARBARIAN("Barbarian", null, Arrays.asList("Evolua de espadas at� chegar no ultimo n�vel!")),
	 BOXER("Boxer", null, Arrays.asList("Tenha mais for�a com seus punhos!")),
	 CANNIBAL("Cannibal", null, Arrays.asList("D� fome ao bater em seus advers�rios")),
	 COPYCAT("Copycat", null, Arrays.asList("Copie o kit do player que voc� matar!")),
	 CRAFTER("Crafter", null, Arrays.asList("Tenha uma furnace e uma crafting table port�teis!")),
	 CULTIVATOR("Cultivator", null, Arrays.asList("Seja um cultivador profissional!")),
	 DEMOMAN("Demoman", null, Arrays.asList("Exploda seus inimigos ao fazer sua plataforma!")),
	 ENDERMAGE("Endermage", null, Arrays.asList("Teleporte os seus inimigos ou amigos at� voc� utilizando o portal!")),
	 FIREMAN("Fireman", null, Arrays.asList("N�o leve dano de fogo.")),
	 FISHERMAN("Fisherman", null, Arrays.asList("Teleporte quem voc� fisgar at� voc�!")),
	 GRANDPA("Grandpa", null, Arrays.asList("Tenha um bast�o com knockback 2")),
	 JACKHAMMER("Jackhammer", null, Arrays.asList("Ao quebrar o ch�o com seu machado, quebre at� a bedrock.")),
	 JUMPER("Jumper", null, Arrays.asList("Ganhe 10 enderpeals para te ajudar!")),
	 KANGAROO("Kangaroo", null, Arrays.asList("Seja um canguru.")),
	 LAUNCHER("Launcher", null, Arrays.asList("Ganhe 20 esponjas que ao subir em cima delas, voc� ganha um impulso.")),
	 LUMBERJACK("Lumberjack", null, Arrays.asList("Quebre todas as madeiras de uma �rvore de baixo pra cima.")),
	 MADMAN("Madman", null, Arrays.asList("D� fraqueza ao chegar perto de seus inimigos")),
	 MINER("Miner", null, Arrays.asList("Quebre todos os min�rios ao quebrar somente 1 (Iron,Carv�o)")),
	 MONK("Monk", null, Arrays.asList("Tire a espada da hotbar de seu inimigo ao utilizar este kit.")),
	 NINJA("Ninja", null, Arrays.asList("Teleporte-se at� o player utilizando shift ao bater nele.")),
	 PHANTOM("Phantom", null, Arrays.asList("Tenha 5 segundos para voar.")),
	 POSEIDON("Poseidon", null, Arrays.asList("Ganhe for�a na �gua")),
	 PYRO("Pyro", null, Arrays.asList("Ganhe 1 isqueiro e 5 bolas de fogo para te ajudar.")),
	 REAPER("Reaper", null, Arrays.asList("D� efeito de wither ao bater nos players usando sua HOE.")),
	 SCOUT("Scout", null, Arrays.asList("Seja um velocista, ganhe 3 po��es de velocidade.")),
	 SNAIL("Snail", null, Arrays.asList("D� lentid�o nos players.")),
	 STOMPER("Stomper", null, Arrays.asList("Caia na cabe�a dos players e mate-os.")),
	 SWITCHER("Switcher", null, Arrays.asList("Troque de lugar com os players que acertar usando sua bola de neve")),
	 TANK("Tank", null, Arrays.asList("Exploda o local em que matou o player")),
	 TOWER("Tower", null, Arrays.asList("Seja stomper e worm ao mesmo tempo.")),
	 THOR("Thor", null, Arrays.asList("Seja o THOR dos filmes da MARVEL.")),
	 TURTLE("Turtle", null, Arrays.asList("N�o leve dano ao ficar no shift, mas tamb�m n�o poder� dar dano.")),
	 URGAL("Urgal", null, Arrays.asList("Ganhe 3 po��es de for�a para usar durante o jogo.")),
	 VIKING("Viking", null, Arrays.asList("D� diferentes danos com todos os tipos de machado.")),
	 VIPER("Viper", null, Arrays.asList("D� veneno nos players.")),
	 SPECIALIST("Specialist", null, Arrays.asList("Ao matar ganhe xp, e seja um encantador profissional.")),
	 WORM("Worm", null, Arrays.asList("Seja uma minhoca.")),
	 FORGER("Worm", null, Arrays.asList("Fa�a min�rios instantaneamente.")),
	 NONE("None", null, Arrays.asList("Nada."));
	
	String nome;
	Material item;
	List<String> hability;
	
	KitsEnum(String name, Material item, List<String> hability) {
		this.nome = name;
		this.item = item;
		this.hability = hability;
	}
	public String getName(){
		return this.nome;
	}
	public Material getItem(){
		return this.item;
	}
	public List<String> getHability(){
		return this.hability;
	}
	public static HashMap<Player, KitsEnum> kit = new HashMap<>();
	public static void setKit(Player jogador, KitsEnum kits){
		kit.put(jogador, kits);
	}
	public static void removeKit(Player jogador){
		kit.put(jogador, NONE);
	}
	public static boolean hasKit(Player jogador){
		return kit.containsKey(jogador) || (kit.get(jogador) == NONE);
	}
	public static KitsEnum getKit(Player jogador){
		return kit.get(jogador);
	}
	public static boolean hasItem(KitsEnum kits){
		return kits.getItem() != null;
	}
}