package SourcePackage;
/*
Programa realizado por: Karina Figueroa y Axel Quiroz
*/

import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Font;

public class tronlike extends JFrame implements KeyListener, ActionListener {
	int vel = 10;
	int valEnemigo = 10;
	int velbonus = 3;
	boolean Sprint = false;
	int origenX = 100, origenY = 400; // Point
	int enemcordX = 1250, enemcordY = 400;

	String cadena, letrero, titulo;
	boolean bandera = false;
	boolean uLose = false; // vida

	boolean w, a, s, d, space = false; // movimiento
	boolean enemigo1 = true; // enemigo

	Lista posX = new Lista();
	Lista posY = new Lista();

	Lista enemListaX = new Lista();
	Lista enemListaY = new Lista();

	tronlike() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
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

		g.clearRect(0, 0, getWidth(), getHeight()); // limpiar actualización;
		g.setColor(new Color(0, 0, 0)); // color de fondo
		g.fillRect(0, 0, getWidth(), getHeight()); // color de fondo
		// Dibujar un marco azul alrededor de la ventana
		g.setColor(new Color(0, 0, 255)); // Establecer el color del marco como azul
		g.drawRect(85, 85, getWidth() - 100, getHeight() - 100); // Dibujar el rectángulo del marco
		// titulo e instrucciones
		g.setColor(Color.YELLOW);
		g.drawString(letrero, 400, 77);
		Font nueva = new Font("Arial", Font.BOLD, 50);
		g.setColor(new Color(43, 0, 255));
		g.setFont(nueva);
		g.drawString(titulo, 20, 77);

		// Jugador
		Graphics2D player = (Graphics2D) g;
		player.setColor(new Color(255, 0, 0));
		player.fillRect(origenX, origenY, 5, 5);

		// Trazo del player
		for (int i = 0; i < posX.getSize() && i < posY.getSize(); i++) {
			player.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			player.fillRect(posX.getValues(i), posY.getValues(i), 6, 6);
		}
		// detalle de la luz
		for (int i = 0; i < posX.getSize() && i < posY.getSize(); i++) {
			player.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			player.setColor(new Color(255, 255, 255));
			player.fillRect(posX.getValues(i) + 2, posY.getValues(i) + 2, 1, 1);
		}
		// Enemigo
		Graphics2D enemigo = (Graphics2D) g;
		enemigo.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		enemigo.setColor(new Color(0, 0, 200));
		if (enemigo1 != false) {
			for (int i = 0; i < enemListaX.getSize() && i < enemListaY.getSize(); i++) {
				enemigo.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				enemigo.fillRect(enemListaX.getValues(i), enemListaY.getValues(i), 6, 6);
				enemigo.setColor(new Color(220, 50, 0));
			}
			// detalle luz
			for (int i = 0; i < enemListaX.getSize() && i < enemListaY.getSize(); i++) {
				enemigo.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				enemigo.setColor(new Color(255, 255, 255));
				enemigo.fillRect(enemListaX.getValues(i) + 2, enemListaY.getValues(i) + 2, 1, 1);
			}
		}
	}

	public void paint(Graphics g) {
		System.out.println("paint");

		if (!bandera) {
			g.drawString("Hola", origenX, origenY);
		} else {
			update(g);
		}
	}

	public void run() {
		bandera = true;
		System.out.println("en sus marcas...");

		Runnable enemyMovementTask = () -> {
			int tiempo = 0;
			while (bandera) {
				try {
					Thread.sleep(valEnemigo);
					if (enemigo1 == true) {
						tiempo++;
						enemListaX.addNodo(enemcordX);
						enemListaY.addNodo(enemcordY);
					}
					if (enemigo1 == false) {
						enemListaX.clearList();
						enemListaY.clearList();
						tiempo = -1;
					}

					if (tiempo < 200) {
						enemcordX--; // left
					} else if (tiempo > 200 && tiempo < 300) {
						enemcordY--;
					} else if (tiempo > 300 && tiempo < 400) {
						enemcordX--;
					} else if (tiempo > 400 && tiempo < 550) {
						enemcordY++;
					} else if (tiempo > 550 && tiempo < 800) {
						enemcordX++;
					} else if (tiempo > 800 && tiempo < 950) {
						enemcordY++;
					} else if (tiempo > 950 && tiempo < 1300) {
						enemcordX--;
					} else if (tiempo > 1300 && tiempo < 1600) {
						enemcordY--;
					} else if (tiempo > 1600 && tiempo < 1700) {
						enemcordX--;
					} else if (tiempo > 1700 && tiempo < 1750) {
						enemcordY++;
					} else if (tiempo > 1750) {
						enemcordX--;
					} else if (tiempo == -1) {
						enemcordX = 1260;
						enemcordY = 680;
					}
					System.out.println(tiempo);
					repaint();
				} catch (InterruptedException e) {
					System.out.println("Enemigo derrotado");
				}
			}
		};
		// correr en hilos diferentes para que tengan velocidades indpendientes
		Thread enemyMovementThread = new Thread(enemyMovementTask);
		enemyMovementThread.start();
		while (bandera) {
			try {
				Thread.sleep(vel);
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

	// Método para comprobar las colisiones
	public void isOver() {
		// Colisiones del marco
		int frameX = 85;
		int frameY = 85;
		int frameWidth = getWidth() - 20;
		int frameHeight = getHeight() - 20;
		// Colision del player con su propio trazo
		for (int i = 0; i < posX.getSize(); i++) {
			if (posX.getValues(i) == origenX && posY.getValues(i) == origenY) {
				bandera = false;
				System.out.println("Player chocó con su propio trazo");
				// reset();
			}
		}
		// Colisiones del player con trazo del enemigo
		for (int i = 0; i < enemListaX.getSize(); i++) {
			if (enemListaX.getValues(i) == origenX && enemListaY.getValues(i) == origenY) {
				System.out.println("Player chocó con el trazo del enemigo");
				bandera = false;
				// reset();
			}
		}
		// Si el enemigo 1 choca con...
		// El trazo del player
		for (int i = 0; i < posX.getSize(); i++) {
			if (posX.getValues(i) == enemcordX && posY.getValues(i) == enemcordY) {
				System.out.println("Enemigo chocó con el trazo del player");
				enemigo1 = false;
			}
		}
		// Con el marco
		if (enemigo1 == true) { //comprobar que el enemigo esta habilitado
			if (enemcordX < frameX || enemcordX > frameWidth || enemcordY < frameY || enemcordY > frameHeight) {
				bandera = false;
				System.out.println("enemigo colisionó con el marco");
				// reset();
			}
		}
		// Colisiones al marco jugador
		if (origenX < frameX || origenX > frameWidth || origenY < frameY || origenY > frameHeight) {
			bandera = false;
			System.out.println("Jugador colisionó con el marco");
			// reset();
		}
	}

	void reset() {
		new Thread(() -> {
			tronlike.main(null); // llamar a la ventana de incio
		}).start();
		dispose();
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