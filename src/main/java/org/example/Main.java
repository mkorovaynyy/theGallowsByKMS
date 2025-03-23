package org.example;

import org.example.exception.FileNotFoundExceptionInZip;
import org.example.service.Dictionary;
import org.example.service.WordRandomizer;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        try {
            // Указываем путь к конфигурационному файлу
            String configFilePath = "config.properties";

            // Создаем объект WordRandomiser
            WordRandomizer wordRandomiser = new WordRandomizer(configFilePath);

            // Получаем словарь
            Dictionary dictionary = wordRandomiser.getDictionary();

            // Получаем список слов из словаря
            List<String> words = dictionary.getDictionary();

            // Выводим первые 10 слов для проверки
            for (int i = 0; i < 10 && i < words.size(); i++) {
                System.out.println(words.get(i));
            }

        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
        } catch (FileNotFoundExceptionInZip e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
    }
