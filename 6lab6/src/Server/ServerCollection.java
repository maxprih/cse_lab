package Server;

import client.Run;
import client.commands.AbstractCommand;
import data.Dragon;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class ServerCollection {
    private LinkedList<String> history = new LinkedList<>();
    private LinkedHashMap<Integer, Dragon> dragonsCollection;
    private final Date date = new Date();
    private JsonProcessing save = new JsonProcessing();
    /**
     * Свойство - ID объектов
     */


    public ServerCollection(){
        try {
            dragonsCollection = JsonProcessing.readFile();
        } catch (IOException e) {
            Run.log.warning("IOException in server");
            e.printStackTrace();
        }

    }
    public String executeCurrentCommand(AbstractCommand command){
        history.add(command.getClass().getSimpleName());
        if (command.getClass().getSimpleName().equals("History")) {
            command.setHistoryCollection(this.history);
        }
        return command.execute(dragonsCollection);
    }


    public void whenExit() {
        try {
            save.saveCollection(dragonsCollection);
        } catch (IOException e) {
            Run.log.warning("IOException in server");
            e.printStackTrace();
        }

    }

}
