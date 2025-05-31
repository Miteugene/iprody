package org.example;

import org.example.lesson24.framework.printers.FilePrinter;
import org.example.lesson24.framework.printers.StdoutPrinter;
import org.example.lesson24.framework.runner.TestApplicationRunner;
import org.example.lesson24.program.test.CalculatorTest;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path target = new File("./test-result.txt").toPath();

        new TestApplicationRunner(
                List.of(CalculatorTest.class),
                new FilePrinter(target, "yyyy-MM-dd HH:mm:ss"),
                new StdoutPrinter("yyyy-MM-dd HH:mm:ss")
        ).run();
    }
}