package com.rest.restfulwebservices.restfulwebservices.versoning;

public class PersonV2 {

    private Name name;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public PersonV2() {
    }

    public PersonV2(Name name) {
        this.name = name;
    }
}
