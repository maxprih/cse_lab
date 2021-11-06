package attacks;

import ru.ifmo.se.pokemon.*;

public class Confusion extends SpecialMove {
    public Confusion() {
        super(Type.PSYCHIC, 50, 100);
    }

    @Override
    public void applyOppEffects(Pokemon enemy) {
        Effect ef = new Effect().chance(0.1);

    }

    @Override
    public String describe() {
        return "использует Confusion";
    }
}
