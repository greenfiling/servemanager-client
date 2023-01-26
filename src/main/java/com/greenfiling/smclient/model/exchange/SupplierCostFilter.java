package com.greenfiling.smclient.model.exchange;

import java.util.ArrayList;

import com.greenfiling.smclient.model.internal.FilterBase;

public class SupplierCostFilter extends FilterBase {

  public enum JobType {
    SOP("1"), CCD("2");

    public String urlCode;

    JobType(String urlCode) {
      this.urlCode = urlCode;
    }

  }

  public enum ServiceLevel {
    ROUTINE("1"), RUSH("2");

    public String urlCode;

    ServiceLevel(String urlCode) {
      this.urlCode = urlCode;
    }
  }

  private JobType jobType;
  private ServiceLevel serviceLevel;

  public ArrayList<FilterPair> getFilters() {
    ArrayList<FilterPair> pairs = super.getFilters();

    if (getJobType() != null) {
      pairs.add(new FilterPair("job_type_id", getJobType().urlCode));
    }
    if (getServiceLevel() != null) {
      pairs.add(new FilterPair("sla_id", getServiceLevel().urlCode));
    }
    return pairs;
  }

  public JobType getJobType() {
    return jobType;
  }

  public ServiceLevel getServiceLevel() {
    return serviceLevel;
  }

  public void setJobType(JobType jobType) {
    this.jobType = jobType;
  }

  public void setServiceLevel(ServiceLevel serviceLevel) {
    this.serviceLevel = serviceLevel;
  }
}
