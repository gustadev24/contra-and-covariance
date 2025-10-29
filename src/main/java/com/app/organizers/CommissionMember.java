package com.app.organizers;

import com.app.participants.Teacher;

public class CommissionMember extends Teacher {

    private String commissionRole;

    public CommissionMember(String id, String name, String email, String institution, String specialty, String commissionRole) {
        super(id, name, email, institution, specialty);
        this.commissionRole = commissionRole;
    }

    public String getCommissionRole() {
        return commissionRole;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + String.format(", Rol en Comisión: %s", commissionRole);
    }
}
