package com.haklerz.brain;

public class Test implements Runnable {

    private double px, py, vx, vy, ax, ay;
    private Net net;
    private static final double FRICTION = 0.1;

    public static void main(String[] args) {
        new Test().run();
    }

    public void update() {
        net.update(px, py, vx, vy);
        double[] out = net.getOutput(2);

        ax = out[0];
        ay = out[1];

        vx += ax;
        vy += ay;

        vx *= 1 - FRICTION;
        vy *= 1 - FRICTION;

        px += vx;
        py += vy;

        System.out.println(String.format("[%4f, %4f]", px, py));
    }

    @Override
    public void run() {
        px = 3;
        py = 2;
        net = new Net(8);
        net.setMatrix(Matrix.sparseGaussian(8, 0.5));
        while (true) {
            update();
        }
    }
}
