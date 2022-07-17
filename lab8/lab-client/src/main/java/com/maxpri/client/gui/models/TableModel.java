package com.maxpri.client.gui.models;

import com.maxpri.client.ConnectionHandler;
import com.maxpri.client.exceptions.AlertException;
import com.maxpri.client.listeners.ClientNetworkListener;
import com.maxpri.common.commands.ShowCommand;
import com.maxpri.common.entities.Person;
import com.maxpri.common.entities.User;
import com.maxpri.common.network.NetworkListener;
import com.maxpri.common.network.Request;
import com.maxpri.common.network.Response;
import com.maxpri.common.state.PerformanceState;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TableModel {

    private User user;
    private final NetworkListener networkListener;
    private final ListProperty<Person> persons;

    {
        networkListener = ClientNetworkListener.getInstance();
        persons = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    private TableModel() {
        launchUpdatingPersons();
    }

    private static class TableModelHolder {
        public static final TableModel TABLE_MODEL_HOLDER = new TableModel();
    }

    public static TableModel getInstance(User user) {
        TableModel tableModel = TableModelHolder.TABLE_MODEL_HOLDER;
        tableModel.setUser(user);
        return tableModel;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void getNewCollection() throws AlertException {
        Response response;
        try {
            response = networkListener.listen(new Request(new ShowCommand(), new Object[]{user}));
            Platform.runLater(() -> updateCollection(response.getPersons()));
        } catch (IOException e) {
            throw new AlertException("Server isn't available, try later", e);
        }
    }

    public void launchUpdatingPersons() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        PerformanceState performanceState = PerformanceState.getInstance();

        long delay = 0;
        long period = 5000L;
        executor.scheduleAtFixedRate(() -> {
            if (!performanceState.getPerformanceStatus()) {
                executor.shutdown();
            }
            try {
                getNewCollection();
            } catch (AlertException e) {
                executor.shutdown();
                Platform.setImplicitExit(false);
                Platform.runLater(e::fatalShowAlert);
            }
            if (executor.isShutdown()) {
                ConnectionHandler.getInstance().close();
            }
        }, delay, period, TimeUnit.MILLISECONDS);
    }

    public void updateCollection(Collection<Person> newPersonsList) {
        for (Person person : newPersonsList) {
            if (!persons.contains(person)) {
                persons.add(person);
            } else {
                Person personFromTableList = persons.stream().filter((p) -> Objects.equals(p.getId(), person.getId())).toList().get(0);
                if (!person.equals(personFromTableList)) {
                    persons.remove(personFromTableList);
                    persons.add(person);
                }
            }
        }
        persons.removeIf(person -> !newPersonsList.contains(person));
    }

    public ObservableList<Person> getPersons() {
        return persons.get();
    }

    public ListProperty<Person> personsProperty() {
        return persons;
    }

    public User getUser() {
        return user;
    }
}
