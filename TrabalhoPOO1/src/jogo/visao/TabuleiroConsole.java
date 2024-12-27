package jogo.visao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import jogo.factory.JogadorFactory;
import jogo.modelo.Jogador;
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
			Set<Integer> tiposJogadores = new HashSet<>();

			for (int i = 1; i <= numPlayers; i++) {

				int opc = 0;
				do {

					System.out.println("Defina o tipo do jogador " + i + "!");
					System.out.print("1 - Jogador azarado\n2 - Jogador normal\n3 - Jogador sortudo\n");
					opc = scan.nextInt();

				} while (opc <= 0 || opc > 3);
				arrayPlayers.add(JogadorFactory.instanciarJogador(i, opc, 0, 0, false));
				tiposJogadores.add(opc);
			}

			repetição = tiposJogadores.size() < 2;

			if (repetição) {
				System.out.println("Você deve definir pelo menos dois tipos diferentes de jogadores.\n");
			}

		} while (repetição);

		System.out.println();

	}

	public void iniciar() {
		tabuleiro = new Tabuleiro(arrayPlayers);
		System.out.println(tabuleiro);
	}

	public void jogar() {
		tabuleiro.logica();
		scan.close();
	}

}
