package attacks;

import ru.ifmo.se.pokemon.*;

public class Slash extends PhysicalMove {
    public Slash() {
        super(Type.NORMAL, 70, 100);
    }

    @Override
    public String describe() {
        return "использует Slash";
    }

    @Override
    public double calcCriticalHit(Pokemon att, Pokemon def) {
        if (Math.random() <= 0.125) {
            return 2;
        } else
            return 1;
    }
}
