package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AnalyticsCounter {

	public static void main(String[] args) {

		// 1. Lire les symptômes via l'interface
		ISymptomReader reader = new ReadSymptomDataFromFile(
				"Project02Eclipse/src/com/hemebiotech/analytics/symptoms.txt"
		);
		List<String> symptoms = reader.GetSymptoms();

		// 2. Compter les occurrences (Map <String, Long>)
		Map<String, Long> symptomCounts = symptoms.stream()
				.map(String::trim)
				.filter(s -> !s.isEmpty())
				.collect(Collectors.groupingBy(
						s -> s,
						Collectors.counting()
				));

		// 3. Trier alphabétiquement
		Map<String, Long> sortedSymptoms = new TreeMap<>(symptomCounts);

		// 4. Écrire via ISymptomWriter (pas directement ici !)
		ISymptomWriter writer = new WriteSymptomDataToFile("result.out");

		try {
			writer.writeSymptoms(sortedSymptoms);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 5. Affichage console (optionnel)
		sortedSymptoms.forEach((k, v) -> System.out.println(k + ": " + v));
	}
}

