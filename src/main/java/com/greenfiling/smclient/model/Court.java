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

import com.greenfiling.smclient.model.internal.CourtBase;

public class Court extends CourtBase {

  private Links links;
  private Integer id;
  private OffsetDateTime updatedAt;
  private OffsetDateTime createdAt;
  private Address address;
  private Integer oneLegalRef;
  private boolean production;

  public Court() {
    super();
  }

  public Address getAddress() {
    return this.address;
  }

  public OffsetDateTime getCreatedAt() {
    return this.createdAt;
  }

  public Integer getId() {
    return this.id;
  }

  public Links getLinks() {
    return this.links;
  }

  public Integer getOneLegalRef() {
    return oneLegalRef;
  }

  public boolean getProduction() {
    return production;
  }

  public OffsetDateTime getUpdatedAt() {
    return this.updatedAt;
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

  public void setLinks(Links links) {
    this.links = links;
  }

  public void setOneLegalRef(Integer oneLegalRef) {
    this.oneLegalRef = oneLegalRef;
  }

  public void setProduction(boolean production) {
    this.production = production;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
