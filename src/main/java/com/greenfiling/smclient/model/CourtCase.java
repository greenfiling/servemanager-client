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

public class CourtCase extends CourtCaseBase {

  private Links links;
  private Integer id;
  private OffsetDateTime updatedAt;
  private OffsetDateTime createdAt;
  private Court court;

  public CourtCase() {
    super();
  }

  public Court getCourt() {
    return this.court;
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

  public OffsetDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  public void setCourt(Court court) {
    this.court = court;
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

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
