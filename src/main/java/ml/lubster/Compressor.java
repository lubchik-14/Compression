package ml.lubster;

import lombok.Getter;
import ml.lubster.compressions.Compression;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Compressor {
    @Getter
    private final Compression compression;

    public Compressor(Compression compression) {
        this.compression = compression;
    }

    public void compress(Path inFile, File outFile) throws IOException {
//        try (BufferedReader in = new BufferedReader(
//                new InputStreamReader(
//                        new FileInputStream(inFile.toString()), Charset.forName("Windows-1251")));
//             BufferedWriter out = new BufferedWriter(
//                     new OutputStreamWriter(
//                             compression.compress(
//                                     new FileOutputStream(outFile))))) {
//            int readCount;
//            char[] chars = new char[100];
//            while ((readCount = in.read(chars)) > 0) {
//                out.write(chars, 0, readCount);
//            }
//        }

        try (OutputStream out = new FileOutputStream(outFile);
             OutputStream compressedOut = compression.compress(out)) {
            Files.copy(inFile, compressedOut);
        }
    }
}

