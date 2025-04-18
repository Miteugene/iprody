package org.example.lesson11.task1;

import org.example.lesson11.task1.enums.ShapeEnum;
import org.example.lesson11.task1.interfaces.ShapeInterface;

public class Rectangle extends Shape {
    private double side1;
    private double side2;

    public Rectangle(double side1, double side2) {
        super(ShapeEnum.RECTANGLE);
        this.side1 = side1;
        this.side2 = side2;
    }

    public Rectangle(double side) {
        this(side, side);
    }

    public static ShapeInterface getRandomShape() {
        return new Rectangle(
                random.nextDouble(MIN_RANDOM_SIDE, MAX_RANDOM_SIDE),
                random.nextDouble(MIN_RANDOM_SIDE, MAX_RANDOM_SIDE)
        );
    }

    @Override
    public double getArea() {
        return side1 * side2;
    }

    @Override
    public void printInfo() {
        System.out.printf(
                "Shape: %s; Side1: %.2f; Side2: %.2f; Area: %.2f\n",
                this.getDisplayName(),
                this.side1,
                this.side2,
                this.getArea()
        );
    }
}
