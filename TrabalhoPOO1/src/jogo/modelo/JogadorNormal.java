package jogo.modelo;

import java.util.Random;

public class JogadorNormal extends Jogador {

	public JogadorNormal(int id,int position,int numberMoves) {
		super(id, position, numberMoves);
	}
	
	public int[] jogarDados() {
	    Random random = new Random();
	    int dado1, dado2, soma;
	    dado1 = random.nextInt(6) + 1;
	    dado2 = random.nextInt(6) + 1; 
	    soma = dado1 + dado2;
	    return new int[]{dado1, dado2, soma}; 
	}

	@Override
	public String classString() {
		return "Normal";
	}
}
