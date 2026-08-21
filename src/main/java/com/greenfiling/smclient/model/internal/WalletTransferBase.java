package com.greenfiling.smclient.model.internal;

public class WalletTransferBase {

  private String type;
  private Integer jobId;
  private Integer processServerCompanyId;

  public Integer getJobId() {
    return this.jobId;
  }

  public Integer getProcessServerCompanyId() {
    return this.processServerCompanyId;
  }

  public String getType() {
    return this.type;
  }

  public void setJobId(Integer jobId) {
    this.jobId = jobId;
  }

  public void setProcessServerCompanyId(Integer processServerCompanyId) {
    this.processServerCompanyId = processServerCompanyId;
  }

  public void setType(String type) {
    this.type = type;
  }

}