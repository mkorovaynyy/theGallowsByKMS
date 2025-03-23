package org.example.service;

import org.example.exception.FileNotFoundExceptionInZip;
import org.example.model.Hangman;

import java.io.IOException;
import java.util.Scanner;

public class GameLogic {
    public static void logicOfGameStart() throws FileNotFoundExceptionInZip, IOException {
        Scanner scanner = new Scanner(System.in);
        Hangman hangman = new Hangman();
        int countOfFail = 0;
        String randomWord = new WordRandomizer("config.properties").getRandomWord().trim().toLowerCase();
        String hiddenRandomWord = "*".repeat(randomWord.length());
        System.out.println("Загадано слово:");
        System.out.println(hiddenRandomWord);
        System.out.println("Текущее кол-во ошибок: " + countOfFail);
        System.out.println("Введите букву");
    }
}
