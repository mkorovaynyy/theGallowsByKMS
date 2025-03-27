package org.example.service;

import java.util.Scanner;

public class GameStarter {
    private static final Scanner scanner = new Scanner(System.in);

    public static void startTheGame() throws Exception {

        System.out.println("\nВведите: 1 - чтобы начать новую игру, 0 - чтобы выйти из приложения");
        String input = scanner.nextLine();

        if (input.equals("0")) {
            System.out.println("До свидания!");
            return;
        } else if (input.equals("1")) {
            GameLogic.startMainLogicOfTheGame();
        } else {
            System.out.println("Неверный ввод. Пожалуйста, введите 1 или 0.");
        }

    }
}
