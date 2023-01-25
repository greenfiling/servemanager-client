/**
 * Copyright 2021 Green Filing, LLC
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

public class LineItem {
  public static final String TYPE = "line_item";

  private String type;
  private Integer id;
  private String name;
  private String description;
  private String unitCost;
  private String quantity;
  private Double taxRate;
  private Double taxAmount;
  private Double subtotal;
  private Double total;
  private OffsetDateTime updatedAt;
  private OffsetDateTime createdAt;

  public LineItem() {
    super();
    setType(TYPE);
  }

  public OffsetDateTime getCreatedAt() {
    return this.createdAt;
  }

  public String getDescription() {
    return this.description;
  }

  public Integer getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getQuantity() {
    return this.quantity;
  }

  public Double getSubtotal() {
    return this.subtotal;
  }

  public Double getTaxAmount() {
    return this.taxAmount;
  }

  public Double getTaxRate() {
    return this.taxRate;
  }

  public Double getTotal() {
    return this.total;
  }

  public String getType() {
    return this.type;
  }

  public String getUnitCost() {
    return this.unitCost;
  }

  public OffsetDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public void setSubtotal(Double subtotal) {
    this.subtotal = subtotal;
  }

  public void setTaxAmount(Double taxAmount) {
    this.taxAmount = taxAmount;
  }

  public void setTaxRate(Double taxRate) {
    this.taxRate = taxRate;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setUnitCost(String unitCost) {
    this.unitCost = unitCost;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
