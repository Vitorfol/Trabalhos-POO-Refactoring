package jogo.visao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import jogo.modelo.Jogador;
import jogo.modelo.JogadorAzarado;
import jogo.modelo.JogadorNormal;
import jogo.modelo.JogadorSortudo;
import jogo.modelo.Tabuleiro;

public class TabuleiroConsole {

	Tabuleiro tabuleiro;

	ArrayList<Jogador> arrayPlayers = new ArrayList<Jogador>();

	Scanner scan = new Scanner(System.in);

	public TabuleiroConsole() {

		boolean repetição;
		int numPlayers = 0;

		do {

			System.out.println("Quantos jogadores?");
			numPlayers = scan.nextInt();

		} while (numPlayers < 2 || numPlayers > 6);

		do {

			arrayPlayers.clear();
			Set<String> tiposJogadores = new HashSet<>();

			for (int i = 1; i <= numPlayers; i++) {

				int opc = 0;
				do {

					System.out.println("Defina o tipo do jogador " + i + "!");
					System.out.print("1 - Jogador azarado\n2 - Jogador normal\n3 - Jogador sortudo\n");
					opc = scan.nextInt();

				} while (opc <= 0 || opc > 3);

				switch (opc) {

				case 1:
					arrayPlayers.add(new JogadorAzarado(i));
					tiposJogadores.add("JogadorAzarado");
					break;
				case 2:
					arrayPlayers.add(new JogadorNormal(i));
					tiposJogadores.add("JogadorNormal");
					break;
				case 3:
					arrayPlayers.add(new JogadorSortudo(i));
					tiposJogadores.add("JogadorSortudo");

				}
			}

			repetição = tiposJogadores.size() < 2;

			if (repetição) {
				System.out.println("Você deve definir pelo menos dois tipos diferentes de jogadores.\n");
			}

		} while (repetição);

		System.out.println();

	}

	public void jogar() {
		tabuleiro = new Tabuleiro(arrayPlayers);

		System.out.println(tabuleiro);
		while (!tabuleiro.veriFicarVitoria()) {
			for (Jogador j : tabuleiro.getJogadores()) {
				tabuleiro.moveInSquare(j);
				try {
					Thread.sleep(000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		scan.close();
	}

}
