package jogo.modelo;

import java.util.ArrayList;
import java.util.Scanner;

public class Tabuleiro {

	private ArrayList<Jogador> jogadores = new ArrayList<>();
	private ArrayList<Campo> squares = new ArrayList<>(41);
	RegrasCasas regras = new RegrasCasas(squares, jogadores);
	private static Tabuleiro instancia;

	private Tabuleiro() {
		for (int i = 0; i < 41; i++) {
			squares.add(new Campo(i));
		}
	}
	
	public static Tabuleiro getInstancia() {
		if(instancia == null) instancia = new Tabuleiro();
		return instancia;
	}
	
	public void inicializarJogadores()
	{
		for(Jogador jogador : jogadores)
		{
			squares.get(0).addPlayer(jogador);
		}
	}

	public ArrayList<Jogador> getJogadores() {
		return jogadores;
	}

	public void setJogadores(ArrayList<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

	public void moveInSquare(Jogador jogador) {

		System.out.println("\nÉ a vez do jogador " + jogador.getColor() + ": " + jogador.classString());

		if (jogador.isBlocked()) {
			System.out.println(jogador.getColor() + " está bloqueado");
			jogador.setBlocked(false);
			return;
		}
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Pressione Enter para girar os dados");
		scan.nextLine();
		
		int sum = jogador.jogarDados();

		jogador.moveAtomic(sum, jogador, this);
		jogador.setNumberMoves(jogador.getNumberMoves() + 1);
		regras.checkPosition(jogador, this);
		System.out.println(jogador.getColor() + " finalizou a jogada na casa: " + jogador.getPosition());
		if(jogador.isDadosIguais()) tratarDadosIguais(jogador);

	}
	
	public void tratarDadosIguais(Jogador jogador) {
			System.out.println("Dados iguais. Jogue mais uma vez");
			moveInSquare(jogadores.get(jogadores.indexOf(jogador)));
	}

	

	public boolean veriFicarVitoria() {
		if (!squares.get(40).getPlayers().isEmpty()) {
			squares.get(40).getPlayers().get(0).setWinner(true);
			System.out.println(squares.get(40).getPlayers().get(0).getColor());
			System.out.println();
			exibirResultadoFinal();
			System.exit(0);
		}
		return false;
	}
	
	private void exibirResultadoFinal()
	{
		for (Jogador j : jogadores) {
			System.out.println("Jogadas do " + j.getColor() + ": " + j.getNumberMoves() + "\n"
			+ j.getColor() + " terminou na casa " + j.getPosition());
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= 10; i++) {
			for (int j = 0; j < 3; j++) {
				sb.append(" ");
			}
			sb.append(i);
			for (int j = 0; j < 4; j++) {
				sb.append(" ");
			}
		}
		sb.append("\n");

		for (int i = 0; i <= 10; i++) {
			sb.append(squares.get(i));
		}
		sb.append("\n");

		for (int i = 1; i <= 10; i++) {
			for (int j = 0; j < 6; j++) {
				sb.append(" ");
			}
			sb.append(41 - i);
			sb.append(squares.get(41 - i));
			for (int j = 0; j < 64; j++) {
				sb.append(" ");
			}
			sb.append(squares.get(10 + i));
			sb.append(10 + i);
			sb.append("\n");
		}

		for (int i = 0; i < 8; i++) {
			sb.append(" ");
		}
		for (int i = 30; i >= 21; i--) {
			sb.append(squares.get(i));
		}
		sb.append("\n");

		for (int i = 0; i < 8; i++) {
			sb.append(" ");
		}
		for (int i = 30; i >= 21; i--) {
			for (int j = 0; j < 3; j++) {
				sb.append(" ");
			}
			sb.append(i);
			for (int j = 0; j < 3; j++) {
				sb.append(" ");
			}
		}
		return sb.toString();
	}

	public ArrayList<Campo> getSquares() {
		return squares;
	}
	
	public void logica() {
		while (!veriFicarVitoria()) {
			moverLosJugadores();
		}
	}
	
	private void moverLosJugadores() {
		for (Jogador j : getJogadores()) {
			moveInSquare(j);
		}
	}
	
}
