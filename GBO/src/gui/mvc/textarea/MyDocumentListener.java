package gui.mvc.textarea;

import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MyDocumentListener implements DocumentListener
{
    private final JTextArea ta;

    public MyDocumentListener(final JTextArea ta)
    {
        this.ta = ta;
    }

    @Override
    public void insertUpdate(final DocumentEvent e)
    {
        ta.append("INSERT: " + e.getLength() + " character, " + "Text length = " + e.getDocument().getLength() + ".\n");
    }

    @Override
    public void removeUpdate(final DocumentEvent e)
    {
        ta.append("REMOVE: " + e.getLength() + "character," + "Text length = " + e.getDocument().getLength() + ".\n");
    }

    @Override
    public void changedUpdate(final DocumentEvent e)
    {
        System.out.println("changed");
    }

}
