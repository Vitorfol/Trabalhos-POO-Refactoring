package jogo.visao;

import java.util.ArrayList;
import java.util.Scanner;

import jogo.modelo.Jogador;
import jogo.modelo.JogadorAzarado;
import jogo.modelo.JogadorNormal;
import jogo.modelo.JogadorSortudo;
import jogo.modelo.Tabuleiro;

public class TabuleiroConsole {

	Tabuleiro tabuleiro;
	
	public TabuleiroConsole() {
		// TODO Auto-generated constructor stub
		boolean repetiçao = true;
		Scanner scan = new Scanner(System.in);
		int numPlayers = 0;
		do {
			System.out.println("Quantos jogadores?");
			numPlayers = scan.nextInt();
		} while(numPlayers < 2 || numPlayers > 6);
		
		ArrayList<Jogador> arrayPlayers = new ArrayList<Jogador>();
		
		do {
		for(int i = 1; i <= numPlayers; i++) {
			
			int opc = 0;
			do {
				System.out.println("Defina o tipo do jogador " + i + "!");
				System.out.print("1 - Jogador azarado\n 2 - Jogador normal\n 3 - Jogador sortudo ");
				opc = scan.nextInt();
			} while(opc <= 0 || opc > 3);
			
			switch(opc) {
			case 1:
				arrayPlayers.add(new JogadorAzarado(i));
				break;
			case 2:
				arrayPlayers.add(new JogadorNormal(i));
				break;
			case 3: 
				arrayPlayers.add(new JogadorSortudo(i));
			}
			if(i > 1 && arrayPlayers.get(i - 1).getClass() != arrayPlayers.get(i - 2).getClass()) {
				repetiçao = false;
			}
		}
		} while(repetiçao);
		
			tabuleiro = new Tabuleiro(arrayPlayers);
			
		System.out.println(tabuleiro);
		while(!tabuleiro.veriFicarVitoria()) {
			for(Jogador j : tabuleiro.getJogadores()) {
				tabuleiro.moveInSquare(j);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		scan.close();
		
	}
	

}
