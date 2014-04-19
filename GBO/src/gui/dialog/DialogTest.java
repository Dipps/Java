package gui.dialog;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class DialogTest extends JFrame implements ActionListener
{

    public DialogTest(final String title)
    {
        super(title);
        final JButton b1 = new JButton("Dialog: modal und abh�ngig");
        b1.addActionListener(this);

        final JButton b2 = new JButton("Dialog: nicht modal und abh�ngig");
        b2.addActionListener(this);

        final JButton b3 = new JButton("Dialog: modal und nicht abh�ngig");
        b3.addActionListener(this);

        final JButton b4 = new JButton("Dialog: nicht modal und nicht abh�ngig");
        b4.addActionListener(this);

        add(b1);
        add(b2);
        add(b3);
        add(b4);

        setLayout(new GridLayout(4, 0));
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        final String s = e.getActionCommand();

        if (s.equals("Dialog: modal und abh�ngig"))
        {
            new MyDialog(this, "modal und abh�ngig", true);
            System.out.println(s);
        }
        else if (s.equals("Dialog: nicht modal und abh�ngig"))
        {
            new MyDialog(this, "nicht modal und abh�ngig", false);
            System.out.println(s);
        }

        else if (s.equals("Dialog: modal und nicht abh�ngig"))
        {
            new MyDialog(null, "modal und nicht abh�ngig", true);
            System.out.println(s);
        }

        else if (s.equals("Dialog: nicht modal und nicht abh�ngig"))
        {
            new MyDialog(null, "nicht modal und nicht abh�ngig", false);
            System.out.println(s);
        }
        else
        {
            throw new RuntimeException("Dialog nicht vorhanden!");
        }

    }

    public static void main(final String[] args)
    {
        new DialogTest("Dialogtest");
    }

}
