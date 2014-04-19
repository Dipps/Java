package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DelayedActionPerformed extends JFrame implements ActionListener
{
    private JLabel label;
    private JSlider slider;

    public DelayedActionPerformed()
    {
        super("Beispiel für länger dauernden ActionListener");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1));

        label = new JLabel();
        add(label);

        slider = new JSlider(0, 20, 10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);
        add(slider);

        JButton button = new JButton("Drück mich");
        button.addActionListener(this);
        add(button);

        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt)
    {
        int rounds = slider.getValue();
        for(int i = 1; i <= rounds; i++)
        {
            String message = "actionPerformed: Runde " + i;
            label.setText(message);
            System.out.println(message);
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
            }
        }
        String message = "actionPerformed: Ende";
        label.setText(message);
        System.out.println(message);
    }

    public static void main(String[] args)
    {
        new DelayedActionPerformed();
    }
}
