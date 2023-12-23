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
package com.greenfiling.smclient.model.exchange;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import com.greenfiling.smclient.model.internal.FilterBase;
import com.greenfiling.smclient.model.internal.JobBase;

public class SupplierCostFilter extends FilterBase {

  public static Integer SERVICE_LEVEL_ROUTINE = 1;
  public static Integer SERVICE_LEVEL_RUSH = 2;

  private Integer jobType;
  private Integer serviceLevel;
  private Integer pageCount;
  private ArrayList<String> zipCodes = null;
  private Integer courtId;
  private OffsetDateTime updatedSince;

  public Integer getCourtId() {
    return courtId;
  }

  @Override
  public ArrayList<FilterPair> getFilters() {
    ArrayList<FilterPair> pairs = super.getFilters();

    if (getJobType() != null) {
      pairs.add(new FilterPair("job_type_id", String.valueOf(getJobType())));
    }
    if (getServiceLevel() != null) {
      pairs.add(new FilterPair("sla_id", String.valueOf(getServiceLevel())));
    }
    if (getPageCount() != null) {
      pairs.add(new FilterPair("page_count", String.valueOf(getPageCount())));
    }
    pairs.addAll(addListFilter("zipcodes[]", getZipCodes()));
    if (getCourtId() != null) {
      pairs.add(new FilterPair("court_id", String.valueOf(getCourtId())));
    }
    return pairs;
  }

  public Integer getJobType() {
    return jobType;
  }

  public Integer getPageCount() {
    return pageCount;
  }

  public Integer getServiceLevel() {
    return serviceLevel;
  }

  public OffsetDateTime getUpdatedSince() {
    return updatedSince;
  }

  public ArrayList<String> getZipCodes() {
    if (zipCodes == null) {
      zipCodes = new ArrayList<String>();
    }
    return zipCodes;
  }

  public void setCourtId(Integer courtId) {
    this.courtId = courtId;
  }

  /**
   * Sets the job type
   * <P>
   * This field uses pre-defined values. Allowed values are:
   * <UL>
   * <LI>{@link JobBase#JOB_TYPE_SOP}</LI>
   * <LI>{@link JobBase#JOB_TYPE_CCD}</LI>
   * </UL>
   *
   * @param jobType
   *          The job type (see method-level doc)
   */
  public void setJobType(Integer jobType) {
    this.jobType = jobType;
  }

  public void setPageCount(Integer pageCount) {
    this.pageCount = pageCount;
  }

  /**
   * Sets the service level
   * <P>
   * This field uses pre-defined values. Allowed values are:
   * <UL>
   * <LI>{@link #SERVICE_LEVEL_ROUTINE}</LI>
   * <LI>{@link #SERVICE_LEVEL_RUSH}</LI>
   * </UL>
   *
   * @param serviceLevel
   *          The service level (see method-level doc)
   */
  public void setServiceLevel(Integer serviceLevel) {
    this.serviceLevel = serviceLevel;
  }

  public void setUpdatedSince(OffsetDateTime updatedSince) {
    this.updatedSince = updatedSince;
  }

  public void setZipCodes(ArrayList<String> zipCodes) {
    this.zipCodes = zipCodes;
  }
}
