package org.example.lesson10;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Random;

public class Lesson10 {
    public static void main(String[] args) {
        Random random = new Random();

        ArrayList<Animal> animalList = getAnimals();

        Cat cat;
        Dog dog;

        System.out.printf(
                """
                Animals Total: %d
                Dogs Total: %d
                Cats Total: %d
                """,
                getAnimalsCount(animalList),
                getAnimalsCount(animalList, Dog.class),
                getAnimalsCount(animalList, Cat.class)
        );

        // Несколько раз всех погоняем
        for (int i = 0; i < 5; i++) {
            for (Animal animal : animalList) {
                if (animal instanceof Cat) {
                    cat = (Cat) animal;
                    cat.run(random.nextInt(1, 50));
                } else {
                    dog = (Dog) animal;
                    if (random.nextBoolean()) {
                        dog.swim(random.nextInt(1, 10));
                    } else {
                        dog.run(random.nextInt(1, 50));
                    }
                }
            }
        }

    }

    // Подсчет всех животных
    private static int getAnimalsCount(ArrayList<Animal> animals) {
        return animals.size();
    }

    // Перегрузка метода с параметром класса для подсчета определенного вида животного
    private static int getAnimalsCount(ArrayList<Animal> animals, Class<?> cl) {
        int count = 0;
        for (Animal animal : animals) {
            if (cl.isInstance(animal)) {
                count++;
            }
        }
        return count;
    }

    private static ArrayList<Animal> getAnimals()
    {
        Faker faker = new Faker();
        Random random = new Random();
        ArrayList<Animal> animalList = new ArrayList<>();
        int animalCount = random.nextInt(5, 15);
        Animal animal;

        for (int i = 0; i <= animalCount; i++) {
            if (random.nextBoolean()) {
                animal = new Cat(faker.name().firstName());
            } else {
                animal = new Dog(faker.name().firstName());
            }
            animalList.add(animal);
        }

        return animalList;
    }
}
