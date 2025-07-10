package core.basesyntax.io.impl;

import core.basesyntax.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReaderImpl implements FileReader {
    @Override
    public List<String> read(Path paths) {
        try {
            return Files.readAllLines(paths);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
