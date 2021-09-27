package logica;

import gui.GUI;
import utilidad.*;

public class Juego {
	private static final boolean MUSICA_POR_DEFECTO = true; //Cambiar esta constante a false si la musica se vuelve molesta durante la correccion!
	
	public final int MOVER_ABAJO = 1;
	public final int MOVER_IZQUIERDA = 2;
	public final int MOVER_DERECHA = 3;
	public final int ROTAR_IZQUIERDA = 4;
	public final int ROTAR_DERECHA = 5;
	
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
	
	public synchronized void mover(int operacion) {
		switch(operacion) {
			case MOVER_ABAJO: {this.miGrilla.moverAbajo(); break;}
			case MOVER_IZQUIERDA : {this.miGrilla.moverIzquierda(); break;}
			case MOVER_DERECHA : {this.miGrilla.moverDerecha(); break;}
			case ROTAR_IZQUIERDA : {this.miGrilla.rotarIzquierda(); break;}
			case ROTAR_DERECHA : {this.miGrilla.rotarDerecha(); break;}
		}
	}
	
	public synchronized void pedirActualizar(Position pos, String imagePath) {
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
