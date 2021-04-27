package com.haklerz.brain;

import java.util.Random;

public class WeightMatrix {
    private int size;
    private double[] weights;

    public WeightMatrix(int n) {
        this.size = n;
        this.weights = new double[n * n];
    }

    public double getWeight(int from, int to) {
        if (from < 0 || from >= size || to < 0 || to >= size)
            throw new IndexOutOfBoundsException();

        return weights[from + to * size];
    }

    public void setWeight(int from, int to, double value) {
        if (from < 0 || from >= size || to < 0 || to >= size)
            throw new IndexOutOfBoundsException();
        
        weights[from + to * size] = value;
    }

    public static WeightMatrix gaussian(int n) {
        WeightMatrix matrix = new WeightMatrix(n);
        Random random = new Random();
        
        for (int i = 0; i < matrix.weights.length; i++) {
            matrix.weights[i] = random.nextGaussian();
        }

        return matrix;
    }
}
