package com.greenfiling.smclient.model;

import com.greenfiling.smclient.model.internal.FirmApiKeyBase;

public class FirmApiKeySubmit extends FirmApiKeyBase {

  private Integer userId; // optional

  public FirmApiKeySubmit() {
    super();
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

}