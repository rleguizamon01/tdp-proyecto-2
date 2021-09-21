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
	}
	
	public void iniciarPartida() {
		this.miReloj.run();
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
	
	/**public void actualizarTiempo(String tiempoNuevo) {
		this.miReloj.setIntervalo(tiempoNuevo);
	}**/
	
	public void actualizarPuntaje(int puntajeNuevo) {
		this.miGui.actualizarPuntaje(Integer.toString(puntajeNuevo));
	}
	
	/**public void actualizarSiguienteTetrimino(String nuevoTetrimino) {
		this.miGrilla.generarSiguienteTetrimino();
	}**/
}
