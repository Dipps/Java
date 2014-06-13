package cg.gl.einfuehrung;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 * Einfuehrung in die Computergrafik
 * 
 * @author F. N. Rudolph (c) 2012 14.05.2012
 */
public class Beispiel2 {
    /**
     * Beispielprogramm
     * 
     * @param args
     *            nicht benutzt
     */
    public static void main(String[] args) {

        // Variablen
        float xStart = 100, yStart = 100;
        float breite = 100, hoehe = 100;

        // Display erstellen
        try {
            Display.setDisplayMode(new DisplayMode(900, 800));
            Display.create(); // nur ein Fenster
        } catch (LWJGLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }

        // init OpenGL
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, Display.getDisplayMode().getWidth(), 0, Display
                .getDisplayMode().getHeight(), 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        // Endlosschleife bis der Strom abgeschaltet wird
        while (!Display.isCloseRequested()) {
            // Display loeschen
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            // set the color of the quad (R,G,B,A)
            GL11.glColor4f(0.2f, 0.5f, 1.0f, 0.5f);
            // draw quad
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(xStart, yStart);
            GL11.glVertex2f(xStart + breite, yStart);
            GL11.glVertex2f(xStart + breite, yStart + hoehe);
            GL11.glVertex2f(xStart, yStart + hoehe);
            GL11.glEnd();
            // Display switchen
            Display.update();
            // Zeit synchronisieren
            Display.sync(100);
        }
        if (Display.isCloseRequested()) {
            Display.destroy();
        }
        System.exit(0);
    }
}
