package com.greenfiling.smclient.model;

import com.greenfiling.smclient.model.internal.AgencyConnectionBase;

public class AgencyConnectionSubmit extends AgencyConnectionBase {

  private Integer agencyAccountId;

  public Integer getAgencyAccountId() {
    return this.agencyAccountId;
  }

  public void setAgencyAccountId(Integer agencyAccountId) {
    this.agencyAccountId = agencyAccountId;
  }
}