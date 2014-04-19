package gui.composite;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeSelectionModel;

/**
 * Klasse zum Testen des eigenen Baummodells.
 * 
 * @author Rainer Oechsle
 */
public class DesignPatternCompositeExampleAsTreeModel extends JFrame implements TreeSelectionListener
{
    private final DPCTreeModel model;

    private final JTextField name;

    private final JCheckBox type;

    private final JTextField weight;

    private final JTextField descriptionOrChildCount;

    private final JTextField parent;

    private final JLabel descriptionLabel;

    private Component selected;

    private final ButtonHandler bHandler;

    /**
     * Konstruktor.
     * 
     * @param title
     *            Titel
     * @param model
     *            Model
     */
    public DesignPatternCompositeExampleAsTreeModel(final String title, final DPCTreeModel model)
    {
        super(title);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.model = model;
        this.selected = (Component) model.getRoot();

        final JTree tree = new JTree(model);
        tree.setName("tree");
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setSelectionRow(0);
        tree.addTreeSelectionListener(this);
        final JScrollPane pane = new JScrollPane(tree);

        final JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0, 2));

        infoPanel.add(new JLabel("Name: "));
        name = new JTextField(selected.getName());
        name.setName("name");
        name.setEditable(false);
        infoPanel.add(name);

        infoPanel.add(new JLabel("Typ: "));
        type = new JCheckBox("Schachtel");
        type.setName("type");
        type.setEnabled(false);
        type.setSelected(true);
        infoPanel.add(type);

        infoPanel.add(new JLabel("Gewicht: "));
        weight = new JTextField("" + selected.getWeight());
        weight.setName("weight");
        weight.setEditable(false);
        infoPanel.add(weight);

        descriptionLabel = new JLabel("Anzahl der Nachfolger: ");
        infoPanel.add(descriptionLabel);
        descriptionOrChildCount = new JTextField("" + ((Container) selected).getNumberOfChildren());
        descriptionOrChildCount.setName("descriptionOrChildCount");
        descriptionOrChildCount.setEditable(false);
        infoPanel.add(descriptionOrChildCount);

        infoPanel.add(new JLabel("Enthalten in: "));
        parent = new JTextField();
        parent.setName("parent");
        parent.setEditable(false);
        infoPanel.add(parent);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 0));
        final JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        bHandler = new ButtonHandler(this);

        final MouseListener ml = new RightMouseButtonHandler(bHandler);
        tree.addMouseListener(ml);

        final JButton addContainer = new JButton("<html>" + "Schachtel" + "<br>" + "hinzufügen" + "</html>");
        addContainer.setName("addContainer");
        addContainer.addActionListener(bHandler);
        buttonPanel.add(addContainer);

        final JButton addItem = new JButton("<html>" + "Gegenstand" + "<br>" + "hinzufügen" + "</html>");
        addItem.setName("addItem");
        addItem.addActionListener(bHandler);
        buttonPanel.add(addItem);

        final JButton delete = new JButton("löschen");
        delete.setName("delete");
        delete.addActionListener(bHandler);
        buttonPanel.add(delete);

        leftPanel.add(pane, BorderLayout.CENTER);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(leftPanel);
        add(infoPanel);
        setLayout(new GridLayout(1, 0));
        setLocation(500, 100);
        setSize(700, 250);
        setVisible(true);

    }

    /**
     * main-Methode.
     * 
     * @param args
     *            Kommandozeilenargumente (nicht verwendet)
     */
    public static void main(final String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (final Exception exc)
        {
        }

        final Container root = DesignPatternCompositeExample.makeExampleTree();
        final DPCTreeModel testmodel = new DPCTreeModel(root);
        new DesignPatternCompositeExampleAsTreeModel("My Own Tree Model " + "Using The Composite " + "Design Pattern", testmodel);
    }

    @Override
    public void valueChanged(final TreeSelectionEvent e)
    {
        // TODO Auto-generated method stub
        selected = (Component) e.getPath().getLastPathComponent();

        name.setText(selected.getName());
        type.setSelected(selected instanceof Container);
        weight.setText("" + selected.getWeight());
        if (selected.getPredecessor() != null)
        {
            parent.setText(selected.getPredecessor().getName());
        }
        else
        {
            parent.setText("");
        }

        if (selected instanceof PrimitiveElement)
        {
            descriptionLabel.setText("Beschreibung: ");
            descriptionOrChildCount.setText(((PrimitiveElement) selected).getDescription());
        }
        else if (selected instanceof Container)
        {
            descriptionLabel.setText("Anzahl der Nachfolger: ");
            descriptionOrChildCount.setText("" + ((Container) selected).getNumberOfChildren());
        }

    }

    public Component getSelected()
    {
        return selected;
    }

    public DPCTreeModel getModel()
    {
        return model;
    }

    public JCheckBox getTypeBox()
    {
        return type;
    }

}
