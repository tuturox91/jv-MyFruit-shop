package core.basesyntax.service.impl;

import core.basesyntax.service.DataWriter;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class WriteDataToFile implements DataWriter {
    private Path csvFilePath;
    private byte[] reportData;

    public WriteDataToFile(Path csvFilePath, byte[] reportData) {
        this.csvFilePath = csvFilePath;
        this.reportData = reportData;
    }

    @Override
    public void writeData() {
        try {
            Optional.ofNullable(csvFilePath).orElseThrow(
                    () -> new RuntimeException("File Path can't be null"));
            Optional.ofNullable(reportData).orElseThrow(
                    () -> new RuntimeException("Recordable data can`t be null"));
            Files.write(csvFilePath, reportData);
        } catch (IOException ex) {
            throw new RuntimeException(
                    String.format("Unable to write data to file by path: %s", csvFilePath));
        }
    }
}
