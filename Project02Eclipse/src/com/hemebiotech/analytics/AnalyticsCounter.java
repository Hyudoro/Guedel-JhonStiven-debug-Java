package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AnalyticsCounter {
	private ISymptomReader reader;
	private ISymptomWriter writer;
	public AnalyticsCounter(ISymptomReader iSymptomReader, ISymptomWriter iSymptomWriter){
		this.reader = iSymptomReader;
		this.writer = iSymptomWriter;
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

	public void writeSymptoms(Map<String, Long> SymptomsRankedAlphabeticallyGroupedByOccurences) throws IOException {
		writer.writeSymptoms(SymptomsRankedAlphabeticallyGroupedByOccurences);
	}


}


