package com.singlestone.calllist.db;

import com.singlestone.calllist.db.dao.PersonDao;
import com.singlestone.calllist.db.model.Person;
import com.singlestone.calllist.db.model.PhoneNumber;
import com.singlestone.calllist.db.model.PhoneType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = { com.singlestone.calllist.CallListApplication.class })
public class DbSanityTest {

    @Autowired
    PersonDao personDao;

    @Test
    void insertWithoutError() {
        Person saved = personDao.saveAndFlush(testPerson);
        Assertions.assertNotEquals(0, saved.getId());
    }

    @Test
    void insertAndSelect() {
        Person saved = personDao.saveAndFlush(testPerson);
        Person found = personDao.findById(saved.getId()).orElseThrow();
        Assertions.assertEquals(saved.getId(), found.getId());
    }

    @Test
    void insertMultiple() {
        List<Person> persons = new ArrayList<>(
                Arrays.asList(testPerson, testPersonTwo, testPersonThree)
        );
        List<Person> personsSaved = (List<Person>)personDao.saveAllAndFlush(persons);
        Assertions.assertEquals(3, personsSaved.size());
        List<Person> personsFound = (List<Person>)personDao.findAll();
        //Make sure each inserted person is found. Can't just assert size of `personsFound` because
        // of the other tests inserting data.
        personsFound.forEach(person -> {
            Assertions.assertEquals(person.getId(),
                    personDao.findById(person.getId()).orElseThrow().getId());
        });
    }


    Person testPerson = new Person(
            "first",
            "middle",
            "last",
            "street",
            "city",
            "state",
            111,
            "email@example.com",
            new ArrayList<>(
                    Arrays.asList(
                            new PhoneNumber("phone number 1", PhoneType.HOME)
                    )
            )

    );
    Person testPersonTwo = new Person(
            "first2",
            "middle2",
            "last2",
            "street2",
            "city2",
            "state2",
            111,
            "email@example.com2",
            new ArrayList<>(
                    Arrays.asList(
                            new PhoneNumber("phone number 2", PhoneType.HOME),
                            new PhoneNumber("phone number 3", PhoneType.WORK)
                    )
            )

    );
    Person testPersonThree = new Person(
            "first3",
            "middle3",
            "last3",
            "street3",
            "city3",
            "state3",
            111,
            "email@example.com3",
            new ArrayList<>(
                    Arrays.asList(
                            new PhoneNumber("phone number 4", PhoneType.MOBILE)
                    )
            )

    );

}
