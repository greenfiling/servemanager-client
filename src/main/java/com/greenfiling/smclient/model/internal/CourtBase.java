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

import com.google.gson.annotations.SerializedName;

public abstract class CourtBase {
  public static final String TYPE = "court";

  private String type;
  private String branchName;
  private String county;
  @SerializedName(value = "default")
  private Boolean isDefault; // This is "default" in the json, but that's a reserved word in java

  public CourtBase() {
    super();
    setType(TYPE);
  }

  public CourtBase(CourtBase court) {
    super();
    setType(TYPE);

    setBranchName(court.getBranchName());
    setCounty(court.getCounty());
    setIsDefault(court.getIsDefault());
  }

  public String getBranchName() {
    return this.branchName;
  }

  public String getCounty() {
    return this.county;
  }

  public Boolean getIsDefault() {
    return this.isDefault;
  }

  public String getType() {
    return this.type;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }

  public void setCounty(String county) {
    this.county = county;
  }

  public void setIsDefault(Boolean isDefault) {
    this.isDefault = isDefault;
  }

  public void setType(String type) {
    this.type = type;
  }

}
