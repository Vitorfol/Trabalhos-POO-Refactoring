package jogo.modelo;

import java.util.Random;

public class JogadorNormal extends Jogador {

	public JogadorNormal(int id,int position,int numberMoves, boolean dadosIguais) {
		super(id, position, numberMoves, dadosIguais);
	}
	
	public int jogarDados() {
	    Random random = new Random();
	    int dado1, dado2, soma;
	    dado1 = random.nextInt(6) + 1;
	    dado2 = random.nextInt(6) + 1; 
	    soma = dado1 + dado2;
	    
	    if(dado1 == dado2) setDadosIguais(true);
	    else setDadosIguais(false);
	    
		System.out.print("Dado 1: ");
		System.out.println(dado1);
		System.out.print("Dado 2: ");
		System.out.println(dado2);
		System.out.println("Soma dos dados: " + soma);
	    
	    return soma; 
	}

	@Override
	public String classString() {
		return "Normal";
	}
}
