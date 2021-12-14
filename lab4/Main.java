public class Main {
    public static void main(String[] args) {
        Stock ozon = new Stock("Озон", 100);
        Stock.StockPrice price = ozon.new StockPrice();
        price.up();
        price.up();
        price.getPrice();
        Stock magnit = new Stock("Магнит", 1030);
        Stock.StockPrice price2 = magnit.new StockPrice();
        price2.up();
        price2.getPrice(); // вложенный класс
        // статик вложенный класс - не нашел в тексте - привести пример про чертеж самолета
        Scooperfield scooper = new Scooperfield("Scooper");
        Neznaika nezn = new Neznaika("Neznaika");
        FatBoy fb = new FatBoy("Tolstyak");
        Kozlik kz = new Kozlik("Kozlik");
        scooper.toSay.say("Да, братцы, в поезде спать -- это не то что в дупле!");
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
        try {
            scooper.sleepAt("лавка",true);
        }
        catch(NotReadyToSleepException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
