package com.app.participants;

import com.app.model.Participant;
import com.app.model.Presenter;

public class NationalPresenter extends Participant implements Presenter {
  private String topic;
  private String institution;

  public NationalPresenter(String id, String name, String email, String topic, String institution) {
    super(id, name, email, "Ponente Nacional");
    this.topic = topic;
    this.institution = institution;
  }

  @Override
  public void present(String topic) {
    System.out.println("[PRESENTACIÓN] " + name + " presenta: " + topic);
  }

  public String getTopic() {
    return topic;
  }

  public String getInstitution() {
    return institution;
  }

  @Override
  public String getInfo() {
    return super.getInfo() + String.format(", Tema: %s, Institución: %s", topic, institution);
  }
}