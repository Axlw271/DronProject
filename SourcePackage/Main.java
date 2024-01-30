package SourcePackage;

/*
 *  @author Axel Isidro Quiroz Avalos
 *  Fecha: 07/01/2024
 *  Notas: Este es el menú del juego y página principal del juego.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    public Main() {
        initUI();
    }

    private void initUI() {
        JButton startButton = new JButton("Iniciar Juego");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Globals.level1=true;
                new Thread(() -> {
                    tronlike.main(null); //llamar juego en otro hilo para no interferir
                }).start();
                dispose(); //cerrar menú
            }
        });

        JButton twoPlayersButton = new JButton("2 Jugadores");
        twoPlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Globals.twoPlayers=true;
                new Thread(() -> {
                    tronlike.main(null); //llamar juego en otro hilo para no interferir
                }).start();
                dispose(); //cerrar menú
            }
        });

        JButton exitButton = new JButton("Salir");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(startButton);
        panel.add(twoPlayersButton);
        panel.add(exitButton);

        add(panel);

        setTitle("Menú de Tron");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main();
        });
    }
}
