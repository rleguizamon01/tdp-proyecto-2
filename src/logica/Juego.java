package logica;

import gui.GUI;
import utilidad.*;

public class Juego {
	protected int puntaje;
	protected int dificultad;
	protected GUI miGui;
	protected Grilla miGrilla;
	protected Reloj miReloj;
	
	public Juego(GUI gui) {
		this.miGui = gui;
		this.miGrilla = new Grilla(this);
		this.miReloj = new Reloj(this);
		this.puntaje=0;
	}
	
	public void iniciarPartida() {
		this.miReloj.start();
	}
	
	public void finalizarPartida() {
		this.miReloj.setSePuedeJugar(false);
	}
	
	public void pedirRotarDerecha() {
		this.miGrilla.rotarDerecha();
	}
	
	public void pedirRotarIzquierda() {
		this.miGrilla.rotarIzquierda();
	}
	
	public void pedirMoverIzquierda() {
		this.miGrilla.moverIzquierda();
	}
	
	public void pedirMoverDerecha() {
		this.miGrilla.moverDerecha();
	}
	
	public void pedirMoverAbajo() {
		this.miGrilla.moverAbajo();
	}
	
	public void pedirActualizar(Position pos, String imagePath) {
		this.miGui.actualizar(pos, imagePath);
	}
	
	public void pedirActualizar(Position[] pos, String imagePath) {
		
		for (Position p: pos) {
			this.miGui.actualizar(p, imagePath);
		}
		
	}
	
	public void actualizarTiempo(String tiempoNuevo) {
		miGui.actualizarTiempo(tiempoNuevo);
	}
	
	public void actualizarPuntaje(int puntosNuevos) {
		puntaje+=puntosNuevos;
		this.miGui.actualizarPuntaje(Integer.toString(puntaje));
	}
	
	public void actualizarSiguienteTetrimino(String nuevoTetrimino) {
		this.miGui.actualizarSiguienteTetrimino(nuevoTetrimino);
	}
	
}
