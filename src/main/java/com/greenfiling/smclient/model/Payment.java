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

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class Payment {
  public static final String TYPE = "payment";

  private String type;
  private Integer id;
  private String amount;
  private String description;
  private LocalDate appliedOn;
  private OffsetDateTime updatedAt;
  private OffsetDateTime createdAt;

  public Payment() {
    super();
    setType(TYPE);
  }

  public String getAmount() {
    return this.amount;
  }

  public LocalDate getAppliedOn() {
    return this.appliedOn;
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

  public String getType() {
    return this.type;
  }

  public OffsetDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public void setAppliedOn(LocalDate appliedOn) {
    this.appliedOn = appliedOn;
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

  public void setType(String type) {
    this.type = type;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
