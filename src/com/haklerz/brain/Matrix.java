package com.haklerz.brain;

import java.util.Random;

public class Matrix {
    public final int size;
    private double[] weights;

    /**
     * Creates a matrix of size <code>size</code> * <code>size</code>
     * 
     * @param size The size
     */
    public Matrix(int size) {
        this.size = size;
        this.weights = new double[size * size];
    }

    /**
     * Sets the weight from node x to node y.
     * 
     * @param x      The first node
     * @param y      The second node
     * @param weight The weight
     */
    public void setWeight(int x, int y, double weight) {
        if (x < 0 || x >= size || y < 0 || y >= size)
            throw new IndexOutOfBoundsException();

        weights[x + y * size] = weight;
    }

    /**
     * Returns the weight from node x to node y.
     * 
     * @param x The first node
     * @param y The second node
     * @return the weight
     */
    public double getWeight(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size)
            throw new IndexOutOfBoundsException();

        return weights[x + y * size];
    }

    public static Matrix sparseGaussian(int size, double rate) {
        Random random = new Random();
        Matrix matrix = new Matrix(size);

        for (int i = 0; i < matrix.weights.length; i++) {
            if (random.nextDouble() <= rate)
                matrix.weights[i] = random.nextGaussian();
        }

        return matrix;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < weights.length; i++) {
            builder.append(String.format("%6f", weights[i]));

            if (i % size == size - 1)
                builder.append("\n");
            else
                builder.append(", ");
        }
        builder.setLength(builder.length() - 1);

        return builder.toString();
    }
}
