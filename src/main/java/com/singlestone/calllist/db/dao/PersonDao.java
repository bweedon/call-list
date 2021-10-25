package com.singlestone.calllist.db.dao;

import com.singlestone.calllist.db.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDao extends JpaRepository<Person, Integer> { }
