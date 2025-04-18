package org.example.lesson11.task1;

import org.example.lesson11.task1.enums.ShapeEnum;
import org.example.lesson11.task1.interfaces.ShapeInterface;

public class Triangle extends Shape {
    private double side1;
    private double side2;
    private double side3;

    public Triangle(double side1, double side2, double side3) {
        super(ShapeEnum.TRIANGLE);

        if (side1 + side2 <= side3 || side1 + side3 <= side2 || side2 + side3 <= side1) {
            throw new IllegalArgumentException("Недопустимые длины сторон треугольника");
        }

        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public Triangle(double side) {
        this(side, side, side);
    }

    public static ShapeInterface getRandomShape() {
        double side1 = random.nextDouble(MIN_RANDOM_SIDE, MAX_RANDOM_SIDE);
        double side2 = random.nextDouble(MIN_RANDOM_SIDE, MAX_RANDOM_SIDE);
        double side3 = random.nextDouble(MIN_RANDOM_SIDE, side1 + side2);
        return new Triangle(side1, side2, side3);
    }

    @Override
    public double getArea() {
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    @Override
    public void printInfo() {
        System.out.printf(
                "Shape: %s; Side1: %.2f; Side2: %.2f; Side3: %.2f; Area: %.2f\n",
                this.getDisplayName(),
                this.side1,
                this.side2,
                this.side3,
                this.getArea()
        );
    }
}
