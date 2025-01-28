package com.library.Person;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public abstract class Person {

    private String name;


    public Person(String name) {
        this.name=name;

    }



    public String getName() {
        return name;
    }

    public abstract void whoYouAre();

}
