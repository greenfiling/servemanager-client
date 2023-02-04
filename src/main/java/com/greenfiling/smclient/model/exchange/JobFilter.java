/**
 * Copyright 2021-2023 Green Filing, LLC
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

package com.greenfiling.smclient.model.exchange;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.greenfiling.smclient.model.internal.FilterBase;

public class JobFilter extends FilterBase {
  public static final String AFFIDAVIT_STATUS_NONE = "none";
  public static final String AFFIDAVIT_STATUS_CREATED = "created";

  public static final String INVOICE_STATUS_NONE = "none";
  public static final String INVOICE_STATUS_DRAFT = "draft";
  public static final String INVOICE_STATUS_ISSUED = "issued";
  public static final String INVOICE_STATUS_PAID = "paid";

  public static final String SERVICE_STATUS_NOT_SET = "";
  public static final String SERVICE_STATUS_ATTEMPTED = "Attempted";
  public static final String SERVICE_STATUS_NON_SERVICE = "Non-Service";
  public static final String SERVICE_STATUS_SERVED = "Served";

  public static final String JOB_STATUS_NOT_SET = "";
  public static final String JOB_STATUS_CANCELED = com.greenfiling.smclient.model.Job.JOB_STATUS_CANCELED;
  public static final String JOB_STATUS_FILED = com.greenfiling.smclient.model.Job.JOB_STATUS_FILED;
  public static final String JOB_STATUS_ON_HOLD = com.greenfiling.smclient.model.Job.JOB_STATUS_ON_HOLD;
  public static final String JOB_STATUS_SKIP_TRACE = com.greenfiling.smclient.model.Job.JOB_STATUS_SKIP_TRACE;

  private Integer courtCaseId = null;
  private Integer companyId = null;
  private Integer server = null;
  private ArrayList<String> affidavitStatus = null;
  private ArrayList<String> invoiceStatus = null;
  private ArrayList<String> attemptCount = null;
  private ArrayList<String> serviceStatus = null;
  private ArrayList<String> jobStatus = null;
  private FilterDateRange dateRange = null;

  public ArrayList<String> getAffidavitStatus() {
    if (this.affidavitStatus == null) {
      this.affidavitStatus = new ArrayList<String>();
    }
    return this.affidavitStatus;
  }

  public ArrayList<String> getAttemptCount() {
    if (this.attemptCount == null) {
      this.attemptCount = new ArrayList<String>();
    }
    return this.attemptCount;
  }

  public Integer getCompanyId() {
    return this.companyId;
  }

  public Integer getCourtCaseId() {
    return this.courtCaseId;
  }

  public FilterDateRange getDateRange() {
    return this.dateRange;
  }

  public ArrayList<FilterPair> getFilters() {
    ArrayList<FilterPair> pairs = super.getFilters();

    if (getCourtCaseId() != null) {
      pairs.add(new FilterPair("court_case_id", getCourtCaseId().toString()));
    }
    if (getCompanyId() != null) {
      pairs.add(new FilterPair("company_id", getCompanyId().toString()));
    }
    if (getServer() != null) {
      pairs.add(new FilterPair("filter[server]", getServer().toString()));
    }

    pairs.addAll(addListFilter("filter[affidavit_status][]", getAffidavitStatus()));

    pairs.addAll(addListFilter("filter[invoice_status][]", getInvoiceStatus()));

    pairs.addAll(addListFilter("filter[attempt_count][]", getAttemptCount()));

    pairs.addAll(addListFilter("filter[service_status][]", getServiceStatus()));

    pairs.addAll(addListFilter("filter[job_status][]", getJobStatus()));

    if (getDateRange() != null) {
      if (getDateRange().getType() != null) {
        pairs.add(new FilterPair("filter[date_range][type]", getDateRange().getType()));
      }
      if (getDateRange().getMin() != null) {
        pairs.add(new FilterPair("filter[date_range][min]", getDateRange().getMin().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
      }
      if (getDateRange().getMax() != null) {
        pairs.add(new FilterPair("filter[date_range][max]", getDateRange().getMax().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
      }
    }

    return pairs;
  }

  public ArrayList<String> getInvoiceStatus() {
    if (this.invoiceStatus == null) {
      this.invoiceStatus = new ArrayList<String>();
    }
    return this.invoiceStatus;
  }

  public ArrayList<String> getJobStatus() {
    if (this.jobStatus == null) {
      this.jobStatus = new ArrayList<String>();
    }
    return this.jobStatus;
  }

  public Integer getServer() {
    return this.server;
  }

  public ArrayList<String> getServiceStatus() {
    if (this.serviceStatus == null) {
      this.serviceStatus = new ArrayList<String>();
    }
    return this.serviceStatus;
  }

  /**
   * Sets the affidavit status filter
   * <P>
   * This field uses pre-defined values. Allowed values are:
   * <UL>
   * <LI>{@link #AFFIDAVIT_STATUS_NONE}</LI>
   * <LI>{@link #AFFIDAVIT_STATUS_CREATED}</LI>
   * </UL>
   * 
   * @param affidavitStatus
   *          affidavit status
   */
  public void setAffidavitStatus(ArrayList<String> affidavitStatus) {
    this.affidavitStatus = affidavitStatus;
  }

  public void setAttemptCount(ArrayList<String> attemptCount) {
    this.attemptCount = attemptCount;
  }

  public void setCompanyId(Integer companyId) {
    this.companyId = companyId;
  }

  public void setCourtCaseId(Integer courtCaseId) {
    this.courtCaseId = courtCaseId;
  }

  public void setDateRange(FilterDateRange dateRange) {
    this.dateRange = dateRange;
  }

  /**
   * Sets the invoice status filter
   * <P>
   * This field uses pre-defined values. Allowed values are:
   * <UL>
   * <LI>{@link #INVOICE_STATUS_NONE}</LI>
   * <LI>{@link #INVOICE_STATUS_DRAFT}</LI>
   * <LI>{@link #INVOICE_STATUS_ISSUED}</LI>
   * <LI>{@link #INVOICE_STATUS_PAID}</LI>
   * </UL>
   * 
   * @param invoiceStatus
   *          invoice status
   */
  public void setInvoiceStatus(ArrayList<String> invoiceStatus) {
    this.invoiceStatus = invoiceStatus;
  }

  /**
   * Sets the job status
   * <P>
   * This field typically uses pre-defined values, but can use a custom value if desired. The "known" values are
   * <UL>
   * <LI>{@link #JOB_STATUS_NOT_SET}</LI>
   * <LI>{@link #JOB_STATUS_CANCELED}</LI>
   * <LI>{@link #JOB_STATUS_FILED}</LI>
   * <LI>{@link #JOB_STATUS_ON_HOLD}</LI>
   * <LI>{@link #JOB_STATUS_SKIP_TRACE}</LI>
   * </UL>
   * 
   * @param jobStatus
   *          job status
   */
  public void setJobStatus(ArrayList<String> jobStatus) {
    this.jobStatus = jobStatus;
  }

  public void setServer(Integer server) {
    this.server = server;
  }

  /**
   * Sets the service status filter
   * <P>
   * This field uses pre-defined values. Allowed values are:
   * <UL>
   * <LI>{@link #SERVICE_STATUS_NOT_SET}</LI>
   * <LI>{@link #SERVICE_STATUS_ATTEMPTED}</LI>
   * <LI>{@link #SERVICE_STATUS_NON_SERVICE}</LI>
   * <LI>{@link #SERVICE_STATUS_SERVED}</LI>
   * </UL>
   * 
   * @param serviceStatus
   *          service status
   */
  public void setServiceStatus(ArrayList<String> serviceStatus) {
    this.serviceStatus = serviceStatus;
  }
}
