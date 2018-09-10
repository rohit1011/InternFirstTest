package com.example.wa_v_er.retrofit_test.adapter;

import io.realm.RealmObject;

public class Example extends RealmObject {
    String name;
    int age;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    }
