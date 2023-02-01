/**
 * Copyright 2023 Green Filing, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
