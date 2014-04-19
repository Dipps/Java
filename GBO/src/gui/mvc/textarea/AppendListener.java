package gui.mvc.textarea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

public class AppendListener implements ActionListener
{
    private final JTextArea ta;

    public AppendListener(final JTextArea ta)
    {
        this.ta = ta;
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        ta.append("#\n");
    }
}
