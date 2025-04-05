package org.example.lesson8;

import java.util.Random;
import java.util.Scanner;

/**
 * Реализовать программу по отгадыванию слов
 * a. Реализовать класс обладающий полем типа данных массив строк и проинициализированный следующими значениями:
 * *"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato" *
 * b. Реализовать метод запуска программы. При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя, сравнивает его с загаданным словом и сообщает правильно ли ответил пользователь.
 * c. Если слово не угадано, компьютер показывает буквы которые стоят на своих местах. Например, apple – загаданное, а apricot - ответ игрока, тогда программа выведет в консоль ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
 * d. Сравнение двух слов необходимо выполнять посимвольно.
 * e. Игра продолжается до тех пор, пока игрок не угадает загаданное компьютером слово.
 */
public class WordGuesser {
    private static final String[] words = {
            "apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
            "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
            "pear", "pepper", "pineapple", "pumpkin", "potato"
    };

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void start() {
        System.out.println("Game Started");

        boolean userGuessed = false;

        String word = getNewWord();

        do {
            userGuessed = gameIteration(word);
        } while (!userGuessed);

        System.out.println("Congrats, you've guessed");
    }

    private static boolean gameIteration(String word)
    {
        System.out.print("Your word: ");
        String userWord = scanner.nextLine().trim();

        boolean userGuessed = word.equalsIgnoreCase(userWord);

        if (userGuessed) {
            return true;
        }

        StringBuilder tipStringBuilder = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == userWord.charAt(i)) {
                tipStringBuilder.append(word.charAt(i));
            } else {
                tipStringBuilder.append('#');
            }
        }

        while (tipStringBuilder.length() < 15) {
            tipStringBuilder.append('#');
        }

        System.out.println("Tip: " + tipStringBuilder);

        return false;
    }

    private static String getNewWord() {
        return words[random.nextInt(words.length)];
    }
}
