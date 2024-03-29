package org.ruslan.utils.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


public class JsonReader {
    private final Logger logger = LogManager.getLogger();
    private static JsonReader INSTANCE;
    ObjectMapper objectMapper;

    private JsonReader() {
        this.objectMapper = new ObjectMapper();
    }

    public static JsonReader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new JsonReader();
        }
        return INSTANCE;
    }

    public List readEntities(Class entityClass, String path) {
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.readValue(new File(path), objectMapper.getTypeFactory().constructCollectionType(List.class, entityClass));
        } catch (IOException e) {
            System.out.println("Could not read data from file " + path.substring(path.lastIndexOf("\\") + 1));
            logger.error("Could not read data from file ", e);
        }

        return Collections.EMPTY_LIST;
    }
}
