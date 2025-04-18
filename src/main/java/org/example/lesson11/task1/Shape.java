package org.example.lesson11.task1;

import org.example.lesson11.task1.enums.ShapeEnum;
import org.example.lesson11.task1.interfaces.ShapeInterface;

import java.util.Random;

public abstract class Shape implements ShapeInterface {
    public final static double MIN_RANDOM_SIDE = 5;
    public final static double MAX_RANDOM_SIDE = 15;

    protected static Random random = new Random();
    protected ShapeEnum shapeType;

    public Shape(ShapeEnum shapeType) {
        this.shapeType = shapeType;
    }

    @Override
    public String getDisplayName() {
        return shapeType.getDisplayName();
    }
}
