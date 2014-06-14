package cg.gl.alphablending;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

public class Alphablending {

    public enum FirstE {
        QUAD, CIRCLE
    }

    // Variablen
    private final float xStart = 10, yStart = 10;
    private final float breite = 50, hoehe = 50;
    private final float radius = breite / 2;
    private float quadAlpha = 1.0f;
    private float circleAlpha = 0.0f;

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

            // render OpenGL
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

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL14.glBlendEquation(GL14.GL_FUNC_ADD);

        // Additives Blending (ohne Alpha)
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        drawQuadCircle(xStart, yStart, FirstE.CIRCLE);

        // Normales Blending
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        drawQuadCircle(xStart + 2 * breite, yStart, FirstE.CIRCLE);

        // Additives Blending (mit Alpha)
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        drawQuadCircle(xStart + 4 * breite, yStart, FirstE.CIRCLE);

        // Tiefentest deaktivieren / BlendEquation ändern
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL14.glBlendEquation(GL14.GL_FUNC_SUBTRACT);

        // Additives Blending (ohne Alpha)
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        drawQuadCircle(xStart + 6 * breite, yStart, FirstE.CIRCLE);

        // Normales Blending
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        drawQuadCircle(xStart + 8 * breite, yStart, FirstE.CIRCLE);

        // Additives Blending (mit Alpha)
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        drawQuadCircle(xStart + 10 * breite, yStart, FirstE.CIRCLE);

        // Tiefentest aktivieren / BlendEquation ändern
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL14.glBlendEquation(GL14.GL_MAX);

        // Additives Blending (ohne Alpha)
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        drawQuadCircle(xStart + 12 * breite, yStart, FirstE.QUAD);

        // Normales Blending
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        drawQuadCircle(xStart + 14 * breite, yStart, FirstE.QUAD);

        // Additives Blending (mit Alpha)
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        drawQuadCircle(xStart + 16 * breite, yStart, FirstE.QUAD);

    }

    private void drawQuadCircle(float xStart, float yStart, FirstE first) {
        float quadDepth = 0.5f;
        float circleDepth = 0.5f;

        if (first == FirstE.QUAD) {
            quadDepth = 0.0f;
        }

        if (first == FirstE.CIRCLE) {
            circleDepth = 0.0f;
        }

        for (int i = 0; i < 8; ++i) {

            float offset = i * 2 * hoehe;
            // draw quad
            drawQuad(xStart, yStart + offset, quadDepth, breite, hoehe,
                    quadAlpha);
            // draw Circle
            drawCircle(xStart + breite, (yStart + hoehe) + offset, circleDepth,
                    radius, circleAlpha);
            quadAlpha -= 0.125;
            circleAlpha += 0.125;
        }

        quadAlpha = 1.0f;
        circleAlpha = 0.0f;
    }

    private void drawQuad(float x, float y, float z, float width, float height,
            float alpha) {
        GL11.glColor4f(0.0f, 0.0f, 1.0f, alpha);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex3f(x, y, z);
        GL11.glVertex3f(x + width, y, z);
        GL11.glVertex3f(x + width, y + height, z);
        GL11.glVertex3f(x, y + height, z);
        GL11.glEnd();
    }

    private void drawCircle(float centerX, float centerY, float z,
            float radius, float alpha) {
        GL11.glColor4f(0.0f, 1.0f, 0.0f, alpha);
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glVertex3f(centerX, centerY, z);

        for (int angle = 0; angle <= 360; angle += 10) {

            float sin = (float) Math.sin(Math.toRadians(angle));
            float cos = (float) Math.cos(Math.toRadians(angle));

            float x = centerX + sin * radius;
            float y = centerY + cos * radius;

            // Punkt setzen
            GL11.glColor4f(1.0f, 1.0f, 0.0f, alpha);
            GL11.glVertex3f(x, y, z);
        }

        GL11.glEnd();
    }

    public static void main(String[] args) {
        Alphablending ab = new Alphablending();
        ab.start();

    }

}
