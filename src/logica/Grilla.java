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
		
	}
	
	private boolean posicionesLibres(Position[] ps) {
		return false;
	}
	
	private boolean perdio() {
		return false;
	}
	
	private boolean lineaCompleta(int linea) {
		return false;
	}
	
	private void borrarFilas(Bloque[] bs) {
		
	}
	
	private void generarSiguienteTetrimino() {
		
	}
	
}
