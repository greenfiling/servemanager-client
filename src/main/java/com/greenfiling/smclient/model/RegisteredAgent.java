/**
 * Copyright 2025 Green Filing, LLC
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
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class RegisteredAgent {
  public static final String TYPE = "registered_agent";
  private Integer id;
  private String name;
  private String type;
  private Address address;
  @SerializedName(value = "supplier_cost")
  private List<SupplierCost> supplierCosts;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  public RegisteredAgent() {
    super();
    setType(TYPE);
  }

  public Address getAddress() {
    return address;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<SupplierCost> getSupplierCosts() {
    if (supplierCosts == null) {
      setSupplierCost(new ArrayList<>());
    }
    return supplierCosts;
  }

  public String getType() {
    return type;
  }

  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSupplierCost(List<SupplierCost> supplierCosts) {
    this.supplierCosts = supplierCosts;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
