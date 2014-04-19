package gui.state.phone;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TextView extends JPanel implements PhoneListener
{

    private final JLabel state;

    private final JLabel number;

    public TextView()
    {
        setLayout(new GridLayout(0, 1));
        state = new JLabel("Idle");
        number = new JLabel("<>");

        add(state);
        add(number);
    }

    @Override
    public void stateChanged(final String newState)
    {
        state.setText(newState);

    }

    @Override
    public void currentNumberChanged(final String newCurrentNumber)
    {
        number.setText("<" + newCurrentNumber + ">");

    }

}
