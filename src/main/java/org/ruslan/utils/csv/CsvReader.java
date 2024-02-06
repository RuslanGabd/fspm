package org.ruslan.utils.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    private String filePath;
    private static CsvReader INSTANCE;


    public CsvReader(String filePath) {
        this.filePath = filePath;
    }
    public static CsvReader getInstance(String filePath) {
        if (INSTANCE == null) {
            INSTANCE = new CsvReader(filePath);
        }
        return INSTANCE;
    }

    public List<List<String>> readCSV() throws IOException {
        List<List<String>> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                List<String> row = new ArrayList<>();
                for (String value : values) {
                    row.add(value.trim());
                }
                data.add(row);
            }
        }
        return data;
    }
}

