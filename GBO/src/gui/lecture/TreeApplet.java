package gui.lecture;

import java.applet.AppletContext;
import java.net.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

public class TreeApplet extends JApplet
{
    public void init()
    {
        // Create the nodes.
        TreeNode top = createNodes();

        // Create a JTree.
        JTree tree = new JTree(top);

        // Listen for selection changes.
        LinkSelectionHandler handler = new LinkSelectionHandler(tree, this);
        tree.addTreeSelectionListener(handler);

        // Add the tree to this frame.
        add(tree);
    }

    private TreeNode createNodes()
    {
        DefaultMutableTreeNode root;
        DefaultMutableTreeNode node;
        DefaultMutableTreeNode link;

        root = new DefaultMutableTreeNode("Alle Links");

        node = new DefaultMutableTreeNode("FH Trier");
        root.add(node);

        link = new DefaultMutableTreeNode("http://www.fh-trier.de");
        node.add(link);
        link = new DefaultMutableTreeNode("http://www.informatik.fh-trier.de");
        node.add(link);

        node = new DefaultMutableTreeNode("Musik");
        root.add(node);
        
        link = new DefaultMutableTreeNode("http://www.vanmorrison.com");
        node.add(link);

        return root;
    }
    
    public void load(String doc)
    {
        System.out.println("load " + doc);
        AppletContext ac = getAppletContext();
        try
        {
            ac.showDocument(new URL(doc), "Content");
            showStatus("loaded " + doc);
        }
        catch(MalformedURLException exc)
        {
            showStatus(exc.toString());
        }
    }
}

class LinkSelectionHandler implements TreeSelectionListener
{
    private JTree tree;
    private TreeApplet applet;

    public LinkSelectionHandler(JTree tree, TreeApplet applet)
    {
        this.tree = tree;
        this.applet = applet;
    }

    public void valueChanged(TreeSelectionEvent e)
    {
        TreePath path = tree.getSelectionPath();
        if(path != null)
        {
            TreeNode node = (TreeNode) path.getLastPathComponent();
            if(node.getChildCount() == 0)
            {
                applet.load(node.toString());
            }
        }
    }
}
