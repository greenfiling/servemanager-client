package com.greenfiling.smclient.model;

import com.greenfiling.smclient.model.internal.FirmApiKeyBase;

public class FirmApiKey extends FirmApiKeyBase {

  private Integer id;
  private String key;

  public Integer getId() {
    return this.id;
  }

  public String getKey() {
    return this.key;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setKey(String key) {
    this.key = key;
  }

}