package com.app.participants;

import com.app.model.Participant;
import com.app.model.Presenter;

public class InternationalPresenter extends Participant implements Presenter {
    private String topic;
    private String country;
    private String institution;

    public InternationalPresenter(String id, String name, String email, String topic, String country, String institution) {
        super(id, name, email, "Ponente Internacional");
        this.topic = topic;
        this.country = country;
        this.institution = institution;
    }

    @Override
    public void present(String topic) {
        System.out.println("[PRESENTACIÓN INTERNACIONAL] " + name + " (" + country + ") presenta: " + topic);
    }

    public String getTopic() {
        return topic;
    }

    public String getCountry() {
        return country;
    }

    public String getInstitution() {
        return institution;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + String.format(", Tema: %s, País: %s, Institución: %s", topic, country, institution);
    }
}