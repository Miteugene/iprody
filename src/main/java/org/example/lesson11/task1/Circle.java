package org.example.lesson11.task1;

import org.example.lesson11.task1.enums.ShapeEnum;
import org.example.lesson11.task1.interfaces.ShapeInterface;

public class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        super(ShapeEnum.CIRCLE);
        this.radius = radius;
    }

    public static ShapeInterface getRandomShape() {
        return new Circle(random.nextDouble(MIN_RANDOM_SIDE, MAX_RANDOM_SIDE));
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public void printInfo() {
        System.out.printf(
                "Shape: %s; Radius: %.2f; Area: %.2f\n",
                this.getDisplayName(),
                this.radius,
                this.getArea()
        );
    }
}
