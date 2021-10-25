package com.singlestone.calllist.db.dao;

import com.singlestone.calllist.db.model.Person;
import com.singlestone.calllist.db.model.PhoneType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDao extends JpaRepository<Person, Integer> {

    // I'm not sure which piece I'm missing, but I'm pretty sure I can use the below method. The app wouldn't start with
    // it so going to leave it a lone for now and do a custon query.
    // List<Person> findAllByPhoneNumber_PhoneType(PhoneType type);
    @Query("SELECT p from Person p join PhoneNumber pn on pn.person.Id = p.Id where lower(pn.type) = lower('home')")
    List<Person> findByPhoneNumber_WithHomePhone();
}
