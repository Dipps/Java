package cg.kurveneditor;

import java.awt.Label;

public class StatusAnzeige extends Label {

    private int punkte = 0;

    public StatusAnzeige() {
        setText("Anzahl Punkte: " + punkte);
    }

    public void setPunkte(int n) {
        this.punkte = n;
        setText("Anzahl Punkte: " + punkte);
    }

}
