package com.greenfiling.smclient.model.exchange;

import java.util.ArrayList;

import com.greenfiling.smclient.model.internal.FilterBase;

public class RegisteredAgentFilter extends FilterBase {
  // https://staging-smolf.servemanager.com/registered_agents?q=&filter%5Barchive_state%5D=active&filter%5Bcity%5D=los&filter%5Bstate%5D=CA&filter%5Bzipcode%5D=90210&filter%5Bregistered_agent_company%5D%5B%5D=&page=1
  // https://staging-smolf.servemanager.com/registered_agents?q=&
  // filter%5Barchive_state%5D=all&
  // filter%5Bcity%5D=los&
  // filter%5Bstate%5D=CA&
  // filter%5Bzipcode%5D=90210%2C+90901&
  // filter%5Bregistered_agent_company%5D%5B%5D=&
  // filter%5Bregistered_agent_company%5D%5B%5D=1&
  // filter%5Bregistered_agent_company%5D%5B%5D=2&page=1

  private ArrayList<String> city = null;
  private String state;
  private ArrayList<String> zipCodes = null;
  private ArrayList<String> registeredAgentCompanys = null;

  @Override
  public ArrayList<FilterPair> getFilters() {
    ArrayList<FilterPair> pairs = super.getFilters();

    if (getState() != null) {
      pairs.add(new FilterPair("filter[state]", getState()));
    }
    if (getCity().size() > 0) {
      pairs.add(new FilterPair("filter[city]", String.join(", ", getState())));
    }
    if (getZipCodes().size() > 0) {
      pairs.add(new FilterPair("filter[zipcode]", String.join(", ", getZipCodes())));
    }
    pairs.addAll(addListFilter("filter[registered_agent_company][]", getRegisteredAgentCompanies()));
    return pairs;
  }

  public ArrayList<String> getCity() {
    if (city == null) {
      setCity(new ArrayList<>());
    }
    return city;
  }

  public String getState() {
    return state;
  }

  public ArrayList<String> getZipCodes() {
    if (zipCodes == null) {
      setZipCodes(new ArrayList<>());
    }
    return zipCodes;
  }

  public ArrayList<String> getRegisteredAgentCompanies() {
    if (registeredAgentCompanys == null) {
      setRegisteredAgentCompanys(new ArrayList<>());
    }
    return registeredAgentCompanys;
  }

  public void setCity(ArrayList<String> city) {
    this.city = city;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setZipCodes(ArrayList<String> zipCodes) {
    this.zipCodes = zipCodes;
  }

  public void setRegisteredAgentCompanys(ArrayList<String> registeredAgentCompanys) {
    this.registeredAgentCompanys = registeredAgentCompanys;
  }

}
