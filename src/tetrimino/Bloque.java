package tetrimino;

import utilidad.*;

public class Bloque {
	protected Position pos;
	protected String caminoImagen;
	protected boolean ocupado;
	protected boolean estatico;
	
	public Bloque() {
		pos = null;
		caminoImagen = "/assets/images/bloqueVacio.png";
		ocupado = false;
		estatico = false;
	}
	
	public Bloque(Position pos, String caminoImagen) {
		this.pos = pos;
		this.caminoImagen = caminoImagen;
		ocupado = false;
		estatico = false;
	}
	
	public Bloque(Position pos, String caminoImagen, boolean ocupado) {
		this.pos = pos;
		this.caminoImagen = caminoImagen;
		this.ocupado = ocupado;
		estatico = false;
	}
	
	public Bloque(int x, int y, String caminoImagen) {
		this.pos = new Position(x, y);
		this.caminoImagen = caminoImagen;
		ocupado = false;
		estatico = false;
	}
	
	public Bloque(int x, int y, String caminoImagen, boolean ocupado) {
		this.pos = new Position(x, y);
		this.caminoImagen = caminoImagen;
		this.ocupado = ocupado;
		estatico = false;
	}
	
	public String getCaminoImagen() {
		return caminoImagen;
	}
	
	public Position getPosicion() {
		return pos;
	}
	
	public boolean estaOcupado() {
		return ocupado;
	}
	
	public boolean estaEstatico() {
		return estatico;
	}
	
	public boolean estaLibre() {
		return !estaOcupado() || !estaEstatico();
	}
	
	public void ocupar() {
		ocupado = true;
	}
	
	public void desocupar() {
		ocupado = false;
	}
	
	public void hacerEstatico() {
		estatico = true;
	}
	
	public void setCaminoImagen(String imagePath) {
		caminoImagen = imagePath;
	}
}
