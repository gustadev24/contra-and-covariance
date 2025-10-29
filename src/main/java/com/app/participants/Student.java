package com.app.participants;

import com.app.model.Participant;

public class Student extends Participant {
    private String university;
    private String career;

    public Student(String id, String name, String email, String university, String career) {
        super(id, name, email, "Estudiante");
        this.university = university;
        this.career = career;
    }

    public String getUniversity() {
        return university;
    }

    public String getCareer() {
        return career;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + String.format(", Universidad: %s, Carrera: %s", university, career);
    }
}
