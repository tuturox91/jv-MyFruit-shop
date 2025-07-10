package core.basesyntax.io;

import java.nio.file.Path;

public interface FileWriter {
    void write(String resultReport, Path path);
}
