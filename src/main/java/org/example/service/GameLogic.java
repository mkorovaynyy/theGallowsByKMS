package org.example.service;

import org.example.exception.FileNotFoundExceptionInZip;
import org.example.model.Hangman;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GameLogic {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Hangman hangman = new Hangman();

    public static void startMainLogicOfTheGame() throws FileNotFoundExceptionInZip, IOException {
        String randomWord = new WordRandomizer("config.properties").getRandomWord().toLowerCase();
        StringBuilder hiddenWord = new StringBuilder("*".repeat(randomWord.length()));
        int countOfFail = 0;
        boolean gameWon = false;
        Set<Character> absentLetters = new HashSet<>(); // Множество для хранения отсутствующих букв

        while (countOfFail < hangman.getHANGMAN_STAGES().length - 1 && !gameWon) {
            System.out.println("\nЗагадано слово:");
            System.out.println(hiddenWord);
            System.out.println("Текущее кол-во ошибок: " + countOfFail);
            System.out.println("Текущее состояние виселицы:");
            System.out.println(hangman.getHANGMAN_STAGES()[countOfFail]);
            System.out.println("Отсутствующие буквы: " + absentLetters);
            System.out.println("Введите букву или '0' для выхода:");

            String input = scanner.nextLine().toLowerCase();

            if (input.equals("0")) {
                return;
            }

            if (input.isBlank()) {
                System.out.println("Ошибка - на вход получена пустая строка");
                continue;
            }

            if (!input.matches("^[а-яё]$")) {
                System.out.println("Ошибка - на вход принимается только одна русская буква");
                continue;
            }

            char letter = input.charAt(0);

            // Если буква уже вводилась и отсутствует в слове
            if (absentLetters.contains(letter)) {
                System.out.println("Эта буква уже вводилась и отсутствует в слове");
                continue;
            }

            boolean letterFound = false;

            for (int i = 0; i < randomWord.length(); i++) {
                if (randomWord.charAt(i) == letter && hiddenWord.charAt(i) == '*') {
                    hiddenWord.setCharAt(i, letter);
                    letterFound = true;
                }
            }

            if (!letterFound) {
                // Добавляем букву в множество отсутствующих только если она там еще не была
                if (!absentLetters.contains(letter)) {
                    absentLetters.add(letter);
                    countOfFail++;
                    System.out.println("Такой буквы нет в слове! Ошибок: " + countOfFail);
                } else {
                    System.out.println("Эта буква уже вводилась и отсутствует в слове");
                }
            }

            if (!hiddenWord.toString().contains("*")) {
                gameWon = true;
            }
        }

        System.out.println("\nИгра завершена!");
        System.out.println("Загаданное слово: " + randomWord);
        if (gameWon) {
            System.out.println("Поздравляем! Вы выиграли!");
        } else {
            System.out.println("Вы проиграли. Состояние виселицы:");
            System.out.println(hangman.getHANGMAN_STAGES()[hangman.getHANGMAN_STAGES().length - 1]);
        }
    }
}
