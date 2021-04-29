package com.haklerz.brain;

public class Net {
    private double[] nodes;
    private Matrix matrix;

    public Net(int nodeCount) {
        this.nodes = new double[nodeCount];
        this.matrix = Matrix.sparseGaussian(nodeCount, 0.1);
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    /**
     * Updates the network with the given input values.
     * 
     * @param input The input values
     */
    public void update(double... input) {
        if (nodes.length < input.length)
            throw new IllegalArgumentException();

        // Overwrite node values with input values
        for (int i = 0; i < input.length; i++)
            nodes[i] = input[i];

        // Calculate the new node values
        double[] newNodes = new double[nodes.length];
        for (int y = 0; y < matrix.size; y++) {
            double sum = 0;
            for (int x = 0; x < matrix.size; x++)
                sum += nodes[x] * matrix.getWeight(x, y);

            newNodes[y] = Math.tanh(sum);
        }
        // Update the node values
        nodes = newNodes;
    }

    /**
     * Returns the values of the n last nodes.
     * 
     * @param count
     * @return
     */
    public double[] getOutput(int count) {
        if (count <= 0 || count > nodes.length)
            throw new IndexOutOfBoundsException();

        double[] output = new double[count];
        for (int i = 0; i < count; i++)
            output[i] = nodes[nodes.length - count + i];

        return output;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < nodes.length; i++) {
            builder.append(String.format("%6f", nodes[i]));

            if (i % nodes.length == nodes.length - 1)
                builder.append("\n");
            else
                builder.append(", ");
        }
        builder.setLength(builder.length() - 1);

        return builder.toString();
    }
}