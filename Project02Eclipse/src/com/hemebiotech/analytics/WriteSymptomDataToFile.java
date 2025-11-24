package com.hemebiotech.analytics;

import java.io.IOException;
import java.nio.file.*;
import java.util.Map;

/**
 *
 * Implementation of ISymptomWriter that writes symptom counts to a text file.
 * Each line in the output file has the format: "symptom: count".
 * The entries are sorted alphabetically by symptom name.
 * Example output:
 * headache: 5
 * nausea: 2
 * This class uses java.nio.file.Files to write the output, creating the file
 * if it does not exist and overwriting it if it does.
 *
 */

public class WriteSymptomDataToFile implements ISymptomWriter {

    private final Path outputPath; //The final path of the file once generated and filled.

    /**
     * Writes the given map of symptoms and their counts to the output file.
     * Each line in the file represents a symptom and its count,
     * sorted alphabetically by symptom name.
     *@param filePath the full or partial path of the output file.
     */
    public WriteSymptomDataToFile(String filePath) {
        this.outputPath = Paths.get(filePath);
    }


    /**
     *
     *Writes the given symptom counts to the file specified by {@code outputPath}.
     *Each line in the file represents a symptom and its occurrence count.
     *If the file does not exist, creates it, and if there is one already, replaces it.
     *Any {@link IOException} encountered during writing is thrown.
     *
     *
     * @param symptoms a map of symptom names to their occurrence counts
     * @throws IOException if there is a problem with the given map.
     */
    @Override
    public void writeSymptoms(Map<String, Long> symptoms) throws IOException {
        StringBuilder builder = new StringBuilder();
        //Create the inputs stream of the map.
        symptoms.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry ->
                        builder.append(entry.getKey()) //by symptoms (diseases).
                                .append(": ")
                                .append(entry.getValue()) //by occurrences.
                                .append("\n")
                );

        Files.write(
                outputPath,
                builder.toString().getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        ); //Create or replace the output file.

    }
}
