package gui.dialog;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyDialog extends JDialog implements ActionListener
{
    public MyDialog(final JFrame owner, final String title, final boolean modal)
    {
        super(owner, title, modal);

        final JLabel l = new JLabel("Meine Dialog");
        final JButton b = new JButton("OK");
        b.addActionListener(this);

        add(l);
        add(b);

        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.setSize(50, 100);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        System.out.println("Ok wurde gedrueckt!");

    }

}
