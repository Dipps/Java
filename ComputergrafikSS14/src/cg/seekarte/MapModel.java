package cg.seekarte;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import cg.matrix.Matrix;
import cg.punkteditor.Punkt;
import cg.punkteditor.Punktliste;

public class MapModel {
    private final ArrayList<IMapModelListener> listeners;

    private Punktliste points;

    private final Map lotse;

    private final Map s09l;

    private Map selectedMap;

    private int mouseX;

    private int mouseY;

    public MapModel() {
        listeners = new ArrayList<>();
        points = new Punktliste();

        // Kalibrierungs Punkte(Lotsekarte) als Vektor (L1, B1, x1, y1)
        final double[] calLotsePoint1 = { 56, 11, 396, 36 };
        final double[] calLotsePoint2 = { 54, 11, 396, 831 };
        final double[] calLotsePoint3 = { 54, 14, 1081, 831 };

        // Kalibrierungs Punkte(S09lkarte) als Vektor (L1, B1, x1, y1)
        final double[] calS09lPoint1 = { 55.75, 10.67, 711, 424 };
        final double[] calS09lPoint2 = { 55.58, 10.67, 859, 1818 };
        final double[] calS09lPoint3 = { 55.58, 10.83, 1648, 1734 };

        lotse = new Map(new ImageIcon("res/Seekarte/lotse1.jpg"),
                calLotsePoint1, calLotsePoint2, calLotsePoint3);
        s09l = new Map(new ImageIcon("res/Seekarte/s09l.jpg"), calS09lPoint1,
                calS09lPoint2, calS09lPoint3);
        selectedMap = lotse;

    }

    // Längengrad
    public double[] getMapCoords() {
        final double[] v = { mouseX, mouseY, 1 };
        return Matrix.matMult(selectedMap.getTransform(), v);
    }

    public int getMouseX() {
        return mouseX;
    }

    public void setMouseX(final int mouseX) {
        this.mouseX = mouseX;
        fireModelChanged();
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(final int mouseY) {
        this.mouseY = mouseY;
        fireModelChanged();
    }

    public Map getLotseMap() {
        return lotse;
    }

    public Map getS09lMap() {
        return s09l;
    }

    public void changeSelectedMap(final Map map) {
        final ArrayList<double[]> MapCoords = new ArrayList<>();

        for (int i = 0; i < points.getSize(); i++) {
            final Punkt p = points.getPunktAt(i);
            final double[] v = { p.getX(), p.getY(), 1.0 };
            final double[] result = Matrix.matMult(getMapTransform(), v);
            MapCoords.add(result);
        }

        final Punktliste newList = new Punktliste();
        selectedMap = map;
        for (final double[] ds : MapCoords) {
            final double[] result = Matrix
                    .matMult(getInverseMapTransform(), ds);
            newList.add(new Punkt((int) result[0], (int) result[1]));
        }
        points = newList;
        firePointsChanged();
        fireModelChanged();
    }

    public ImageIcon getSelectedMapIcon() {
        return selectedMap.getImageIcon();
    }

    public void addMapModelListener(final IMapModelListener l) {
        listeners.add(l);
    }

    public void removeMapModelListener(final IMapModelListener l) {
        listeners.remove(l);
    }

    public void fireModelChanged() {
        for (final IMapModelListener l : listeners) {
            l.mapModelChanged(this);
        }
    }

    public void addPoint(final Punkt p) {
        // points.add(point);
        points.add(p);
        firePointsChanged();
    }

    public void removeMarkedPoint() {
        points.removeMarked();
        firePointsChanged();
    }

    public void firePointsChanged() {
        for (final IMapModelListener l : listeners) {
            l.pointsChanged(this);
        }
    }

    public double[][] getMapTransform() {
        return selectedMap.getTransform();
    }

    public double[][] getInverseMapTransform() {
        return selectedMap.getInverseTransform();
    }

    public Punktliste getPoints() {
        return points;
    }

    public void moveMarked(final int x, final int y) {
        points.moveMarked(x, y);
        firePointsChanged();
    }

    public void mark(final int x, final int y) {
        points.mark(x, y);
        firePointsChanged();

    }

}
