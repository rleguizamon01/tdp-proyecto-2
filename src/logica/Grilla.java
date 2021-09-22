package logica;

import tetrimino.Bloque;
import tetrimino.Tetrimino;
import tetrimino.TetriminoT;
import utilidad.Position;

public class Grilla {
	
	protected Bloque[][] matrizGrilla;
	protected Tetrimino tetriminoActual;
	protected Tetrimino tetriminoSiguiente;
	protected Juego miJuego;

	public Grilla(Juego miJuego) {
		
		Bloque bloque;
		
		matrizGrilla = new Bloque[10][21];
		int j;
		
		for (int i = 0; i < matrizGrilla.length; i++) {
			for (j = 0; j < matrizGrilla[0].length; j++) {
				bloque = new Bloque(new Position(i, j), "/assets/images/bloqueVacio.png", false);
				
				matrizGrilla[i][j] = bloque;
			}
			
			j = 0;
		}
		
		this.miJuego = miJuego;
		
		tetriminoActual = new TetriminoT(matrizGrilla[4][0], matrizGrilla[4][1], matrizGrilla[3][1], matrizGrilla[5][1]);
		
		for(Bloque b : tetriminoActual.getBloquesActuales()) {
			miJuego.pedirActualizar(b.getPosicion(), b.getCaminoImagen());
		}
		
		generarSiguienteTetrimino();
		
	}
	
	public void rotarDerecha() {
		
	}
	
	public void rotarIzquierda() {
		
	}
	
	public void moverIzquierda() {
		
	}
	
	public void moverDerecha() {
		
	}
	
	public void moverAbajo() {
		
		Position[] posicionesActuales = tetriminoActual.getPosicionesActuales();
		Position[] posicionesAbajo = tetriminoActual.getPosicionesAbajo();
		
		if (posicionesLibres(posicionesAbajo)) {
			
			tetriminoActual.setBloqueA(matrizGrilla[posicionesAbajo[0].getFila()][posicionesAbajo[0].getColumna()]);
			tetriminoActual.setBloqueB(matrizGrilla[posicionesAbajo[1].getFila()][posicionesAbajo[1].getColumna()]);
			tetriminoActual.setBloqueC(matrizGrilla[posicionesAbajo[2].getFila()][posicionesAbajo[2].getColumna()]);
			tetriminoActual.setBloqueD(matrizGrilla[posicionesAbajo[3].getFila()][posicionesAbajo[3].getColumna()]);

			miJuego.pedirActualizar(posicionesActuales, "/assets/images/bloqueVacio.png");
			miJuego.pedirActualizar(posicionesAbajo, tetriminoActual.getBloquesActuales()[0].getCaminoImagen());
		} else {
			tetriminoActual.hacerEstatico();
		}
		
		posicionesActuales = tetriminoActual.getPosicionesActuales();
		
		if (perdio())
			miJuego.finalizarPartida();
		else {
			
			 
			int filas[] = {posicionesActuales[0].getFila(),posicionesActuales[1].getFila(),posicionesActuales[2].getFila(),posicionesActuales[3].getFila()};
			filas=filasSinRepetir(filas);
			int filasCompletas=0;
			for(int fila : filas) 
				if(fila!=-1) 
					if (lineaCompleta(fila)) {
						limpiarLinea(fila);
						filasCompletas++;
					}
			miJuego.actualizarPuntaje(cuantosPuntos(filasCompletas));
			// Hola, por aca iria el tema de actualizar las filas y desplazar todos los bloques como corresponda
		}
		
	}
	
	private boolean posicionesLibres(Position[] ps) {
		
		boolean result = true;
		int fila,columna;
		
		for(int i = 0; (i < ps.length) && result; i++) {
			fila=ps[i].getFila();
			columna=ps[i].getColumna();
			
			if(0<=fila && fila<=10 && 0<=columna && columna<=21)
				if (matrizGrilla[fila][columna].estaOcupado()) {
					result = false;
				}
			else
				result = false;
		
		}
		
		return result;
	}
	
	private boolean perdio() {
		return false;
	}
	
	private boolean lineaCompleta(int linea) {
		boolean cumple=true;
		for(int i=0;(i<matrizGrilla[linea].length) && cumple;i++) 
			if (!matrizGrilla[linea][i].estaLibre())
				cumple=false;
		return cumple;
	}
	
	private void limpiarLinea(int linea) {
		for(int i=0;(i<matrizGrilla[linea].length);i++) 
			matrizGrilla[linea][i].desocupar();
	}
	
	private void generarSiguienteTetrimino() {
		
	}
	
	// Esta funcion lo que va a hacer es que le ingresas un arreglo con todas las filas y te va a devolver el mismo arreglo pero si hay filas repetidas les va a poner un -1
	private int[] filasSinRepetir(int[] filas) {
		int nuevo[] = {-1,-1,-1,-1};
		boolean va=true;
		for(int i=0;i<4;i++) {
			va=true;
			for(int j=i+1;j<4;j++) {
				if(filas[i]==filas[j])
					va=false;
			}
			if (va)
				nuevo[i]=filas[i];
		}
		return nuevo;
	}
	//Esta funcion lo que va a hacer es calcular cuantos puntos le da al jugador dependiendo de cuantas filas hizo
	private int cuantosPuntos(int filasHechas) {
		int puntos=0;
		switch (filasHechas) {
			case 1: puntos=100;
			case 2: puntos=200;
			case 3: puntos=500;
			case 4: puntos=800;
		}
		return puntos;
	}
}
