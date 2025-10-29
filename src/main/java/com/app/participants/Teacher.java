package com.app.participants;

import com.app.model.Participant;

public class Teacher extends Participant {
    private String institution;
    private String specialty;

    public Teacher(String id, String name, String email, String institution, String specialty) {
        super(id, name, email, "Docente");
        this.institution = institution;
        this.specialty = specialty;
    }

    public String getInstitution() {
        return institution;
    }

    public String getSpecialty() {
        return specialty;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + String.format(", Institución: %s, Especialidad: %s", institution, specialty);
    }
}
