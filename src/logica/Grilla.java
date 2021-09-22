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
		tetriminoActual.setAnguloActual(0);
		
		for(Bloque b : tetriminoActual.getBloquesActuales()) {
			miJuego.pedirActualizar(b.getPosicion(), b.getCaminoImagen());
		}
		
		generarSiguienteTetrimino();
		
	}
	
	public void rotarDerecha() {
		Position[] posicionesActuales = tetriminoActual.getPosicionesActuales();
		Position[] posicionesRotadas = tetriminoActual.getRotacionDerecha();

		//posicionesLibres(posicionesRotadas)
		if(posicionesLibres(posicionesRotadas)) {
			Bloque[] bloquesOriginales = tetriminoActual.getBloquesActuales();
			Bloque[] bloquesNuevos = {
					matrizGrilla[posicionesRotadas[0].getFila()][posicionesRotadas[0].getColumna()],
					matrizGrilla[posicionesRotadas[1].getFila()][posicionesRotadas[1].getColumna()],
					matrizGrilla[posicionesRotadas[2].getFila()][posicionesRotadas[2].getColumna()],
					matrizGrilla[posicionesRotadas[3].getFila()][posicionesRotadas[3].getColumna()]
			};
			
			//Efectuamos la Rotacion.
			
			//Primero, desocupamos los bloques actuales.
			for(Bloque b : bloquesOriginales) {
				b.desocupar();
				b.setCaminoImagen("/assets/images/bloqueVacio.png");
			}
			
			//Segundo, ocupamos los bloques nuevos.
			tetriminoActual.setBloqueA(bloquesNuevos[0]);
			tetriminoActual.setBloqueB(bloquesNuevos[1]);
			tetriminoActual.setBloqueC(bloquesNuevos[2]);
			tetriminoActual.setBloqueD(bloquesNuevos[3]);
			
			for(Bloque b : bloquesNuevos) {
				b.ocupar();
				b.setCaminoImagen(tetriminoActual.getCaminoImagenColor());
			}
			
			//Tercero, ajustamos el angulo:
			int angulo = tetriminoActual.getAnguloActual();
			switch(angulo) {
				case 0:
					tetriminoActual.setAnguloActual(270);
					break;
				case 90:
					tetriminoActual.setAnguloActual(0);
					break;
				case 180:
					tetriminoActual.setAnguloActual(90);
					break;
				case 270:
					tetriminoActual.setAnguloActual(180);
					break;
					
			}
			
			//Cuarto, le pedimos a Juego que le pida a la GUI que actualice las imagenes de los cambios que hicimos.
			for(Bloque b : bloquesOriginales) {
				miJuego.pedirActualizar(b.getPosicion(), b.getCaminoImagen());
			}
			for(Bloque b : bloquesNuevos) {
				miJuego.pedirActualizar(b.getPosicion(), b.getCaminoImagen());
			}
		}
	}
	
	public void rotarIzquierda() {
		Position[] posicionesActuales = tetriminoActual.getPosicionesActuales();
		Position[] posicionesRotadas = tetriminoActual.getRotacionIzquierda();

		//posicionesLibres(posicionesRotadas)
		if(posicionesLibres(posicionesRotadas)) {
			Bloque[] bloquesOriginales = tetriminoActual.getBloquesActuales();
			Bloque[] bloquesNuevos = {
					matrizGrilla[posicionesRotadas[0].getFila()][posicionesRotadas[0].getColumna()],
					matrizGrilla[posicionesRotadas[1].getFila()][posicionesRotadas[1].getColumna()],
					matrizGrilla[posicionesRotadas[2].getFila()][posicionesRotadas[2].getColumna()],
					matrizGrilla[posicionesRotadas[3].getFila()][posicionesRotadas[3].getColumna()]
			};
			
			//Efectuamos la Rotacion.
			
			//Primero, desocupamos los bloques actuales.
			for(Bloque b : bloquesOriginales) {
				b.desocupar();
				b.setCaminoImagen("/assets/images/bloqueVacio.png");
			}
			
			//Segundo, ocupamos los bloques nuevos.
			tetriminoActual.setBloqueA(bloquesNuevos[0]);
			tetriminoActual.setBloqueB(bloquesNuevos[1]);
			tetriminoActual.setBloqueC(bloquesNuevos[2]);
			tetriminoActual.setBloqueD(bloquesNuevos[3]);
			
			for(Bloque b : bloquesNuevos) {
				b.ocupar();
				b.setCaminoImagen(tetriminoActual.getCaminoImagenColor());
			}
			
			//Tercero, ajustamos el angulo:
			int angulo = tetriminoActual.getAnguloActual();
			switch(angulo) {
				case 0:
					tetriminoActual.setAnguloActual(90);
					break;
				case 90:
					tetriminoActual.setAnguloActual(180);
					break;
				case 180:
					tetriminoActual.setAnguloActual(270);
					break;
				case 270:
					tetriminoActual.setAnguloActual(0);
					break;
					
			}
			
			//Cuarto, le pedimos a Juego que le pida a la GUI que actualice las imagenes de los cambios que hicimos.
			for(Bloque b : bloquesOriginales) {
				miJuego.pedirActualizar(b.getPosicion(), b.getCaminoImagen());
			}
			for(Bloque b : bloquesNuevos) {
				miJuego.pedirActualizar(b.getPosicion(), b.getCaminoImagen());
			}
		}
	}
	
	public void moverIzquierda() {
		Position[] posicionesActuales = tetriminoActual.getPosicionesActuales();
		Position[] posicionesCorridas = tetriminoActual.getPosicionesIzquierda();
		
		//posicionesLibres(posicionesCorridas)
		if(posicionesLibres(posicionesCorridas)) {
			Bloque[] bloquesOriginales = tetriminoActual.getBloquesActuales();
			Bloque[] bloquesNuevos = {
					matrizGrilla[posicionesCorridas[0].getFila()][posicionesCorridas[0].getColumna()],
					matrizGrilla[posicionesCorridas[1].getFila()][posicionesCorridas[1].getColumna()],
					matrizGrilla[posicionesCorridas[2].getFila()][posicionesCorridas[2].getColumna()],
					matrizGrilla[posicionesCorridas[3].getFila()][posicionesCorridas[3].getColumna()]
			};
			
			//Efectuamos la Rotacion.
			
			//Primero, desocupamos los bloques actuales.
			for(Bloque b : bloquesOriginales) {
				b.desocupar();
				b.setCaminoImagen("/assets/images/bloqueVacio.png");
			}
			
			//Segundo, ocupamos los bloques nuevos.
			tetriminoActual.setBloqueA(bloquesNuevos[0]);
			tetriminoActual.setBloqueB(bloquesNuevos[1]);
			tetriminoActual.setBloqueC(bloquesNuevos[2]);
			tetriminoActual.setBloqueD(bloquesNuevos[3]);
			
			for(Bloque b : bloquesNuevos) {
				b.ocupar();
				b.setCaminoImagen(tetriminoActual.getCaminoImagenColor());
			}
			
			//Tercero, le pedimos a Juego que le pida a la GUI que actualice las imagenes de los cambios que hicimos.
			for(Bloque b : bloquesOriginales) {
				miJuego.pedirActualizar(b.getPosicion(), b.getCaminoImagen());
			}
			for(Bloque b : bloquesNuevos) {
				miJuego.pedirActualizar(b.getPosicion(), b.getCaminoImagen());
			}
		}
	}
	
	public void moverDerecha() {
		Position[] posicionesActuales = tetriminoActual.getPosicionesActuales();
		Position[] posicionesCorridas = tetriminoActual.getPosicionesDerecha();

		//posicionesLibres(posicionesCorridas)
		if(posicionesLibres(posicionesCorridas)) {
			Bloque[] bloquesOriginales = tetriminoActual.getBloquesActuales();
			Bloque[] bloquesNuevos = {
					matrizGrilla[posicionesCorridas[0].getFila()][posicionesCorridas[0].getColumna()],
					matrizGrilla[posicionesCorridas[1].getFila()][posicionesCorridas[1].getColumna()],
					matrizGrilla[posicionesCorridas[2].getFila()][posicionesCorridas[2].getColumna()],
					matrizGrilla[posicionesCorridas[3].getFila()][posicionesCorridas[3].getColumna()]
			};
			
			//Efectuamos la Rotacion.
			
			//Primero, desocupamos los bloques actuales.
			for(Bloque b : bloquesOriginales) {
				b.desocupar();
				b.setCaminoImagen("/assets/images/bloqueVacio.png");
			}
			
			//Segundo, ocupamos los bloques nuevos.
			tetriminoActual.setBloqueA(bloquesNuevos[0]);
			tetriminoActual.setBloqueB(bloquesNuevos[1]);
			tetriminoActual.setBloqueC(bloquesNuevos[2]);
			tetriminoActual.setBloqueD(bloquesNuevos[3]);
			
			for(Bloque b : bloquesNuevos) {
				b.ocupar();
				b.setCaminoImagen(tetriminoActual.getCaminoImagenColor());
			}
			
			//Tercero, le pedimos a Juego que le pida a la GUI que actualice las imagenes de los cambios que hicimos.
			for(Bloque b : bloquesOriginales) {
				miJuego.pedirActualizar(b.getPosicion(), b.getCaminoImagen());
			}
			for(Bloque b : bloquesNuevos) {
				miJuego.pedirActualizar(b.getPosicion(), b.getCaminoImagen());
			}
		}
	}
	
	public void moverAbajo() {
		Position[] posicionesActuales = tetriminoActual.getPosicionesActuales();
		Position[] posicionesCorridas = tetriminoActual.getPosicionesAbajo();

		//posicionesLibres(posicionesCorridas)
		if(posicionesLibres(posicionesCorridas)) {
			Bloque[] bloquesOriginales = tetriminoActual.getBloquesActuales();
			Bloque[] bloquesNuevos = {
					matrizGrilla[posicionesCorridas[0].getFila()][posicionesCorridas[0].getColumna()],
					matrizGrilla[posicionesCorridas[1].getFila()][posicionesCorridas[1].getColumna()],
					matrizGrilla[posicionesCorridas[2].getFila()][posicionesCorridas[2].getColumna()],
					matrizGrilla[posicionesCorridas[3].getFila()][posicionesCorridas[3].getColumna()]
			};
			
			//Efectuamos la Rotacion.
			
			//Primero, desocupamos los bloques actuales.
			for(Bloque b : bloquesOriginales) {
				b.desocupar();
				b.setCaminoImagen("/assets/images/bloqueVacio.png");
			}
			
			//Segundo, ocupamos los bloques nuevos.
			tetriminoActual.setBloqueA(bloquesNuevos[0]);
			tetriminoActual.setBloqueB(bloquesNuevos[1]);
			tetriminoActual.setBloqueC(bloquesNuevos[2]);
			tetriminoActual.setBloqueD(bloquesNuevos[3]);
			
			for(Bloque b : bloquesNuevos) {
				b.ocupar();
				b.setCaminoImagen(tetriminoActual.getCaminoImagenColor());
			}
			
			//Tercero, le pedimos a Juego que le pida a la GUI que actualice las imagenes de los cambios que hicimos.
			for(Bloque b : bloquesOriginales) {
				miJuego.pedirActualizar(b.getPosicion(), b.getCaminoImagen());
			}
			for(Bloque b : bloquesNuevos) {
				miJuego.pedirActualizar(b.getPosicion(), b.getCaminoImagen());
			}
		} else {
			tetriminoActual.hacerEstatico();
			if(perdio()) {
				System.out.println("F");
				miJuego.finalizarPartida();
			} else {
				//Checkeo de Filas
			}
		}
	}
	
	/**public void moverAbajo() {
		
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
		
	}**/
	
	private boolean posicionesLibres(Position[] ps) {
		
		boolean result = true;
		int fila,columna;
		
		for(int i = 0; (i < ps.length) && result; i++) {
			fila=ps[i].getFila();
			columna=ps[i].getColumna();
			
			if(0<=fila && fila<=21 && 0<=columna && columna<=10) {
				if (!(matrizGrilla[fila][columna].estaLibre())) {
					result = false;
				}
			} else {
				result = false;
			}
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
