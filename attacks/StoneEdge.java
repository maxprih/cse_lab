package attacks;

import ru.ifmo.se.pokemon.*;

public class StoneEdge extends PhysicalMove {
    public StoneEdge() {
        super(Type.ROCK, 100, 80);
    }

    @Override
    public String describe() {
        return "использует StoneEdge";
    }

    @Override
    public double calcCriticalHit(Pokemon att, Pokemon def) {
        if (Math.random() <= 0.125) {
            return 2;
        }
        else
            return 1;
    }
}
