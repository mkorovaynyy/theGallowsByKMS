package org.example.service;

import org.example.exception.FileNotFoundExceptionInZip;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class WordRandomizer {
    private final Dictionary dictionary;
    private final Random random;

    public WordRandomizer(String configFilePath) throws IOException, FileNotFoundExceptionInZip {
        this.dictionary = new Dictionary(configFilePath);
        this.random = new Random(dictionary.getDictionary().size() - 1);
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public String getRandomWord() throws FileNotFoundExceptionInZip, IOException {
        List<String> allWords = dictionary.getDictionary();
        return allWords.get(random.nextInt());
    }
}
