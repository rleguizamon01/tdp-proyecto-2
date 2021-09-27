package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import tetrimino.*;
import utilidad.Position;

public class Grilla {
	
	protected Bloque[][] matrizGrilla;
	protected Tetrimino tetriminoActual;
	protected Tetrimino tetriminoSiguiente;
	protected Juego miJuego;
	
	private Tetrimino[] posiblesTetriminos;
	private Position[][] posiblesPosicionesIniciales;

	private void inicializar() {
		Tetrimino I = new TetriminoI();
		Tetrimino J = new TetriminoJ();
		Tetrimino L = new TetriminoL();
		Tetrimino O = new TetriminoO();
		Tetrimino S = new TetriminoS();
		Tetrimino T = new TetriminoT();
		Tetrimino Z = new TetriminoZ();
		
		posiblesTetriminos = new Tetrimino[7];
		posiblesTetriminos[0] = I;
		posiblesTetriminos[1] = J;
		posiblesTetriminos[2] = L;
		posiblesTetriminos[3] = O;
		posiblesTetriminos[4] = S;
		posiblesTetriminos[5] = T;
		posiblesTetriminos[6] = Z;
		
		Position[] posInicialesI = {new Position(3, 1), new Position(4, 1), new Position(5, 1), new Position(6,1)};
		Position[] posInicialesJ = {new Position(5, 1), new Position(4, 1), new Position(3, 1), new Position(3, 0)};
		Position[] posInicialesL = {new Position(3, 1), new Position(4, 1), new Position(5, 1), new Position(5, 0)};
		Position[] posInicialesO = {new Position(3, 1), new Position(4, 1), new Position(3, 2), new Position(4, 2)};
		Position[] posInicialesS = {new Position(3, 1), new Position(4, 1), new Position(4, 0), new Position(5, 0)};
		Position[] posInicialesT = {new Position(3, 1), new Position(4, 1), new Position(5, 1), new Position(4, 0)};
		Position[] posInicialesZ = {new Position(5, 1), new Position(4, 1), new Position(4, 0), new Position(3, 0)};
		
		posiblesPosicionesIniciales = new Position[7][4];
		posiblesPosicionesIniciales[0] = posInicialesI;
		posiblesPosicionesIniciales[1] = posInicialesJ;
		posiblesPosicionesIniciales[2] = posInicialesL;
		posiblesPosicionesIniciales[3] = posInicialesO;
		posiblesPosicionesIniciales[4] = posInicialesS;
		posiblesPosicionesIniciales[5] = posInicialesT;
		posiblesPosicionesIniciales[6] = posInicialesZ;
	}
	
	private Tetrimino getRandomTetrimino() {
		Tetrimino t;
		int i = ThreadLocalRandom.current().nextInt(0, 7); 
		// int i = 0; // Descomentar para testear con únicamente bloque I
		t = posiblesTetriminos[i].clone();
		Position[] pos = posiblesPosicionesIniciales[i];
		
		t.setBloqueA(matrizGrilla[pos[0].getFila()][pos[0].getColumna()]);
		t.setBloqueB(matrizGrilla[pos[1].getFila()][pos[1].getColumna()]);
		t.setBloqueC(matrizGrilla[pos[2].getFila()][pos[2].getColumna()]);
		t.setBloqueD(matrizGrilla[pos[3].getFila()][pos[3].getColumna()]);
		
		for(Bloque b : t.getBloquesActuales()) {
			//Por ahora, los bloques de este tetrimino no existen. Seran actualizados cuando este pase a ser el siguiente.
			b.setCaminoImagen("/assets/images/bloqueVacio.png");
		}
		
		//t.actualizarCaminoImagen();
		
		return t;
	}
	
	public Grilla(Juego miJuego) {
		inicializar();
		
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
		
		tetriminoActual = getRandomTetrimino();
		tetriminoSiguiente = getRandomTetrimino();
		
		miJuego.actualizarSiguienteTetrimino(tetriminoSiguiente.getCaminoImagenTetrimino());
		
		for(Bloque b : tetriminoActual.getBloquesActuales()) {
			b.ocupar();
			b.setCaminoImagen(tetriminoActual.getCaminoImagenColor());
			miJuego.pedirActualizar(b.getPosicion(), b.getCaminoImagen());
		}
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
				int filas[] = {posicionesActuales[0].getColumna(),posicionesActuales[1].getColumna(),posicionesActuales[2].getColumna(),posicionesActuales[3].getColumna()};
				ArrayList<Integer> filasSinRepeticion = eliminarRepeticionFilas(filas);
				ArrayList<Integer> filasCompletadas = filasCompletas(filasSinRepeticion);
				if(filasCompletadas.size() > 0)
					eliminarFilasCompletadas(filasCompletadas);
				
				generarSiguienteTetrimino();
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

            if(0<=fila && fila<10 && 0<=columna && columna<21) {
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
    	//Si NO se puede generar el siguiente tetrimino, el jugador perdio su partida.
    	return !posicionesLibres(tetriminoSiguiente.getPosicionesActuales());
    }
	
	/**
	 * Elimina las filas que están completadas
	 * @param filasCompletadas filas a borrar
	 */
	private void eliminarFilasCompletadas(ArrayList<Integer> filasCompletadas) {
		int saltoDeFila = 1;
		int cantFilasCompletadas = filasCompletadas.size();
		ArrayList<Integer> filasCompletadasDesc = filasCompletadas;
		Collections.sort(filasCompletadasDesc, Collections.reverseOrder());  
		
		// Comienza el bucle por la fila completada que está "más abajo" (la mayor) y va subiendo
		for(int fila = filasCompletadasDesc.get(0); fila >= 0; fila--) {
			// Si la fila recorrida es de las superiores (más arriba) se debe desocupar, simulando que crea una fila nueva
			if(fila < cantFilasCompletadas)
				desocuparFila(fila);
			// Sino, debe copiar a una fila superior que no sea otra fila completa (si lo es, aumenta el salto de fila para esquivarla)
			else {
				while(filasCompletadas.contains(fila - saltoDeFila))
					saltoDeFila++;
				copiarFila(fila, fila - saltoDeFila);
			}
		}
	}
	
	/**
     * Chequea si determinadas filas están completas y devuelve un ArrayList de las que sí lo están
     * @param filasTotalesAfectadas filas a chequear si están completas o no
     * @return ArrayList de las filas que sí están completas
     */
	private ArrayList<Integer> filasCompletas(ArrayList<Integer> filasTotalesAfectadas) {
		ArrayList<Integer> filasCompletadas = new ArrayList<Integer>();
		boolean estaCompleta;
		
		for(int filaAfectada: filasTotalesAfectadas) {
			estaCompleta = true;
			for(int columna = 0; columna < matrizGrilla.length && estaCompleta; columna++)
				if(matrizGrilla[columna][filaAfectada].estaLibre())
					estaCompleta = false;
			if(estaCompleta)
				filasCompletadas.add(filaAfectada);
		}
		return filasCompletadas;
	}
	
	/**
	 * Filtra las repeticiones de filas dentro de un arreglo y devuelve un ArrayList sin las repeticiones
	 * @param filas arreglo de filas con posibles elementos repetidos
	 * @return ArrayList de filas sin repeticiones
	 */
	private ArrayList<Integer> eliminarRepeticionFilas(int[] filas) {
		ArrayList<Integer> filasSinRepeticion = new ArrayList<Integer>();
		
		for(int fila: filas)
			if(!filasSinRepeticion.contains(fila))
				filasSinRepeticion.add(fila);
	
		return filasSinRepeticion;
	}

	/**
	 * Modifica una fila entera copiando el valor de otra y llama a Juego para notificar el cambio
	 * @param filaOrigen fila que se va a modificar copiando los valores de filaACopiar
	 * @param filaACopiar fila cuyos valores son copiados
	 */
	private void copiarFila(int filaOrigen, int filaACopiar) {
		for(int columna = 0; columna < matrizGrilla.length; columna++) {
			matrizGrilla[columna][filaOrigen].copy(matrizGrilla[columna][filaACopiar]);
			miJuego.pedirActualizar(matrizGrilla[columna][filaOrigen].getPosicion(), matrizGrilla[columna][filaOrigen].getCaminoImagen());
		}
	}
	
	/**
	 * Modifica una fila entera para que cada bloque esté desocupado y muestre un bloque vacío, y notifica a Juego el cambio
	 * @param fila fila a desocupar
	 */
	private void desocuparFila(int fila) {
		for(int columna = 0; columna < matrizGrilla.length; columna++) {
			matrizGrilla[columna][fila].desocupar();
			matrizGrilla[columna][fila].setCaminoImagen("/assets/images/bloqueVacio.png");
			miJuego.pedirActualizar(matrizGrilla[columna][fila].getPosicion(), matrizGrilla[columna][fila].getCaminoImagen());
		}
	}
	
	private void generarSiguienteTetrimino() {
		tetriminoActual = tetriminoSiguiente;
		tetriminoActual.actualizarCaminoImagen();
		tetriminoSiguiente = getRandomTetrimino();
		
		miJuego.actualizarSiguienteTetrimino(tetriminoSiguiente.getCaminoImagenTetrimino());
		
		for(Bloque b : tetriminoActual.getBloquesActuales()) {
			b.ocupar();
			b.setCaminoImagen(tetriminoActual.getCaminoImagenColor());
			miJuego.pedirActualizar(b.getPosicion(), b.getCaminoImagen());
		}
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
