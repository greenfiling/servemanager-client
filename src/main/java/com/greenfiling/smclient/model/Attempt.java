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

import com.google.gson.annotations.SerializedName;
import com.greenfiling.smclient.model.internal.AttemptBase;

public class Attempt extends AttemptBase {
  public static final String TYPE = "attempt";

  private Integer id;
  private Integer jobId;
  private Boolean success;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
  @SerializedName(value = "servemanager_job_number")
  private Integer serveManagerJobNumber;
  private String serviceStatus;
  private String recipientFullDescription;
  private Boolean mobile;
  private Recipient recipient;
  private Employee processServer;
  private Address address;

  public Attempt() {
    super();
    setType(TYPE);
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

  public Integer getJobId() {
    return this.jobId;
  }

  public Boolean getMobile() {
    return this.mobile;
  }

  public Employee getProcessServer() {
    return this.processServer;
  }

  public Recipient getRecipient() {
    return this.recipient;
  }

  public String getRecipientFullDescription() {
    return this.recipientFullDescription;
  }

  public Integer getServeManagerJobNumber() {
    return this.serveManagerJobNumber;
  }

  public String getServiceStatus() {
    return this.serviceStatus;
  }

  public Boolean getSuccess() {
    return this.success;
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

  public void setJobId(Integer jobId) {
    this.jobId = jobId;
  }

  public void setMobile(Boolean mobile) {
    this.mobile = mobile;
  }

  public void setProcessServer(Employee processServer) {
    this.processServer = processServer;
  }

  public void setRecipient(Recipient recipient) {
    this.recipient = recipient;
  }

  public void setRecipientFullDescription(String recipientFullDescription) {
    this.recipientFullDescription = recipientFullDescription;
  }

  public void setServeManagerJobNumber(Integer serveManagerJobNumber) {
    this.serveManagerJobNumber = serveManagerJobNumber;
  }

  public void setServiceStatus(String serviceStatus) {
    this.serviceStatus = serviceStatus;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
