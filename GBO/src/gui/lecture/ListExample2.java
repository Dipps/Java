package gui.lecture;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ListExample2 extends JFrame implements ActionListener
{
    private JList<String> leftList;
    private JList<String> rightList;

    public ListExample2(String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 0));
        DefaultListModel<String> leftModel = new DefaultListModel<String>();
        leftModel.addElement("eins");
        leftModel.addElement("zwei");
        leftModel.addElement("drei");
        leftModel.addElement("vier");
        leftModel.addElement("fünf");
        leftModel.addElement("sechs");
        leftList = new JList<String>(leftModel);
        JScrollPane leftPane = new JScrollPane(leftList);
        p1.add(leftPane);
        DefaultListModel<String> rightModel = new DefaultListModel<String>();
        rightList = new JList<String>(rightModel);
        rightModel.addElement("un");
        rightModel.addElement("deux");
        rightModel.addElement("trois");
        rightModel.addElement("quatre");
        rightModel.addElement("cinq");
        rightModel.addElement("six");
        JScrollPane rightPane = new JScrollPane(rightList);
        p1.add(rightPane);

        // Create buttons for adding/removing items from lists
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(1, 0));
        JButton b;
        b = new JButton(">");
        p2.add(b);
        b.addActionListener(this);
        b = new JButton(">>");
        p2.add(b);
        b.addActionListener(this);
        b = new JButton("<");
        p2.add(b);
        b.addActionListener(this);
        b = new JButton("<<");
        p2.add(b);
        b.addActionListener(this);

        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(p1, BorderLayout.CENTER);
        p.add(p2, BorderLayout.SOUTH);

        add(p);
        setSize(330, 200);
        setVisible(true);
    }

    private void move(JList<String> l1, JList<String> l2, boolean all)
    {
        DefaultListModel<String> model1 = (DefaultListModel<String>) l1.getModel();
        DefaultListModel<String> model2 = (DefaultListModel<String>) l2.getModel();
        if(all)
        {
            for(int i = 0; i < model1.getSize(); i++)
            {
                String s = model1.getElementAt(i);
                model2.addElement(s);
            }
            model1.removeAllElements();
        }
        else
        {
            java.util.List<String> items = l1.getSelectedValuesList();
            l2.clearSelection();
            for(String s: items)
            {
                model2.addElement(s);
                model1.removeElement(s);
            }
        }
    }

    public void actionPerformed(ActionEvent evt)
    {
        String arg = evt.getActionCommand();
        if(">".equals(arg))
        {
            move(leftList, rightList, false);
        }
        else if(">>".equals(arg))
        {
            move(leftList, rightList, true);
        }
        else if("<".equals(arg))
        {
            move(rightList, leftList, false);
        }
        else if("<<".equals(arg))
        {
            move(rightList, leftList, true);
        }
    }

    public static void main(String[] args)
    {
        new ListExample2("Beispiel für zwei Listen");
    }
}
