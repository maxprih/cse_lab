package client.commands;

import data.Dragon;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;

abstract public class AbstractCommand implements Serializable {
    private LinkedList<String> historyCollection = new LinkedList<>();
    public String execute(LinkedHashMap<Integer, Dragon> dragonsCollection) {
        return "";
    }
    public LinkedList<String> getHistoryCollection(){
        return this.historyCollection;
    }
    public void setHistoryCollection(LinkedList<String> historyCollection) {
        this.historyCollection = historyCollection;
    }
}
