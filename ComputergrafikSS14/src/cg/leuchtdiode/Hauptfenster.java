package cg.leuchtdiode;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Hauptfenster extends JFrame {

    private final LEDFenster ledFenster = new LEDFenster();
    private final JScrollPane sp = new JScrollPane(ledFenster);

    public Hauptfenster() {
        this("LED - Fenster");
    }

    public Hauptfenster(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(sp, BorderLayout.CENTER);

        setLocation(10, 10);
        setSize(800, 800);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Hauptfenster();

    }

}
