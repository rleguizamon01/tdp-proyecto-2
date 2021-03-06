package gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import logica.Juego;
import utilidad.*;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class GUI {	
	private JFrame frmTetris;
	private JPanel grilla;
	private JPanel informacion;
	private JLabel lblProximoTetrimino;
	private JLabel lblReloj;
	private JLabel lblPuntaje;
	private JLabel lblGameOver;
	private JTextArea txtrCreditos;
	private JLabel[][] matrizLabels;
	private JTextArea txtrCaptadorDeEventos;
	private JToggleButton tglbtnMusica;
	
	private Juego miJuego;
	private JLabel lblMMusica;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmTetris.setVisible(true);
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
		miJuego = new Juego(this);
		
		//En caso de que se haya cambiado el valor por defecto de la musica, actualizamos posterior a la inicializacion.
		actualizarIconoMusica();
		
		
		miJuego.iniciarPartida();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTetris = new JFrame();
		frmTetris.setTitle("TETRIS");
		frmTetris.setResizable(false);
		frmTetris.setBounds(100, 100, 470, 700);
		frmTetris.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTetris.setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/assets/images/logo.png")));
		frmTetris.setLocationRelativeTo(null);
		frmTetris.getContentPane().setLayout(null);
		
		lblGameOver = new JLabel("");
		lblGameOver.setBounds(10, 11, 300, 630);
		ImageIcon imgGameOver = new ImageIcon(GUI.class.getResource("/assets/images/gameOver.png"));
		lblGameOver.setIcon(imgGameOver);
		lblGameOver.setVisible(false);
		frmTetris.getContentPane().add(lblGameOver);
		
		grilla = new JPanel();
		grilla.setBorder(new LineBorder(new Color(0, 0, 0)));
		grilla.setBounds(10, 11, 300, 630);
		grilla.setLayout(null);
		frmTetris.getContentPane().add(grilla);
		
		informacion = new JPanel();
		informacion.setBounds(320, 11, 124, 630);
		frmTetris.getContentPane().add(informacion);
		informacion.setLayout(null);
		
		lblProximoTetrimino = new JLabel("Proximo Tetrimino");
		lblProximoTetrimino.setBounds(10, 11, 84, 84);
		lblProximoTetrimino.setBorder(new LineBorder(new Color(0, 0, 0)));
		informacion.add(lblProximoTetrimino);
		
		lblReloj = new JLabel("00:00");
		lblReloj.setHorizontalAlignment(SwingConstants.CENTER);
		lblReloj.setBounds(10, 107, 84, 23);
		lblReloj.setBorder(new LineBorder(new Color(0, 0, 0)));
		informacion.add(lblReloj);
		
		lblPuntaje = new JLabel("0");
		lblPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntaje.setBounds(10, 141, 84, 23);
		lblPuntaje.setBorder(new LineBorder(new Color(0, 0, 0)));
		informacion.add(lblPuntaje);
		
		txtrCreditos = new JTextArea();
		txtrCreditos.setBackground(new Color(255, 255, 255));
		txtrCreditos.setEditable(false);
		txtrCreditos.setText(" Creditos:\n Antich\n Dingevan\n Feuilles\n Leguizamon");
		txtrCreditos.setBounds(10, 532, 104, 98);
		txtrCreditos.setBorder(new LineBorder(new Color(0, 0, 0)));
		informacion.add(txtrCreditos);
		
		txtrCaptadorDeEventos = new JTextArea();
		txtrCaptadorDeEventos.setOpaque(false);
		txtrCaptadorDeEventos.setBounds(10, 172, 1, 1);
		informacion.add(txtrCaptadorDeEventos);
		
		tglbtnMusica = new JToggleButton("");
		tglbtnMusica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(miJuego.hayMusica()) {
					miJuego.terminarMusica();
				} else {
					miJuego.iniciarMusica();
				}
				actualizarIconoMusica();
				txtrCaptadorDeEventos.grabFocus();
			}
		});
		tglbtnMusica.setBounds(34, 175, 36, 36);
		tglbtnMusica.setIcon(new ImageIcon(GUI.class.getResource("/assets/images/musicaOFF.png")));
		informacion.add(tglbtnMusica);
		
		JLabel lblControles = new JLabel("Controles:");
		lblControles.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblControles.setBounds(10, 222, 84, 23);
		informacion.add(lblControles);
		
		JLabel lblFlechas = new JLabel("Flechas: Mover");
		lblFlechas.setBounds(10, 250, 104, 23);
		informacion.add(lblFlechas);
		
		JLabel lblRotacionDerecha = new JLabel("X: Rotar Derecha");
		lblRotacionDerecha.setBounds(10, 271, 104, 23);
		informacion.add(lblRotacionDerecha);
		
		JLabel lblZRotarIzquierda = new JLabel("Z: Rotar Izquierda");
		lblZRotarIzquierda.setBounds(10, 294, 104, 23);
		informacion.add(lblZRotarIzquierda);
		
		lblMMusica = new JLabel("M: Alternar Musica");
		lblMMusica.setBounds(10, 317, 114, 23);
		informacion.add(lblMMusica);
		
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
			            miJuego.mover(miJuego.MOVER_ABAJO);
			            break;
			        case KeyEvent.VK_LEFT:
			        	miJuego.mover(miJuego.MOVER_IZQUIERDA);
			            break;
			        case KeyEvent.VK_RIGHT :
			        	miJuego.mover(miJuego.MOVER_DERECHA);
			            break;
			        case KeyEvent.VK_Z :
			        	miJuego.mover(miJuego.ROTAR_IZQUIERDA);
			        	break;
			        case KeyEvent.VK_X :
			        	miJuego.mover(miJuego.ROTAR_DERECHA);
			        	break;
			        case KeyEvent.VK_M :
			        	if(miJuego.hayMusica()) {
							miJuego.terminarMusica();
						} else {
							miJuego.iniciarMusica();
						}
						actualizarIconoMusica();
						txtrCaptadorDeEventos.grabFocus();
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
	
	private void actualizarIconoMusica() {
		if(!miJuego.hayMusica()) {
			tglbtnMusica.setIcon(new ImageIcon(GUI.class.getResource("/assets/images/musicaON.png")));
		} else {
			tglbtnMusica.setIcon(new ImageIcon(GUI.class.getResource("/assets/images/musicaOFF.png")));
		}
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
	
	public void mostrarDerrota() {
		lblGameOver.setVisible(true);
	}
}
