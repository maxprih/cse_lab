public class Stock {
    private String name;
    private double price;

    public Stock(String name, double price){
        this.name = name;
        this.price = price;
    }
    public class StockPrice {
        public void up() {
            System.out.println("Цена на акции " + Stock.this.name + " пошла вверх.");
            price = 1.1 * price;
        }

        public void down() {
            System.out.println("Цена на акции " + Stock.this.name + " пошла вниз.");
            price = 0.9 * price;
        }

        public void getPrice() {
            System.out.println("Цена на акцию: " + price);
        }
    }
}
