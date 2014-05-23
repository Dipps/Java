package cg.leuchtdiode;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class NCView {

    private final SiebenSegmentAnzeige ssa;
    private final JFrame jf;

    public NCView(SiebenSegmentAnzeige ssa, JFrame jf) {
        this.ssa = ssa;
        this.jf = jf;
    }

    public void writeImage() {
        try {
            BufferedImage bi = new BufferedImage(jf.getWidth(), jf.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = bi.createGraphics();
            ssa.draw(g2d);

            File outputfFile = new File("res/saved.png");
            ImageIO.write(bi, "png", outputfFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeNCFile() {
        try {
            File file = new File("res/NC-Programm.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            createBufferWriter(bw);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createBufferWriter(BufferedWriter bw) throws IOException {

        ArrayList<Segment> segments = ssa.getSegments();
        int n = 1 * 10;
        int led = 1;
        int pin = 1;

        for (Segment s : segments) {
            for (Leuchtdiode l : s.leds) {
                double[][] pinPos = l.getBohrPosition();
                n = createDrillCycle(bw, n, led, pin, pinPos[0][0],
                        pinPos[1][0]);
                pin++;
                n = createDrillCycle(bw, n, led, pin, pinPos[0][1],
                        pinPos[1][1]);
                pin = 1;
                led++;

                // bw.write("(LED: " + led + " " + pin + ")");
                // bw.newLine();
                // bw.write("N" + n + " G0 X" + pinPos[0][0] + " Y" +
                // pinPos[1][0]
                // + ";");
                // bw.newLine();
                // n += 10;
                //
                // bw.write("N" + n + " G0 Z" + 118.0 + ";");
                // bw.newLine();
                // n += 10;
                //
                // bw.write("N" + n + " G1 Z" + 125.0 + ";");
                // bw.newLine();
                // n += 10;
                //
                // bw.write("N" + n + " G4 " + 1.0 + ";");
                // bw.newLine();
                // n += 10;
                //
                // bw.write("N" + n + " G1 Z" + 118.0 + ";");
                // bw.newLine();
                // n += 10;
                //
                // bw.write("N" + n + " G0 Z" + 60.0 + ";");
                // bw.newLine();
                // n += 10;

            }
        }
    }

    private int createDrillCycle(BufferedWriter bw, int n, int led, int pin,
            double posX, double posY) throws IOException {
        bw.write("(LED: " + led + " " + pin + ")");
        bw.newLine();
        bw.write("N" + n + " G0 X" + posX + " Y" + posY + ";");
        bw.newLine();
        n += 10;

        bw.write("N" + n + " G0 Z" + 118.0 + ";");
        bw.newLine();
        n += 10;

        bw.write("N" + n + " G1 Z" + 125.0 + ";");
        bw.newLine();
        n += 10;

        bw.write("N" + n + " G4 " + 1.0 + ";");
        bw.newLine();
        n += 10;

        bw.write("N" + n + " G1 Z" + 118.0 + ";");
        bw.newLine();
        n += 10;

        bw.write("N" + n + " G0 Z" + 60.0 + ";");
        bw.newLine();
        n += 10;

        return n;
    }
}
