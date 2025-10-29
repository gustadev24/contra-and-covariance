package com.app.participants;

import com.app.model.Participant;

public class Audience extends Participant {
    private boolean isInternational;
    private String country;

    public Audience(String id, String name, String email, boolean isInternational, String country) {
        super(id, name, email, "Público General");
        this.isInternational = isInternational;
        this.country = country;
    }

    public boolean isInternational() {
        return isInternational;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String getInfo() {
        String audienceType = isInternational ? "Internacional" : "Nacional";
        return super.getInfo() + String.format(", Tipo: %s, País: %s", audienceType, country);
    }
}