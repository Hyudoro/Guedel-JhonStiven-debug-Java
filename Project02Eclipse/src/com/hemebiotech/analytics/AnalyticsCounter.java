package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AnalyticsCounter {
	/**
	 * Main entry point for counting and sorting symptoms.
	 *
	 * Steps performed:
	 * 1. Reads symptoms from a file.
	 * 2. Trims and filters empty entries.
	 * 3. Counts occurrences of each symptom.
	 * 4. Sorts symptoms alphabetically.
	 * 5. Writes the result to an output file.
	 * 6. Prints the result to the console.
	 */
	public static void main(String[] args) {
		ISymptomReader reader = new ReadSymptomDataFromFile("Project02Eclipse/src/com/hemebiotech/analytics/symptoms.txt");
		List<String> symptoms = reader.GetSymptoms();
		Map<String, Long> symptomCounts = symptoms.stream()
				.map(String::trim)
				.filter(s -> !s.isEmpty())
				.collect(Collectors.groupingBy(
						s -> s,
						Collectors.counting()
				));
		Map<String, Long> sortedSymptoms = new TreeMap<>(symptomCounts);
		ISymptomWriter writer = new WriteSymptomDataToFile("result.out");

		try {
			writer.writeSymptoms(sortedSymptoms);
		} catch (IOException e) {
			e.printStackTrace(); // handling errors temporarely
		}
		sortedSymptoms.forEach((k, v) -> System.out.println(k + ": " + v));
	}
}

