package com.sigarda.CRUDMySQL.Models;

import java.io.Serializable;

public class Student implements Serializable {
    String name;
    String nim;
    String gender;

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getNim() {
        return nim;
    }
}