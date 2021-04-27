package com.haklerz.brain;

import java.util.Random;

public class WeightMatrix {
    private final int nodeCount;
    private double[] weights;

    public WeightMatrix(int nodeCount) {
        this.nodeCount = nodeCount;
        this.weights = new double[nodeCount * nodeCount];
    }

    public static WeightMatrix newGaussian(int nodeCount) {
        WeightMatrix matrix = new WeightMatrix(nodeCount);
        Random random = new Random();

        for (int i = 0; i < matrix.weights.length; i++)
            matrix.weights[i] = random.nextGaussian();

        return matrix;
    }

    public double getWeight(int from, int to) {
        if (from < 0 || from >= nodeCount || to < 0 || to >= nodeCount)
            throw new IndexOutOfBoundsException();

        return weights[from + to * nodeCount];
    }

    public void setWeight(int from, int to, double value) {
        if (from < 0 || from >= nodeCount || to < 0 || to >= nodeCount)
            throw new IndexOutOfBoundsException();

        weights[from + to * nodeCount] = value;
    }

}
