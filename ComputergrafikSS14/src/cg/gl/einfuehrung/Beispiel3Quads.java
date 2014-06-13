package cg.gl.einfuehrung;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Beispiel3Quads {

    // Variablen
    private final float xStart = 0, yStart = 0;
    private final float breite = 20, hoehe = 20;

    public void start() {

        // Display erstellen
        try {
            Display.setDisplayMode(new DisplayMode(900, 800));
            Display.create(); // nur ein Fenster
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // init OpenGL
        init();

        // Endlosschleife bis der Strom abgeschaltet wird
        while (!Display.isCloseRequested()) {

            // render OpenGL here
            render();

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

    private void init() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, Display.getDisplayMode().getWidth(), 0, Display
                .getDisplayMode().getHeight(), 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

    }

    private void render() {
        // Display loeschen
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        // set the color of the quad (R,G,B,A)
        GL11.glColor4f(1.0f, 0.0f, 0.0f, 0.5f);
        // draw quads
        for (int i = 0; i < 50; ++i) {
            for (int j = 0; j < 50; ++j) {
                drawQuad(xStart + j * 2 * breite, yStart + i * 2 * hoehe,
                        breite, hoehe);
            }
        }
    }

    private void drawQuad(float x, float y, float width, float height) {
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(x - width / 2, y - height / 2);
        GL11.glVertex2f(x - width / 2, y + height / 2);
        GL11.glVertex2f(x + width / 2, y + height / 2);
        GL11.glVertex2f(x + width / 2, y - height / 2);
        GL11.glEnd();
    }

    public static void main(String[] args) {
        Beispiel3Quads bsp3 = new Beispiel3Quads();
        bsp3.start();
    }
}
