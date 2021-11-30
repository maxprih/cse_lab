public class Neznaika extends Human implements ISleep{
    public Neznaika(String name) {
        this.setName(name);
    }

    @Override
    public String toSleepAt(PLACE place) {
        return this + " спит на " + PLACE.UPPER_SHELVES.getName();
    }
}
