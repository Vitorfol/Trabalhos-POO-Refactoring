package robo.modelo;

import robo.excecao.MovimentoInvalidoException;

public class Robo {

	@SuppressWarnings("unused")
	private String cor;
	private int posX;
	private int posY;

	public Robo(String cor) {
		this.cor = cor;
		posX = 0;
		posY = 0;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public void moverRobo(String str) {
		switch (str) {
		case "up":
			posY++;
			break;
		case "down":
			if(posY == 0) throw new MovimentoInvalidoException();
			else posY--; 
			break;
		case "right":
			posX++; 
			break;
		case "left": 
			if(posX == 0) throw new MovimentoInvalidoException();
			posX--; 
			break;
		}
		
		System.out.println("A posição X é: " + getPosX());
		System.out.println("A posição Y é: " + getPosY());
	}
	
	public void moverRobo(int i) {
		switch (i) {
		case 1:
			posY++;
			break;
		case 2:
			if(posY == 0) throw new MovimentoInvalidoException();
			else posY--; 
			break;
		case 3:
			posX++; 
			break;
		case 4: 
			if(posX == 0) throw new MovimentoInvalidoException();
			posX--; 
			break;
		}
		
		System.out.println("A posição X é: " + getPosX());
		System.out.println("A posição Y é: " + getPosY());
	}
	
}