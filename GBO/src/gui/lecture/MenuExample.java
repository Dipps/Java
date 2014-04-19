package gui.lecture;

import java.awt.event.*;
import javax.swing.*;

public class MenuExample extends JFrame implements ActionListener
{
    public MenuExample(String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JCheckBoxMenuItem cbMenuItem;
        JRadioButtonMenuItem rbMenuItem;

        // add regular components to the window
        JPanel p = new JPanel();
        add(p);

        // create the menu bar.
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // build the first menu.
        menu = new JMenu("File");
        menuBar.add(menu);

        // a group of JMenuItems
        menuItem = new JMenuItem("New");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                                                       ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("Open");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("Save");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        // a group of radio button menu items
        menu.addSeparator();
        menu.add(new JLabel("Hier kommen Radiobuttons"));
        ButtonGroup group = new ButtonGroup();
        rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
        rbMenuItem.setSelected(true);
        rbMenuItem.addActionListener(this);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem = new JRadioButtonMenuItem("Another one");
        rbMenuItem.addActionListener(this);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        // a group of check box menu items
        menu.addSeparator();
        cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
        cbMenuItem.addActionListener(this);
        menu.add(cbMenuItem);
        cbMenuItem = new JCheckBoxMenuItem("Another one");
        cbMenuItem.addActionListener(this);
        menu.add(cbMenuItem);

        // a submenu
        menu.addSeparator();
        submenu = new JMenu("A submenu");
        menuItem = new JMenuItem("An item in the submenu");
        menuItem.addActionListener(this);
        submenu.add(menuItem);
        menuItem = new JMenuItem("Another item in the submenu");
        menuItem.addActionListener(this);
        submenu.add(menuItem);
        menu.add(submenu);

        // build second menu in the menu bar.
        menu = new JMenu("Options");
        menuBar.add(menu);

        // a group of JMenuItems
        menuItem = new JMenuItem("Copy");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("Cut");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("Paste");
        menuItem.addActionListener(this);
        menu.add(menuItem);
    }

    public void actionPerformed(ActionEvent evt)
    {
        System.out.println("selected menu entry " + evt.getActionCommand());
    }

    public static void main(String[] args)
    {
        MenuExample window = new MenuExample("Menü-Beispiel");
        window.setSize(450, 260);
        window.setVisible(true);
    }
}
