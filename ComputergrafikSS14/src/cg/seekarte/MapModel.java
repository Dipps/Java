package cg.seekarte;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import cg.matrix.Matrix;

public class MapModel {
    private final ArrayList<IMapModelListener> listeners;
    private final ArrayList<double[]> points;
    private final Map lotse;
    private final Map s09l;
    private Map selectedMap;

    private int mouseX;
    private int mouseY;

    public MapModel() {
        listeners = new ArrayList<>();
        points = new ArrayList<>();

        // Kalibrierungs Punkte(Lotsekarte) als Vektor (L1, B1, x1, y1)
        double[] calLotsePoint1 = { 56, 11, 396, 36 };
        double[] calLotsePoint2 = { 54, 11, 396, 831 };
        double[] calLotsePoint3 = { 54, 14, 1081, 831 };

        // Kalibrierungs Punkte(S09lkarte) als Vektor (L1, B1, x1, y1)
        double[] calS09lPoint1 = { 55.75, 10.67, 711, 424 };
        double[] calS09lPoint2 = { 55.58, 10.67, 859, 1818 };
        double[] calS09lPoint3 = { 55.58, 10.83, 1648, 1734 };

        lotse = new Map(new ImageIcon("res/Seekarte/lotse1.jpg"),
                calLotsePoint1, calLotsePoint2, calLotsePoint3);
        s09l = new Map(new ImageIcon("res/Seekarte/s09l.jpg"), calS09lPoint1,
                calS09lPoint2, calS09lPoint3);
        selectedMap = lotse;

    }

    // Längengrad
    public double[] getMapCoords() {
        double[] v = { mouseX, mouseY, 1 };
        return Matrix.matMult(selectedMap.getTransform(), v);
    }

    public int getMouseX() {
        return mouseX;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
        fireModelChanged();
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
        fireModelChanged();
    }

    public Map getLotseMap() {
        return lotse;
    }

    public Map getS09lMap() {
        return s09l;
    }

    public void changeSelectedMap(Map map) {
        selectedMap = map;
        fireModelChanged();
    }

    public ImageIcon getSelectedMapIcon() {
        return selectedMap.getImageIcon();
    }

    public void addMapModelListener(IMapModelListener l) {
        listeners.add(l);
    }

    public void removeMapModelListener(IMapModelListener l) {
        listeners.remove(l);
    }

    public void fireModelChanged() {
        for (IMapModelListener l : listeners) {
            l.mapModelChanged(this);
        }
    }

    public void addPoint(double[] point) {
        points.add(point);
        firePointsChanged();
    }

    public void removePoint(int i) {
        points.remove(i);
        firePointsChanged();
    }

    public void firePointsChanged() {
        for (IMapModelListener l : listeners) {
            l.pointsChanged(this);
        }
    }

    public double[][] getMapTransform() {
        return selectedMap.getTransform();
    }

    public double[][] getInverseMapTransform() {
        return selectedMap.getInverseTransform();
    }

    public ArrayList<double[]> getPoints() {
        return points;
    }

}
