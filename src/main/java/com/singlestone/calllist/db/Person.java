package com.singlestone.calllist.db;

import javax.persistence.*;
import java.util.List;

@Entity

public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private int zip;
    private String email;
    @OneToMany(mappedBy = "person")
    private List<PhoneNumber> phoneNumbers;

}
