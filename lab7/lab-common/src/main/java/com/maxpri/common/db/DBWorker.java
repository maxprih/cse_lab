package com.maxpri.common.db;

import com.maxpri.common.entities.Person;
import com.maxpri.common.entities.User;

import java.util.Set;

public interface DBWorker {

    long addPerson(Person person, User user);
    long addUser(User user);
    long updatePerson(Person person);
    long checkUser(User user);
    long deletePersonsByUser(User user);
    long deletePersonById(long personId);
    Set<Person> selectAllPersons();

}
