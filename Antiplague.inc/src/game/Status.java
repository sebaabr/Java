package game;

import model.ModelBuilder;

import java.time.LocalDate;

public class Status {
    public static LocalDate date = LocalDate.now();
    public static int money = ModelBuilder.START_MONEY;
    public static int score = 0; // czas gry, ludzie
    public static Level level = null;
    public static boolean gameOver = false;
    public static boolean gameInterrupted = false;
}
