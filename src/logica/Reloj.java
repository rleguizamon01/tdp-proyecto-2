package logica;

public class Reloj extends Thread {
	protected int intervalo;
	protected boolean sePuedeJugar;
	protected Juego miJuego;
	protected long milisegundosInicio;
	
	public Reloj(Juego miJuego) {
		this.miJuego = miJuego;
		this.intervalo = 1000;
		this.sePuedeJugar = true;
		this.milisegundosInicio = System.currentTimeMillis();
	};
	
	public void run() {
		try {
			while(this.sePuedeJugar) {
				Thread.sleep(intervalo);
				this.miJuego.pedirMoverAbajo();
				miJuego.actualizarTiempo(getTiempoTranscurrido().toString());
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
		
		return (tiempoEnEjecucion / 1000);
	}
}
