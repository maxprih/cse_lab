package attacks;

import ru.ifmo.se.pokemon.*;

public class Facade extends PhysicalMove {
    public Facade() {
        super(Type.NORMAL,70,100);
    }

    @Override
    public void applyOppDamage(Pokemon enemy, double damage) {
        Status enemy_status = enemy.getCondition();
        if (enemy_status.equals(Status.POISON) || enemy_status.equals(Status.BURN) || enemy_status.equals(Status.PARALYZE)) {
            super.applyOppDamage(enemy, 2*damage);
        }
        else {
            super.applyOppDamage(enemy, damage);
        }
    }

    @Override
    public String describe() {
        return "использует Facade";
    }
}
