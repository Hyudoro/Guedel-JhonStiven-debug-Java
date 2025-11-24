package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads symptom data from a text file, one symptom per line.
 *This class implements the {@link ISymptomReader} interface and provides
 * a simple, brute-force mechanism to read all lines from a file into a list of strings.
 * If the file cannot be read or {@code filepath} is null, an empty list is returned.
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

	private final String filepath;
	
	/**
	 * Constructs a reader for the given file path.
	 * @param filepath a full or partial path to file with symptom strings in it, one per line.
	 */
	public ReadSymptomDataFromFile (String filepath) {
		this.filepath = filepath;
	}

	/**
	 * Reads the file specified in {@code filepath} and returns a list of symptoms.
	 * Each line in the file is considered a separate symptom string.
	 * If the file cannot be read or {@code filepath} is null, an empty list is returned.
	 * @return a {@link List} of symptom strings read from the file.
	 */
	
	@Override
	public List<String> getSymptoms() {
		ArrayList<String> result = new ArrayList<>();
		
		if (filepath != null) {
			try {
				BufferedReader reader = new BufferedReader (new FileReader(filepath)); // Creates buffered file reader.
				String line = reader.readLine();
				
				while (line != null) {
					result.add(line);
					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
