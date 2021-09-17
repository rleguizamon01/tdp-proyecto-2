package tetrimino;

import utilidad.Position;

public class TetriminoO extends Tetrimino {

	public TetriminoO() {
		super();
		tipoTetrimino = 'O';
	}
	
	public TetriminoO(Bloque a, Bloque b, Bloque c, Bloque d) {
		super(a, b, c, d);
		tipoTetrimino = 'O';
	}
	
	@Override
	protected Position[] rotacionCero() {
		return getPosicionesActuales();
	}

	@Override
	protected Position[] rotacionNoventa() {
		return getPosicionesActuales();
	}

	@Override
	protected Position[] rotacionCienOchenta() {
		return getPosicionesActuales();
	}

	@Override
	protected Position[] rotacionDosSetenta() {
		return getPosicionesActuales();
	}

}
