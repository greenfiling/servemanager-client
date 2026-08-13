package com.greenfiling.smclient.model;

import java.util.ArrayList;

public class AgencyExchangeProfile {

  private Integer id;
  private String description;
  private ArrayList<String> services;
  private String tier; // best, better, good, do_not_use
  private Integer tierRank; // 1-4
  private Boolean ready;

  public String getDescription() {
    return this.description;
  }

  public Integer getId() {
    return this.id;
  }

  public Boolean getReady() {
    return this.ready;
  }

  public ArrayList<String> getServices() {
    return this.services;
  }

  public String getTier() {
    return this.tier;
  }

  public Integer getTierRank() {
    return this.tierRank;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setReady(Boolean ready) {
    this.ready = ready;
  }

  public void setServices(ArrayList<String> services) {
    this.services = services;
  }

  public void setTier(String tier) {
    this.tier = tier;
  }

  public void setTierRank(Integer tierRank) {
    this.tierRank = tierRank;
  }

}