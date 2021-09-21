package tetrimino;

import utilidad.Position;

public class TetriminoT extends Tetrimino {
	
	public TetriminoT() {
		super();
		tipoTetrimino = 'T';
	}
	
	public TetriminoT(Bloque a, Bloque b, Bloque c, Bloque d) {
		super(a, b, c, d);
		tipoTetrimino = 'T';
		actualizarCaminoImagen();
	}

	@Override
	protected Position[] rotacionCero() {
		Position[] nuevasPosiciones = new Position[4];
		Position[] actualesPosiciones = getPosicionesActuales();
		
		nuevasPosiciones[0] = new Position(actualesPosiciones[1].getFila(), actualesPosiciones[1].getColumna()-1);
		nuevasPosiciones[1] = new Position(actualesPosiciones[1].getFila(), actualesPosiciones[1].getColumna());
		nuevasPosiciones[2] = new Position(actualesPosiciones[1].getFila(), actualesPosiciones[1].getColumna()+1);
		nuevasPosiciones[3] = new Position(actualesPosiciones[1].getFila()-1, actualesPosiciones[1].getColumna());
		
		return nuevasPosiciones;
	}
	
	@Override
	protected Position[] rotacionNoventa() {
		Position[] nuevasPosiciones = new Position[4];
		Position[] actualesPosiciones = getPosicionesActuales();
		
		nuevasPosiciones[0] = new Position(actualesPosiciones[1].getFila()+1, actualesPosiciones[1].getColumna());
		nuevasPosiciones[1] = new Position(actualesPosiciones[1].getFila(), actualesPosiciones[1].getColumna());
		nuevasPosiciones[2] = new Position(actualesPosiciones[1].getFila()-1, actualesPosiciones[1].getColumna());
		nuevasPosiciones[3] = new Position(actualesPosiciones[1].getFila(), actualesPosiciones[1].getColumna()-1);
		
		return nuevasPosiciones;
	}

	@Override
	protected Position[] rotacionCienOchenta() {
		Position[] nuevasPosiciones = new Position[4];
		Position[] actualesPosiciones = getPosicionesActuales();
		
		nuevasPosiciones[0] = new Position(actualesPosiciones[1].getFila(), actualesPosiciones[1].getColumna()+1);
		nuevasPosiciones[1] = new Position(actualesPosiciones[1].getFila(), actualesPosiciones[1].getColumna());
		nuevasPosiciones[2] = new Position(actualesPosiciones[1].getFila(), actualesPosiciones[1].getColumna()-1);
		nuevasPosiciones[3] = new Position(actualesPosiciones[1].getFila()+1, actualesPosiciones[1].getColumna());
		
		return nuevasPosiciones;
	}

	@Override
	protected Position[] rotacionDosSetenta() {
		Position[] nuevasPosiciones = new Position[4];
		Position[] actualesPosiciones = getPosicionesActuales();
		
		nuevasPosiciones[0] = new Position(actualesPosiciones[1].getFila()-1, actualesPosiciones[1].getColumna());
		nuevasPosiciones[1] = new Position(actualesPosiciones[1].getFila(), actualesPosiciones[1].getColumna());
		nuevasPosiciones[2] = new Position(actualesPosiciones[1].getFila()+1, actualesPosiciones[1].getColumna());
		nuevasPosiciones[3] = new Position(actualesPosiciones[1].getFila(), actualesPosiciones[1].getColumna()+1);
		
		return nuevasPosiciones;
	}

}
