package jogo.modelo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Tabuleiro {

	private ArrayList<Jogador> jogadores = new ArrayList<>();
	private ArrayList<Campo> squares = new ArrayList<>(41);

	public Tabuleiro(ArrayList<Jogador> players) {
		for (int i = 0; i < 41; i++) {
			squares.add(new Campo(i));
		}
		for (Jogador jogador : players) {
			jogadores.add(jogador);
			squares.get(0).addPlayer(jogador);
		}
	}

	public ArrayList<Jogador> getJogadores() {
		return jogadores;
	}

	public void setJogadores(ArrayList<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

	private void moveAtomic(int sum, Jogador jogador) {
		for (int i = 0; i < sum; i++) {
			squares.get(jogador.getPosition()).remPlayer(jogador);
			jogador.movePlayer(1);
			squares.get(jogador.getPosition()).addPlayer(jogador);
			System.out.println("\n" + this);
			System.out.println(jogador.getColor() + " na casa " + jogador.getPosition());
			if (jogador.getPosition() == 40) {
				jogador.setNumberMoves(jogador.getNumberMoves() + 1);
			}
			veriFicarVitoria();
			try {
				Thread.sleep(00);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
		int[] resultadoDados = jogador.jogarDados();
		int sum = resultadoDados[2];
		int dado2 = resultadoDados[1];
		int dado1 = resultadoDados[0];
		System.out.print("Dado 1: ");
		System.out.println(dado1);
		System.out.print("Dado 2: ");
		System.out.println(dado2);
		System.out.println("Soma dos dados: " + sum);
		try {
			Thread.sleep(00);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		moveAtomic(sum, jogador);
		jogador.setNumberMoves(jogador.getNumberMoves() + 1);
		int index = jogadores.indexOf(jogador);
		checkPosition(jogador);
		System.out.println(jogador.getColor() + " finalizou a jogada na casa: " + jogador.getPosition());
		if (dado1 == dado2) {
			System.out.println("Dados iguais. Jogue mais uma vez");
			moveInSquare(jogadores.get(index));
		}

	}

	public void checkPosition(Jogador jogador) {
		switch (jogador.getPosition()) {
		case 10:
		case 25:
		case 38:
			System.out.println("Casa " + jogador.getPosition() + ": nao joga a proxima rodada");
			jogador.setBlocked(true);
			break;

		case 13:
			Jogador newPlayer = null;
			Random random = new Random();
			int option = random.nextInt(2) + 1;
			if (jogador instanceof JogadorAzarado) {
				if (option == 1) {
					newPlayer = new JogadorNormal(jogador.getId());
					System.out.println(jogador.getColor() + " mudou de azarado para NORMAL");
				} else {
					newPlayer = new JogadorSortudo(jogador.getId());
					System.out.println(jogador.getColor() + " mudou de azarado para SORTUDO");
				}
			} else if (jogador instanceof JogadorNormal) {
				if (option == 1) {
					newPlayer = new JogadorAzarado(jogador.getId());
					System.out.println(jogador.getColor() + " mudou de normal para AZARADO");
				} else {
					newPlayer = new JogadorSortudo(jogador.getId());
					System.out.println(jogador.getColor() + " mudou de normal para SORTUDO");
				}
			} else {
				if (option == 1) {
					newPlayer = new JogadorNormal(jogador.getId());
					System.out.println(jogador.getColor() + " mudou de sortudo para NORMAL");
				} else {
					newPlayer = new JogadorAzarado(jogador.getId());
					System.out.println(jogador.getColor() + " mudou de sortudo para AZARADO");
				}
			}

			newPlayer.setBlocked(jogador.isBlocked());
			newPlayer.setPosition(jogador.getPosition());
			newPlayer.setNumberMoves(jogador.getNumberMoves());
			int index = jogadores.indexOf(jogador);
			squares.get(jogador.getPosition()).remPlayer(jogador);
			squares.get(jogador.getPosition()).addPlayer(newPlayer);
			jogadores.set(index, newPlayer);
			break;

		case 5:
		case 15:
		case 30:

			System.out.println("Casa " + jogador.getPosition() + ": ande 3 casas");

			try {
				Thread.sleep(00);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (!(jogador instanceof JogadorAzarado)) {
				moveAtomic(3, jogador);
			} else {
				System.out.println("Jogador é azarado. Não andará as 3 casas");
			}

			break;
			
		case 17:
		case 27:
			if (jogadores.size() <= 1)
				return;
			Scanner enter = new Scanner(System.in);
			System.out.println("Casa " + jogador.getPosition() + ": Escolha um jogador para voltar para o início!");
			System.out.println("Diga sua cor: ");
			String harmed = enter.next();

			try {
				boolean found = false;
				for (Jogador j : jogadores) {
					if (j.getColor().toLowerCase().contains(harmed.toLowerCase())) {
						squares.get(j.getPosition()).remPlayer(j);
						j.setPosition(0);
						squares.get(0).addPlayer(j);
						found = true;
						break; // Exit loop after finding and processing the player
					}
				}
				if (!found) {
					System.out.println("Jogador não encontrado");
					checkPosition(jogador);
				}
			} catch (Exception e) {
				System.out.println("Exception occurred: " + e.getMessage());
				e.printStackTrace();
			}
			break;

		case 20:
		case 35:

			System.out.print("Casa " + jogador.getPosition() + ": Troca de posiçao com o jogador mais atras\n");
			int lower = 40;
			Jogador aux = null;
			for (Jogador j : jogadores) {
				if (j.getPosition() < lower) {
					lower = j.getPosition();
					aux = j;
				}
			}
			squares.get(lower).remPlayer(aux);
			aux.setPosition(jogador.getPosition());
			squares.get(jogador.getPosition()).addPlayer(aux);

			squares.get(jogador.getPosition()).remPlayer(jogador);
			jogador.setPosition(lower);
			squares.get(jogador.getPosition()).addPlayer(jogador);
			break;

		}
		try {
			Thread.sleep(00);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean veriFicarVitoria() {
		if (!squares.get(40).getPlayers().isEmpty()) {
			squares.get(40).getPlayers().get(0).setWinner(true);
			System.out.println(squares.get(40).getPlayers().get(0).getColor());
			System.out.println();
			for (Jogador j : jogadores) {
				System.out.println("Jogadas do " + j.getColor() + ": " + j.getNumberMoves());
				System.out.println(j.getColor() + " terminou na casa " + j.getPosition());
				System.out.println();
			}
			System.exit(0);
		}
		return false;
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
}
