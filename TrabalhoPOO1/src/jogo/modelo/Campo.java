package jogo.modelo;

import java.util.ArrayList;

public class Campo {

	int number;
	private ArrayList<Jogador> players = new ArrayList<Jogador>();

	public ArrayList<Jogador> getPlayers() {
		return players;
	}

	public Campo(int number) {
		this.number = number;
	}

	public void addPlayer(Jogador player) {
		players.add(player);
	}

	public void remPlayer(Jogador player) {
		int index = searchPlayerIndex(player);
		if(index != - 1)
			players.remove(index);
	}
	
	private int searchPlayerIndex(Jogador player)
	{
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getId() == player.getId()) {
				return i;
			}
		}
		return -1;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		int length = players.size();
		for (int i = 0; i < length; i++) {
			sb.append(players.get(i));
		}
		for (int i = 0; i < 6 - length; i++) {
			sb.append(" ");
		}
		sb.append("]");
		return sb.toString();
	}
}
