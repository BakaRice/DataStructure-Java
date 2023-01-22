package com.ricemarch.datastructure.chapter05;

import java.util.Objects;

public class Employee {

    @Override
    public boolean equals(Object rhs) {
        return rhs instanceof Employee && name.equals(((Employee) rhs).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    private String name;
    private double salary;
    private int seniority;
}
