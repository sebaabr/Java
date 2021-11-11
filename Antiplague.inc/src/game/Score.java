package game;

import java.io.Serializable;

public
    class Score
    implements Serializable {

    String name;
    Level level;
    int days;
    int score;

    public Score(String name, Level level, int days, int score) {
        this.name = name;
        this.level = level;
        this.days = days;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Level getLevel() {
        return level;
    }

    public int getDays() {
        return days;
    }

    public int getScore() {
        return score;
    }
}
