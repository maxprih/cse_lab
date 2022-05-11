package client.commands;

import data.Dragon;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class History extends AbstractCommand{
    private LinkedList<String> historyCollection = new LinkedList<>();
    @Override
    public String execute(LinkedHashMap<Integer,Dragon> dragonsCollection) {
        StringBuilder retString = new StringBuilder();
        LinkedList<String> templist = getHistoryCollection();
        for (String string : templist) {
            retString.append(" - ").append(string).append("\n");
        }
        return retString.toString();
    }
    public LinkedList<String> getHistoryCollection(){
        return this.historyCollection;
    }
    public void setHistoryCollection(LinkedList<String> historyCollection) {
        this.historyCollection = historyCollection;
    }
}
