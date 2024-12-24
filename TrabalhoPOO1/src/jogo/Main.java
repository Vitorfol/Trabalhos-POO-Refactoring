package jogo;

import jogo.visao.TabuleiroConsole;

public class Main {

	public static void main(String[] args) {
		TabuleiroConsole tab = new TabuleiroConsole();
		tab.iniciar();
		tab.jogar();
	}

}
