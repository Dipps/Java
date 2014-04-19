package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.undo.*;

import java.util.*;

class UndoRedoModel
{
    private float f;
    private int i;
    private String s;
    private ArrayList<UndoRedoModelChangeListener> listeners;
    
    public UndoRedoModel(float f, int i, String s)
    {
        this.f = f;
        this.i = i;
        this.s = s;
        this.listeners = new ArrayList<UndoRedoModelChangeListener>();
    }

    public float getF()
    {
        return f;
    }

    public void setF(float f)
    {
        this.f = f;
        for(UndoRedoModelChangeListener l: listeners)
        {
            l.changedF(f);
        }
    }
    
    public int getI()
    {
        return i;
    }
    
    public void setI(int i)
    {
        this.i = i;
        for(UndoRedoModelChangeListener l: listeners)
        {
            l.changedI(i);
        }
    }
    
    public String getS()
    {
        return s;
    }
    
    public void setS(String s)
    {
        this.s = s;
        for(UndoRedoModelChangeListener l: listeners)
        {
            l.changedS(s);
        }
    }
    
    public void addView(UndoRedoModelChangeListener l)
    {
        listeners.add(l);
    }
}

interface UndoRedoModelChangeListener
{
    public void changedF(float newValue);
    public void changedI(int newValue);
    public void changedS(String newValue);
}

class UndoRedoModelView extends JPanel
                        implements UndoRedoModelChangeListener
{
    private JLabel fView, iView, sView;

    public UndoRedoModelView(float fInit, int iInit, String sInit)
    {
        setLayout(new GridLayout(3,2));
        Font ft = new Font("Arial", Font.BOLD, 22);

        JLabel l = new JLabel("f:", SwingConstants.RIGHT);
        l.setFont(ft);
        add(l);
        fView = new JLabel("" + fInit);
        fView.setFont(ft);
        add(fView);

        l = new JLabel("i:", SwingConstants.RIGHT);
        l.setFont(ft);
        add(l);
        iView = new JLabel("" + iInit);
        iView.setFont(ft);
        add(iView);

        l = new JLabel("s:", SwingConstants.RIGHT);
        l.setFont(ft);
        add(l);
        sView = new JLabel(sInit);
        sView.setFont(ft);
        add(sView);
    }
    
    public void changedF(float newValue)
    {
        fView.setText("" + newValue);
    }

    public void changedI(int newValue)
    {
        iView.setText("" + newValue);
    }

    public void changedS(String newValue)
    {
        sView.setText(newValue);
    }
}

class XController
{
    protected UndoManager manager;
    private JButton undoButton;
    private JButton redoButton;

    public XController(UndoManager manager,
                       JButton undoButton, JButton redoButton)
    {
        this.manager = manager;
        this.undoButton = undoButton;
        this.redoButton = redoButton;
    }
    
    protected void update()
    {
        if(manager.canUndo())
        {
            undoButton.setEnabled(true);
            undoButton.setText(manager.getUndoPresentationName());
        }
        else
        {
            undoButton.setEnabled(false);
            undoButton.setText("No undo");
        }
        if(manager.canRedo())
        {
            redoButton.setEnabled(true);
            redoButton.setText(manager.getRedoPresentationName());
        }
        else
        {
            redoButton.setEnabled(false);
            redoButton.setText("No redo");
        }
    }
}

class FController extends XController implements ActionListener
{
    private UndoRedoModel model;

    public FController(UndoRedoModel model, UndoManager manager,
                       JButton undoButton, JButton redoButton)
    {
        super(manager, undoButton, redoButton);
        this.model = model;
    }

    public void actionPerformed(ActionEvent e)
    {
        JTextField input = (JTextField) e.getSource();
        try
        {
            float f = Float.parseFloat(input.getText());
            UndoRedoSetF command = new UndoRedoSetF(model, f);
            manager.addEdit(command);
            update();
            model.setF(f);
        }
        catch(NumberFormatException exc)
        {
            JOptionPane.showMessageDialog(null,
                                          "Ungültige Eingabe",
                                          "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
        }
        input.setText("");
    }    
}

class IController extends XController implements ActionListener
{
    private UndoRedoModel model;

    public IController(UndoRedoModel model, UndoManager manager,
                       JButton undoButton, JButton redoButton)
    {
        super(manager, undoButton, redoButton);
        this.model = model;
    }

    public void actionPerformed(ActionEvent e)
    {
        JTextField input = (JTextField) e.getSource();
        try
        {
            int i = Integer.parseInt(input.getText());
            UndoRedoSetI command = new UndoRedoSetI(model, i);
            manager.addEdit(command);
            update();
            model.setI(i);
        }
        catch(NumberFormatException exc)
        {
            JOptionPane.showMessageDialog(null,
                                          "Ungültige Eingabe",
                                          "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
        }
        input.setText("");
    }
    
}

class SController extends XController implements ActionListener
{
    private UndoRedoModel model;

    public SController(UndoRedoModel model, UndoManager manager,
                       JButton undoButton, JButton redoButton)
    {
        super(manager, undoButton, redoButton);
        this.model = model;
    }

    public void actionPerformed(ActionEvent e)
    {
        JTextField input = (JTextField) e.getSource();
        String s = input.getText();
        UndoRedoSetS command = new UndoRedoSetS(model, s);
        manager.addEdit(command);
        update();
        model.setS(s);
        input.setText("");
    }
    
}

abstract class UndoRedoSetX implements UndoableEdit
{
    public boolean addEdit(UndoableEdit edit)
    {
        return false;
    }

    public boolean canRedo()
    {
        return true;
    }

    public boolean canUndo()
    {
        return true;
    }

    public void die()
    {
    }

    public String getRedoPresentationName()
    {
        return "Redo " + getPresentationName();
    }

    public String getUndoPresentationName()
    {
        return "Undo " + getPresentationName();
    }

    public boolean isSignificant()
    {
        return true;
    }

    public boolean replaceEdit(UndoableEdit arg0)
    {
        return false;
    }
}

class UndoRedoSetF extends UndoRedoSetX
{
    private UndoRedoModel model;
    private float previousValue, newValue;
    
    public UndoRedoSetF(UndoRedoModel model, float newValue)
    {
        this.model = model;
        this.previousValue = model.getF();
        this.newValue = newValue;
    }

    public String getPresentationName()
    {
        return "change f from " + previousValue + " to " + newValue;
    }
    
    public void undo() throws CannotUndoException
    {
        model.setF(previousValue);
    }    

    public void redo() throws CannotRedoException
    {
        model.setF(newValue);
    }
}

class UndoRedoSetI extends UndoRedoSetX
{
    private UndoRedoModel model;
    private int previousValue, newValue;
    
    public UndoRedoSetI(UndoRedoModel model, int newValue)
    {
        this.model = model;
        this.previousValue = model.getI();
        this.newValue = newValue;
    }

    public String getPresentationName()
    {
        return "change i from " + previousValue + " to " + newValue;
    }
    
    public void undo() throws CannotUndoException
    {
        model.setI(previousValue);
    }    

    public void redo() throws CannotRedoException
    {
        model.setI(newValue);
    }
}

class UndoRedoSetS extends UndoRedoSetX
{
    private UndoRedoModel model;
    private String previousValue, newValue;
    
    public UndoRedoSetS(UndoRedoModel model, String newValue)
    {
        this.model = model;
        this.previousValue = model.getS();
        this.newValue = newValue;
    }

    public String getPresentationName()
    {
        return "change s from " + previousValue + " to " + newValue;
    }
    
    public void undo() throws CannotUndoException
    {
        model.setS(previousValue);
    }    

    public void redo() throws CannotRedoException
    {
        model.setS(newValue);
    }
}

class UndoRedoListener extends XController implements ActionListener
{
    public UndoRedoListener(UndoManager manager,
                            JButton undoButton, JButton redoButton)
    {
        super(manager, undoButton, redoButton);
    }

    /*
    public void actionPerformed(ActionEvent event)
    {
        if(event.getActionCommand().startsWith("Undo"))
        {
            if(manager.canUndo())
            {
                manager.undo();
                update();
            }
            else
            {
                JOptionPane.showMessageDialog(null,
                                              "Kein Undo mehr möglich",
                                              "Warnung", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if(event.getActionCommand().startsWith("Redo"))
        {
            if(manager.canRedo())
            {
                manager.redo();
                update();
            }
            else
            {
                JOptionPane.showMessageDialog(null,
                                              "Kein Redo mehr möglich",
                                              "Warnung", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    */

    public void actionPerformed(ActionEvent event)
    {
        if(event.getActionCommand().startsWith("Undo"))
        {
            manager.undo();
        }
        else if(event.getActionCommand().startsWith("Redo"))
        {
            manager.redo();
        }
        update();
    }
}

public class UndoDemo1
{
    public static void main(String[] args)
    {
        JFrame f = new JFrame("Beispiel für Undo / Redo");
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setLayout(new BorderLayout());

        UndoRedoModel model = new UndoRedoModel(1.1f, 2, "hallo");
        UndoManager manager = new UndoManager();
        
        JPanel undoredoPanel = new JPanel(new GridLayout(1, 0));
        JButton undoButton = new JButton("No undo");
        undoButton.setEnabled(false);
        JButton redoButton = new JButton("No redo");
        redoButton.setEnabled(false);
        UndoRedoListener unreHandler = new UndoRedoListener(manager,
                                                            undoButton,
                                                            redoButton);
        redoButton.addActionListener(unreHandler);
        undoButton.addActionListener(unreHandler);
        undoredoPanel.add(undoButton);
        undoredoPanel.add(redoButton);
        f.add(undoredoPanel, BorderLayout.SOUTH);

        JPanel controllerPanel = new JPanel(new GridLayout(0,2));
        controllerPanel.add(new JLabel("f:", SwingConstants.RIGHT));
        JTextField fField = new JTextField();
        FController fController = new FController(model, manager,
                                                  undoButton, redoButton);
        fField.addActionListener(fController);
        controllerPanel.add(fField);
        controllerPanel.add(new JLabel("i:", SwingConstants.RIGHT));
        JTextField iField = new JTextField();
        IController iController = new IController(model, manager,
                                                  undoButton, redoButton);
        iField.addActionListener(iController);
        controllerPanel.add(iField);
        controllerPanel.add(new JLabel("s:", SwingConstants.RIGHT));
        JTextField sField = new JTextField();
        SController sController = new SController(model, manager,
                                                  undoButton, redoButton);
        sField.addActionListener(sController);
        controllerPanel.add(sField);

        UndoRedoModelView view = new UndoRedoModelView(model.getF(),
                                                       model.getI(),
                                                       model.getS());
        model.addView(view);
        
        JPanel mainPanel = new JPanel(new GridLayout(1, 0));
        mainPanel.add(controllerPanel);
        mainPanel.add(view);
        f.add(mainPanel, BorderLayout.NORTH);

        f.setLocation(300, 250);
        f.setSize(500, 150); // f.pack();
        f.setVisible(true);
    }
}
