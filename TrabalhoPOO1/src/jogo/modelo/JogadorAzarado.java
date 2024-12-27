package jogo.modelo;

import java.util.Random;

public class JogadorAzarado extends Jogador {

	public JogadorAzarado(int id, int position, int numberMoves, boolean dadosIguais) {
		super(id, position, numberMoves, dadosIguais);
	}

	public int[] jogarDados() {
	    Random random = new Random();
	    int dado1, dado2, soma;

	    do {
	        dado1 = random.nextInt(6) + 1;
	        dado2 = random.nextInt(6) + 1; 
	        soma = dado1 + dado2;
	    } while (soma > 6);
	    
	    if(dado1 == dado2) setDadosIguais(true);
	    else setDadosIguais(false);

	    return new int[]{dado1, dado2, soma}; 
	}
	
	@Override
	public String classString() {
		return "Azarado";
	}

}
