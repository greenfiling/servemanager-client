package com.greenfiling.smclient.model;

import java.time.OffsetDateTime;

public class SupplierCost {

  private Integer id;
  private String zipcode;
  private String jobTypeId;
  private String slaId;
  private Double amount;
  private Double suggestedRetailPrice;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  public Double getAmount() {
    return this.amount;
  }

  public OffsetDateTime getCreatedAt() {
    return this.createdAt;
  }

  public Integer getId() {
    return this.id;
  }

  public String getJobTypeId() {
    return this.jobTypeId;
  }

  public String getSlaId() {
    return this.slaId;
  }

  public Double getSuggestedRetailPrice() {
    return this.suggestedRetailPrice;
  }

  public OffsetDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  public String getZipcode() {
    return this.zipcode;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setJobTypeId(String jobTypeId) {
    this.jobTypeId = jobTypeId;
  }

  public void setSlaId(String slaId) {
    this.slaId = slaId;
  }

  public void setSuggestedRetailPrice(Double suggestedRetailPrice) {
    this.suggestedRetailPrice = suggestedRetailPrice;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }
}
