package org.example.service;

import java.util.Scanner;

public class GameStart {
    public static void gameInit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите: 1, чтобы начать новую игру, 0 - чтобы выйти из приложения");
        int id = scanner.nextInt();
        if (id != 0 && id != 1) {
            System.out.println("Значение введено не верно");
            gameInit();
        } else scanner.close();
    }
}
