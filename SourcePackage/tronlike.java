package SourcePackage;

import javax.imageio.ImageIO;

/*
Programa realizado por: Karina Figueroa y Axel Quiroz
*/

import javax.swing.JFrame;
import java.awt.event.*;
import java.io.File;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Font;


public class tronlike extends JFrame implements KeyListener, ActionListener {
	int vel = 10;
	int valEnemigo = 10;
	int velbonus = 3;
	boolean Sprint = false;
	int origenX = 100, origenY = 550; // Point
	int enemcordX = 1884, enemcordY = 550;

	String cadena, letrero, titulo;
	boolean bandera = false;
	boolean uLose = false; // vida

	boolean w, a, s, d, space = false; // movimiento

	Lista posX = new Lista();
	Lista posY = new Lista();

	Lista enemListaX = new Lista();
	Lista enemListaY = new Lista();

	tronlike() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(null);
		letrero = new String("¿CÓMO JUGAR? ==> Usa w,a,s,d para moverte. Usa la tecla espacio para activar turbo");
		titulo = new String("THRONE GAME");
		cadena = new String();
		addKeyListener(this);
		getContentPane().setBackground(Color.BLACK);
		repaint();
	}

	public static void main(String[] args) {
		tronlike vn = new tronlike();
		vn.setVisible(true);
		vn.run();
	}

	public void update(Graphics g) {
		System.out.println("update");
		
		g.clearRect(0, 0, getWidth(), getHeight()); // limpiar actualizacion;

		g.setColor(new Color(0, 0, 0)); // color de fondo
		g.fillRect(0, 0, getWidth(), getHeight()); // color de fondo
		// Dibujar un marco azul alrededor de la ventana
		g.setColor(new Color(0, 0, 255)); // Establecer el color del marco como azul (RGB: 0, 0, 255)
		g.drawRect(85, 85, getWidth() - 100, getHeight() - 100); // Dibujar el rectángulo del marco
		//titulo e instrucciones
		g.setColor(Color.YELLOW);
		g.drawString(letrero, 400, 77);
		Font nueva = new Font("Arial",Font.BOLD,50);
		g.setColor(new Color(43, 0, 255));
		g.setFont(nueva);
		g.drawString(titulo, 20, 77);
		
		// Player
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(new Color(255, 81, 0)); // Color Naranja al puntito
		// g2d.fillOval(origenX, origenY, 30, 30); // tamaño de la bolita

		Graphics2D gb2d = (Graphics2D) g;
		gb2d.setColor(new Color(255, 0, 0));
		gb2d.fillRect(origenX, origenY, 5, 5);
		// gb2d.fillRect(origenX,origenY,40,5);
		// Trazo del player
		for (int i = 0; i < posX.getSize(); i++) {
			gb2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			gb2d.fillRect(posX.getValues(i), posY.getValues(i), 6, 6);
		}
		// detalle de la luz
		for (int i = 0; i < posX.getSize(); i++) {
			gb2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			gb2d.setColor(new Color(255, 255, 255));
			gb2d.fillRect(posX.getValues(i) + 2, posY.getValues(i) + 2, 1, 1);
		}

		// Enemigo
		Graphics2D enemigo = (Graphics2D) g;
		enemigo.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		enemigo.setColor(new Color(0, 0, 200));
		// enemigo.fillOval(enemcordX, enemcordY, 30, 30);;

		for (int i = 0; i < enemListaX.getSize(); i++) {
			enemigo.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			enemigo.fillRect(enemListaX.getValues(i), enemListaY.getValues(i), 6, 6);
			enemigo.setColor(new Color(220, 50, 0));
		}
		// detalle luz
		for (int i = 0; i < posX.getSize(); i++) {
			enemigo.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			enemigo.setColor(new Color(255, 255, 255));
			enemigo.fillRect(enemListaX.getValues(i) + 2, enemListaY.getValues(i) + 2, 1, 1);
		}

	}

	public void paint(Graphics g) {
		System.out.println("paint");

		if (!bandera) {
			g.drawString(letrero, origenX, origenY);
		} else {
			update(g);
		}
	}

	public void run() {
		bandera = true;
		System.out.println("en sus marcas...");
		int cont = 0;

		Runnable enemyMovementTask = () -> {
			while (bandera) {
				try {
					Thread.sleep(valEnemigo);
					enemListaX.addNodo(enemcordX);
					enemListaY.addNodo(enemcordY);
					for (int i = 0; i < 1; i++) {
						enemcordX = enemcordX - 1;
					}
					repaint();
				} catch (InterruptedException e) {
					System.out.println("Enemigo derrotado");
				}
			}
		};
		Thread enemyMovementThread = new Thread(enemyMovementTask); // correr en hilos separados para que no vayan a la misma velocidad
		enemyMovementThread.start();
		while (bandera) {
			try {
				Thread.sleep(vel);
				cadena = "contador = " + cont++;
				posX.addNodo(origenX);
				posY.addNodo(origenY);

				if (w == true) {
					origenY--;
				} else if (a == true) {
					origenX--;
				} else if (s == true) {
					origenY++;
				} else if (d == true) {
					origenX++;
				}
				repaint();
				if (d == true || w == true || s == true || a == true) {
					isOver();
				}
			} catch (InterruptedException e) {
				System.out.println("oh oh me molestan....");
			}
		}
		enemyMovementThread.interrupt();
	}

	public void keyPressed(KeyEvent ispress) {
		// System.out.println(ispress + "KEY PRESSED: ");
		// 37 izq, 38 up, 39 right, 40 down
		if (ispress.getKeyCode() == 87) { // w
			if (s == true) {
				// No moverse para atrás
			} else {
				w = true;
				a = false;
				s = false;
				d = false;
				space = false;
				vel = 10;
			}
		} else if (ispress.getKeyCode() == 83) { // s
			if (w == true) {
				// No moverse para atrás
			} else {
				w = false;
				a = false;
				s = true;
				d = false;
				space = false;
				vel = 10;
			}
		} else if (ispress.getKeyCode() == 65) { // a
			if (d == true) {
				// No moverse para atrás
			} else {
				w = false;
				a = true;
				s = false;
				d = false;
				space = false;
				vel = 10;
			}
		} else if (ispress.getKeyCode() == 68) { // d
			if (a == true) {
				// No moverse para atrás
			} else {
				w = false;
				a = false;
				s = false;
				d = true;
				space = false;
				vel = 10;
			}
		} else if (ispress.getKeyCode() == 32) {
			space = true;
			vel = 8;
		}
	}

	// Pantalla game over
	public void isOver() {
		//Colisiones del marco
		int frameX = 85;
        int frameY = 85;
        int frameWidth = getWidth() - 100;
        int frameHeight = getHeight() - 100;

		for (int i = 0; i < posX.getSize(); i++) {
			if (posX.getValues(i) == origenX && posY.getValues(i) == origenY) {
				bandera = false;
				new Thread(() -> {
					tronlike.main(null); // llamar a la ventana de incio
				}).start();
				dispose();
			}
		}
        if (origenX < frameX || origenX > frameWidth || origenY < frameY || origenY > frameHeight) {
            bandera = false;
			new Thread(() -> {
				tronlike.main(null); // llamar a la ventana de incio
			}).start();
			dispose();
        }
	}
	void checkCollision() {
       
    }

	/** Handle the key released event from the text field. */
	public void keyReleased(KeyEvent e) {
		displayInfo(e, "KEY RELEASED: ");
	}

	public void keyTyped(KeyEvent e) {
		displayInfo(e, "KEY RELEASED: ");
	}

	/** Handle the button click. */
	public void actionPerformed(ActionEvent e) {
		// Clear the text components.

		// Return the focus to the typing area.
	}

	// MUESTRA EL CÓDIGO DE CADA TECLA
	private void displayInfo(KeyEvent e, String keyStatus) {

		// You should only rely on the key char if the event
		// is a key typed event.
		int id = e.getID();
		String keyString;
		if (id == KeyEvent.KEY_TYPED) {
			char c = e.getKeyChar();
			keyString = "key character = '" + c + "'";
		} else {
			int keyCode = e.getKeyCode();
			keyString = "key code = " + keyCode
					+ " ("
					+ KeyEvent.getKeyText(keyCode)
					+ ")";
		}
		// System.out.println("display" + e + keyStatus);
	}
}