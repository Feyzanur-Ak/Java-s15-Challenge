package com.library.Person;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public abstract class Person {

    private String name;
    private String id;

    public Person(String name, String id) {
        this.name=name;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract void whoYouAre();

}
