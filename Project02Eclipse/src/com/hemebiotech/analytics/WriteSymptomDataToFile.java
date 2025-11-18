package com.hemebiotech.analytics;

import java.io.IOException;
import java.nio.file.*;
import java.util.Map;

public class WriteSymptomDataToFile implements ISymptomWriter {

    private final Path outputPath;

    public WriteSymptomDataToFile(String filePath) {
        this.outputPath = Paths.get(filePath);
    }

    @Override
    public void writeSymptoms(Map<String, Long> symptoms) throws IOException {
        StringBuilder builder = new StringBuilder();

        symptoms.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry ->
                        builder.append(entry.getKey())
                                .append(": ")
                                .append(entry.getValue())
                                .append("\n")
                );

        Files.write(
                outputPath,
                builder.toString().getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );
    }
}
