package ml.lubster.compressions;

import java.io.IOException;
import java.io.OutputStream;

public interface Compression {
    OutputStream compress (OutputStream data) throws IOException;
}
