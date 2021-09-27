package utilidad;

import java.io.FileInputStream;
import java.io.InputStream;

import javazoom.jl.player.Player;

public class AudioPlayer extends Thread{
	private String nombreDeLaCancion;
	boolean seDebeReproducir;
	
	public AudioPlayer(String s) {
		nombreDeLaCancion = s;
		seDebeReproducir = true;
	}
	
	public void run() {
		try{
		    InputStream archivoInputStream = new FileInputStream(nombreDeLaCancion);
		    Player p = new Player(archivoInputStream);
		    p.play();
		    
		    while(seDebeReproducir) {
		    	p.play();
		    	Thread.sleep(100); //Un pequeño break entre cancion y cancion.
		    }
		}
		catch(Exception exc){
		    exc.printStackTrace();
		    System.out.println(exc.getMessage());
		}
	}
}
