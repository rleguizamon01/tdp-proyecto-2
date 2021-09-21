package logica;

public class Reloj extends Thread {
	protected int intervalo;
	protected boolean sePuedeJugar;
	protected Juego miJuego;
	protected int tiempoTranscurrido; // ?
	
	public Reloj(Juego miJuego) {
		this.miJuego = miJuego;
		this.intervalo = 1000;
		this.sePuedeJugar = true;
	};
	
	public void run() {
		try {
			while(this.sePuedeJugar) {
				Thread.sleep(intervalo);
				this.miJuego.pedirMoverAbajo();		
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void setIntervalo(int intervalo) {
		this.intervalo = intervalo;
	}
	
	public void setSePuedeJugar(boolean puedeJugar) {
		this.sePuedeJugar = puedeJugar;
	}
	
	private int getTiempoTranscurrido() { // ?
		return this.tiempoTranscurrido;
	}
}
