package service;

import controller.Client;
import pojo.MusicBand;

/**
 * Counting average value of field numberOpParticipants in objects from collection
 */
public class AverageOfNumberOfParticipants extends Command {

    @Override
    public void execute(String[] args) {
        if (Client.bands.isEmpty())  {
            System.out.println("Collection is empty");
            return;
        }
        Double avg = 0.0;
        for (MusicBand b: Client.bands) {
            avg += b.getNumberOfParticipants();
        }
        System.out.println("Average of number of participants is " + avg / Client.bands.size());
    }

    @Override
    public String getCommandName() {
        return "average_of_number_of_participants";
    }

}
