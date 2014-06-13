package cg.gl.einfuehrung;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Beispiel1 {

    public void start() {

        // Display erstellen
        try {
            Display.setDisplayMode(new DisplayMode(400, 300));
            Display.create();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        // TODO: init OpenGL here

        // Endlosschleife
        while (!Display.isCloseRequested()) {

            // TODO: render OpenGL here

            // Display switchen
            Display.update();
        }

        // Display zerstoeren wenn CloseRequest = true
        Display.destroy();
    }

    public static void main(String[] args) {
        Beispiel1 bsp1 = new Beispiel1();
        bsp1.start();

    }
}
