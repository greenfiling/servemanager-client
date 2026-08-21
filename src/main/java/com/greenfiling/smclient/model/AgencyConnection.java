package com.greenfiling.smclient.model;

import java.util.List;

public class AgencyConnection {

  private Integer processServerCompanyId;
  private String processServerCompanyName;
  private String apiKey;
  private String status;
  private List<Object> webhookData;

  public String getApiKey() {
    return this.apiKey;
  }

  public Integer getProcessServerCompanyId() {
    return this.processServerCompanyId;
  }

  public String getProcessServerCompanyName() {
    return this.processServerCompanyName;
  }

  public String getStatus() {
    return this.status;
  }

  public List<Object> getWebhookData() {
    return this.webhookData;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public void setProcessServerCompanyId(Integer processServerCompanyId) {
    this.processServerCompanyId = processServerCompanyId;
  }

  public void setProcessServerCompanyName(String processServerCompanyName) {
    this.processServerCompanyName = processServerCompanyName;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setWebhookData(List<Object> webhookData) {
    this.webhookData = webhookData;
  }
}