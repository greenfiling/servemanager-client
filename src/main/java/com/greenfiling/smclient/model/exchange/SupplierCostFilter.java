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

public class SupplierCostFilter extends FilterBase {

  public static String JOB_TYPE_SOP = "1";
  public static String JOB_TYPE_CCD = "2";

  public static String SERVICE_LEVEL_ROUTINE = "1";
  public static String SERVICE_LEVEL_RUSH = "2";

  private String jobType;
  private String serviceLevel;
  private ArrayList<String> zipCodes = null;
  private OffsetDateTime updatedSince;

  public ArrayList<FilterPair> getFilters() {
    ArrayList<FilterPair> pairs = super.getFilters();

    if (getJobType() != null) {
      pairs.add(new FilterPair("job_type_id", getJobType()));
    }
    if (getServiceLevel() != null) {
      pairs.add(new FilterPair("sla_id", getServiceLevel()));
    }
    pairs.addAll(addListFilter("zipcodes[]", getZipCodes()));
    return pairs;
  }

  public String getJobType() {
    return jobType;
  }

  public String getServiceLevel() {
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

  /**
   * Sets the job type
   * <P>
   * This field uses pre-defined values. Allowed values are:
   * <UL>
   * <LI>{@link #JOB_TYPE_SOP}</LI>
   * <LI>{@link #JOB_TYPE_CCD}</LI>
   * </UL>
   *
   */
  public void setJobType(String jobType) {
    this.jobType = jobType;
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
   */
  public void setServiceLevel(String serviceLevel) {
    this.serviceLevel = serviceLevel;
  }

  public void setUpdatedSince(OffsetDateTime updatedSince) {
    this.updatedSince = updatedSince;
  }

  public void setZipCodes(ArrayList<String> zipCodes) {
    this.zipCodes = zipCodes;
  }
}
