package org.example.lesson24.framework.executions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Execution {
    private Class<?> testClass;
    private List<ExecutionItem> executionItems;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    public Execution(Class<?> testClass, List<ExecutionItem> executionItems, LocalDateTime startedAt, LocalDateTime endedAt) {
        this.testClass = testClass;
        this.executionItems = executionItems;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public Class<?> getTestClass() {
        return testClass;
    }

    public List<ExecutionItem> getExecutionItems() {
        return executionItems;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public String getExecutionResultHeaders(String dateTemplate) {
        return String.format(
                "Execution: \n" +
                "\ttestClass: %s\n" +
                "\tstartedAt: %s\n" +
                "\tendedAt: %s\n" +
                "\ttest results: \n",
                testClass,
                startedAt.format(DateTimeFormatter.ofPattern(dateTemplate)),
                endedAt.format(DateTimeFormatter.ofPattern(dateTemplate))
        );
    }
}
