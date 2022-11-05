package core.basesyntax.service.impl;

import core.basesyntax.service.DataReader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ReadDataFromCSV implements DataReader<List<String>> {
    private static final int LINE_SKIP_IN_DATA_FILE = 1;
    private Path filePath;


    public ReadDataFromCSV(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<String> readData() {
        List<String> fruitData = new ArrayList<>();
        try {
            fruitData = Files.lines(filePath, StandardCharsets.UTF_8)
                    .skip(LINE_SKIP_IN_DATA_FILE).toList();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if(fruitData.size() == 0) {
            throw new RuntimeException("File: " + filePath + " is empty");
        } else {
            return fruitData;
        }
    }
}
