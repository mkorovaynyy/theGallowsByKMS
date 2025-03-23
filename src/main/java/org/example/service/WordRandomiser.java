package org.example.service;

import org.example.exception.FileNotFoundExceptionInZip;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class WordRandomiser {
    private final Dictionary dictionary;
    private final Random random;

    public WordRandomiser(String configFilePath) throws IOException, FileNotFoundExceptionInZip {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFilePath)) {
            if (input == null) {
                throw new IOException("Конфигурационный файл не найден: " + configFilePath);
            }
            properties.load(input);
        }
        this.dictionary = new Dictionary(configFilePath);
        this.random = new Random(dictionary.getDictionary().size()-1);
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public String getRandomWord() throws FileNotFoundExceptionInZip, IOException {
        List<String> allWords = dictionary.getDictionary();
        return allWords.get(random.nextInt());
    }
}
