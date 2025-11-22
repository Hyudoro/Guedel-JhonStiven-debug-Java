package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AnalyticsCounter {
	private ISymptomReader reader;
	private ISymptomWriter writer;
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

	public AnalyticsCounter(ISymptomReader iSymptomReader, ISymptomWriter iSymptomWriter){
		this.reader = iSymptomReader;
		this.writer = iSymptomWriter;
	}



	public static void main(String[] args) {
		ISymptomReader reader = new ReadSymptomDataFromFile("Project02Eclipse/src/com/hemebiotech/analytics/symptoms.txt");
		ISymptomWriter writer = new WriteSymptomDataToFile("result.out");
		AnalyticsCounter counter = new AnalyticsCounter(reader, writer);

		List<String> symptoms = counter.getSymptoms();
		Map<String, Long> symptomsByOccurences = counter.countBySymptoms(symptoms);
        Map<String, Long> symtomsRankedAlphabetically = counter.SortSymptomsAlphabetically(symptomsByOccurences);
		



//		/try {
//			writer.writeSymptoms(sortedSymptoms);
//		} catch (IOException e) {
//			e.printStackTrace(); // handling errors temporarely
//		}
//		sortedSymptoms.forEach((k, v) -> System.out.println(k + ": " + v));
//	}
	}

	public List<String> getSymptoms(){
		return reader.getSymptoms();
	}




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

	public Map<String,Long> SortSymptomsAlphabetically(Map<String, Long> naturalRanking){
		return new TreeMap<>(naturalRanking);
	}


 }


