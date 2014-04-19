package gui.cursor;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class CursorBsp extends JFrame implements ActionListener
{
    private final JPanel mainPanel;

    private final JPanel northPanel;

    private final JPanel southPanel;

    private final JPanel radioPanel;

    public CursorBsp(final String title, final String[] bName)
    {
        super(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Panels erzeugen
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(0, 1));
        southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(0, 1));

        // JButtons erzeugen
        final JButton northB = new JButton("NORTH");
        northPanel.add(northB);

        final JButton southB = new JButton("SOUTH");
        southPanel.add(southB);

        // JRadiobuttons erzeugen
        radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(0, 1));
        final ButtonGroup rbg = new ButtonGroup();
        for (final String i : bName)
        {
            final JRadioButton rb = new JRadioButton(i);
            rb.setName(i);
            radioPanel.add(rb);
            rbg.add(rb);
            rb.addActionListener(this);

        }

        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(radioPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        add(mainPanel);
        pack();
        setVisible(true);

    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        int cursor = Cursor.DEFAULT_CURSOR;
        final JRadioButton rb = (JRadioButton) e.getSource();

        if (rb.isSelected())
        {
            cursor = switchCursor(rb.getName());
            if (cursor == Cursor.CROSSHAIR_CURSOR)
            {
                southPanel.setCursor(new Cursor(cursor));
                northPanel.setCursor(new Cursor(cursor));
            }
            else if (cursor == Cursor.HAND_CURSOR)
            {
                radioPanel.setCursor(new Cursor(cursor));
            }
            else
            {
                mainPanel.setCursor(new Cursor(cursor));
                radioPanel.setCursor(new Cursor(cursor));
                southPanel.setCursor(new Cursor(cursor));
                northPanel.setCursor(new Cursor(cursor));
            }
            System.out.println(rb.getName());
        }

    }

    public int switchCursor(final String s)
    {
        switch (s)
        {
            case "CROSSHAIR":
                return Cursor.CROSSHAIR_CURSOR;
            case "HAND":
                return Cursor.HAND_CURSOR;
            case "TEXT":
                return Cursor.TEXT_CURSOR;
            case "WAIT":
                return Cursor.WAIT_CURSOR;
            case "MOVE":
                return Cursor.MOVE_CURSOR;
            case "HEIGHT RESIZE":
                return Cursor.N_RESIZE_CURSOR;
            case "WIDTH RESIZE":
                return Cursor.E_RESIZE_CURSOR;
            case "NW-SE RESIZE":
                return Cursor.NW_RESIZE_CURSOR;
            case "NE-SW RESIZE":
                return Cursor.NE_RESIZE_CURSOR;
            default:
                return Cursor.DEFAULT_CURSOR;
        }

    }

    public static void main(final String[] args)
    {
        final String[] cursorName =
        { "DEFAULT", "CROSSHAIR", "HAND", "TEXT", "WAIT", "MOVE", "HEIGHT RESIZE", "WIDTH RESIZE", "NW-SE RESIZE", "NE-SW RESIZE" };
        new CursorBsp("Beispiele für Cursor", cursorName);
    }
}
