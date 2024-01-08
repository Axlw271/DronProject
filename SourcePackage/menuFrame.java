package SourcePackage;

/*
 *  @author Axel Isidro Quiroz Avalos
 *  Fecha: 07/01/2024
 *  Notas: Este es el menú del juego.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menuFrame extends JFrame {

    public menuFrame() {
        initUI();
    }

    private void initUI() {
        JButton startButton = new JButton("Iniciar Juego");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes llamar a tu código principal para iniciar el juego
                // En la clase Menu.java, dentro del ActionListener del botón "Iniciar Juego"
                new Thread(() -> {
                    tronlike.main(null);
                }).start();
                dispose(); // Cierra el menú después de iniciar el juego
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
            new menuFrame();
        });
    }
}
