package tetrimino;

import utilidad.*;

public abstract class Tetrimino {
	protected int anguloActual; //Nota: Se espera que solo se varie entre 0, 90, 180 y 270.
	protected char tipoTetrimino; //Se utiliza para concatenar en el path y obtener la imagen correspondiente al tetrimino.
	
	protected Bloque bloqueA;
	protected Bloque bloqueB;
	protected Bloque bloqueC;
	protected Bloque bloqueD;
	
	public Tetrimino() {
		anguloActual = 0;
		bloqueA = null;
		bloqueB = null;
		bloqueC = null;
		bloqueD = null;
	}
	
	public Tetrimino(Bloque a, Bloque b, Bloque c, Bloque d) {
		anguloActual = 0;
		
		bloqueA = a;
		bloqueB = b;
		bloqueC = c;
		bloqueD = d;
	}

	public int getAnguloActual() {
		return anguloActual;
	}

	public Bloque[] getBloquesActuales() {
		Bloque[] arregloDeBloques = new Bloque[4];
		
		arregloDeBloques[0] = bloqueA;
		arregloDeBloques[1] = bloqueB;
		arregloDeBloques[2] = bloqueC;
		arregloDeBloques[3] = bloqueD;
		
		return arregloDeBloques;
	}
	
	public Position[] getPosicionesActuales() {
		Position[] posiciones = new Position[4];
		int i = 0;
		
		for (Bloque b : getBloquesActuales()) {
			posiciones[i] = b.getPosicion();
			i++;
		}
		
		return posiciones;
	}
	
	public Position[] getPosicionesAbajo() {
		Position[] posicionesAbajo = new Position[4];
		int i = 0;
		
		for (Bloque b : getBloquesActuales()) {
			posicionesAbajo[i] = new Position(b.getPosicion().getFila(), b.getPosicion().getColumna()+1);
			i++;
		}
		
		return posicionesAbajo;
	}
	
	public Position[] getPosicionesIzquierda() {
		Position[] posicionesIzquierda = new Position[4];
		int i = 0;
		
		for (Bloque b : getBloquesActuales()) {
			posicionesIzquierda[i] = new Position(b.getPosicion().getFila()-1, b.getPosicion().getColumna());
			i++;
		}
		
		return posicionesIzquierda;
	}
	
	public Position[] getPosicionesDerecha() {
		Position[] posicionesDerecha = new Position[4];
		int i = 0;
		
		for (Bloque b : getBloquesActuales()) {
			posicionesDerecha[i] = new Position(b.getPosicion().getFila()+1, b.getPosicion().getColumna());
			i++;
		}
		
		return posicionesDerecha;
	}
	
	public Position[] getRotacionIzquierda() {
		Position[] posicionesRotadas = null;
		
		switch(anguloActual) {
			case 0:
				posicionesRotadas = rotacionNoventa();
				break;
			case 90:
				posicionesRotadas = rotacionCienOchenta();
				break;
			case 180:
				posicionesRotadas = rotacionDosSetenta();
				break;
			case 270:
				posicionesRotadas = rotacionCero();
				break;
		}
		
		return posicionesRotadas;
	}
	
	public Position[] getRotacionDerecha() {
		Position[] posicionesRotadas = null;
		
		switch(anguloActual) {
			case 0:
				posicionesRotadas = rotacionDosSetenta();
				break;
			case 90:
				posicionesRotadas = rotacionCero();
				break;
			case 180:
				posicionesRotadas = rotacionNoventa();
				break;
			case 270:
				posicionesRotadas = rotacionCienOchenta();
				break;
		}
		
		return posicionesRotadas;
		
	}
	
	public void hacerEstatico() {
		for (Bloque b : getBloquesActuales()) {
			b.hacerEstatico();
		}
	}

	public void setAnguloActual(int anguloActual) {
		this.anguloActual = anguloActual;
	}

	public void setBloqueA(Bloque bloqueA) {
		this.bloqueA = bloqueA;
	}

	public void setBloqueB(Bloque bloqueB) {
		this.bloqueB = bloqueB;
	}

	public void setBloqueC(Bloque bloqueC) {
		this.bloqueC = bloqueC;
	}

	public void setBloqueD(Bloque bloqueD) {
		this.bloqueD = bloqueD;
	}
	
	public String getCaminoImagenColor() {
		return "/assets/images/bloque" + tipoTetrimino +".png";
	}
	
	public String getCaminoImagenTetrimino() {
		return "/assets/images/tetrimino" + tipoTetrimino +".png";
	}
	
	public void actualizarCaminoImagen() {
		String aux = getCaminoImagenColor();
		
		bloqueA.setCaminoImagen(aux);
		bloqueB.setCaminoImagen(aux);
		bloqueC.setCaminoImagen(aux);
		bloqueD.setCaminoImagen(aux);
	}
	
	//Las rotaciones funcionan tomando a BloqueB como ancla (pues nunca cambia su posicion al rotar)
	//Y expresando las posiciones de todos los otros bloques en funcion de el.
	protected abstract Position[] rotacionCero();
	protected abstract Position[] rotacionNoventa();
	protected abstract Position[] rotacionCienOchenta();
	protected abstract Position[] rotacionDosSetenta();
	public abstract Tetrimino clone();
}
