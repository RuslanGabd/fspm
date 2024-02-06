package org.ruslan.utils.csv;

public class CsvWriter<E> {
    private final Class<E> clazz;
    private static CsvWriter INSTANCE;

    public CsvWriter(Class clazz) {
        this.clazz = clazz;
    }


    public static CsvWriter getInstance(Class clazz) {
        if (INSTANCE == null) {
            INSTANCE = new CsvWriter(clazz);
        }
        return INSTANCE;
    }
}
