package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.Map;


/**
 *
 * Defines a component capable of writing symptoms and their occurrence counts
 * to an output destination (e.g. file, console, or external system).
 *
 *
 */
public interface ISymptomWriter {

    /**
     * Writes the provided symptoms and their number of occurrences.
     * The map must contain unique symptom names as keys and their
     * associated occurrence counts as values.
     *
     *
     * @param symptoms
     * @throws IOException
     */


    void writeSymptoms(Map<String, Long> symptoms) throws IOException;
}
