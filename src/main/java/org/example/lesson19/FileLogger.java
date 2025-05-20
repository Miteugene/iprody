package org.example.lesson19;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger implements Logger {
    private FileLoggerConfiguration configuration;

    public FileLogger(FileLoggerConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void debug(String message) {
        writeToFile(LoggingLevel.DEBUG, message);
    }

    @Override
    public void info(String message) {
        writeToFile(LoggingLevel.INFO, message);
    }

    private void writeToFile(LoggingLevel loggingLevel, String message) {
        // Проверка уровня логирования
        if (!this.configuration.level().includes(loggingLevel)) {
            return;
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        String formattedLogString = String.format(
                this.configuration.pattern(),
                localDateTime,
                localDateTime,
                loggingLevel,
                message
                ) + "\n";

        byte[] bytes = formattedLogString.getBytes();
        try (FileOutputStream stream = getFileOutputStream(bytes.length)) {
            stream.write(bytes);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private FileOutputStream getFileOutputStream(long messageSize) throws FileNotFoundException {
        if (!checkOrCreateFolder(this.configuration.getDirPath())) {
            throw new FileNotFoundException(this.configuration.getDirPath());
        }

        File logFile = findLogFile(messageSize);

        System.out.println("File: " + logFile.getAbsolutePath());

        return new FileOutputStream(logFile, true);
    }

    /**
     * Ищем подходящий файл для логов.
     *
     * @param messageSize
     * @return
     */
    private File findLogFile(long messageSize) {
        String datetime = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm")
                .format(LocalDateTime.now());

        String baseFilename = this.configuration.getFilename();
        String dirPath = this.configuration.getDirPath();

        long maxFileSize = this.configuration.getMaxFileSize();

        File file;
        Path path;
        int index = 0;
        String basename;

        do {
            basename = String.format("%s_%s_%03d.%s", baseFilename, datetime, index, "log");
            path = Path.of(dirPath, basename);
            file = path.toFile();
            index++;
        } while (file.exists() && file.length() + messageSize > maxFileSize);

        return file;
    }

    private boolean checkOrCreateFolder(String dirPath) {
        File dir = new File(dirPath);

        boolean dirCreated = true;

        if (!dir.exists()) {
            dirCreated = dir.mkdirs();
        }

        return dirCreated;
    }
}
