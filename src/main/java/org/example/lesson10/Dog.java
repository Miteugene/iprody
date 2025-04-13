package org.example.lesson10;

import org.example.lesson10.interfaces.Runnable;
import org.example.lesson10.interfaces.Swimmable;

public class Dog extends TalkingAnimal implements Runnable, Swimmable {
    private final int RUN_STAMINA = 500;
    private final int SWIM_STAMINA = 10;

    private int runStaminaLeft;
    private int swimStaminaLeft;

    public Dog(String name) {
        super(name);
        takeRest();
    }

    @Override
    public void run(int distance) {
        if (distance > runStaminaLeft) {
            System.out.println(getName() + ": Я устал, я ухожу, гаф");
            return;
        }

        runStaminaLeft -= distance;
        say(getName() + " пробежал " + distance + "м.");
    }

    @Override
    public void swim(int distance) {
        if (distance > swimStaminaLeft) {
            System.out.println(getName() + ": Я устал, я ухожу, гаф");
            return;
        }

        swimStaminaLeft -= distance;
        say(getName() + " проплыл " + distance + "м.");
    }

    public void takeRest() {
        this.runStaminaLeft = this.RUN_STAMINA;
        this.swimStaminaLeft = this.SWIM_STAMINA;
    }
}
