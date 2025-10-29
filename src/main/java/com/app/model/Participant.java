package com.app.model;

public abstract class Participant {
    protected String id;
    protected String name;
    protected String email;
    protected String type;

    public Participant(String id, String name, String email, String type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
    }

    public void register() {
        System.out.println("[REGISTRO] " + type + " registrado: " + name);
    }

    public String getInfo() {
        return String.format("%s: %s (ID: %s, Email: %s)", type, name, id, email);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
