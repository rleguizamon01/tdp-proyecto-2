package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import utilidad.Position;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI {

	private JFrame frame;
	private JPanel grilla;
	private JPanel informacion;
	private JLabel lblProximoTetrimino;
	private JLabel lblReloj;
	private JLabel lblPuntaje;
	private JTextArea txtrCreditos;
	private JLabel[][] matrizLabels;
	private JTextArea txtrCaptadorDeEventos;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
		actualizarSiguienteTetrimino("/assets/images/tetriminoL.png");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		grilla = new JPanel();
		grilla.setBorder(new LineBorder(new Color(0, 0, 0)));
		grilla.setBounds(10, 11, 300, 630);
		grilla.setLayout(null);
		frame.getContentPane().add(grilla);
		
		informacion = new JPanel();
		informacion.setBounds(320, 11, 104, 630);
		frame.getContentPane().add(informacion);
		informacion.setLayout(null);
		
		lblProximoTetrimino = new JLabel("Proximo Tetrimino");
		//lblProximoTetrimino.setHorizontalAlignment(SwingConstants.CENTER);
		lblProximoTetrimino.setBounds(10, 11, 84, 84);
		lblProximoTetrimino.setBorder(new LineBorder(new Color(0, 0, 0)));
		informacion.add(lblProximoTetrimino);
		
		lblReloj = new JLabel("Reloj");
		lblReloj.setHorizontalAlignment(SwingConstants.CENTER);
		lblReloj.setBounds(10, 107, 84, 23);
		lblReloj.setBorder(new LineBorder(new Color(0, 0, 0)));
		informacion.add(lblReloj);
		
		lblPuntaje = new JLabel("Puntaje");
		lblPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntaje.setBounds(10, 141, 84, 23);
		lblPuntaje.setBorder(new LineBorder(new Color(0, 0, 0)));
		informacion.add(lblPuntaje);
		
		txtrCreditos = new JTextArea();
		txtrCreditos.setEditable(false);
		txtrCreditos.setText("Creditos:\nAntich\nDingevan\nFeuilles\nLeguizamon");
		txtrCreditos.setBounds(10, 450, 84, 169);
		txtrCreditos.setBorder(new LineBorder(new Color(0, 0, 0)));
		informacion.add(txtrCreditos);
		
		txtrCaptadorDeEventos = new JTextArea();
		txtrCaptadorDeEventos.setOpaque(false);
		txtrCaptadorDeEventos.setBounds(10, 172, 1, 1);
		informacion.add(txtrCaptadorDeEventos);
		
		matrizLabels = new JLabel[10][21];
		JLabel aux;
		ImageIcon img;
		
		int j = 0;
		for(int i = 0; i < matrizLabels.length; i++) {
			for(j = 0; j < matrizLabels[0].length; j++) {
				aux = new JLabel();
				aux.setBounds(30*i, 30*j, 30, 30);
				
				img = new ImageIcon(GUI.class.getResource("/assets/images/bloqueVacio.png"));
				
				aux.setIcon(img);
				grilla.add(aux);
				matrizLabels[i][j] = aux;
			}
			
			j = 0;
		}
		
		txtrCaptadorDeEventos.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent k) {
				int keyCode = k.getKeyCode();
			    switch( keyCode ) { 
			        case KeyEvent.VK_DOWN:
			            System.out.println("Bajar"); 
			            break;
			        case KeyEvent.VK_LEFT:
			        	System.out.println("Mover Izquierda");
			            break;
			        case KeyEvent.VK_RIGHT :
			        	System.out.println("Mover Derecha");
			            break;
			        case KeyEvent.VK_Z :
			        	System.out.println("Rotar Izquierda");
			        	break;
			        case KeyEvent.VK_X :
			        	System.out.println("Rotar Derecha");
			        	break;
			     }
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
	}
	
	public void actualizar(int f, int c, String imagePath) {
		matrizLabels[f][c].setIcon(new ImageIcon(GUI.class.getResource(imagePath)));
	}
	
	public void actualizar(Position pos, String imagePath) {
		matrizLabels[pos.getFila()][pos.getColumna()].setIcon(new ImageIcon(GUI.class.getResource(imagePath)));
	}
	
	public void actualizarTiempo(String tiempoNuevo) {
		lblReloj.setText(tiempoNuevo);
	}
	
	public void actualizarPuntaje(String puntajeNuevo) {
		lblPuntaje.setText(puntajeNuevo);
	}
	
	public void actualizarSiguienteTetrimino(String nuevoTetriminoImagePath) {
		lblProximoTetrimino.setIcon(new ImageIcon(GUI.class.getResource(nuevoTetriminoImagePath)));
	}
	
}
