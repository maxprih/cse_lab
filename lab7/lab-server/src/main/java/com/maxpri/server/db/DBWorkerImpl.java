package com.maxpri.server.db;


import com.maxpri.common.db.DBWorker;
import com.maxpri.common.entities.Coordinates;
import com.maxpri.common.entities.Location;
import com.maxpri.common.entities.Person;
import com.maxpri.common.entities.User;
import com.maxpri.common.entities.enums.Color;
import com.maxpri.common.entities.enums.Country;

import com.maxpri.server.utils.Encryptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DBWorkerImpl implements DBWorker {

    private final DBConnector dbConnector;
    private final Encryptor encryptor;

    public DBWorkerImpl(DBConnector dbConnector, Encryptor encryptor) {
        this.dbConnector = dbConnector;
        this.encryptor = encryptor;
    }

    @Override
    public synchronized long addPerson(Person person, User user) {
        try (Connection connection = dbConnector.connect();
             PreparedStatement addPersonToTable = connection.prepareStatement(DBQuery.INSERT_PERSON.getQuery());) {
            int paramCounter = 1;
            paramCounter = fillStatementWithPersonData(addPersonToTable, person, paramCounter);
            addPersonToTable.setTimestamp(paramCounter++, Timestamp.from(person.getCreationDate().toInstant()));
            addPersonToTable.setLong(paramCounter, user.getId());

            ResultSet resultSet = addPersonToTable.executeQuery();
            resultSet.next();
            long personId = resultSet.getLong("person_id");
            person.setId(personId);
            person.setAuthor(user.getLogin());
            return personId;
        } catch (SQLException e) {
            return -1;
        }
    }

    @Override
    public synchronized long addUser(User user) {
        try (Connection connection = dbConnector.connect();
             PreparedStatement addUserToTable = connection.prepareStatement(DBQuery.INSERT_USER.getQuery());) {
            addUserToTable.setString(1, user.getLogin());
            addUserToTable.setString(2, encryptor.encrypt(user.getPassword()));

            ResultSet resultSet = addUserToTable.executeQuery();
            resultSet.next();
            long userId = resultSet.getLong("user_id");
            user.setId(userId);
            return userId;
        } catch (SQLException e) {
            return -1;
        }
    }

    @Override
    public synchronized long checkUser(User user) {
        try (Connection connection = dbConnector.connect();
             PreparedStatement checkUser = connection.prepareStatement(DBQuery.SELECT_USER_BY_LOGIN_AND_PASSWORD.getQuery());
        ) {
            checkUser.setString(1, user.getLogin());
            checkUser.setString(2, encryptor.encrypt(user.getPassword()));

            ResultSet resultSet = checkUser.executeQuery();
            if (resultSet.next()) {
                long userId = resultSet.getLong("user_id");
                return userId;
            }
            return 0;
        } catch (SQLException e) {
            return -1;
        }
    }

    @Override
    public synchronized long updatePerson(Person person) {
        try (Connection connection = dbConnector.connect();
             PreparedStatement updatePerson = connection.prepareStatement(DBQuery.UPDATE_PERSON.getQuery());
        ) {
            int paramCounter = 1;
            paramCounter = fillStatementWithPersonData(updatePerson, person, paramCounter);
            updatePerson.setLong(paramCounter, person.getId());
            updatePerson.executeUpdate();

            return person.getId();
        } catch (SQLException e) {
            return -1;
        }

    }

    @Override
    public synchronized long deletePersonsByUser(User user) {
        try (Connection connection = dbConnector.connect();
             PreparedStatement deletePersonByUser = connection.prepareStatement(DBQuery.DELETE_PERSONS_BY_AUTHOR.getQuery());
        ) {
            deletePersonByUser.setString(1, user.getLogin());

            return deletePersonByUser.executeUpdate();
        } catch (SQLException e) {
            return -1;
        }
    }

    @Override
    public synchronized long deletePersonById(long personId) {
        try (Connection connection = dbConnector.connect();
             PreparedStatement deletePersonById = connection.prepareStatement(DBQuery.DELETE_PERSON_BY_ID.getQuery());) {
            deletePersonById.setLong(1, personId);
            return deletePersonById.executeUpdate();
        } catch (SQLException e) {
            return -1;
        }
    }

    @Override
    public synchronized Set<Person> selectAllPersons() {
        try (Connection connection = dbConnector.connect();
             PreparedStatement selectAllPersons = connection.prepareStatement(DBQuery.SELECT_ALL_PERSONS.getQuery());
        ) {
            ResultSet resultSet = selectAllPersons.executeQuery();
            return parsePersonsFromResultSet(resultSet);
        } catch (SQLException e) {
            return null;
        }
    }

    private Set<Person> parsePersonsFromResultSet(ResultSet resultSet) throws SQLException {
        Set<Person> personSet = Collections.synchronizedSet(new HashSet<>());
        while (resultSet.next()) {
            Person person = new Person();
            person.setId(resultSet.getLong("person_id"));
            person.setAuthor(resultSet.getString("login"));
            person.setName(resultSet.getString("person_name"));
            person.setCoordinates(new Coordinates(resultSet.getDouble("coordinates_x"),
                    resultSet.getDouble("coordinates_y")));
            person.setCreationDate(ZonedDateTime.ofInstant(resultSet.getTimestamp("creation_date").toInstant(), ZoneId.of("UTC")));
            person.setHeight(resultSet.getLong("height"));
            String eyeColor = resultSet.getString("eye_color");
            person.setEyeColor(eyeColor == null ? null : Color.valueOf(eyeColor));
            String hairColor = resultSet.getString("hair_color");
            person.setHairColor(hairColor == null ? null : Color.valueOf(hairColor));
            person.setNationality(Country.valueOf(resultSet.getString("country_name")));
            person.setLocation(new Location(resultSet.getDouble("location_x"),
                    resultSet.getLong("location_y"),
                    resultSet.getFloat("location_z")));
            personSet.add(person);
        }
        return personSet;
    }

    private int fillStatementWithPersonData(PreparedStatement statement, Person person, int givenParamCounter) throws SQLException {
        int paramCounter = givenParamCounter;

        statement.setDouble(paramCounter++, person.getLocation().getX());
        statement.setLong(paramCounter++, person.getLocation().getY());
        statement.setFloat(paramCounter++, person.getLocation().getZ());
        statement.setString(paramCounter++, person.getName());
        statement.setDouble(paramCounter++, person.getCoordinates().getX());
        statement.setDouble(paramCounter++, person.getCoordinates().getY());
        statement.setLong(paramCounter++, person.getHeight());
        if (person.getEyeColor() != null) {
            statement.setString(paramCounter++, person.getEyeColor().name());
        } else {
            statement.setNull(paramCounter++, Types.VARCHAR);
        }
        if (person.getHairColor() != null) {
            statement.setString(paramCounter++, person.getHairColor().name());
        } else {
            statement.setNull(paramCounter++, Types.VARCHAR);
        }
        statement.setString(paramCounter++, person.getNationality().name());
        statement.setDouble(paramCounter++, person.getLocation().getX());
        statement.setLong(paramCounter++, person.getLocation().getY());
        statement.setFloat(paramCounter++, person.getLocation().getZ());

        return paramCounter;
    }

}
