package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


/**
 *
 * This class orchestrates the processing of symptom data: retrieving raw symptoms,
 * counting their occurrences, sorting them, and delegating the final output to a writer
 * It is the core program code where the output is being processed.
 *
 *
 */

public class AnalyticsCounter {
	private ISymptomReader reader;
	private ISymptomWriter writer;

	/**
	 * The constructor allowing the Counter to operate with the writer and reader through its object.
	 *
	 *
	 * @param iSymptomReader an interface allowing the counter to access the data.
	 * @param iSymptomWriter an interface allowing the counter to send its result to.
	 */
	public AnalyticsCounter(ISymptomReader iSymptomReader, ISymptomWriter iSymptomWriter){
		this.reader = iSymptomReader;
		this.writer = iSymptomWriter;
	}

	/**
	 *
	 * Retrieves all symptoms from the reader.
	 * @return a list of symptoms where there might be duplicates.
	 */
	public List<String> getSymptoms(){
		return reader.getSymptoms();
	}

	/**
	 *The core data processing logic, data is being filtered, grouped, counted then inserted into the Map return.
	 *
	 * @param symptoms the list of symptoms, possible duplicates.
	 * @return a map with the symptoms and their occurrences filtered.
	 */
	public static Map<String, Long> countBySymptoms(List<String> symptoms){
		Map<String, Long> count = symptoms
				.stream()
				.map(String::trim)
				.filter(s -> !s.isEmpty())
				.collect(Collectors.groupingBy(
						s->s,
						Collectors.counting()
				));
	    return count;
	}

	/**
	 *Sorts symptoms alphabetically by name.
	 *
	 * @param naturalRanking the map of symptoms and their counts to be sorted alphabetically.
	 * @return a new map containing the symptoms sorted alphabetically by their name.
	 */
	public Map<String,Long> sortSymptomsAlphabetically(Map<String, Long> naturalRanking){
		return new TreeMap<>(naturalRanking);
	}


	/**
	 *
	 * Allows initiating the writing phase by the Counter once the data is shaped as requested.
	 * Any {@link IOException} encountered during writing is thrown.
	 *
	 * @param symptomsRankedAlphabeticallyGroupedByOccurrences the final processed dataset to be written to the output file.
	 * @throws IOException if there is a problem with the final input before being written.
	 */

	public void writeSymptoms(Map<String, Long> symptomsRankedAlphabeticallyGroupedByOccurrences) throws IOException {
		writer.writeSymptoms(symptomsRankedAlphabeticallyGroupedByOccurrences);
	}


}


