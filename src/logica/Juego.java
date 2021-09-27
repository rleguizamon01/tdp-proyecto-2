package logica;

import gui.GUI;
import utilidad.*;

public class Juego {
	private static final boolean MUSICA_POR_DEFECTO = true; //Cambiar esta constante a false si la musica se vuelve molesta durante la correccion!
	
	protected int puntaje;
	protected int dificultad;
	protected GUI miGui;
	protected Grilla miGrilla;
	protected Reloj miReloj;
	
	private boolean hayMusica;
	private AudioPlayer miAudioPlayer;
	
	public Juego(GUI gui) {
		this.miGui = gui;
		this.miGrilla = new Grilla(this);
		this.miReloj = new Reloj(this);
		this.puntaje = 0;
		this.miAudioPlayer = new AudioPlayer("soundtrack.mp3");
		this.hayMusica = MUSICA_POR_DEFECTO;
	}
	
	public void iniciarPartida() {
		if(MUSICA_POR_DEFECTO) {
			miAudioPlayer.start();
		}
		
		this.miReloj.start();
	}
	
	public void finalizarPartida() {
		terminarMusica();
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
	
	public void iniciarMusica() {
		if(!hayMusica) {
			miAudioPlayer = new AudioPlayer("soundtrack.mp3");
			miAudioPlayer.start();
			hayMusica = !hayMusica;
		}
	}
	
	public void terminarMusica() {
		if(hayMusica) {
			miAudioPlayer.stop();
			miAudioPlayer = null;
			hayMusica = !hayMusica;
		}
	}
	
	public boolean hayMusica() {
		return hayMusica;
	}
	
	public void setMusica(boolean b) {
		hayMusica = b;
	}
	
}
