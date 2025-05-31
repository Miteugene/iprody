package org.example.lesson24.framework.printers;

import org.example.lesson24.framework.executions.Execution;
import org.example.lesson24.framework.executions.ExecutionItem;
import org.example.lesson24.framework.printers.interfaces.PrinterInterface;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FilePrinter extends Printer {
    private Path target;

    public FilePrinter(Path target, String template) {
        super(template);
        this.target = target;
    }

    @Override
    public void write(List<Execution> executions) {
        try (BufferedWriter writer = Files.newBufferedWriter(target, StandardCharsets.UTF_8)) {
            for (Execution execution : executions) {
                writer.write(execution.getExecutionResultHeaders(template));

                for (ExecutionItem executionItem : execution.getExecutionItems()) {
                    writer.write(executionItem.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
