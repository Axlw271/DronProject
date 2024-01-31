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
	int origenX = 88, origenY = 275; // Jugador 1
	int origenX2 = 618, origenY2 = 275; // Jugador 2;
	int enemcordX = 618, enemcordY = 275;
	int enemcordX1 = 618, enemcordY1 = 200;
	int enemcordX2 = 618, enemcordY2 = 250;

	String cadena, letrero, titulo, letrero2, letrero3;
	boolean bandera = false;
	boolean uLose = false; // vida

	boolean w, a, s, d, space = false; // movimiento
	boolean arriba = false, abajo = false, izquierda = false, derecha = false, boost = false;
	boolean enemigo1 = true; // enemigo
	boolean enemigo2 = true;
	boolean enemigo3 = true;
	boolean jug2 = true;

	Lista posX = new Lista();
	Lista posY = new Lista();

	Lista posX2 = new Lista();
	Lista posY2 = new Lista();

	Lista enemListaX = new Lista();
	Lista enemListaY = new Lista();

	Lista enemListaX1 = new Lista();
	Lista enemListaY1 = new Lista();

	Lista enemListaX2 = new Lista();
	Lista enemListaY2 = new Lista();

	tronlike() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		setLayout(null);
		letrero = new String("¿CÓMO JUGAR? ==> Usa w,a,s,d para moverte. Usa la tecla espacio para activar turbo");
		letrero2 = new String("JUGADOR 1 ==> Usa w,a,s,d para moverte. Usa la tecla espacio para activar turbo");
		letrero3 = new String(
				"JUGADOR 2 ==> Usa las flechas para moverte. Usa la tecla control derecho para activar turbo");
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

		if (Globals.twoPlayers == true && Globals.level1 != true) {
			g.setColor(Color.YELLOW);
			g.drawString(letrero2, 90, 77);
			g.drawString(letrero3, 90, 65);
			Font nueva = new Font("Arial", Font.BOLD, 18);
			g.setColor(new Color(43, 0, 255));
			g.setFont(nueva);
			g.drawString("THRONE", 10, 110);
			g.drawString("GAME", 20, 140);
		} else {
			g.setColor(Color.YELLOW);
			g.drawString(letrero, 90, 50);
			Font nueva = new Font("Arial", Font.BOLD, 18);
			g.setColor(new Color(43, 0, 255));
			g.setFont(nueva);
			g.drawString("THRONE", 10, 110);
			g.drawString("GAME", 20, 140);
		}

		Graphics2D message = (Graphics2D) g;
		if (enemigo1 == false) {
			Font fuenteF = new Font("Arial", Font.BOLD, 15);
			message.setColor(Color.RED);
			message.setFont(fuenteF);
			message.drawString("Derrotaste a los enemigos presiona 'm' para regresar al menú", 100, 300);
		}

		if (enemigo2 == false && enemigo3 == false) {
			Font fuenteF = new Font("Arial", Font.BOLD, 15);
			message.setColor(Color.RED);
			message.setFont(fuenteF);
			message.drawString("Derrotaste a los enemigos presiona 'm' para regresar al menú", 100, 300);
		}
		// mensaje de perdiste
		if (uLose == true && Globals.level1 != true && Globals.twoPlayers == true) {

			Font fuenteL = new Font("Arial", Font.BOLD, 15);
			message.setColor(Color.RED);
			message.setFont(fuenteL);
			message.drawString("Jugador 2 gana presiona 'n' para reiniciar el nivel", 100, 300);

			bandera = false;
		}
		if (uLose == true && Globals.level1 == true && Globals.twoPlayers != true) {
			Font fuenteL = new Font("Arial", Font.BOLD, 15);
			message.setColor(Color.RED);
			message.setFont(fuenteL);
			message.drawString("Perdiste :( presiona 'n' para reiniciar el nivel", 100, 300);
			bandera = false;
		}
		if (uLose == true && Globals.level2 == true && Globals.twoPlayers != true && Globals.level1 !=true) {
			Font fuenteL = new Font("Arial", Font.BOLD, 15);
			message.setColor(Color.RED);
			message.setFont(fuenteL);
			message.drawString("Perdiste :( presiona 'n' para reiniciar el nivel", 100, 300);
			bandera = false;
		}

		if (jug2 == false) {
			Font fuenteL = new Font("Arial", Font.BOLD, 15);
			message.setColor(Color.RED);
			message.setFont(fuenteL);
			message.drawString("Jugador 1 gana presiona 'n' para reiniciar el nivel", 100, 300);
			bandera = false;
		}

		// Jugador
		Graphics2D player = (Graphics2D) g;
		player.setColor(new Color(0, 102, 204));
		player.fillRect(origenX, origenY, 5, 5);

		// Trazo del player
		for (int i = 0; i < posX.getSize() && i < posY.getSize(); i++) {
			player.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			player.fillRect(posX.getValues(i), posY.getValues(i), 5, 5);
		}
		// detalle de la luz
		for (int i = 0; i < posX.getSize() && i < posY.getSize(); i++) {
			player.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			player.setColor(new Color(255, 255, 255));
			player.fillRect(posX.getValues(i) + 2, posY.getValues(i) + 2, 1, 1);
		}

		// Jugador 2
		if (Globals.twoPlayers == true) {
			Graphics2D player2 = (Graphics2D) g;
			player2.setColor(new Color(255, 128, 0));
			player2.fillRect(origenX2, origenY2, 5, 5);

			// Trazo del player 2
			for (int i = 0; i < posX2.getSize() && i < posY2.getSize(); i++) {
				player2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				player2.fillRect(posX2.getValues(i), posY2.getValues(i), 5, 5);
			}
			// detalle de la luz
			for (int i = 0; i < posX2.getSize() && i < posY2.getSize(); i++) {
				player2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				player2.setColor(new Color(255, 255, 255));
				player2.fillRect(posX2.getValues(i) + 2, posY2.getValues(i) + 2, 1, 1);
			}
		}

		// Enemigos

		// Nivel 1
		if (Globals.level1 == true) { // Revisar que nivel es cambiar después
			Graphics2D enemigo = (Graphics2D) g;
			enemigo.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			enemigo.setColor(new Color(0, 0, 200));
			// trazado
			for (int i = 0; i < enemListaX.getSize() && i < enemListaY.getSize(); i++) {
				enemigo.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				enemigo.fillRect(enemListaX.getValues(i), enemListaY.getValues(i), 5, 5);
				enemigo.setColor(new Color(220, 50, 0));
			}
			// detalle luz
			for (int i = 0; i < enemListaX.getSize() && i < enemListaY.getSize(); i++) {
				enemigo.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				enemigo.setColor(new Color(255, 255, 255));
				enemigo.fillRect(enemListaX.getValues(i) + 2, enemListaY.getValues(i) + 2, 1, 1);
			}
		}

		// Nivel 2
		if (Globals.level2 == true) { // Revisar que nivel es cambiar después
			// Enemigo 1
			Graphics2D enemigo1 = (Graphics2D) g;
			enemigo1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			enemigo1.setColor(new Color(0, 0, 200));
			// trazado
			for (int i = 0; i < enemListaX1.getSize() && i < enemListaY1.getSize(); i++) {
				enemigo1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				enemigo1.fillRect(enemListaX1.getValues(i), enemListaY1.getValues(i), 5, 5);
				enemigo1.setColor(new Color(220, 50, 0));
			}
			// detalle luz
			for (int i = 0; i < enemListaX1.getSize() && i < enemListaY1.getSize(); i++) {
				enemigo1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				enemigo1.setColor(new Color(255, 255, 255));
				enemigo1.fillRect(enemListaX1.getValues(i) + 2, enemListaY1.getValues(i) + 2, 1, 1);
			}
			// Enemigo 2
			Graphics2D enemigo2 = (Graphics2D) g;
			enemigo2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			enemigo2.setColor(new Color(0, 0, 200));

			for (int i = 0; i < enemListaX2.getSize() && i < enemListaY2.getSize(); i++) {
				enemigo2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				enemigo2.fillRect(enemListaX2.getValues(i), enemListaY2.getValues(i), 5, 5);
				enemigo2.setColor(new Color(220, 50, 0));
			}
			// detalle luz
			for (int i = 0; i < enemListaX2.getSize() && i < enemListaY2.getSize(); i++) {
				enemigo2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				enemigo2.setColor(new Color(255, 255, 255));
				enemigo2.fillRect(enemListaX2.getValues(i) + 2, enemListaY2.getValues(i) + 2, 1, 1);
			}
		}
	}

	public void paint(Graphics g) {
		System.out.println("paint");

		if (!bandera) {
			g.drawString(".", origenX, origenY);
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
					tiempo++;
					
					if (enemigo1 == true) {
						enemListaX.addNodo(enemcordX);
						enemListaY.addNodo(enemcordY);
					}

					if (enemigo2 == true ) {
						enemListaX1.addNodo(enemcordX1);
						enemListaY1.addNodo(enemcordY1);

					}
					if ( enemigo3 == true) {
						enemListaX2.addNodo(enemcordX2);
						enemListaY2.addNodo(enemcordY2);
					}

					if (enemigo2 == false ) {
						enemListaX1.clearList();
						enemListaY1.clearList();
					}
					if ( enemigo3 == false) {
						enemListaX2.clearList();
						enemListaY2.clearList();
					}

					if (enemigo1 == false) {
						enemListaX.clearList();
						enemListaY.clearList();
						tiempo = -1;
					}

					// set de movimientos del enemigo level 1
					if (Globals.level1 == true) {
						if (tiempo < 100) {
							enemcordX--;
						} else if (tiempo > 100 && tiempo < 150) {
							enemcordY++;
						} else if (tiempo > 150 && tiempo < 200) {
							enemcordX++;
						} else if (tiempo > 200 && tiempo < 270) {
							enemcordY++;
						} else if (tiempo > 270 && tiempo < 350) {
							enemcordX--;
						} else if (tiempo > 350 && tiempo < 400) {
							enemcordY++;
						} else if (tiempo > 400 && tiempo < 500) {
							enemcordX--;
						} else if (tiempo > 500 && tiempo < 520) {
							enemcordY--;
						} else if (tiempo > 520 && tiempo < 800) {
							enemcordX--;
						} else if (tiempo > 800) {
							enemcordY--;
						} else if (tiempo == -1) {
							
						}
					} // end set movimiento enemigo

					if (Globals.level2 == true) { // enemigos set movimiento nivel 2
						if (tiempo < 100) {
							enemcordX1--;
							enemcordX2--;
						} else if (tiempo > 100 && tiempo < 150) {
							enemcordY1--;
							enemcordY2++;
						} else if (tiempo > 150 && tiempo < 210) {
							enemcordX1--;
							enemcordY2++;
						} else if (tiempo > 210 && tiempo < 240) {
							enemcordY1++;
							enemcordX2--;
						}else if (tiempo > 240 && tiempo < 280) {
							enemcordX1--;
							enemcordX2--;
						} else if (tiempo > 280 && tiempo < 320) {
							enemcordY1++;
							enemcordY2--;
						}else if (tiempo > 320 && tiempo < 350) {
							enemcordY1++;
							enemcordX2++;
						}else if (tiempo > 350 && tiempo < 375) {
							enemcordX1--;
							enemcordY2--;
						}else if (tiempo > 375 && tiempo < 470) {
							enemcordX1--;
							enemcordX2--;
						}else if (tiempo > 470 && tiempo < 500) {
							enemcordX1--;
							enemcordY2++;
						}else if (tiempo > 500 && tiempo < 530) {
							enemcordY1--;
							enemcordX2--;
						}else if (tiempo > 470) {
							enemcordX1--;
							enemcordY2--;
						}else if (tiempo == -1) {
							
						}

					} // end set de movimientos
					repaint();
				} catch (InterruptedException e) {
					System.out.println("Enemigo derrotado");
				}
			}
		};

		// correr en hilos diferentes para que tengan velocidades indpendientes
		Thread enemyMovementThread = new Thread(enemyMovementTask);
		enemyMovementThread.start();
		// hilo jugador 2
		Runnable player2MovementTask = () -> {
			while (bandera) {
				try {
					Thread.sleep(valEnemigo);
					posX2.addNodo(origenX2);
					posY2.addNodo(origenY2);

					if (arriba == true) {
						origenY2--;
					} else if (izquierda == true) {
						origenX2--;
					} else if (abajo == true) {
						origenY2++;
					} else if (derecha == true) {
						origenX2++;
					}
					repaint();

					if (derecha == true || arriba == true || abajo == true || izquierda == true) {
						isOverP2();
					}

				} catch (InterruptedException e) {
					System.out.println("Jugador2 derrotado");
				}
			}

		};

		Thread player2MovementThread = new Thread(player2MovementTask);
		player2MovementThread.start();

		// Controles jugador 1
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
		player2MovementThread.interrupt();
	}

	public void keyPressed(KeyEvent ispress) {
		// movimiento jugador1
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
		} else if (ispress.getKeyCode() == 77) { // letra m para regresar al menú
			backMenu();
		} else if (ispress.getKeyCode() == 78) { // letra m para regresar al menú
			resetLevel();
		}
		// Controles jugador 2
		if (Globals.twoPlayers == true && d == true || w == true || s == true || a == true) {
			// 37 izq, 38 up, 39 right, 40 down
			if (ispress.getKeyCode() == 38) { // w
				if (abajo == true) {
					// No moverse para atrás
				} else {
					arriba = true;
					izquierda = false;
					abajo = false;
					derecha = false;
					boost = false;
					valEnemigo = 10;
				}
			} else if (ispress.getKeyCode() == 40) { // s
				if (arriba == true) {
					// No moverse para atrás
				} else {
					arriba = false;
					izquierda = false;
					abajo = true;
					derecha = false;
					boost = false;
					valEnemigo = 10;
				}
			} else if (ispress.getKeyCode() == 37) { // a
				if (derecha == true) {
					// No moverse para atrás
				} else {
					arriba = false;
					izquierda = true;
					abajo = false;
					derecha = false;
					boost = false;
					valEnemigo = 10;
				}
			} else if (ispress.getKeyCode() == 39) { // d
				if (izquierda == true) {
					// No moverse para atrás
				} else {
					arriba = false;
					izquierda = false;
					abajo = false;
					derecha = true;
					boost = false;
					valEnemigo = 10;
				}
			} else if (ispress.getKeyCode() == 17) { // velocidad
				boost = true;
				valEnemigo = 8;
			}
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
				uLose = true;
				// System.out.println("Jugador1 chocó con su propio trazo");

			}
		}
		// Colisiones al marco jugador
		if (origenX < frameX || origenX > frameWidth || origenY < frameY || origenY > frameHeight) {
			uLose = true;
			// System.out.println("Jugador1 colisionó con el marco");

		}

		if (Globals.level1 == true) { // verificar nivel
			// Colisiones del player con trazo del enemigo
			for (int i = 0; i < enemListaX.getSize(); i++) {
				if (enemListaX.getValues(i) == origenX && enemListaY.getValues(i) == origenY) {
					// System.out.println("Jugador1 chocó con el trazo del enemigo");
					uLose = true;
				}
			}

			// Si el enemigo 1 choca con...
			// El trazo del player
			for (int i = 0; i < posX.getSize(); i++) {
				if (posX.getValues(i) == enemcordX && posY.getValues(i) == enemcordY) {
					// System.out.println("Enemigo chocó con el trazo del player");
					enemigo1 = false;
				}
			}
			// Con el marco
			if (enemigo1 == true) { // comprobar que el enemigo esta habilitado
				if (enemcordX < frameX || enemcordX > frameWidth || enemcordY < frameY || enemcordY > frameHeight) {
					enemigo1 = false;
					// System.out.println("enemigo colisionó con el marco");
				}
			}
		}

		if (Globals.level2 == true) { //verificar nivel
			
			// Colisiones del player con trazo del enemigo
			for (int i = 0; i < enemListaX1.getSize(); i++) { //enemigo1
				if (enemListaX1.getValues(i) == origenX && enemListaY1.getValues(i) == origenY) {
					// System.out.println("Jugador1 chocó con el trazo del enemigo");
					uLose = true;
				}
			}
			for (int i = 0; i < enemListaX2.getSize(); i++) { //enemigo2
				if (enemListaX2.getValues(i) == origenX && enemListaY2.getValues(i) == origenY) {
					// System.out.println("Jugador1 chocó con el trazo del enemigo");
					uLose = true;
				}
			}

			// Si el enemigo  choca con...
			// El trazo del player
			for (int i = 0; i < posX.getSize(); i++) {
				if (posX.getValues(i) == enemcordX1 && posY.getValues(i) == enemcordY1) {
					// System.out.println("Enemigo chocó con el trazo del player");
					enemigo2 = false;
				}
			}
			// Con el marco
			if (enemigo2 == true) { // comprobar que el enemigo esta habilitado
				if (enemcordX1 < frameX || enemcordX1 > frameWidth || enemcordY1 < frameY || enemcordY1 > frameHeight) {
					enemigo2 = false;
					// System.out.println("enemigo colisionó con el marco");
				}
			}

			//enemigo 2
			for (int i = 0; i < posX.getSize(); i++) {
				if (posX.getValues(i) == enemcordX2 && posY.getValues(i) == enemcordY2) {
					// System.out.println("Enemigo chocó con el trazo del player");
					enemigo3 = false;
				}
			}
			// Con el marco
			if (enemigo3 == true) { // comprobar que el enemigo esta habilitado
				if (enemcordX2 < frameX || enemcordX2 > frameWidth || enemcordY2 < frameY || enemcordY2 > frameHeight) {
					enemigo3 = false;
					// System.out.println("enemigo colisionó con el marco");
				}
			}
		}

	}

	// Arreglar colisiones
	void isOverP2() {
		int frameX = 85;
		int frameY = 85;
		int frameWidth = getWidth() - 20;
		int frameHeight = getHeight() - 20;

		if (origenX2 < frameX || origenX2 > frameWidth || origenY2 < frameY || origenY2 > frameHeight) {
			jug2 = false;
			// System.out.println("Jugador2 colisionó con el marco");
		}
		//
		for (int i = 0; i < posX2.getSize(); i++) {
			if (posX2.getValues(i) == origenX && posY2.getValues(i) == origenY) {
				// System.out.println("Jugador1 chocó con el trazo del Jugador2");
				uLose = true;
			}
		}
		if (arriba == true || abajo == true || izquierda == true || derecha == true) {
			for (int i = 0; i < posY2.getSize(); i++) {
				if (posX2.getValues(i) == origenX2 && posY2.getValues(i) == origenY2) {
					jug2 = false;
					// System.out.println("Jugador2 chocó con su propio trazo");
				}
			}
		}
		for (int i = 0; i < posX.getSize(); i++) {
			if (posX.getValues(i) == origenX2 && posY.getValues(i) == origenY2) {
				// System.out.println("Jugador2 chocó con el trazo del player");
				jug2 = false;
			}
		}

	}

	void resetLevel() {
		new Thread(() -> {
			tronlike.main(null);
		}).start();
		dispose();
	}

	void backMenu() {
		new Thread(() -> {
			Main.main(null); // llamar a la ventana de incio
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