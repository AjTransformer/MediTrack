package com.airtribe.meditrack.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class CSVUtil {

    private CSVUtil() {
    }

    public static void writeLines(Path path, List<String> lines) throws IOException {
        Files.write(path, lines);
    }

    public static List<String> readLines(Path path) throws IOException {
        return Files.readAllLines(path);
    }
}
