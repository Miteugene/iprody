package org.example.lesson24.framework.printers;

import org.example.lesson24.framework.executions.Execution;
import org.example.lesson24.framework.executions.ExecutionItem;

import java.util.List;

public class StdoutPrinter extends Printer {
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String WHITE = "\u001B[37m";

    public StdoutPrinter(String template) {
        super(template);
    }

    @Override
    public void write(List<Execution> executions) {
        executions.forEach(execution -> {
            System.out.println(execution.getExecutionResultHeaders(template));
            for (ExecutionItem executionItem: execution.getExecutionItems()) {

                if (executionItem.getAssertResult().isSuccess()) {
                    System.out.print(GREEN);
                } else {
                    System.out.print(RED);
                }

                System.out.print(executionItem);

                System.out.println(WHITE);
            }
        });
    }
}
