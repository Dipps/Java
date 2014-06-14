package cg.gl.alphablending;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControllerFrame extends JFrame implements ActionListener,
        ChangeListener {

    private final Alphablending2 ab2;

    private final JRadioButton quadFirst;
    private final JRadioButton circleFirst;

    private final JCheckBox mQuadAlpha = new JCheckBox("Quad Alpha Manuell");
    private final JCheckBox mCircleAlpha = new JCheckBox("Circle Alpha Manuell");

    private final JCheckBox cbDepthTest = new JCheckBox("DepthTest");

    private final JLabel lQuadAlpha = new JLabel("QuadAlpha");
    private final JSlider sQuadAlpha = new JSlider(0, 100);

    private final JLabel lCircleAlpha = new JLabel("CircleAlpha");
    private final JSlider sCircleAlpha = new JSlider(0, 100);

    public ControllerFrame(Alphablending2 ab2) {
        super("Kontroll - Fenster");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.ab2 = ab2;

        quadFirst = new JRadioButton("Quad First");
        quadFirst.addActionListener(this);
        circleFirst = new JRadioButton("Circle First");
        circleFirst.addActionListener(this);

        mQuadAlpha.addActionListener(this);
        mCircleAlpha.addActionListener(this);
        cbDepthTest.addActionListener(this);

        ButtonGroup grp = new ButtonGroup();
        quadFirst.setSelected(true);
        grp.add(quadFirst);
        grp.add(circleFirst);

        JPanel bPanel = new JPanel();
        bPanel.setLayout(new GridLayout());
        bPanel.add(quadFirst);
        bPanel.add(circleFirst);

        JPanel sPanel = new JPanel();
        sPanel.setLayout(new GridLayout(0, 2));

        sQuadAlpha.addChangeListener(this);
        sQuadAlpha.setMinorTickSpacing(1);
        sQuadAlpha.setMajorTickSpacing(10);
        sQuadAlpha.setPaintTicks(true);
        sQuadAlpha.setName("sQuadAlpha");
        sQuadAlpha.setEnabled(false);
        // sQuadAlpha.setPaintLabels(true);

        sCircleAlpha.addChangeListener(this);
        sCircleAlpha.setMinorTickSpacing(1);
        sCircleAlpha.setMajorTickSpacing(10);
        sCircleAlpha.setPaintTicks(true);
        sCircleAlpha.setName("sCircleAlpha");
        sCircleAlpha.setEnabled(false);
        // sCircleAlpha.setPaintLabels(true);

        sPanel.add(mQuadAlpha);
        sPanel.add(mCircleAlpha);
        sPanel.add(lQuadAlpha);
        sPanel.add(sQuadAlpha);
        sPanel.add(lCircleAlpha);
        sPanel.add(sCircleAlpha);

        add(sPanel, BorderLayout.NORTH);
        add(bPanel, BorderLayout.CENTER);
        add(cbDepthTest, BorderLayout.SOUTH);

        setLocation(0, 50);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Quad First")) {
            System.out.println("QuadFirst");
            ab2.setFirstElement(FirstElement.QUAD);
        }
        if (s.equals("Circle First")) {
            System.out.println("CircleFirst");
            ab2.setFirstElement(FirstElement.CIRCLE);
        }

        if (s.equals("DepthTest")) {
            JCheckBox jcb = (JCheckBox) e.getSource();
            System.out.println("DepthTest");
            ab2.setDepthTest(jcb.isSelected());
        }

        if (s.equals("Quad Alpha Manuell")) {
            JCheckBox jcb = (JCheckBox) e.getSource();
            ab2.setmQuadAlpha(jcb.isSelected());
            sQuadAlpha.setEnabled(jcb.isSelected());
            System.out.println("Manuell Quad Alpha: " + jcb.isSelected());
        }

        if (s.equals("Circle Alpha Manuell")) {
            JCheckBox jcb = (JCheckBox) e.getSource();
            ab2.setmCircleAlpha(jcb.isSelected());
            sCircleAlpha.setEnabled(jcb.isSelected());
            System.out.println("Circle Alpha Manuell: " + jcb.isSelected());
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider js = (JSlider) e.getSource();

        if (js.getName().equals("sQuadAlpha")) {
            ab2.setQuadAlpha((float) (js.getValue()) / 100);
        } else if (js.getName().equals("sCircleAlpha")) {
            ab2.setCircleAlpha((float) (js.getValue()) / 100);
        }
    }
}
