package org.ruslan.csv;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.ruslan.entity.Product;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;


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


//    private static void writeCsv(String filePath, List<List<String>> data) {
//        try (Writer writer = new FileWriter(filePath);
//             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
//
//            // Writing header
//            csvPrinter.printRecord(data.get(0));
//
//            // Writing data
//            for (int i = 1; i < data.size(); i++) {
//                csvPrinter.printRecord(data.get(i));
//            }
//
//            System.out.println("CSV file created successfully!");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public void writeRowsToCsv(String filePath, List<E> entities)
            throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filePath))) {
            StringWriter writer = new StringWriter();
            ColumnPositionMappingStrategy mappingStrategy =
                    new ColumnPositionMappingStrategy();
            mappingStrategy.setType(clazz);

            StatefulBeanToCsvBuilder<E> builder = new StatefulBeanToCsvBuilder(writer);
            StatefulBeanToCsv beanWriter = builder
                    .withMappingStrategy(mappingStrategy)
                    .withSeparator('#')
                    .withQuotechar('\'')
                    .build();

            beanWriter.write(entities);
        }
    }

    public  void beanToCSVWithCustomHeaderAndPositionStrategy(List applications)
            throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try (FileWriter writer = new FileWriter("application4.csv")) {
            ColumnPositionMappingStrategy mappingStrategy =
                    new ColumnPositionMappingStrategy();
            mappingStrategy.setType(clazz);
            var builder = new StatefulBeanToCsvBuilder<E>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(mappingStrategy)
                    .build();
            builder.write(applications);
        }
    }


    public  void beanToCSVWithDefault(List<Product> products) throws Exception {
        try (FileWriter writer = new FileWriter("Products.csv")) {
            var builder = new StatefulBeanToCsvBuilder<Product>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withSeparator(',')
                    .build();

            builder.write(products);
        }
    }


    public void toCsv (List products) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new JavaTimeModule());
        //mapper.registerModule(new JodaModule());

        JsonNode node = mapper.convertValue(products, JsonNode.class);

        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
        JsonNode firstNode = node.elements().next();
        firstNode.fieldNames().forEachRemaining(name -> {csvSchemaBuilder.addColumn(name);});
        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();

        CsvMapper csvMapper = new CsvMapper();
        csvMapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);

        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValue(new File("src/main/resources/export.csv"), node);
    }
}
