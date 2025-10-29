package com.app.participants;

import com.app.model.Participant;

public class Administrative extends Participant {
    private String department;
    private String position;

    public Administrative(String id, String name, String email, String department, String position) {
        super(id, name, email, "Administrativo");
        this.department = department;
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + String.format(", Departamento: %s, Cargo: %s", department, position);
    }
}
