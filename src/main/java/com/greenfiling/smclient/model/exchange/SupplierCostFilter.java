package com.greenfiling.smclient.model.exchange;

import java.util.ArrayList;

import com.greenfiling.smclient.model.internal.FilterBase;

public class SupplierCostFilter extends FilterBase {

  public static String JOB_TYPE_SOP = "1";
  public static String JOB_TYPE_CCD = "2";

  public static String SERVICE_LEVEL_ROUTINE = "1";
  public static String SERVICE_LEVEL_RUSH = "2";

  private String jobType;
  private String serviceLevel;

  public ArrayList<FilterPair> getFilters() {
    ArrayList<FilterPair> pairs = super.getFilters();

    if (getJobType() != null) {
      pairs.add(new FilterPair("job_type_id", getJobType()));
    }
    if (getServiceLevel() != null) {
      pairs.add(new FilterPair("sla_id", getServiceLevel()));
    }
    return pairs;
  }

  public String getJobType() {
    return jobType;
  }

  public String getServiceLevel() {
    return serviceLevel;
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
}
