package gui.state.phone;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PhoneController extends JPanel implements ActionListener
{
    private final PhoneModel model;

    public PhoneController(final PhoneModel model)
    {
        this.model = model;

        setLayout(new GridLayout(0, 1));

        final JButton menu = new JButton("Menü");
        menu.addActionListener(this);
        add(menu);

        final JButton up = new JButton("^");
        up.addActionListener(this);
        add(up);

        final JPanel pane = new JPanel();
        pane.setLayout(new GridLayout(0, 3));
        final JButton ok = new JButton("Okay");
        ok.addActionListener(this);
        pane.add(ok);
        final JButton buttonBook = new JButton("<html>" + "Telefon-" + "<br>" + "buch" + "</html>");
        buttonBook.addActionListener(this);
        pane.add(buttonBook);
        final JButton cancel = new JButton("Abbruch");
        cancel.addActionListener(this);
        pane.add(cancel);
        add(pane);

        final JButton down = new JButton("v");
        down.addActionListener(this);
        add(down);

        final JPanel numberPanel1 = new JPanel();
        numberPanel1.setLayout(new GridLayout(0, 3));
        for (int i = 1; i <= 3; i++)
        {
            final JButton b = new JButton("" + i);
            b.addActionListener(this);
            numberPanel1.add(b);
        }
        add(numberPanel1);

        final JPanel numberPanel2 = new JPanel();
        numberPanel2.setLayout(new GridLayout(0, 3));
        for (int i = 4; i <= 6; i++)
        {
            final JButton b = new JButton("" + i);
            b.addActionListener(this);
            numberPanel2.add(b);
        }
        add(numberPanel2);

        final JPanel numberPanel3 = new JPanel();
        numberPanel3.setLayout(new GridLayout(0, 3));
        for (int i = 7; i <= 9; i++)
        {
            final JButton b = new JButton("" + i);
            b.addActionListener(this);
            numberPanel3.add(b);
        }
        add(numberPanel3);

        final JButton b = new JButton("0");
        b.addActionListener(this);
        add(b);

    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        final String s = e.getActionCommand();

        if (s.equals("Menü"))
        {
            try
            {
                model.menu();
            }
            catch (final PhoneException e1)
            {
                System.out.println(e1.getMessage());
            }
        }
        else if (s.equals("^"))
        {
            try
            {
                model.up();
            }
            catch (final PhoneException e1)
            {
                System.out.println(e1.getMessage());
            }
        }
        else if (s.equals("Okay"))
        {
            try
            {
                model.okay();
            }
            catch (final PhoneException e1)
            {
                System.out.println(e1.getMessage());
            }
        }
        else if (s.equals("<html>" + "Telefon-" + "<br>" + "buch" + "</html>"))
        {
            try
            {
                model.phoneList();
            }
            catch (final PhoneException e1)
            {
                System.out.println(e1.getMessage());
            }
        }
        else if (s.equals("Abbruch"))
        {
            model.cancel();

        }
        else if (s.equals("v"))
        {
            try
            {
                model.down();
            }
            catch (final PhoneException e1)
            {
                System.out.println(e1.getMessage());
            }
        }
        for (int i = 0; i <= 9; i++)
        {
            if (s.equals("" + i))
            {
                try
                {
                    model.digit(i);
                }
                catch (final PhoneException e1)
                {
                    System.out.println(e1.getMessage());
                }
            }
        }
    }
}
