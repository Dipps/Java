package gui.lecture;

import javax.swing.*;

public class SplitPaneExample extends JFrame
{
    public SplitPaneExample(String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JTextArea ta1 = new JTextArea();
        JScrollPane scrollPane1 = new JScrollPane(ta1);
        JTextArea ta2 = new JTextArea();
        JScrollPane scrollPane2 = new JScrollPane(ta2);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                              scrollPane1, scrollPane2);
        splitPane.setDividerSize(10);
        splitPane.setDividerLocation(200);
        splitPane.setOneTouchExpandable(true);

        add(splitPane);
    }

    public static void main(String[] args)
    {
        SplitPaneExample frame = new SplitPaneExample("Beispiel "
                                                      + "für SplitPanes");

        frame.setSize(400, 200);
        frame.setVisible(true);
    }
}
