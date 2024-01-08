package SourcePackage;

/*
Programa realizado por: Karina Figueroa
*/
/*
 * Cosas pendientes
 * Hacer que al mover de arriba a abajo vaya recto, investigar porque sucede
 */
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;

public class tronlike extends JFrame implements KeyListener, ActionListener {
	int vel = 10;
	int velbonus = 3;
	int origenX = 100, origenY = 550;
	String cadena, letrero;
	boolean bandera = false;

	boolean w, a, s, d = false; // movimiento

	tronlike() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(null);
		letrero = new String("Presione la flecha hacia arriba para iniciar..");
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

		g.clearRect(0, 0, 1200, 1000); // limpiar actualizacion

		g.setColor(new Color(0, 0, 0)); // color de fondo
		g.fillRect(0, 0, getWidth(), getHeight()); // color de fondo
		// Dibujar un marco azul alrededor de la ventana
		g.setColor(new Color(0, 0, 255)); // Establecer el color del marco como azul (RGB: 0, 0, 255)
		g.drawRect(85, 85, getWidth() - 100, getHeight() - 100); // Dibujar el rectángulo del marco

		g.drawString(cadena, origenX, origenY);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(new Color(255, 81, 0)); // Color Naranja al puntito
		g2d.fillOval(origenX, origenY, 30, 30); // tamaño de la bolita

	}

	public void paint(Graphics g) {
		System.out.println("paint");

		if (!bandera)
			g.drawString(letrero, origenX, origenY);
		else
			update(g);

	}

	public void run() {
		bandera = true;
		System.out.println("en sus marcas...");
		int cont = 0;
		while (bandera) {
			try {
				Thread.sleep(vel);
				cadena = "contador = " + cont++;
				// origenX++;// Mueve automaticamente
				if (w == true) {
					origenY--;
				} else if (a == true) {
					origenX--;
				} else if (s == true) {
					origenY++;
				} else if (d == true) {
					origenX++;
				}

			} catch (InterruptedException e) {
				System.out.println("oh oh me molestan....");
			}
			repaint();
		}
	}

	public void keyPressed(KeyEvent ispress) {
		// origenX += 1;
		System.out.println(ispress + "KEY PRESSED: ");
		// 37 izq, 38 up, 39 right, 40 down
		if (ispress.getKeyCode() == 87) { // w
			w = true;
			a = false;
			s = false;
			d = false;
			origenY -= velbonus;
		} else if (ispress.getKeyCode() == 83) { // s
			w = false;
			a = false;
			s = true;
			d = false;
			origenY += velbonus;
		} else if (ispress.getKeyCode() == 65) { // a
			w = false;
			a = true;
			s = false;
			d = false;
			origenX -= velbonus;
		} else if (ispress.getKeyCode() == 68) { // d
			w = false;
			a = false;
			s = false;
			d = true;
			origenX += velbonus; // Bonus de vel, cuando dejas presionado
		}
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
		System.out.println("display" + e + keyStatus);
	}
}