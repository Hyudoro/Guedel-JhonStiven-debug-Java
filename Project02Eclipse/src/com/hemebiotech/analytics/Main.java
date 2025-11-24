package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Main class that launches the application.
 * Uses reader and writer abstractions to handle input and output operations.
 * Takes a symptom file as input, counts occurrences, sorts them alphabetically,
 * and produces an output file.
 */



public class Main {
    /**
     * Entry point of the application.
     * Coordinates reading, processing, and writing of symptom data.
     * Any I/O issue encountered during the process is caught and handled
     * within this method.
     *
     * @param args command-line arguments (unused).
     */
    public static void main(String[] args) {
        try {
            ISymptomReader reader = new ReadSymptomDataFromFile("Project02Eclipse/src/com/hemebiotech/analytics/symptoms.txt");
            ISymptomWriter writer = new WriteSymptomDataToFile("result.out");
            AnalyticsCounter counter = new AnalyticsCounter(reader, writer);

            List<String> symptoms = counter.getSymptoms();
            Map<String, Long> symptomsByOccurrences = AnalyticsCounter.countBySymptoms(symptoms);
            Map<String, Long> symptomsRankedAlphabetically = counter.sortSymptomsAlphabetically(symptomsByOccurrences);
            counter.writeSymptoms(symptomsRankedAlphabetically);

        } catch (IOException e) {
            System.err.println("Error: unable to complete the process.");
        }
    }
}
