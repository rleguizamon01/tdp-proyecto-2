package tetrimino;

import utilidad.Position;

public class TetriminoJ extends Tetrimino {

	public TetriminoJ() {
		super();
		tipoTetrimino = 'J';
	}
	
	public TetriminoJ(Bloque a, Bloque b, Bloque c, Bloque d) {
		super(a, b, c, d);
		tipoTetrimino = 'J';
	}
	
	@Override
	protected Position[] rotacionCero() {
		Position[] nuevasPosiciones = new Position[4];
		Position[] actualesPosiciones = getPosicionesActuales();
		
		nuevasPosiciones[0] = new Position(actualesPosiciones[1].getFila(), actualesPosiciones[1].getColumna()+1);
		nuevasPosiciones[1] = new Position(actualesPosiciones[1].getFila(), actualesPosiciones[1].getColumna());
		nuevasPosiciones[2] = new Position(actualesPosiciones[1].getFila(), actualesPosiciones[1].getColumna()-1);
		nuevasPosiciones[3] = new Position(actualesPosiciones[1].getFila()-1, actualesPosiciones[1].getColumna()-1);
		
		return nuevasPosiciones;
	}

	@Override
	protected Position[] rotacionNoventa() {
		Position[] nuevasPosiciones = new Position[4];
		Position[] actualesPosiciones = getPosicionesActuales();
		
		nuevasPosiciones[0] = new Position(actualesPosiciones[1].getFila()-1, actualesPosiciones[1].getColumna());
		nuevasPosiciones[1] = new Position(actualesPosiciones[1].getFila(), actualesPosiciones[1].getColumna());
		nuevasPosiciones[2] = new Position(actualesPosiciones[1].getFila()+1, actualesPosiciones[1].getColumna());
		nuevasPosiciones[3] = new Position(actualesPosiciones[1].getFila()+1, actualesPosiciones[1].getColumna()-1);
		
		return nuevasPosiciones;
	}

	@Override
	protected Position[] rotacionCienOchenta() {
		Position[] nuevasPosiciones = new Position[4];
		Position[] actualesPosiciones = getPosicionesActuales();
		
		nuevasPosiciones[0] = new Position(actualesPosiciones[1].getFila(), actualesPosiciones[1].getColumna()-1);
		nuevasPosiciones[1] = new Position(actualesPosiciones[1].getFila(), actualesPosiciones[1].getColumna());
		nuevasPosiciones[2] = new Position(actualesPosiciones[1].getFila(), actualesPosiciones[1].getColumna()+1);
		nuevasPosiciones[3] = new Position(actualesPosiciones[1].getFila()+1, actualesPosiciones[1].getColumna()+1);
		
		return nuevasPosiciones;
	}

	@Override
	protected Position[] rotacionDosSetenta() {
		Position[] nuevasPosiciones = new Position[4];
		Position[] actualesPosiciones = getPosicionesActuales();
		
		nuevasPosiciones[0] = new Position(actualesPosiciones[1].getFila()+1, actualesPosiciones[1].getColumna());
		nuevasPosiciones[1] = new Position(actualesPosiciones[1].getFila(), actualesPosiciones[1].getColumna());
		nuevasPosiciones[2] = new Position(actualesPosiciones[1].getFila()-1, actualesPosiciones[1].getColumna());
		nuevasPosiciones[3] = new Position(actualesPosiciones[1].getFila()-1, actualesPosiciones[1].getColumna()+1);
		
		return nuevasPosiciones;
	}
	
	public Tetrimino clone() {
		Tetrimino t = new TetriminoJ(bloqueA, bloqueB, bloqueC, bloqueD);
		t.setAnguloActual(anguloActual);
		return t;
	}

}
