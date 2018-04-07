package com.company;

public class Person {
    private String name;
    public Person(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        obj=(Person)obj;
        if (this.name.equals(((Person) obj).getName())){
            return true;
        }
        return false;
     //   return super.equals(obj);
    }

    @Override
    public String toString() {
        //return super.toString();
        return name;
    }

    @Override
    public int hashCode() {
        return this.name.length();
    }
}
