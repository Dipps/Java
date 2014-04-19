package cg.ueb01;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

public class Hauptfenster extends JFrame implements ActionListener
{

    private final JMenuBar menuBar = new JMenuBar();

    private final JMenu menuProgram = new JMenu("Program");

    private final JMenu menukarte = new JMenu("Karte");

    private final JMenuItem mikal = new JMenuItem("Kalibrierung");

    private final JMenuItem miBeenden = new JMenuItem("Beenden");

    private final JMenuItem miLotse = new JMenuItem("Lotse");

    private final JMenuItem miS09l = new JMenuItem("S09l");

    private final JMenuItem miErde = new JMenuItem("Erde");

    private final JMenuItem miFarbe = new JMenuItem("Farbe");

    private final Zeichenflaeche zf = new Zeichenflaeche();

    private final JScrollPane sp = new JScrollPane(zf);

    private final StatusLeiste sl = new StatusLeiste();

    public Hauptfenster()
    {
        this("Swing-Test");

    }

    public Hauptfenster(final String title)
    {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        menuProgram.add(miFarbe);
        menuProgram.add(mikal);
        menuProgram.add(miBeenden);
        menuBar.add(menuProgram);

        menukarte.add(miLotse);
        menukarte.add(miS09l);
        menukarte.add(miErde);
        menuBar.add(menukarte);

        add(menuBar, BorderLayout.NORTH);

        mikal.addActionListener(this);
        miBeenden.addActionListener(this);
        miFarbe.addActionListener(this);
        miLotse.addActionListener(this);
        miS09l.addActionListener(this);
        miErde.addActionListener(this);

        add(sp, BorderLayout.CENTER);
        zf.addMouseMotionListener(sl);

        add(sl, BorderLayout.SOUTH);

        setLocation(200, 200);
        setSize(400, 400);
        setVisible(true);

    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        final String s = e.getActionCommand();
        if (s.equals("Kalibrierung"))
        {
            System.out.println("Kalibrierung");
        }
        else if (s.equals("Beenden"))
        {
            System.out.println("Beenden");
        }
        else if (s.equals("Farbe"))
        {
            zf.setKreisFarbe(JColorChooser.showDialog(this, "Farbe wählen", Color.RED));
            // zf.setKreisFarbe(Color.BLUE);
        }
        else if (s.equals("Lotse"))
        {
            zf.setIcon(new ImageIcon("res/Seekarte/lotse1.jpg"));
            // System.out.println("lotse");
        }
        else if (s.equals("S09l"))
        {
            zf.setIcon(new ImageIcon("res/Seekarte/s09l.jpg"));
            // System.out.println("s09l");
        }
        else if (s.equals("Erde"))
        {
            zf.setIcon(new ImageIcon("res/erde.jpg"));
        }

        // System.out.println(s);

    }

    public static void main(final String[] args)
    {
        new Hauptfenster();
    }

}
