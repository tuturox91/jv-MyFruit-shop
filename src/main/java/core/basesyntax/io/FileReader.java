package core.basesyntax.io;

import java.nio.file.Path;
import java.util.List;

public interface FileReader {

    List<String> read(Path path);
}
