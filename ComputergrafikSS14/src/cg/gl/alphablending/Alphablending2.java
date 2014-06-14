package cg.gl.alphablending;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

public class Alphablending2 {

    // Variablen
    private final float xStart = 10, yStart = 10;
    private final float breite = 50, hoehe = 50;
    private final float radius = breite / 2;
    private float quadAlpha = 1.0f;
    private float circleAlpha = 0.0f;
    private float quadStep = 0.25f;
    private float circleStep = 0.125f;
    private FirstElement first = FirstElement.QUAD;

    private boolean mQuadAlpha = false;
    private boolean mCircleAlpha = false;
    private boolean depthTest = false;

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
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, Display.getDisplayMode().getWidth(), 0, Display
                .getDisplayMode().getHeight(), 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        // Zeit vor Schleifendurchlauf merken
        long tAlt = System.currentTimeMillis();

        // Endlosschleife bis der Strom abgeschaltet wird
        while (!Display.isCloseRequested()) {

            // render OpenGL
            render();

            // Zeit nach Rendern
            long tNeu = System.currentTimeMillis();
            long tDelta = tNeu - tAlt;
            tAlt = tNeu;

            // Alpha ändern

            float dt = tDelta * 0.001f;

            if (!mQuadAlpha) {
                quadAlpha -= quadStep * dt;
                if (quadAlpha < 0.0 || quadAlpha > 1.0) {
                    quadStep *= -1;
                }
            }

            if (!mCircleAlpha) {
                circleAlpha += circleStep * dt;
                if (circleAlpha < 0.0 || circleAlpha > 1.0) {
                    circleStep *= -1;
                }
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

    private void render() {
        // Display loeschen
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glEnable(GL11.GL_BLEND);

        if (depthTest) {
            GL11.glEnable(GL11.GL_DEPTH_TEST);
        } else {
            GL11.glDisable(GL11.GL_DEPTH_TEST);
        }

        GL14.glBlendEquation(GL14.GL_FUNC_ADD);

        blendFuncZero();
        blendFuncOne();
        blendFuncDstColor();
        blendFuncOneMinusDstColor();
        blendFuncSrcAlpha();
        blendFuncOneMinusSrcAlpha();
        blendFuncDstAlpha();
        blendFuncOneMinusDstAlpha();
        blendFuncSrcAlphaSaturate();
    }

    private void blendFuncSrcAlphaSaturate() {
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA_SATURATE, GL11.GL_ZERO);
        drawQuadCircle(xStart + breite * 16, yStart);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA_SATURATE, GL11.GL_ONE);
        drawQuadCircle(xStart + breite * 16, yStart + 2 * hoehe);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA_SATURATE, GL11.GL_SRC_COLOR);
        drawQuadCircle(xStart + breite * 16, yStart + 4 * hoehe);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA_SATURATE,
                GL11.GL_ONE_MINUS_SRC_COLOR);
        drawQuadCircle(xStart + breite * 16, yStart + 6 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_SUBTRACT);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA_SATURATE, GL11.GL_SRC_ALPHA);
        drawQuadCircle(xStart + breite * 16, yStart + 8 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_REVERSE_SUBTRACT);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA_SATURATE,
                GL11.GL_ONE_MINUS_SRC_ALPHA);
        drawQuadCircle(xStart + breite * 16, yStart + 10 * hoehe);
        GL14.glBlendEquation(GL14.GL_MIN);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA_SATURATE, GL11.GL_DST_ALPHA);
        drawQuadCircle(xStart + breite * 16, yStart + 12 * hoehe);
        GL14.glBlendEquation(GL14.GL_MAX);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA_SATURATE,
                GL11.GL_ONE_MINUS_DST_ALPHA);
        drawQuadCircle(xStart + breite * 16, yStart + 14 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_ADD);
    }

    private void blendFuncOneMinusDstAlpha() {
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_ALPHA, GL11.GL_ZERO);
        drawQuadCircle(xStart + breite * 14, yStart);
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_ALPHA, GL11.GL_ONE);
        drawQuadCircle(xStart + breite * 14, yStart + 2 * hoehe);
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_ALPHA, GL11.GL_SRC_COLOR);
        drawQuadCircle(xStart + breite * 14, yStart + 4 * hoehe);
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_ALPHA,
                GL11.GL_ONE_MINUS_SRC_COLOR);
        drawQuadCircle(xStart + breite * 14, yStart + 6 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_SUBTRACT);
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_ALPHA, GL11.GL_SRC_ALPHA);
        drawQuadCircle(xStart + breite * 14, yStart + 8 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_REVERSE_SUBTRACT);
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_ALPHA,
                GL11.GL_ONE_MINUS_SRC_ALPHA);
        drawQuadCircle(xStart + breite * 14, yStart + 10 * hoehe);
        GL14.glBlendEquation(GL14.GL_MIN);
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_ALPHA, GL11.GL_DST_ALPHA);
        drawQuadCircle(xStart + breite * 14, yStart + 12 * hoehe);
        GL14.glBlendEquation(GL14.GL_MAX);
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_ALPHA,
                GL11.GL_ONE_MINUS_DST_ALPHA);
        drawQuadCircle(xStart + breite * 14, yStart + 14 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_ADD);
    }

    private void blendFuncDstAlpha() {
        GL11.glBlendFunc(GL11.GL_DST_ALPHA, GL11.GL_ZERO);
        drawQuadCircle(xStart + breite * 12, yStart);
        GL11.glBlendFunc(GL11.GL_DST_ALPHA, GL11.GL_ONE);
        drawQuadCircle(xStart + breite * 12, yStart + 2 * hoehe);
        GL11.glBlendFunc(GL11.GL_DST_ALPHA, GL11.GL_SRC_COLOR);
        drawQuadCircle(xStart + breite * 12, yStart + 4 * hoehe);
        GL11.glBlendFunc(GL11.GL_DST_ALPHA, GL11.GL_ONE_MINUS_SRC_COLOR);
        drawQuadCircle(xStart + breite * 12, yStart + 6 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_SUBTRACT);
        GL11.glBlendFunc(GL11.GL_DST_ALPHA, GL11.GL_SRC_ALPHA);
        drawQuadCircle(xStart + breite * 12, yStart + 8 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_REVERSE_SUBTRACT);
        GL11.glBlendFunc(GL11.GL_DST_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        drawQuadCircle(xStart + breite * 12, yStart + 10 * hoehe);
        GL14.glBlendEquation(GL14.GL_MIN);
        GL11.glBlendFunc(GL11.GL_DST_ALPHA, GL11.GL_DST_ALPHA);
        drawQuadCircle(xStart + breite * 12, yStart + 12 * hoehe);
        GL14.glBlendEquation(GL14.GL_MAX);
        GL11.glBlendFunc(GL11.GL_DST_ALPHA, GL11.GL_ONE_MINUS_DST_ALPHA);
        drawQuadCircle(xStart + breite * 12, yStart + 14 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_ADD);
    }

    private void blendFuncOneMinusSrcAlpha() {
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ZERO);
        drawQuadCircle(xStart + breite * 10, yStart);
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE);
        drawQuadCircle(xStart + breite * 10, yStart + 2 * hoehe);
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_SRC_COLOR);
        drawQuadCircle(xStart + breite * 10, yStart + 4 * hoehe);
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_SRC_ALPHA,
                GL11.GL_ONE_MINUS_SRC_COLOR);
        drawQuadCircle(xStart + breite * 10, yStart + 6 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_SUBTRACT);
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_SRC_ALPHA);
        drawQuadCircle(xStart + breite * 10, yStart + 8 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_REVERSE_SUBTRACT);
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_SRC_ALPHA,
                GL11.GL_ONE_MINUS_SRC_ALPHA);
        drawQuadCircle(xStart + breite * 10, yStart + 10 * hoehe);
        GL14.glBlendEquation(GL14.GL_MIN);
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_DST_ALPHA);
        drawQuadCircle(xStart + breite * 10, yStart + 12 * hoehe);
        GL14.glBlendEquation(GL14.GL_MAX);
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_SRC_ALPHA,
                GL11.GL_ONE_MINUS_DST_ALPHA);
        drawQuadCircle(xStart + breite * 10, yStart + 14 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_ADD);
    }

    private void blendFuncSrcAlpha() {
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ZERO);
        drawQuadCircle(xStart + breite * 8, yStart);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        drawQuadCircle(xStart + breite * 8, yStart + 2 * hoehe);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_COLOR);
        drawQuadCircle(xStart + breite * 8, yStart + 4 * hoehe);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_COLOR);
        drawQuadCircle(xStart + breite * 8, yStart + 6 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_SUBTRACT);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_ALPHA);
        drawQuadCircle(xStart + breite * 8, yStart + 8 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_REVERSE_SUBTRACT);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        drawQuadCircle(xStart + breite * 8, yStart + 10 * hoehe);
        GL14.glBlendEquation(GL14.GL_MIN);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_DST_ALPHA);
        drawQuadCircle(xStart + breite * 8, yStart + 12 * hoehe);
        GL14.glBlendEquation(GL14.GL_MAX);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_DST_ALPHA);
        drawQuadCircle(xStart + breite * 8, yStart + 14 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_ADD);

    }

    private void blendFuncOneMinusDstColor() {
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_ZERO);
        drawQuadCircle(xStart + breite * 6, yStart);
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_ONE);
        drawQuadCircle(xStart + breite * 6, yStart + 2 * hoehe);
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_SRC_COLOR);
        drawQuadCircle(xStart + breite * 6, yStart + 4 * hoehe);
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR,
                GL11.GL_ONE_MINUS_SRC_COLOR);
        drawQuadCircle(xStart + breite * 6, yStart + 6 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_SUBTRACT);
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_SRC_ALPHA);
        drawQuadCircle(xStart + breite * 6, yStart + 8 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_REVERSE_SUBTRACT);
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR,
                GL11.GL_ONE_MINUS_SRC_ALPHA);
        drawQuadCircle(xStart + breite * 6, yStart + 10 * hoehe);
        GL14.glBlendEquation(GL14.GL_MIN);
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_DST_ALPHA);
        drawQuadCircle(xStart + breite * 6, yStart + 12 * hoehe);
        GL14.glBlendEquation(GL14.GL_MAX);
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR,
                GL11.GL_ONE_MINUS_DST_ALPHA);
        drawQuadCircle(xStart + breite * 6, yStart + 14 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_ADD);
    }

    private void blendFuncDstColor() {
        GL11.glBlendFunc(GL11.GL_DST_COLOR, GL11.GL_ZERO);
        drawQuadCircle(xStart + breite * 4, yStart);
        GL11.glBlendFunc(GL11.GL_DST_COLOR, GL11.GL_ONE);
        drawQuadCircle(xStart + breite * 4, yStart + 2 * hoehe);
        GL11.glBlendFunc(GL11.GL_DST_COLOR, GL11.GL_SRC_COLOR);
        drawQuadCircle(xStart + breite * 4, yStart + 4 * hoehe);
        GL11.glBlendFunc(GL11.GL_DST_COLOR, GL11.GL_ONE_MINUS_SRC_COLOR);
        drawQuadCircle(xStart + breite * 4, yStart + 6 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_SUBTRACT);
        GL11.glBlendFunc(GL11.GL_DST_COLOR, GL11.GL_SRC_ALPHA);
        drawQuadCircle(xStart + breite * 4, yStart + 8 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_REVERSE_SUBTRACT);
        GL11.glBlendFunc(GL11.GL_DST_COLOR, GL11.GL_ONE_MINUS_SRC_ALPHA);
        drawQuadCircle(xStart + breite * 4, yStart + 10 * hoehe);
        GL14.glBlendEquation(GL14.GL_MIN);
        GL11.glBlendFunc(GL11.GL_DST_COLOR, GL11.GL_DST_ALPHA);
        drawQuadCircle(xStart + breite * 4, yStart + 12 * hoehe);
        GL14.glBlendEquation(GL14.GL_MAX);
        GL11.glBlendFunc(GL11.GL_DST_COLOR, GL11.GL_ONE_MINUS_DST_ALPHA);
        drawQuadCircle(xStart + breite * 4, yStart + 14 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_ADD);

    }

    private void blendFuncOne() {
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ZERO);
        drawQuadCircle(xStart + breite * 2, yStart);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        drawQuadCircle(xStart + breite * 2, yStart + 2 * hoehe);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_SRC_COLOR);
        drawQuadCircle(xStart + breite * 2, yStart + 4 * hoehe);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
        drawQuadCircle(xStart + breite * 2, yStart + 6 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_SUBTRACT);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_SRC_ALPHA);
        drawQuadCircle(xStart + breite * 2, yStart + 8 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_REVERSE_SUBTRACT);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
        drawQuadCircle(xStart + breite * 2, yStart + 10 * hoehe);
        GL14.glBlendEquation(GL14.GL_MIN);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_DST_ALPHA);
        drawQuadCircle(xStart + breite * 2, yStart + 12 * hoehe);
        GL14.glBlendEquation(GL14.GL_MAX);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_DST_ALPHA);
        drawQuadCircle(xStart + breite * 2, yStart + 14 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_ADD);

    }

    private void blendFuncZero() {
        GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_ZERO);
        drawQuadCircle(xStart, yStart);
        GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_ONE);
        drawQuadCircle(xStart, yStart + 2 * hoehe);
        GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_SRC_COLOR);
        drawQuadCircle(xStart, yStart + 4 * hoehe);
        GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_ONE_MINUS_SRC_COLOR);
        drawQuadCircle(xStart, yStart + 6 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_SUBTRACT);
        GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_SRC_ALPHA);
        drawQuadCircle(xStart, yStart + 8 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_REVERSE_SUBTRACT);
        GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_ONE_MINUS_SRC_ALPHA);
        drawQuadCircle(xStart, yStart + 10 * hoehe);
        GL14.glBlendEquation(GL14.GL_MIN);
        GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_DST_ALPHA);
        drawQuadCircle(xStart, yStart + 12 * hoehe);
        GL14.glBlendEquation(GL14.GL_MAX);
        GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_ONE_MINUS_DST_ALPHA);
        drawQuadCircle(xStart, yStart + 14 * hoehe);
        GL14.glBlendEquation(GL14.GL_FUNC_ADD);
    }

    private void drawQuadCircle(float xStart, float yStart) {

        float quadZ = 0.5f;
        float circleZ = 0.5f;

        if (first == FirstElement.QUAD) {
            quadZ = 0.0f;
        }

        if (first == FirstElement.CIRCLE) {
            circleZ = 0.0f;
        }

        GL11.glColor4f(0.0f, 0.0f, 1.0f, quadAlpha);
        drawQuad(xStart, yStart, quadZ, breite, hoehe);

        GL11.glColor4f(1.0f, 1.0f, 0.0f, circleAlpha);
        drawCircle(xStart + breite, yStart + hoehe, circleZ, radius);
    }

    private void drawQuad(float x, float y, float z, float width, float height) {

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex3f(x, y, z);
        GL11.glVertex3f(x + width, y, z);
        GL11.glVertex3f(x + width, y + height, z);
        GL11.glVertex3f(x, y + height, z);
        GL11.glEnd();
    }

    private void drawCircle(float centerX, float centerY, float z, float radius) {
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glVertex3f(centerX, centerY, z);

        for (int angle = 0; angle <= 360; angle += 10) {

            float sin = (float) Math.sin(Math.toRadians(angle));
            float cos = (float) Math.cos(Math.toRadians(angle));

            float x = centerX + sin * radius;
            float y = centerY + cos * radius;

            // Punkt setzen
            GL11.glVertex3f(x, y, z);
        }

        GL11.glEnd();
    }

    public String getFirst() {
        if (first == FirstElement.QUAD) {
            return "Quad";
        } else if (first == FirstElement.CIRCLE) {
            return "Circle";
        } else {
            return "No First";
        }
    }

    public void setFirstElement(FirstElement fe) {
        this.first = fe;
    }

    public boolean ismQuadAlpha() {
        return mQuadAlpha;
    }

    public void setmQuadAlpha(boolean mQuadAlpha) {
        this.mQuadAlpha = mQuadAlpha;
    }

    public boolean ismCircleAlpha() {
        return mCircleAlpha;
    }

    public void setmCircleAlpha(boolean mCircleAlpha) {
        this.mCircleAlpha = mCircleAlpha;
    }

    public float getQuadAlpha() {
        return quadAlpha;
    }

    public void setQuadAlpha(float quadAlpha) {
        this.quadAlpha = quadAlpha;
    }

    public float getCircleAlpha() {
        return circleAlpha;
    }

    public void setCircleAlpha(float circleAlpha) {
        this.circleAlpha = circleAlpha;
    }

    public boolean isDepthTest() {
        return depthTest;
    }

    public void setDepthTest(boolean depthTest) {
        this.depthTest = depthTest;
    }

    public static void main(String[] args) {
        Alphablending2 ab2 = new Alphablending2();
        new ControllerFrame(ab2);
        ab2.start();

    }
}
