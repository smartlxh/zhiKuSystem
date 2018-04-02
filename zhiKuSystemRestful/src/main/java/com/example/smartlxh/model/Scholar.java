package com.example.smartlxh.model;

/**
 * Created by lixianhai on 23/03/2018.
 */
public class Scholar {
    int id;
    String name;
    int age;
    String researchDirction;
    String works;
    String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResearchDirction() {
        return researchDirction;
    }

    public void setResearchDirction(String researchDirction) {
        this.researchDirction = researchDirction;
    }


    public String getWorks() {
        return works;
    }

    public void setWorks(String works) {
        this.works = works;
    }
}
