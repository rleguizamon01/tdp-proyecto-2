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
		Position position;
		
		matrizGrilla = new Bloque[10][21];
		
		for (int i = 0; i < matrizGrilla.length; i++) {
			
			for (int j = 0; j < matrizGrilla[0].length; j++) {
				
				bloque = new Bloque();
				position = new Position(i,j);
				
				bloque.getPosicion().setFila(i);
				bloque.getPosicion().setColumna(j);
				
				matrizGrilla[i][j] = bloque;
				
			}
		}
		
		this.miJuego = miJuego;
		
		tetriminoActual = new TetriminoT(matrizGrilla[4][0], matrizGrilla[4][1], matrizGrilla[3][1], matrizGrilla[5][1]);
		
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
