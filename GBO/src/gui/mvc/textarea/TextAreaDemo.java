package gui.mvc.textarea;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class TextAreaDemo extends JFrame
{
    private final LimitedDocument model;

    private final JTextArea leftArea;

    private final JTextArea rightArea;

    private final JButton appendButton;

    private final JTextArea logArea;

    private final JLabel infoLabel;

    public TextAreaDemo(final String title)
    {
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 1));

        model = new LimitedDocument(200);

        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 0));

        infoLabel = new JLabel();
        infoLabel.setName("infoLabel");

        leftArea = new JTextArea(model);
        leftArea.setName("leftArea");
        leftArea.addCaretListener(new MyCaretListener(infoLabel));
        final JScrollPane sc1 = new JScrollPane(leftArea);
        panel.add(sc1);

        rightArea = new JTextArea(model);
        rightArea.setName("rightArea");
        rightArea.addCaretListener(new MyCaretListener(infoLabel));
        final JScrollPane sc2 = new JScrollPane(rightArea);
        panel.add(sc2);

        appendButton = new JButton("Text anhängen");
        appendButton.setName("appendButton");
        final AppendListener al = new AppendListener(rightArea);
        appendButton.addActionListener(al);

        // Teilaufgabe d)
        // final AppendListener alTest = new AppendListener(leftArea);
        // appendButton.addActionListener(alTest);

        logArea = new JTextArea();
        logArea.setName("logArea");
        model.addDocumentListener(new MyDocumentListener(logArea));
        final JScrollPane logSc = new JScrollPane(logArea);

        add(panel);
        add(appendButton);
        add(logSc);
        add(infoLabel);
        pack();
        setVisible(true);

    }

    public static void main(final String[] args)
    {
        new TextAreaDemo("TextAreaDemo");
    }
}
