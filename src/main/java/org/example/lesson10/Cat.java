package org.example.lesson10;

import org.example.lesson10.interfaces.Runnable;

public class Cat extends TalkingAnimal implements Runnable {
    private final int RUN_STAMINA = 200;

    private int runStaminaLeft;

    public Cat(String name) {
        super(name);
        takeRest();
    }

    @Override
    public void run(int distance) {
        if (distance > runStaminaLeft) {
            System.out.println(getName() + ": Я устал, я ухожу, мяу");
            return;
        }

        runStaminaLeft -= distance;
        say(getName() + " пробежал " + distance + "м.");
    }

    public void takeRest() {
        this.runStaminaLeft = this.RUN_STAMINA;
    }
}
