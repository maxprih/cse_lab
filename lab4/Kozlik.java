public class Kozlik extends Human implements ISleep{
    public Kozlik(String name) {
        this.setName(name);
    }

    @Override
    public String toSleepAt(PLACE place) {
        return this + " спит на " + PLACE.UPPER_SHELVES.getName();
    }
}
