package com.greenfiling.smclient.model;

public class Owner {

  private String firstName;
  private String lastName;
  private String password;
  private String timeZone;

  public Owner() {
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getPassword() {
    return this.password;
  }

  public String getTimeZone() {
    return this.timeZone;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setTimeZone(String timeZone) {
    this.timeZone = timeZone;
  }

}