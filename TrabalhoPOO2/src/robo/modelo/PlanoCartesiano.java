package robo.modelo;

import java.util.ArrayList;

public class PlanoCartesiano {
	
	private int dimX;
	private int dimY;
	private int foodX;
	private int foodY;
	
	private ArrayList<ArrayList<Campo>> campos = new ArrayList<ArrayList<Campo>>();
   
	private void criarCampos() {
		for (int i = 0; i <= dimY; i++) {
		campos.add(new ArrayList<Campo>());
		for (int j = 0; j <= dimX; j++) {
			campos.get(i).add(new Campo(i, j));
		}
	}	
	}
	
	public PlanoCartesiano(int foodY, int foodX) {
		if (foodX > 0 && foodY > 0) {
			this.foodY = foodY;
			this.foodX = foodX;
			this.dimX = foodX;
			this.dimY = foodY;
			criarCampos();
			campos.get(foodY).get(foodX).colocarAlimento();
		}
	}

	public int getDimX() {
		return dimX;
	}

	public int getDimY() {
		return dimY;
	}

	public int getFoodX() {
		return foodX;
	}

	public int getFoodY() {
		return foodY;
	}
  
	public void moverNoCampo(Robo robo, int i) {
		campos.get(robo.getPosY()).get(robo.getPosX()).remRobo(robo);
		robo.moverRobo(i);
		campos.get(robo.getPosY()).get(robo.getPosX()).addRobo(robo);
	}
	
	public void moverNoCampo(Robo robo, String str) {
		campos.get(robo.getPosY()).get(robo.getPosX()).remRobo(robo);
		robo.moverRobo(str);
		campos.get(robo.getPosY()).get(robo.getPosX()).addRobo(robo);
	}

	public boolean verificarVitoria() {
	if(campos.get(foodY).get(foodX).isOcupado()) {
		System.out.println(campos.get(foodY).get(foodX).getRobos().get(0).getCor() +
				" Venceu");
		return true;
	}
	return false;
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		for (int i = dimY; i >= 0; i--) {
			sb.append(i + " ");
			for (int j = 0; j <= dimX; j++) {
				sb.append(campos.get(i).get(j) + " ");
			}
			sb.append("\n");
		}
		sb.append("  ");
		for (int j = 0; j <= dimX; j++) {
			sb.append("  " + j + "   ");
			sb.append(" ");
		}
		sb.append("\n");
		return sb.toString();
	}

}
