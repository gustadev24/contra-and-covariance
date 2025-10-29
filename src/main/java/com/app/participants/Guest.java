package com.app.participants;

import com.app.model.Participant;

public class Guest extends Participant {
    
    private String organization;

    public Guest(String id, String name, String email, String organization) {
        super(id, name, email, "Invitado");
        this.organization = organization;
    }

    public String getOrganization() {
        return organization;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + String.format(", Organización: %s", organization);
    }
}
