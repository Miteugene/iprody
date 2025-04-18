package org.example.lesson11;

import org.example.lesson11.task1.Circle;
import org.example.lesson11.task1.Rectangle;
import org.example.lesson11.task1.Triangle;
import org.example.lesson11.task1.enums.ShapeEnum;
import org.example.lesson11.task1.interfaces.ShapeInterface;
import org.example.lesson11.task2.Competition;

import java.util.Random;

public class Lesson11 {
    public static void main(String[] args) {
        task1();
        System.out.println("\n===========================================");
        task2();
    }

    private static void task1() {
        Random random = new Random();

        int arrayLen = random.nextInt(5, 10);

        ShapeInterface[] shapes = new ShapeInterface[arrayLen];
        ShapeEnum[] shapeTypes = ShapeEnum.values();
        ShapeEnum currentShapeType;
        ShapeInterface shape;

        for (int i = 0; i < arrayLen; i++) {
            currentShapeType = shapeTypes[random.nextInt(0, shapeTypes.length)];

            shape = switch (currentShapeType) {
                case CIRCLE -> Circle.getRandomShape();
                case RECTANGLE -> Rectangle.getRandomShape();
                case TRIANGLE -> Triangle.getRandomShape();
            };

            shape.printInfo();

            shapes[i] = shape;
        }

        System.out.printf("Total Area: %.2f\n", calcSumArea(shapes));
    }

    private static double calcSumArea(ShapeInterface[] shapes) {
        double area = 0;
        for (ShapeInterface shape : shapes) {
            area += shape.getArea();
        }
        return area;
    }

    private static void task2() {
        Competition competition = new Competition();
        competition.start();
    }
}
