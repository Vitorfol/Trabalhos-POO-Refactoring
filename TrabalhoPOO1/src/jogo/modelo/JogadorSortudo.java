package jogo.modelo;

import java.util.Random;

public class JogadorSortudo extends Jogador {

	public JogadorSortudo(int id, int position, int numberMoves) {
		super(id, position, numberMoves);
	}
	
	public int[] jogarDados() {
	    Random random = new Random();
	    int dado1, dado2, soma;

	    do {
	        dado1 = random.nextInt(6) + 1; 
	        dado2 = random.nextInt(6) + 1; 
	        soma = dado1 + dado2;
	    } while (soma < 7);

	    return new int[]{dado1, dado2, soma};
	}

	@Override
	public String classString() {
		return "Sortudo";
	}
}
