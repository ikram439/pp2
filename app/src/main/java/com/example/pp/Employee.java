package com.example.pp;

public class Employee {
    String matricule;
    String name;
    String type;

    public Employee(String matricule, String name, String type) {
        this.matricule = matricule;
        this.name = name;
        this.type = type;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
