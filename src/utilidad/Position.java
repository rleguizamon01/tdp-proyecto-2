package utilidad;

public class Position {
	protected int f;
	protected int c;
	
	public Position(int f, int c) {
		this.f = f;
		this.c = c;
	}
	
	public Position() {
		f = 0;
		c = 0;
	}

	public int getFila() {
		return f;
	}

	public void setFila(int f) {
		this.f = f;
	}

	public int getColumna() {
		return c;
	}

	public void setColumna(int c) {
		this.c = c;
	}
	
}
