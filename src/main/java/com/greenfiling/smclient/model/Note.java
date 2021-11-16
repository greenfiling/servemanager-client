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

public class Note {
  public static final String TYPE = "note";

  private Links links;
  private String type;
  private Integer id;
  private String label;
  private String body;
  private Integer jobId;
  private OffsetDateTime updatedAt;
  private OffsetDateTime createdAt;

  public Note() {
    super();
    setType(TYPE);
  }

  public String getBody() {
    return this.body;
  }

  public OffsetDateTime getCreatedAt() {
    return this.createdAt;
  }

  public Integer getId() {
    return this.id;
  }

  public Integer getJobId() {
    return this.jobId;
  }

  public String getLabel() {
    return this.label;
  }

  public Links getLinks() {
    return this.links;
  }

  public String getType() {
    return this.type;
  }

  public OffsetDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setJobId(Integer jobId) {
    this.jobId = jobId;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public void setLinks(Links links) {
    this.links = links;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
