package com.sanvi.dto;

/**
 * Created by jagapathiraju on 19/07/17.
 */
public class Employee {
    private Integer age;
    private String name;
    private String gender;

    public Employee() {
    }

    public Employee(Integer age, String name, String gender) {
        this.age=age;
        this.name=name;
        this.gender=gender;
    }
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
