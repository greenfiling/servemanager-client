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
import java.util.ArrayList;

public class Account {
  public static final String TYPE = "account";

  private String type;
  private Links links;
  private Integer id;
  private String companyName;
  private String phone;
  private String fax;
  private String email;
  private String website;
  private Integer monthlyJobsQuota;
  private Integer monthJobCount;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
  private ArrayList<Address> addresses = new ArrayList<Address>();

  public Account() {
    super();
    setType(TYPE);
  }

  public ArrayList<Address> getAddresses() {
    return this.addresses;
  }

  public String getCompanyName() {
    return this.companyName;
  }

  public OffsetDateTime getCreatedAt() {
    return this.createdAt;
  }

  public String getEmail() {
    return this.email;
  }

  public String getFax() {
    return this.fax;
  }

  public Integer getId() {
    return this.id;
  }

  public Links getLinks() {
    return this.links;
  }

  public Integer getMonthJobCount() {
    return this.monthJobCount;
  }

  public Integer getMonthlyJobsQuota() {
    return this.monthlyJobsQuota;
  }

  public String getPhone() {
    return this.phone;
  }

  public String getType() {
    return this.type;
  }

  public OffsetDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  public String getWebsite() {
    return this.website;
  }

  public void setAddresses(ArrayList<Address> addresses) {
    this.addresses = addresses;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setLinks(Links links) {
    this.links = links;
  }

  public void setMonthJobCount(Integer monthJobCount) {
    this.monthJobCount = monthJobCount;
  }

  public void setMonthlyJobsQuota(Integer monthlyJobsQuota) {
    this.monthlyJobsQuota = monthlyJobsQuota;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void setWebsite(String website) {
    this.website = website;
  }
}
