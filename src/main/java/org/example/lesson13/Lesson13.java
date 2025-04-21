package org.example.lesson13;

import com.github.javafaker.Faker;
import org.example.lesson13.phonebook.Contact;
import org.example.lesson13.phonebook.ContactList;
import org.example.lesson13.task1.Occurrence;
import org.example.lesson13.task1.Task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Lesson13 {
    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    public static void main(String[] args) {
        firstTask();
        System.out.println("===========================================");
        secondTask();
    }

    private static void firstTask() {
        Task1 task1 = new Task1();

        ArrayList<String> wordsList = getRandomWordList(20);
        String word = faker.lorem().word();
        int occurrenceCount = task1.countOccurrence(
                wordsList,
                word
        );
        System.out.println("Word List: " + wordsList);
        System.out.println("Word: " + word);
        System.out.println("Occurrence Count: " + occurrenceCount);
        System.out.println("--------------------------------------");


        int[] arrayOfInts = getRandomIntArray(20);
        ArrayList<Integer> listOfInts = task1.toList(arrayOfInts);
        System.out.println("Array of ints: " + Arrays.toString(arrayOfInts));
        System.out.println("ArrayList of ints: " + listOfInts);
        System.out.println("--------------------------------------");


        ArrayList<Integer> uniqueInts = task1.findUnique(listOfInts);
        System.out.println("ArrayList of ints: " + listOfInts);
        System.out.println("Unique ints: " + uniqueInts);
        System.out.println("--------------------------------------");


        wordsList = getRandomWordList(20);
        HashMap<String, Integer> occurrenceMap = task1.calcOccurrence(wordsList);
        System.out.println("Word List: " + wordsList);
        System.out.print("Hash Map: ");
        for (String key: occurrenceMap.keySet()) {
            System.out.printf("%s: %d, ", key, occurrenceMap.get(key));
        }
        System.out.println("--------------------------------------");


        wordsList = getRandomWordList(20);
        ArrayList<Occurrence> occurrenceList = task1.findOccurrence(wordsList);
        System.out.println("Word List: " + wordsList);
        System.out.println("Occurrence List: " + occurrenceList);
    }

    private static void secondTask() {
        ContactList contactList = new ContactList();

        String someLastName = "";
        String somePhone = "";

        for (int i = 0; i < 20; i++) {
            someLastName = faker.name().lastName();
            somePhone = faker.phoneNumber().phoneNumber();

            contactList.add(
                    new Contact(
                            someLastName,
                            somePhone
                    )
            );
        }

        // Добавим еще одного по приколу и в контактах все равно будет только один такой
        contactList.add(
                new Contact(
                        someLastName,
                        somePhone
                )
        );

        System.out.println("All contacts: " + contactList);
        System.out.println("--------------------------------------");

        System.out.printf("Find: %s, Result: %s\n", someLastName, contactList.find(someLastName));
        System.out.printf("Find: %s, Result: %s\n", somePhone, contactList.find(somePhone));
        System.out.println("--------------------------------------");

        for (int i = 0; i < 20; i++) {
            someLastName = faker.name().lastName();
            somePhone = faker.phoneNumber().phoneNumber();
            System.out.printf("Find: %s, Result: %s\n", someLastName, contactList.find(someLastName));
            System.out.printf("Find: %s, Result: %s\n", somePhone, contactList.find(somePhone));
            System.out.println("--------------------------------------");
        }
    }

    private static ArrayList<String> getRandomWordList(int wordsCount) {
        ArrayList<String> wordsList = new ArrayList<>();

        for (int i = 0; i < wordsCount; i++) {
            wordsList.add(faker.lorem().word());
        }

        return wordsList;
    }

    private static int[] getRandomIntArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(0, 10);
        }
        return array;
    }
}
