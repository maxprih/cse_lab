public class Main {
    public static void main(String[] args) {
        Scooperfield scooper = new Scooperfield("Scooper");
        Neznaika nezn = new Neznaika("Neznaika");
        FatBoy fb = new FatBoy("Tolstyak");
        Kozlik kz = new Kozlik("Kozlik");
        System.out.println(scooper.say("Да, братцы, в поезде спать -- это не то что в дупле!"));
        scooper.setReadyToSleep(true);
        System.out.println(scooper.isReadyToSleep());
        System.out.println(scooper.putHandIn(PLACE.LAVKA));
        System.out.println(scooper.searchAt(OBJECTS.CYLINDER,PLACE.LAVKA));
        scooper.setCalm(true);
        System.out.println(scooper.isCalm());
        System.out.println(scooper.remember(OBJECTS.CANE));
        System.out.println(scooper.putHandIn(PLACE.FLOOR));
        System.out.println(scooper.searchAt(OBJECTS.CANE,PLACE.FLOOR));
        System.out.println(scooper.searchAt(OBJECTS.CANE,PLACE.LAVKA_TOLSTYAKA));
        System.out.println(fb.toSleepAt(PLACE.LAVKA_TOLSTYAKA));
        System.out.println(fb.toSnore());
        System.out.println(scooper.searchAt(OBJECTS.CANE,PLACE.UPPER_SHELVES));
        System.out.println(nezn.toSleepAt(PLACE.UPPER_SHELVES));
        System.out.println(kz.toSleepAt(PLACE.UPPER_SHELVES));
        System.out.println(OBJECTS.CANE.getPlaceWithName());
    }
}
