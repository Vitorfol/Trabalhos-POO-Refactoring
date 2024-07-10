package robo.modelo;

import java.util.ArrayList;
import java.util.Arrays;

public class PlanoCartesiano {
	@SuppressWarnings("unused")
	private int dimX;
	@SuppressWarnings("unused")
	private int dimY;
	private int foodX;
	private int foodY;
	private ArrayList<Robo> robos = new ArrayList<Robo>();

	public PlanoCartesiano(int foodX, int foodY) {
		this.foodX = foodX;
		this.foodY = foodY;
		this.dimX = foodX;
		this.dimY = foodY;
	}
	
	
	
	public int getDimX() {
		return dimX;
	}



	public void setDimX(int dimX) {
		this.dimX = dimX;
	}



	public int getDimY() {
		return dimY;
	}



	public void setDimY(int dimY) {
		this.dimY = dimY;
	}



	public int getFoodX() {
		return foodX;
	}



	public void setFoodX(int foodX) {
		this.foodX = foodX;
	}



	public int getFoodY() {
		return foodY;
	}



	public void setFoodY(int foodY) {
		this.foodY = foodY;
	}


	public void adicionarRobos(Robo... robos) {
		this.robos.addAll(Arrays.asList(robos));
	}
	
	public void expandirX() {
		dimX++;
	}
	
	public void expandirY() {
	   dimY++;	
	}
	
	public boolean verificarVitoria(Robo robo) {
		if(robo.getPosX() == foodX && robo.getPosY() == foodY)
		{
			return true;
		}
		return false;
	}
}
