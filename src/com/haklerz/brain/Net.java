package com.haklerz.brain;

import java.util.Random;

public class Net {
    private double[] nodes;
    private WeightMatrix matrix;

    public Net(int nodeCount) {
        this.nodes = new double[nodeCount];
        this.matrix = WeightMatrix.newGaussian(nodeCount);

        // Should be moved somewhere else.
        Random random = new Random();
        for (int i = 0; i < nodes.length; i++)
            nodes[i] = random.nextGaussian();
    }

    public void update() {
        double[] newNodes = new double[nodes.length];

        for (int to = 0; to < newNodes.length; to++) {
            double value = nodes[to];
            for (int from = 0; from < newNodes.length; from++) {
                newNodes[to] = value * matrix.getWeight(from, to);
            }
        }
    }

    public double[] getOutput(int count) {
        if (count <= 0 || count > nodes.length)
            throw new IndexOutOfBoundsException();

        double[] output = new double[count];
        for (int i = 0; i < count; i++)
            output[i] = nodes[nodes.length - count + i];

        return output;
    }

    public void setInput(double[] values) {
        if (values.length > nodes.length)
            throw new IllegalArgumentException();

        for (int i = 0; i < values.length; i++)
            nodes[i] = values[i];
    }

    public static void main(String[] args) {
        Net net = new Net(5);
        double[] out = net.getOutput(3);

        for (int i = 0; i < out.length; i++) {
            System.out.print(out[i] + ", ");
        }
    }
}