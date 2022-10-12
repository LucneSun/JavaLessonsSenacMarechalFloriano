package main;

import javax.swing.*;

public class Window extends JFrame {
    private ImageIcon icon;

    public Window(JPanel panel){
        icon = new ImageIcon("src/com/sun/collectiblestore/static/sunlogo.png");
        setIconImage(icon.getImage());
        setTitle("Collectible Store");
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
