package jogo.factory;

import jogo.modelo.JogadorNormal;
import jogo.modelo.Jogador;
import jogo.modelo.JogadorAzarado;
import jogo.modelo.JogadorSortudo;

public class JogadorFactory {
	public static Jogador instanciarJogador(int id, int type, int position, int numberMoves, boolean dadosIguais)
	{
		switch(type) {
		case 1 :
			return new JogadorAzarado(id, position, numberMoves, dadosIguais);
		case 2 :
			return new JogadorNormal(id, position, numberMoves, dadosIguais);
		case 3 :
			return new JogadorSortudo(id, position, numberMoves, dadosIguais);
		  default:
              throw new IllegalArgumentException("Tipo de jogador inválido: " + type);
		}
	}

}