package org.example.lesson19;

public interface LoggerConfiguration {
    public final String DEFAULT_PATTERN = "[%tF %tT][%s] Message: [%s]";
    public LoggingLevel level();
    public String pattern();
}
