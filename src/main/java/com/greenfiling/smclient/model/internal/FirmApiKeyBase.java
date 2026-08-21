package com.greenfiling.smclient.model.internal;

public class FirmApiKeyBase {
  public static final String TYPE = "api_key";

  private String type;
  private String title;

  public FirmApiKeyBase() {
    setType(TYPE);
  }

  public String getTitle() {
    return title;
  }

  public String getType() {
    return this.type;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setType(String type) {
    this.type = type;
  }

}