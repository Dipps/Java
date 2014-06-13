package cg.gl.einfuehrung;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Beispiel4Smiley {

    private final float centerX = 450, centerY = 400;
    private final float radius = 200;

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
        initOpenGL();

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

    private void initOpenGL() {
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
        GL11.glColor4f(1.0f, 1.0f, 0.0f, 0.5f); // Gelb

        // Kopf
        drawCircle(centerX, centerY, radius);

        GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.5f); // Schwarz

        // Augen
        drawCircle(centerX - radius / 2, centerY + radius / 2, radius / 6);
        drawCircle(centerX + radius / 2, centerY + radius / 2, radius / 6);

        // Mund
        drawMouth(centerX, centerY - radius / 2, radius / 2, 30);
    }

    private void drawMouth(float x, float y, float length, float smile) {
        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glVertex2f(x - length, y + smile);
        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x + length, y + smile);
        GL11.glEnd();

    }

    private void drawCircle(float centerX, float centerY, float radius) {
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glVertex2f(centerX, centerY);

        for (int angle = 0; angle <= 360; angle += 10) {

            float sin = (float) Math.sin(Math.toRadians(angle));
            float cos = (float) Math.cos(Math.toRadians(angle));

            float x = centerX + sin * radius;
            float y = centerY + cos * radius;

            // Punkt setzen
            GL11.glVertex2f(x, y);
        }

        GL11.glEnd();
    }

    public static void main(String[] args) {
        Beispiel4Smiley smiley = new Beispiel4Smiley();
        smiley.start();

    }
}
