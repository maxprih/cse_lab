public class FatBoy extends Human implements ISleep{
    public FatBoy(String name) {
        this.setName(name);
    }

    @Override
    public String toSleepAt(PLACE place) {
        return this + " спит на " + PLACE.LAVKA_TOLSTYAKA.getName();
    }

    public String toSnore() {
        return this + " храпит";
    }
}
