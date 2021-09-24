package logica;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Reloj extends Thread {
	protected int intervalo;
	protected boolean sePuedeJugar;
	protected Juego miJuego;
	protected long milisegundosInicio;
	protected long maximoMilisegundosAntesDeAumentar;
	protected int aumentosDeVelocidad;
	
	public Reloj(Juego miJuego) {
		this.miJuego = miJuego;
		this.intervalo = 1000;
		this.sePuedeJugar = true;
		this.milisegundosInicio = System.currentTimeMillis();
		this.maximoMilisegundosAntesDeAumentar = 30000;
		this.aumentosDeVelocidad = 0;
	};
	
	public void run() {
		long aux = 0;
		
		try {
			while(this.sePuedeJugar) {
				Thread.sleep(intervalo);
				this.miJuego.pedirMoverAbajo();
				aux = getTiempoTranscurrido();
				
				if(aumentosDeVelocidad < 6 && aux >= maximoMilisegundosAntesDeAumentar) {
					intervalo = (int) (intervalo / 1.2);
					maximoMilisegundosAntesDeAumentar += maximoMilisegundosAntesDeAumentar*0.85;
					aumentosDeVelocidad++;
				}
				
				miJuego.actualizarTiempo(tiempoConFormato(aux));
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
	
	//TODO: Hacer que tenga formato mm:ss
	private Long getTiempoTranscurrido() {
		long milisegundosActual = System.currentTimeMillis();
		long tiempoEnEjecucion = milisegundosActual - milisegundosInicio;
		
		return tiempoEnEjecucion;
	}
	
	private String tiempoConFormato(Long t) {
		return String.format("%02d:%02d",
				TimeUnit.MILLISECONDS.toMinutes(t),
				TimeUnit.MILLISECONDS.toSeconds(t) - 
				TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(t)));
	}
}
