package com.greenfiling.smclient.model.internal;

public class AccountBase {
  public static final String TYPE = "account";

  private String type;
  private String companyName;

  public AccountBase() {
    setType(TYPE);
  }

  public String getCompanyName() {
    return this.companyName;
  }

  public String getType() {
    return this.type;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public void setType(String type) {
    this.type = type;
  }

}