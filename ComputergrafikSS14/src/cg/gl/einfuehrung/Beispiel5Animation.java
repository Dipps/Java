package cg.gl.einfuehrung;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Beispiel5Animation {
    private float centerX = 450;
    private float centerY = 400;
    private final float radius = 200;
    private final float speed = 30;
    private float smile = 30;

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

        // Zeit vor Schleifendurchlauf merken
        long tAlt = System.currentTimeMillis();

        // Endlosschleife bis der Strom abgeschaltet wird
        while (!Display.isCloseRequested()) {

            // render OpenGL here
            render();

            // Zeit nach Rendern
            long tNeu = System.currentTimeMillis();
            long tDelta = tNeu - tAlt;
            tAlt = tNeu;

            // centerposition anpassen
            float dt = tDelta * 0.001f;
            centerX += dt * speed;
            centerY += dt * speed;
            smile += dt * 5;

            if (centerX + radius >= Display.getWidth()
                    && centerY + radius >= Display.getHeight()) {
                centerX = 0.0f + radius;
                centerY = 0.0f + radius;
            }

            if (smile > 60) {
                smile = 0.0f;
            }

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
        drawMouth(centerX, centerY - radius / 2, radius / 2, smile);
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
        Beispiel5Animation anim = new Beispiel5Animation();
        anim.start();

    }
}
