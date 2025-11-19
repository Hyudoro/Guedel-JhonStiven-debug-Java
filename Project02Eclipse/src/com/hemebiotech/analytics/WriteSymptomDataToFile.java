package com.hemebiotech.analytics;

import java.io.IOException;
import java.nio.file.*;
import java.util.Map;

/**
 *
 *
 * Implementation of ISymptomWriter that writes symptom counts to a text file.
 * Each line in the output file has the format: "symptom: count".
 * The entries are sorted alphabetically by symptom name.
 *
 * Example output:
 * headache: 5
 * nausea: 2
 *
 * This class uses java.nio.file.Files to write the output, creating the file
 * if it does not exist and overwriting it if it does.
 *
 */

public class WriteSymptomDataToFile implements ISymptomWriter {

    private final Path outputPath;

    /**
     * Writes the given map of symptoms and their counts to the output file.
     * Each symptom is written on a new line in the format "symptom: count",
     * sorted alphabetically by symptom name.
     *@param filePath
     */
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
