package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.undo.*;

class UndoRedoHandler implements ActionListener
{
    private UndoManager manager;

    public UndoRedoHandler(UndoManager manager)
    {
        this.manager = manager;
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getActionCommand().equals("Undo"))
        {
            if(manager.canUndo())
            {
                manager.undo();
            }
        }
        else if(event.getActionCommand().equals("Redo"))
        {
            if(manager.canRedo())
            {
                manager.redo();
            }
        }
    }
}

public class UndoDemo2
{
    public static void main(String[] args)
    {
        JFrame f = new JFrame("Beispiel für Undo / Redo");
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JTextArea area = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(area);
        f.add(scrollPane, BorderLayout.CENTER);
        Document doc = area.getDocument();
        UndoManager manager = new UndoManager();
        doc.addUndoableEditListener(manager);
        
        JPanel buttonRow = new JPanel(new GridLayout(1, 0));
        UndoRedoHandler unreHandler = new UndoRedoHandler(manager);
        JButton back = new JButton("Undo");
        back.addActionListener(unreHandler);
        buttonRow.add(back);
        JButton forward = new JButton("Redo");
        forward.addActionListener(unreHandler);
        buttonRow.add(forward);
        f.add(buttonRow, BorderLayout.SOUTH);

        f.setLocation(300, 200);
        f.setSize(400, 500); // f.pack();
        f.setVisible(true);
    }
}
