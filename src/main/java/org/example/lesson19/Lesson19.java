package org.example.lesson19;

import com.github.javafaker.Faker;

import java.util.Random;
import java.util.Scanner;

public class Lesson19 {
    public static void main(String[] args) {
        Faker faker = new Faker();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        FileLoggerConfiguration fileLoggerConfiguration = getFileLoggerConfiguration();

        FileLogger logger = new FileLogger(fileLoggerConfiguration);

        boolean exit;
        String message;

        do {
            for (int i = 0; i < 10; i++) {
                message = faker.lorem().sentence();

                switch (getLoggingLevel(random)) {
                    case INFO -> logger.info(message);
                    case DEBUG -> logger.debug(message);
                }
            }

            System.out.println("If you want to exit, enter: \"e\"");
            exit = scanner.nextLine().trim().equals("e");
        } while (!exit);
    }

    /**
     * Со звездочкой.
     * Я думаю что тут просто хотели чтобы мы использовали сериализацию при чтении и записи.
     *
     * Первый раз мы создадим сами объект конфигураци, а в дальнейшем будем читать из файла
     * @return
     */
    private static FileLoggerConfiguration getFileLoggerConfiguration() {
        FileLoggerConfiguration fileLoggerConfiguration;

        FileLoggerConfigurationLoader fileLoggerConfigurationLoader = new FileLoggerConfigurationLoader();
        fileLoggerConfiguration = fileLoggerConfigurationLoader.load("filelogger-config.cfg");

        if (fileLoggerConfiguration == null) {
            fileLoggerConfiguration = new FileLoggerConfiguration(
                    ".",
                    "Custom_name",
                    LoggingLevel.DEBUG,
                    "[%tF %tT][%s]: [%s]"
            );

            fileLoggerConfigurationLoader.save("filelogger-config.cfg", fileLoggerConfiguration);
        }

        return fileLoggerConfiguration;
    }

    private static LoggingLevel getLoggingLevel(Random random) {
        LoggingLevel[] values = LoggingLevel.values();
        return values[random.nextInt(values.length)];
    }
}
