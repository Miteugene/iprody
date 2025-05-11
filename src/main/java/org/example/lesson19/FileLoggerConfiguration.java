package org.example.lesson19;

import java.io.Serializable;

public class FileLoggerConfiguration implements LoggerConfiguration, Serializable {
    private final String dirPath;
    private final String filename;
    private int maxFileSize = 1024; // в байтах
    private final LoggingLevel loggingLevel;
    private final String pattern;

    public FileLoggerConfiguration(String dirPath, String filename, LoggingLevel loggingLevel, String pattern) {
        this.dirPath = dirPath;
        this.filename = filename;
        this.loggingLevel = loggingLevel;
        this.pattern = pattern;
    }

    public FileLoggerConfiguration(String dirPath, String filename, LoggingLevel loggingLevel) {
        this(dirPath, filename, loggingLevel, LoggerConfiguration.DEFAULT_PATTERN);
    }

    public FileLoggerConfiguration(String dirPath, String filename) {
        this(dirPath, filename, LoggingLevel.INFO);
    }

    public FileLoggerConfiguration(String dirPath) {
        this(dirPath, "log");
    }

    public FileLoggerConfiguration() {
        this(".");
    }

    public String getDirPath() {
        return dirPath;
    }

    public String getFilename() {
        return filename;
    }

    @Override
    public LoggingLevel level() {
        return loggingLevel;
    }

    @Override
    public String pattern() {
        return pattern;
    }

    public int getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(int maxFileSize) {
        this.maxFileSize = maxFileSize;
    }
}
