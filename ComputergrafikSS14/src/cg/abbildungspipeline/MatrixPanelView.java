package cg.abbildungspipeline;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MatrixPanelView extends JPanel implements IAbbListener {

    private final JLabel lAnsicht = new JLabel("Ansichtstransformation");
    private final JLabel lViewport = new JLabel("Viewport");
    private final JLabel lFactor;
    private final JLabel lAlpha;
    private final JLabel lBeta;
    private final JLabel lGamma;
    private final JButton bMZxZ = new JButton("Mzxz");

    private final MatrixView mAnsicht;
    private final MatrixView mViewport;
    private final MatrixView mFactor;
    private final MatrixView mAlpha;
    private final MatrixView mBeta;
    private final MatrixView mGamma;
    private final MatrixView mZxZ;

    public MatrixPanelView(AbbModel model, AbbController controller) {
        setLayout(new GridLayout(0, 2));

        bMZxZ.addActionListener(controller);

        lFactor = new JLabel("Faktor = " + model.getFactor());
        lAlpha = new JLabel("Alpha = " + model.getAlpha());
        lBeta = new JLabel("Beta = " + model.getAlpha());
        lGamma = new JLabel("Gamma = " + model.getGamma());

        mAnsicht = new MatrixView(model.getMAnsicht());
        mViewport = new MatrixView(model.getMViewport());
        mFactor = new MatrixView(model.getMFactor());
        mAlpha = new MatrixView(model.getMAlpha());
        mBeta = new MatrixView(model.getMBeta());
        mGamma = new MatrixView(model.getMGamma());
        mZxZ = new MatrixView(model.getMZxZ());

        formatLabels();
        formatMatrices();

        add(lAnsicht);
        add(mAnsicht);

        add(lViewport);
        add(mViewport);

        add(lFactor);
        add(mFactor);

        add(lAlpha);
        add(mAlpha);

        add(lBeta);
        add(mBeta);

        add(lGamma);
        add(mGamma);

        add(bMZxZ);
        add(mZxZ);
    }

    private void formatMatrices() {
        mAnsicht.setLabelBorder(Color.BLACK, 1);
        mViewport.setLabelBorder(Color.BLACK, 1);
        mFactor.setLabelBorder(Color.BLACK, 1);
        mAlpha.setLabelBorder(Color.BLACK, 1);
        mBeta.setLabelBorder(Color.BLACK, 1);
        mGamma.setLabelBorder(Color.BLACK, 1);
        mZxZ.setLabelBorder(Color.BLACK, 1);

        // mAnsicht.setLabelBorder(new LineBorder(Color.cyan, 1));
        // mViewport.setLabelBorder(new LineBorder(Color.cyan, 1));
        // mFactor.setLabelBorder(new LineBorder(Color.cyan, 1));
        // mAlpha.setLabelBorder(new LineBorder(Color.cyan, 1));
        // mBeta.setLabelBorder(new LineBorder(Color.cyan, 1));
        // mGamma.setLabelBorder(new LineBorder(Color.cyan, 1));
        // mZxZ.setLabelBorder(new LineBorder(Color.cyan, 1));

    }

    private void formatLabels() {
        Font f = new Font(getFont().getFontName(), Font.PLAIN, 12);

        lAnsicht.setBorder(new LineBorder(Color.BLACK));
        lAnsicht.setHorizontalAlignment(JLabel.CENTER);
        lAnsicht.setFont(f);

        lViewport.setBorder(new LineBorder(Color.BLACK));
        lViewport.setHorizontalAlignment(JLabel.CENTER);
        lViewport.setFont(f);

        lFactor.setBorder(new LineBorder(Color.BLACK));
        lFactor.setHorizontalAlignment(JLabel.CENTER);
        lFactor.setFont(f);

        lAlpha.setBorder(new LineBorder(Color.BLACK));
        lAlpha.setHorizontalAlignment(JLabel.CENTER);
        lAlpha.setFont(f);

        lBeta.setBorder(new LineBorder(Color.BLACK));
        lBeta.setHorizontalAlignment(JLabel.CENTER);
        lBeta.setFont(f);

        lGamma.setBorder(new LineBorder(Color.BLACK));
        lGamma.setHorizontalAlignment(JLabel.CENTER);
        lGamma.setFont(f);

        bMZxZ.setBorder(new LineBorder(Color.BLACK));
        bMZxZ.setHorizontalAlignment(JLabel.CENTER);
        bMZxZ.setFont(f);
    }

    @Override
    public void AbbModelChanged(AbbModel model) {
        lFactor.setText("Faktor = " + model.getFactor());
        lAlpha.setText("Alpha = " + model.getAlpha());
        lBeta.setText("Beta = " + model.getBeta());
        lGamma.setText("Gamma = " + model.getGamma());

        mAnsicht.setLabels(model.getMAnsicht());
        mViewport.setLabels(model.getMViewport());
        mFactor.setLabels(model.getMFactor());
        mAlpha.setLabels(model.getMAlpha());
        mBeta.setLabels(model.getMBeta());
        mGamma.setLabels(model.getMGamma());
        mZxZ.setLabels(model.getMZxZ());

    }

}
