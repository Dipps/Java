package gui.lecture;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

public class TreeExample extends JFrame
{
    public TreeExample(String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Create the nodes.
        DefaultMutableTreeNode top = createNodes();

        // Create a JTree.
        JTree tree = new JTree(top);

        // Listen for selection changes.
        SelectionHandler handler = new SelectionHandler(tree);
        tree.addTreeSelectionListener(handler);

        // Add the tree to this frame.
        add(tree);
    }

    private DefaultMutableTreeNode createNodes()
    {
        DefaultMutableTreeNode department = null;
        DefaultMutableTreeNode person = null;
        DefaultMutableTreeNode root = null;

        root = new DefaultMutableTreeNode("FH Trier");

        department = new DefaultMutableTreeNode("Informatik");
        root.add(department);

        person = new DefaultMutableTreeNode("Bläsius");
        department.add(person);

        person = new DefaultMutableTreeNode("Gemmar");
        department.add(person);

        person = new DefaultMutableTreeNode("Klösener");
        department.add(person);

        person = new DefaultMutableTreeNode("Knorr");
        department.add(person);

        person = new DefaultMutableTreeNode("Künkler");
        department.add(person);

        person = new DefaultMutableTreeNode("Linn");
        department.add(person);

        person = new DefaultMutableTreeNode("Lohscheller");
        department.add(person);

        person = new DefaultMutableTreeNode("Lürig");
        department.add(person);

        person = new DefaultMutableTreeNode("Lux");
        department.add(person);

        person = new DefaultMutableTreeNode("Oechsle");
        department.add(person);

        person = new DefaultMutableTreeNode("Rock");
        department.add(person);

        person = new DefaultMutableTreeNode("Rudolph");
        department.add(person);

        person = new DefaultMutableTreeNode("Schmitz");
        department.add(person);

        person = new DefaultMutableTreeNode("Schneider1");
        department.add(person);

        person = new DefaultMutableTreeNode("Schneider2");
        department.add(person);

        department = new DefaultMutableTreeNode("BWL");
        root.add(department);

        person = new DefaultMutableTreeNode("Kuhn");
        department.add(person);

        person = new DefaultMutableTreeNode("Rieder");
        department.add(person);

        person = new DefaultMutableTreeNode("Steinbuß");
        department.add(person);

        person = new DefaultMutableTreeNode("Steinmann");
        department.add(person);

        return root;
    }

    public static void main(String[] args)
    {
        JFrame frame = new TreeExample("Baum-Beispiel");
        frame.setSize(200, 350);
        frame.setVisible(true);
    }
}

class SelectionHandler implements TreeSelectionListener
{
    private JTree tree;

    public SelectionHandler(JTree tree)
    {
        this.tree = tree;
    }

    public void valueChanged(TreeSelectionEvent e)
    {
        TreePath path = tree.getSelectionPath();
        System.out.println("ausgewählt: " + path);
    }
}
