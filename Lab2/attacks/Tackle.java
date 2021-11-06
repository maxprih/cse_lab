package attacks;

import ru.ifmo.se.pokemon.*;

public class Tackle extends PhysicalMove {
    public Tackle() {
        super(Type.NORMAL, 40, 100);
    }

    @Override
    public String describe() {
        return "использует Tackle";
    }
}
