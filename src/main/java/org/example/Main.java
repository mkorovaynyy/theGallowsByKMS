package org.example;


import org.example.exception.FileNotFoundExceptionInZip;
import org.example.service.Dictionary;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String configFilePath = "src/main/resources/config.properties";
        Dictionary dictionary = new Dictionary(configFilePath);
        try {
            List<String> words = dictionary.getDictionary();

        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
        } catch (FileNotFoundExceptionInZip e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

    }
}