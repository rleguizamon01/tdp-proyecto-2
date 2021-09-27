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
		    InputStream archivoInputStream = null;
		    Player p = null;
		    
		    do {
		    	archivoInputStream = new FileInputStream(nombreDeLaCancion);
		    	p = new Player(archivoInputStream);
		    	p.play();
		    	Thread.sleep(2000); //Un pequeño break entre cancion y cancion.
		    }while(seDebeReproducir);
		}
		catch(Exception exc){
		    exc.printStackTrace();
		    System.out.println(exc.getMessage());
		}
	}
}
