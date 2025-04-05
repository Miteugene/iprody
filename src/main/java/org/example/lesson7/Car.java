package org.example.lesson7;

/**
 * Задание под звездочкой
 * Создать класс “Car” с публичным методом start. Метод start вызывает внутри себя методы:
 * a. startElectricity()
 * b. startCommand()
 * c. startFuelSystem()
 * Перечисленные методы, являются внутренней (скрытой) частью комплексного метода start.
 * Они по сути отображают внутреннее поведение класса “Car” и не должны быть использованы за пределами данного класса.
 */
public class Car {

    public void start() {
        startElectricity();
        startCommand();
        startFuelSystem();
    }

    private void startElectricity() {
        System.out.println("Electricity started");
    }

    private void startCommand() {
        System.out.println("Command started");
    }

    private void startFuelSystem() {
        System.out.println("FuelSystem started");
    }
}
