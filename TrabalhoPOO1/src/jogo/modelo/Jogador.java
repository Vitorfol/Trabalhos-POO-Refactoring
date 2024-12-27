package jogo.modelo;

import java.util.ArrayList;

import jogo.factory.CorFactory;

public abstract class Jogador {

	private Cor cor;
	protected int id;
	protected int position;
	protected int numberMoves = 0;
	protected boolean blocked = false;
	protected boolean winner = false;
	private boolean dadosIguais;
	
	public Jogador(int id, int position, int numberMoves, boolean dadosIguais) {
		this.id = id;
		cor = CorFactory.instanciarCor(id);
		this.position = position;
		this.numberMoves = numberMoves;
		this.dadosIguais = dadosIguais;
	}
	
	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
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
	
	public void moveAtomic(int sum, Jogador jogador, Tabuleiro tabuleiro) {
		ArrayList<Campo> squares = tabuleiro.getSquares();
		for (int i = 0; i < sum; i++) {
			squares.get(jogador.getPosition()).remPlayer(jogador);
			jogador.movePlayer(1);
			squares.get(jogador.getPosition()).addPlayer(jogador);
			System.out.println("\n" + tabuleiro);
			System.out.println(jogador.getColor() + " na casa " + jogador.getPosition());
			if (jogador.getPosition() == 40) {
				jogador.setNumberMoves(jogador.getNumberMoves() + 1);
			}
			tabuleiro.veriFicarVitoria();
			try {
				Thread.sleep(00);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public abstract int[] jogarDados();

	public abstract String classString();

	public boolean isDadosIguais() {
		return dadosIguais;
	}

	public void setDadosIguais(boolean dadosIguais) {
		this.dadosIguais = dadosIguais;
	}
}
