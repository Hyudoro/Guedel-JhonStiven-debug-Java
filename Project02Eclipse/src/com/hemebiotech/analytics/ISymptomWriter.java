package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.Map;
public interface ISymptomWriter {
    void writeSymptoms(Map<String, Long> symptoms) throws IOException;
}
