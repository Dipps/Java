package cg.abbildungspipeline;

import java.util.ArrayList;

import cg.matrix.Matrix;

public class AbbModel {
    private final ArrayList<IAbbListener> listeners = new ArrayList<>();

    private double[] center = { 0.0, 0.0, 0.0, 1.0 };

    private double factor = 1.0;
    private double alpha = 0.0;
    private double beta = 0.0;
    private double gamma = 0.0;

    private double[][] mAnsicht = new double[4][4];
    private double[][] mViewport = new double[4][4];
    private double[][] mFactor = new double[4][4];
    private double[][] mAlpha = new double[4][4];
    private double[][] mBeta = new double[4][4];
    private double[][] mGamma = new double[4][4];
    private double[][] mZxZ = new double[4][4];

    private double[] x = { 100, 0, 0, 1.0 };
    private double[] y = { 0, 100, 0, 1.0 };
    private double[] z = { 0, 0, 100, 1.0 };

    private final double[] xAchse = { 5000, 0, 0, 1.0 };
    private final double[] yAchse = { 0, 5000, 0, 1.0 };
    private final double[] zAchse = { 0, 0, 5000, 1.0 };

    private double[] xS, yS, zS, xAchseS, yAchseS, zAchseS;

    public AbbModel() {
        initMatrices();
        update();

    }

    private void initMatrices() {
        for (int i = 0; i < 4; ++i) {
            mAnsicht[i][i] = 1.0;
            mViewport[i][i] = 1.0;
            mFactor[i][i] = 1.0;
            mAlpha[i][i] = 1.0;
            mBeta[i][i] = 1.0;
            mGamma[i][i] = 1.0;
            mZxZ[i][i] = 1.0;
        }
    }

    public void update() {
        updateFactorMatrix();
        updateAlphaMatrix();
        updateBetaMatrix();
        updateGammaMatrix();
        updateZxZMatrix();
        updateViewport();
        // updateAnsicht();
        calculatePoints();
    }

    public void calculatePoints() {

        calculatePlanePoints();
        calculateAxesPoints();
    }

    private void calculateAxesPoints() {

        xAchseS = Matrix.matMult(mViewport, xAchse);
        yAchseS = Matrix.matMult(mViewport, yAchse);
        zAchseS = Matrix.matMult(mViewport, zAchse);

        xAchseS = Matrix.matMult(mAnsicht, xAchseS);
        yAchseS = Matrix.matMult(mAnsicht, yAchseS);
        zAchseS = Matrix.matMult(mAnsicht, zAchseS);

    }

    private void calculatePlanePoints() {
        xS = Matrix.matMult(mZxZ, x);
        yS = Matrix.matMult(mZxZ, y);
        zS = Matrix.matMult(mZxZ, z);

        xS = Matrix.matMult(mViewport, xS);
        yS = Matrix.matMult(mViewport, yS);
        zS = Matrix.matMult(mViewport, zS);

        xS = Matrix.matMult(mAnsicht, xS);
        yS = Matrix.matMult(mAnsicht, yS);
        zS = Matrix.matMult(mAnsicht, zS);
    }

    private void updateAnsicht() {
        mAnsicht[0][0] = 0.71;
        mAnsicht[0][1] = 0.71;

        mAnsicht[1][0] = -0.50;
        mAnsicht[1][1] = 0.50;
        mAnsicht[1][2] = 0.71;

        mAnsicht[2][0] = 0.50;
        mAnsicht[2][1] = 0.50;
        mAnsicht[2][2] = 0.71;

    }

    private void updateViewport() {
        mViewport[0][3] = center[0];
        mViewport[1][3] = center[1];

        mViewport[1][1] = -1.0;
        mViewport[2][2] = 0.0;

    }

    private void updateZxZMatrix() {
        mZxZ = Matrix.matMult(mGamma, mBeta);
        double[][] tmp = Matrix.matMult(mZxZ, mAlpha);
        mZxZ = Matrix.matMult(tmp, mFactor);

    }

    private void updateGammaMatrix() {
        double cos = Math.cos(Math.toRadians(gamma));
        double sin = Math.sin(Math.toRadians(gamma));

        mGamma[0][0] = cos;
        mGamma[0][1] = -sin;
        mGamma[1][0] = sin;
        mGamma[1][1] = cos;

    }

    private void updateBetaMatrix() {
        double cos = Math.cos(Math.toRadians(beta));
        double sin = Math.sin(Math.toRadians(beta));

        mBeta[1][1] = cos;
        mBeta[1][2] = -sin;
        mBeta[2][1] = sin;
        mBeta[2][2] = cos;

    }

    private void updateAlphaMatrix() {
        double cos = Math.cos(Math.toRadians(alpha));
        double sin = Math.sin(Math.toRadians(alpha));

        mAlpha[0][0] = cos;
        mAlpha[0][2] = sin;
        mAlpha[2][0] = -sin;
        mAlpha[2][2] = cos;
    }

    private void updateFactorMatrix() {
        for (int i = 0; i < 3; ++i) {
            mFactor[i][i] = factor;
        }
    }

    public void addAbbModelListener(final IAbbListener l) {
        listeners.add(l);
    }

    public void removeAbbModelListener(final IAbbListener l) {
        listeners.remove(l);
    }

    public void fireModelChanged() {
        update();
        for (final IAbbListener l : listeners) {
            l.AbbModelChanged(this);
        }
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
        fireModelChanged();
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
        fireModelChanged();
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
        fireModelChanged();
    }

    public double getGamma() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
        fireModelChanged();
    }

    public double[][] getMAnsicht() {
        return mAnsicht;
    }

    public void setMAnsicht(double[][] mAnsicht) {
        this.mAnsicht = mAnsicht;
        fireModelChanged();
    }

    public double[][] getMViewport() {
        return mViewport;
    }

    public void setMViewport(double[][] mViewport) {
        this.mViewport = mViewport;
        fireModelChanged();
    }

    public double[][] getMFactor() {
        return mFactor;
    }

    public void setMFactor(double[][] mFactor) {
        this.mFactor = mFactor;
        fireModelChanged();
    }

    public double[][] getMAlpha() {
        return mAlpha;
    }

    public void setMAlpha(double[][] mAlpha) {
        this.mAlpha = mAlpha;
        fireModelChanged();
    }

    public double[][] getMBeta() {
        return mBeta;
    }

    public void setMBeta(double[][] mBeta) {
        this.mBeta = mBeta;
        fireModelChanged();
    }

    public double[][] getMGamma() {
        return mGamma;
    }

    public void setMGamma(double[][] mGamma) {
        this.mGamma = mGamma;
        fireModelChanged();
    }

    public double[][] getMZxZ() {
        return mZxZ;
    }

    public void setMZxZ(double[][] mZxZ) {
        this.mZxZ = mZxZ;
        fireModelChanged();
    }

    public double[] getCenter() {
        return center;
    }

    public void setCenter(double[] center) {
        this.center = center;
        fireModelChanged();
    }

    public double[] getX() {
        return xS;
    }

    public void setX(double[] x) {
        this.x = x;
        fireModelChanged();
    }

    public double[] getY() {
        return yS;
    }

    public void setY(double[] y) {
        this.y = y;
        fireModelChanged();
    }

    public double[] getZ() {
        return zS;
    }

    public void setZ(double[] z) {
        this.z = z;
        fireModelChanged();
    }

    public double[] getXAxe() {
        return xAchseS;
    }

    public double[] getYAxe() {
        return yAchseS;
    }

    public double[] getZAxe() {
        return zAchseS;
    }

}
