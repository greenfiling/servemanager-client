/**
 * Copyright 2021-2024 Green Filing, LLC
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

package com.greenfiling.smclient.model.internal;

import java.time.LocalDate;

import com.greenfiling.smclient.model.Data;

public abstract class JobBase extends Data {
  public static final String TYPE = "job";

  public static final String JOB_STATUS_CANCELED = "Canceled";
  public static final String JOB_STATUS_FILED = "Filed";
  public static final String JOB_STATUS_ON_HOLD = "On Hold";
  public static final String JOB_STATUS_SKIP_TRACE = "Skip Trace";

  public static final Integer JOB_TYPE_SOP = 1;
  public static final Integer JOB_TYPE_CCD = 2;

  private String jobStatus;
  private Integer jobTypeId;
  private String instructionsFromClient;
  private Boolean requireServerAcceptance;
  private String clientJobNumber;
  private String serviceInstructions;
  private LocalDate dueDate;
  private Boolean rush;
  private Integer quotedSupplierCostId;
  private Double quotedRetailPrice;
  private Integer quotedPageCount;
  private Integer clientTransactionRef;
  private String attorneyName;
  private String attorneyEmail;
  private String sbn;
  // custom?

  public JobBase() {
    super();
    setType(TYPE);
  }

  public JobBase(JobBase job) {
    super();
    setType(TYPE);

    setJobStatus(job.getJobStatus());
    setClientJobNumber(job.getClientJobNumber());
    setServiceInstructions(job.getServiceInstructions());
    setDueDate(job.getDueDate());
    setRush(job.getRush());
    setJobTypeId(job.getJobTypeId());
  }

  public String getAttorneyEmail() {
    return attorneyEmail;
  }

  public String getAttorneyName() {
    return attorneyName;
  }

  public String getClientJobNumber() {
    return this.clientJobNumber;
  }

  public Integer getClientTransactionRef() {
    return clientTransactionRef;
  }

  public LocalDate getDueDate() {
    return this.dueDate;
  }

  public String getInstructionsFromClient() {
    return this.instructionsFromClient;
  }

  public String getJobStatus() {
    return this.jobStatus;
  }

  public Integer getJobTypeId() {
    return this.jobTypeId;
  }

  public Integer getQuotedPageCount() {
    return quotedPageCount;
  }

  public Double getQuotedRetailPrice() {
    return quotedRetailPrice;
  }

  public Integer getQuotedSupplierCostId() {
    return quotedSupplierCostId;
  }

  public Boolean getRequireServerAcceptance() {
    return this.requireServerAcceptance;
  }

  public Boolean getRush() {
    return this.rush;
  }

  public String getSbn() {
    return sbn;
  }

  public String getServiceInstructions() {
    return this.serviceInstructions;
  }

  public void setAttorneyEmail(String attorneyEmail) {
    this.attorneyEmail = attorneyEmail;
  }

  public void setAttorneyName(String attorneyName) {
    this.attorneyName = attorneyName;
  }

  public void setClientJobNumber(String clientJobNumber) {
    this.clientJobNumber = clientJobNumber;
  }

  public void setClientTransactionRef(Integer clientTransactionRef) {
    this.clientTransactionRef = clientTransactionRef;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public void setInstructionsFromClient(String instructionsFromClient) {
    this.instructionsFromClient = instructionsFromClient;
  }

  /**
   * Sets the job status
   * <P>
   * This field typically uses pre-defined values, but can use a custom value if desired. The "known" values are
   * <UL>
   * <LI>{@link #JOB_STATUS_CANCELED}</LI>
   * <LI>{@link #JOB_STATUS_FILED}</LI>
   * <LI>{@link #JOB_STATUS_ON_HOLD}</LI>
   * <LI>{@link #JOB_STATUS_SKIP_TRACE}</LI>
   * </UL>
   * 
   * @param jobStatus
   *          job status
   */
  public void setJobStatus(String jobStatus) {
    this.jobStatus = jobStatus;
  }

  public void setJobTypeId(Integer jobTypeId) {
    this.jobTypeId = jobTypeId;
  }

  public void setQuotedPageCount(Integer quoted_page_count) {
    this.quotedPageCount = quoted_page_count;
  }

  public void setQuotedRetailPrice(Double quotedRetailPrice) {
    this.quotedRetailPrice = quotedRetailPrice;
  }

  public void setQuotedSupplierCostId(Integer quotedSupplierCodeId) {
    this.quotedSupplierCostId = quotedSupplierCodeId;
  }

  public void setRequireServerAcceptance(Boolean requireServerAcceptance) {
    this.requireServerAcceptance = requireServerAcceptance;
  }

  public void setRush(Boolean rush) {
    this.rush = rush;
  }

  public void setSbn(String sbn) {
    this.sbn = sbn;
  }

  public void setServiceInstructions(String serviceInstructions) {
    this.serviceInstructions = serviceInstructions;
  }

}
