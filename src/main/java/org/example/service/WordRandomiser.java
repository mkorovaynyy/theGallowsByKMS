package org.example.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WordRandomiser {
    private final Dictionary dictionary;

    public WordRandomiser(String configFilePath) throws IOException {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFilePath)) {
            if (input == null) {
                throw new IOException("Конфигурационный файл не найден: " + configFilePath);
            }
            properties.load(input);
        }
        this.dictionary = new Dictionary(configFilePath);
    }

    public Dictionary getDictionary() {
        return dictionary;
    }
}
