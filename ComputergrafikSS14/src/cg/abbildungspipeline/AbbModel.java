package cg.abbildungspipeline;

import java.util.ArrayList;

import cg.matrix.Matrix;

public class AbbModel {
    private final ArrayList<IAbbListener> listeners = new ArrayList<>();

    private double factor = 1.3;
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
    }

}
