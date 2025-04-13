package org.example.lesson10;

import org.example.lesson10.interfaces.Talkable;

public abstract class TalkingAnimal extends Animal implements Talkable {
    public TalkingAnimal(String name) {
        super(name);
    }

    @Override
    public void say(String phrase) {
        // Добавил получение класса чтобы было видно кто именно что сказал
        System.out.println(this.getClass().getSimpleName() + ": " + phrase);
    }
}
