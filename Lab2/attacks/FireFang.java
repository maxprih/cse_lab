package attacks;

import ru.ifmo.se.pokemon.*;

public class FireFang extends PhysicalMove {
    public FireFang() {
        super(Type.FIRE, 65, 95);
    }

    @Override
    public void applyOppEffects(Pokemon enemy) {
        Effect ef1 = new Effect().chance(0.1);
        Effect ef2 = new Effect().chance(0.1);
        ef2.flinch(enemy);
        ef1.burn(enemy);

    }

    @Override
    public String describe() {
        return "использует Fire Fang";
    }
}
