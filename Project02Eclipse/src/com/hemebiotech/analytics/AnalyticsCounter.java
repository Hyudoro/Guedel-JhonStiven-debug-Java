package com.hemebiotech.analytics;

import java.util.*;
import java.util.stream.*;
import java.nio.file.*;
import java.nio.file.StandardOpenOption;
import java.io.IOException;

/**
 * AnalyticsCounter reads a file containing symptoms, counts the occurrences
 * of each symptom, and outputs the results both to the console and a file.
 * <p>
 * The program demonstrates a Map-Reduce style approach using Java Streams.
 * It trims whitespace, ignores empty lines, groups symptoms by name,
 * counts their occurrences, sorts them alphabetically, and writes the results.
 * </p>
 *
 * Example input (symptoms.txt):
 * headache
 * rash
 * headache
 * pupil
 * rash
 *
 * Example output:
 * headache: 2
 * pupil: 1
 * rash: 2
 *
 * @author
 */
public class AnalyticsCounter {

	/**
	 * Main method. Reads a file of symptoms, counts occurrences, sorts alphabetically,
	 * and writes results to console and "result.out".
	 *
	 * @param args command-line arguments (not used)
	 * @throws IOException if the file cannot be read or written
	 */
	public static void main(String args[]) throws IOException {
		// Read lines from the file, trim, filter empty lines, and count occurrences
		Map<String, Long> symptomCounts = Files.lines(Paths.get(
						"Project02Eclipse/src/com/hemebiotech/analytics/symptoms.txt"))
				.map(String::trim)                     // Remove leading/trailing spaces
				.filter(s -> !s.isEmpty())            // Skip empty lines
				.collect(Collectors.groupingBy(        // Group by symptom
						s -> s,
						Collectors.counting()         // Count occurrences (Map-Reduce style)
				));

		// Sort the map by symptom name (alphabetical order) and process each entry
		symptomCounts.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())   // Sort keys alphabetically
				.forEach(entry -> {
					String output = entry.getKey() + ": " + entry.getValue();
					System.out.println(output);       // Print to console
					try {
						// Append each line to "result.out"
						Files.write(
								Paths.get("result.out"),
								(output + "\n").getBytes(),
								StandardOpenOption.CREATE,
								StandardOpenOption.APPEND
						);
					} catch (IOException e) {
						e.printStackTrace();          // Handle potential file write exceptions
					}
				});
	}
}
