package pacman;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame {

    public Main() {
        
        initUI();
    }
    private void initUI() {
        
        add(new Board());
       
        setTitle("Pacman Game");
        pack();
        setSize(344, 427);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

    	EventQueue.invokeLater(() -> {
            	Main ex = new Main();
                ex.setVisible(true);
                ex.setResizable(false);
                
                ImageIcon icon = new ImageIcon("C:\\Users\\Muhammet Þahin\\eclipse-workspace\\Pacman\\image\\pacman1.png");
                ex.setIconImage(icon.getImage());
        });
    }
}

