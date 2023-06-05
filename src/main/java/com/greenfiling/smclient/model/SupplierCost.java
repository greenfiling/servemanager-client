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
  public static final String TYPE = "supplier_cost";

  private Integer id;
  private String type;
  private String zipcode;
  private Integer zipcodeZoneId;
  private Integer jobTypeId;
  private Integer slaId;
  private Double amount;
  private Double pageCountPrice;
  private Double suggestedRetailPrice;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  public SupplierCost() {
    super();
    setType(TYPE);
  }

  public Double getAmount() {
    return this.amount;
  }

  public OffsetDateTime getCreatedAt() {
    return this.createdAt;
  }

  public Integer getId() {
    return this.id;
  }

  public Integer getJobTypeId() {
    return this.jobTypeId;
  }

  public Double getPageCountPrice() {
    return pageCountPrice;
  }

  public Integer getSlaId() {
    return this.slaId;
  }

  public Double getSuggestedRetailPrice() {
    return this.suggestedRetailPrice;
  }

  public String getType() {
    return type;
  }

  public OffsetDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  public String getZipcode() {
    return this.zipcode;
  }

  /**
   * <ul>
   * <li>1 - Very Densely Populated</li>
   * <li>2 - Densely Populated</li>
   * <li>3 - Semi-Densely Populated</li>
   * <li>4 - Moderately Populated</li>
   * <li>5 - Sparsely Populated</li>
   * </ul>
   * 
   * @return the zone id of the zipcode for this SupplierCost object
   */
  public Integer getZipcodeZoneId() {
    return zipcodeZoneId;
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

  public void setJobTypeId(Integer jobTypeId) {
    this.jobTypeId = jobTypeId;
  }

  public void setPageCountPrice(Double pageCountPrice) {
    this.pageCountPrice = pageCountPrice;
  }

  public void setSlaId(Integer slaId) {
    this.slaId = slaId;
  }

  public void setSuggestedRetailPrice(Double suggestedRetailPrice) {
    this.suggestedRetailPrice = suggestedRetailPrice;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public void setZipcodeZoneId(Integer zipcodeZoneId) {
    this.zipcodeZoneId = zipcodeZoneId;
  }
}
