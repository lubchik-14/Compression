package ml.lubster;

import ml.lubster.compressions.GzipCompression;
import ml.lubster.compressions.ZipCompression;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPOutputStream;

public class CompressionMain {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Arguments not found");
            System.exit(0);
        }
        if (!args[0].equals("zip") && !args[0].equals("gzip")) {
            System.out.println("Compression method not found");
            System.exit(0);
        }
        String method = args[0];
        Path inFile = Paths.get(args[1]);
        File outFile;

//        Compressor gzipCompressor = new Compressor(new GzipCompression());
        Compressor gzipCompressor = new Compressor(GZIPOutputStream::new);
        Compressor zipCompressor = new Compressor(new ZipCompression(args[1]));
        try {
            switch (method) {
                case "gzip": {
                    outFile = Paths.get(args[1] + ".gz").toFile();
                    gzipCompressor.compress(inFile, outFile);
                    break;
                }
                case "zip": {
                    outFile = Paths.get(args[1].substring(0, args[1].lastIndexOf(".") + 1) + args[0]).toFile();
                    zipCompressor.compress(inFile, outFile);
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}


