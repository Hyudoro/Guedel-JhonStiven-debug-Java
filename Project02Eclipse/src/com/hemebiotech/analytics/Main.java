package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.List;
import java.util.Map;




public class Main {
    public static void main(String[] args) throws IOException {
        ISymptomReader reader = new ReadSymptomDataFromFile("Project02Eclipse/src/com/hemebiotech/analytics/symptoms.txt");
        ISymptomWriter writer = new WriteSymptomDataToFile("result.out");
        AnalyticsCounter counter = new AnalyticsCounter(reader, writer);

        List<String> symptoms = counter.getSymptoms();
        Map<String, Long> symptomsByOccurences = counter.countBySymptoms(symptoms);
        Map<String, Long> symtomsRankedAlphabetically = counter.SortSymptomsAlphabetically(symptomsByOccurences);
        counter.writeSymptoms(symtomsRankedAlphabetically);
    }
}