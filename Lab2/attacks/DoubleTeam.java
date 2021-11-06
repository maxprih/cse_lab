package attacks;

import ru.ifmo.se.pokemon.*;

public class DoubleTeam extends StatusMove {
    public DoubleTeam() {
        super(Type.NORMAL, 0, 0);
    }

    @Override
    public String describe() {
        return "использует Facade";
    }

    @Override
    public void applySelfEffects(Pokemon self) {
        self.setMod(Stat.EVASION, +1);
    }
}
