/*
 *  @author Axel Isidro Quiroz Avalos
 *  Fecha: 07/01/2024
 *  Notas: Este es el menú del juego.
 */


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JMenuBar;

public class menuFrame extends JFrame {
   
    JMenuBar menubar;
    menuFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setTitle("Tdron Game");
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout() );

        menubar = new JMenuBar();
        
        this.setJMenuBar(menubar);

        this.setVisible(true);

    }
}
