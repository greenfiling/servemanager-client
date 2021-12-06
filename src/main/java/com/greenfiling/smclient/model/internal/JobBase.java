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

package com.greenfiling.smclient.model.internal;

import java.time.LocalDate;

public abstract class JobBase {
  public static final String TYPE = "job";

  public static final String JOB_STATUS_CANCELED = "Canceled";
  public static final String JOB_STATUS_FILED = "Filed";
  public static final String JOB_STATUS_ON_HOLD = "On Hold";
  public static final String JOB_STATUS_SKIP_TRACE = "Skip Trace";

  private String type;
  private String jobStatus;
  private String clientJobNumber;
  private String serviceInstructions;
  private LocalDate dueDate;
  private Boolean rush;
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
  }

  public String getClientJobNumber() {
    return this.clientJobNumber;
  }

  public LocalDate getDueDate() {
    return this.dueDate;
  }

  public String getJobStatus() {
    return this.jobStatus;
  }

  public Boolean getRush() {
    return this.rush;
  }

  public String getServiceInstructions() {
    return this.serviceInstructions;
  }

  public String getType() {
    return this.type;
  }

  public void setClientJobNumber(String clientJobNumber) {
    this.clientJobNumber = clientJobNumber;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
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
   */
  public void setJobStatus(String jobStatus) {
    this.jobStatus = jobStatus;
  }

  public void setRush(Boolean rush) {
    this.rush = rush;
  }

  public void setServiceInstructions(String serviceInstructions) {
    this.serviceInstructions = serviceInstructions;
  }

  public void setType(String type) {
    this.type = type;
  }

}
