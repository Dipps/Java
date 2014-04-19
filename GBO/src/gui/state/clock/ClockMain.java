package gui.state.clock;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ClockMain extends JFrame
{
    private final Clock model;

    public ClockMain()
    {
        super("Clock");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new Clock();
        final ClockView view = new ClockView();
        model.addClockListener(view);

        final ClockController controller = new ClockController(model);

        final JButton setB = new JButton("Set");
        setB.setName("set");
        setB.addActionListener(controller);

        final JButton plusB = new JButton("+");
        plusB.setName("plus");
        plusB.addActionListener(controller);

        final JButton minusB = new JButton("-");
        minusB.setName("minus");
        minusB.addActionListener(controller);

        final JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new GridLayout(0, 3));
        buttonPane.add(setB);
        buttonPane.add(plusB);
        buttonPane.add(minusB);

        add(view, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    public static void main(final String[] args)
    {
        new ClockMain();
        new ClockMain();
        new ClockMain();
        new ClockMain();
    }

}
