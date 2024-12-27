package jogo.modelo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import jogo.factory.JogadorFactory;

public class RegrasCasas {
	
	private ArrayList<Campo> squares;
	private ArrayList<Jogador> jogadores;
	
	public RegrasCasas(ArrayList<Campo> squares, ArrayList<Jogador> jogadores) {
		this.squares = squares;
		this.jogadores = jogadores;
	}

	public void checkPosition(Jogador jogador, Tabuleiro tabuleiro) {
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
					newPlayer = JogadorFactory.instanciarJogador(jogador.getId(), 1 , jogador.getPosition(), jogador.getNumberMoves(), jogador.isDadosIguais());
					System.out.println(jogador.getColor() + " mudou de azarado para NORMAL");
				} else {
					newPlayer = JogadorFactory.instanciarJogador(jogador.getId(), 3 , jogador.getPosition(), jogador.getNumberMoves(), jogador.isDadosIguais());
					System.out.println(jogador.getColor() + " mudou de azarado para SORTUDO");
				}
			} else if (jogador instanceof JogadorNormal) {
				if (option == 1) {
					newPlayer =  JogadorFactory.instanciarJogador(jogador.getId(), 1, jogador.getPosition(), jogador.getNumberMoves(), jogador.isDadosIguais());
					System.out.println(jogador.getColor() + " mudou de normal para AZARADO");
				} else {
					newPlayer = JogadorFactory.instanciarJogador(jogador.getId(), 3, jogador.getPosition(),  jogador.getNumberMoves(), jogador.isDadosIguais());
					System.out.println(jogador.getColor() + " mudou de normal para SORTUDO");
				}
			} else {
				if (option == 1) {
					newPlayer =  JogadorFactory.instanciarJogador(jogador.getId(), 2, jogador.getPosition(), jogador.getNumberMoves(), jogador.isDadosIguais());
					System.out.println(jogador.getColor() + " mudou de sortudo para NORMAL");
				} else {
					newPlayer = JogadorFactory.instanciarJogador(jogador.getId(), 1, jogador.getPosition(), jogador.getNumberMoves(), jogador.isDadosIguais());
					System.out.println(jogador.getColor() + " mudou de sortudo para AZARADO");
				}
			}
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
				jogador.moveAtomic(3, jogador, tabuleiro);
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
					checkPosition(jogador, tabuleiro);
				}
			} catch (Exception e) {
				System.out.println("Exception occurred: " + e.getMessage());
				e.printStackTrace();
			}
			break;

		case 20:
		case 35:
			System.out.print("Casa " + jogador.getPosition() + ": Troca de posiçao com o jogador mais atras\n");
			casas20e35(jogador);
			break;

		}
		try {
			Thread.sleep(00);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void casas20e35(Jogador jogador) {
		int lower = buscarPosicaoLower();
		Jogador aux = atribuirAux();
		squares.get(lower).remPlayer(aux);
		aux.setPosition(jogador.getPosition());
		squares.get(jogador.getPosition()).addPlayer(aux);

		squares.get(jogador.getPosition()).remPlayer(jogador);
		jogador.setPosition(lower);
		squares.get(jogador.getPosition()).addPlayer(jogador);
	}
	
	public int buscarPosicaoLower() {
		int lower = 40;
		for (Jogador j : jogadores) {
			if (j.getPosition() < lower) {
				lower = j.getPosition();
			}
		}
		return lower;
	}
	
	public Jogador atribuirAux() {
		Jogador aux = null;
		int lower = buscarPosicaoLower();
		for (Jogador j : jogadores) {
			if (j.getPosition() == lower) {
				aux = j;
			}
		}
		return aux;
	}
	
}
