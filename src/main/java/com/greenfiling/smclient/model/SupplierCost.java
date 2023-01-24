package com.greenfiling.smclient.model;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class SupplierCost {

  private Integer id;
  private String zipcode;
  @SerializedName(value = "job_type_id")
  private String jobTypeId;
  @SerializedName(value = "sla_id")
  private String slaId;
  private Double amount;
  @SerializedName(value = "suggested_retail_price")
  private Double suggestedRetailPrice;
  @SerializedName(value = "created_at")
  private Date createdAt;
  @SerializedName(value = "updated_at")
  private Date updatedAt;

  public Double getAmount() {
    return this.amount;
  }

  public Date getCreatedAt() {
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

  public Date getUpdatedAt() {
    return this.updatedAt;
  }

  public String getZipcode() {
    return this.zipcode;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public void setCreatedAt(Date createdAt) {
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

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }
}
