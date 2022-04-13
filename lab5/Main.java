import controller.Client;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        try {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("\nExiting program...")));
            Client client = new Client(new File(args[0])); // "/Users/max_pri/IdeaProjects/tjsifjds/src","inpu.xml"
            client.init();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Filename must be argument");
        }
    }

}
