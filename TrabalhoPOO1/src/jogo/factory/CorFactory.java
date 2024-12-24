package jogo.factory;

import jogo.modelo.Cor;

public class CorFactory {
	
	public static Cor instanciarCor(int id) {
		switch(id) {
		  case 1:
              return new Cor("azul");
          case 2:
              return new Cor("vermelho");
          case 3:
              return new Cor("rosa");
          case 4:
              return new Cor("amarelo");
          case 5:
              return new Cor("verde");
          case 6:
              return new Cor("roxo");
          default:
              throw new IllegalArgumentException("ID de cor inválido: " + id);
		}
	}
	
}
