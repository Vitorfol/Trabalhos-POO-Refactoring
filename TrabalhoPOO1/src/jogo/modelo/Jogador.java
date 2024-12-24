package jogo.modelo;

import jogo.factory.CorFactory;

public abstract class Jogador {

	private Cor cor;
	protected int id;
	protected int position;
	protected int numberMoves = 0;
	protected boolean blocked = false;
	protected boolean winner = false;

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public Jogador(int id) {
		this.id = id;
		cor = CorFactory.instanciarCor(id);
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getNumberMoves() {
		return numberMoves;
	}

	public void setNumberMoves(int numberMoves) {
		this.numberMoves = numberMoves;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.blocked = isBlocked;
	}
	
	public String getColor() {
		String resetColor = "\u001B[0m";
		if(winner) {
			setWinner(false);
			return cor.getNome() + " VENCEU :)" + resetColor;
		}
		else return cor.getNome() + resetColor;
	}

	public int getId() {
		return id;
	}

	public void movePlayer(int sum) {
		int newPosition = this.position + sum;
		setPosition(newPosition);
	}
	
	public String toString() {
		String resetColor = "\u001B[0m";
		return cor.toString() + id + resetColor;
	}
	
	public abstract int[] jogarDados();

	public abstract String classString();
}
