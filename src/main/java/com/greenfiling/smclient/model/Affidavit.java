/**
 * Copyright 2026 Green Filing, LLC
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

public class Affidavit {
  public static final String TYPE = "affidavit";

  private Links links;
  private String type;
  private Integer id;
  private String affidavitAcceptance;
  private String rejectReason;
  private boolean signed = false;
  private OffsetDateTime acceptedAt;
  private OffsetDateTime rejectedAt;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  public OffsetDateTime getAcceptedAt() {
    return this.acceptedAt;
  }

  public String getAffidavitAcceptance() {
    return this.affidavitAcceptance;
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

  public OffsetDateTime getRejectedAt() {
    return this.rejectedAt;
  }

  public String getRejectReason() {
    return this.rejectReason;
  }

  public boolean getSigned() {
    return this.signed;
  }

  public String getType() {
    return this.type;
  }

  public OffsetDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  public void setAcceptedAt(OffsetDateTime acceptedAt) {
    this.acceptedAt = acceptedAt;
  }

  public void setAffidavitAcceptance(String affidavitAcceptance) {
    this.affidavitAcceptance = affidavitAcceptance;
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

  public void setRejectedAt(OffsetDateTime rejectedAt) {
    this.rejectedAt = rejectedAt;
  }

  public void setRejectReason(String rejectReason) {
    this.rejectReason = rejectReason;
  }

  public void setSigned(boolean signed) {
    this.signed = signed;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}