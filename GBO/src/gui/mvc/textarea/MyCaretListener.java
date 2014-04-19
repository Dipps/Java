package gui.mvc.textarea;

import javax.swing.JLabel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class MyCaretListener implements CaretListener
{
    private final JLabel l;

    public MyCaretListener(final JLabel l)
    {
        this.l = l;
    }

    @Override
    public void caretUpdate(final CaretEvent e)
    {
        if (e.getMark() != e.getDot())
        {
            l.setText("selection from: " + e.getMark() + " to " + e.getDot());
        }
        else
        {
            l.setText("caret: text position: " + e.getDot());
        }

    }
}
