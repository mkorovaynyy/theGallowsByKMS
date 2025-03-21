package org.example.model;

import java.util.Arrays;
import java.util.Objects;

public class Hangman {

    private final String[] HANGMAN_STAGES = {
            """
              +---+
              |   |
                  |
                  |
                  |
                  |
            =========""",
            """
              +---+
              |   |
              O   |
                  |
                  |
                  |
            =========""",
            """
              +---+
              |   |
              O   |
              |   |
                  |
                  |
            =========""",
            """
              +---+
              |   |
              O   |
             /|   |
                  |
                  |
            =========""",
            """
              +---+
              |   |
              O   |
             /|\\  |
                  |
                  |
            =========""",
            """
              +---+
              |   |
              O   |
             /|\\  |
             /    |
                  |
            =========""",
            """
              +---+
              |   |
              O   |
             /|\\  |
             / \\  |
                  |
            ========="""
    };

    public String[] getHANGMAN_STAGES() {
        return HANGMAN_STAGES;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Hangman gallows = (Hangman) o;
        return Objects.deepEquals(HANGMAN_STAGES, gallows.HANGMAN_STAGES);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(HANGMAN_STAGES);
    }

    @Override
    public String toString() {
        return "Gallows{" +
                "HANGMAN_STAGES=" + Arrays.toString(HANGMAN_STAGES) +
                '}';
    }
}
