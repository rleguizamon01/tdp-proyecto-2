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
		bloqueA = null;
		bloqueB = null;
		bloqueC = null;
		bloqueD = null;
	}
	
	public Tetrimino(Bloque a, Bloque b, Bloque c, Bloque d) {
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
	
	public Position[] getPosicionesAbajo() {
		Position[] posicionesAbajo = new Position[4];
		int i = 0;
		
		for (Bloque b : getBloquesActuales()) {
			posicionesAbajo[i] = new Position(b.getPosicion().getX(), b.getPosicion().getY()+1);
			i++;
		}
		
		return posicionesAbajo;
	}
	
	public Position[] getPosicionesIzquierda() {
		Position[] posicionesIzquierda = new Position[4];
		int i = 0;
		
		for (Bloque b : getBloquesActuales()) {
			posicionesIzquierda[i] = new Position(b.getPosicion().getX()-1, b.getPosicion().getY());
			i++;
		}
		
		return posicionesIzquierda;
	}
	
	public Position[] getPosicionesDerecha() {
		Position[] posicionesDerecha = new Position[4];
		int i = 0;
		
		for (Bloque b : getBloquesActuales()) {
			posicionesDerecha[i] = new Position(b.getPosicion().getX()+1, b.getPosicion().getY());
			i++;
		}
		
		return posicionesDerecha;
	}
	
	public abstract Position[] getRotacionIzquierda();
	
	public abstract Position[] getRotacionDerecha();
	
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
}
