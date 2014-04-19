package gui.mvc.textarea;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitedDocument extends PlainDocument
{
    // Attribute (falls n�tig):
    private final int capacity;

    // �ffentlicher Konstruktor (Kapazit�t der Zeichen
    // soll als Parameter des Typs int angegeben werden):
    public LimitedDocument(final int capacity)
    {
        if (capacity < 0)
        {
            throw new IllegalArgumentException("capacity < 0");
        }
        this.capacity = capacity;
    }

    // �berschriebene Methode insertString:
    @Override
    public void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException
    {
        if ((str.length() + getLength()) <= capacity)
        {
            super.insertString(offs, str, a);
        }
        else
        {
            Toolkit.getDefaultToolkit().beep();
        }
    }
}
