package com.singlestone.calllist.db.dao;

import com.singlestone.calllist.db.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonDao extends CrudRepository<Person, Integer> { }
