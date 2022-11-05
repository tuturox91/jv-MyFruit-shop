package core.basesyntax.service.impl;

import core.basesyntax.service.DataWriter;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class WriteDataToCSV implements DataWriter<StringBuilder> {
    private Path filePath;

    public WriteDataToCSV(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public void writeData(StringBuilder data) {
        if(data.isEmpty()) throw new RuntimeException("Current data is empty");
        try {
            var out = new PrintWriter(filePath.toString(), StandardCharsets.UTF_8);
            out.print(data);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException("Path: " + filePath.toString() + " is wrong!" + "\n" + e);
        }
    }
}
