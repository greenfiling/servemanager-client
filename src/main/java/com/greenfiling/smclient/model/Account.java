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
import java.util.List;

import com.greenfiling.smclient.model.internal.AccountBase;

public class Account extends AccountBase {

  private Links links;
  private Integer id;
  private String phone;
  private String fax;
  private String email;
  private String website;
  private Integer monthlyJobsQuota;
  private Integer monthJobCount;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
  private ArrayList<Address> addresses = new ArrayList<Address>();
  private Boolean canReceiveFunds;
  private String subscriptionPlan;
  private Boolean staff;
  private OffsetDateTime firstJobCreatedAt;
  private OffsetDateTime lastJobCreatedAt;
  private Integer pendingJobsCount;
  private List<String> jobStatuses;
  private Boolean infotrackExchangeReady;

  public Account() {
    super();
  }

  public ArrayList<Address> getAddresses() {
    return this.addresses;
  }

  public Boolean getCanReceiveFunds() {
    return this.canReceiveFunds;
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

  public OffsetDateTime getFirstJobCreatedAt() {
    return this.firstJobCreatedAt;
  }

  public Integer getId() {
    return this.id;
  }

  public Boolean getInfotrackExchangeReady() {
    return this.infotrackExchangeReady;
  }

  public List<String> getJobStatuses() {
    return this.jobStatuses;
  }

  public OffsetDateTime getLastJobCreatedAt() {
    return this.lastJobCreatedAt;
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

  public Integer getPendingJobsCount() {
    return this.pendingJobsCount;
  }

  public String getPhone() {
    return this.phone;
  }

  public Boolean getStaff() {
    return this.staff;
  }

  public String getSubscriptionPlan() {
    return this.subscriptionPlan;
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

  public void setCanReceiveFunds(Boolean canReceiveFunds) {
    this.canReceiveFunds = canReceiveFunds;
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

  public void setFirstJobCreatedAt(OffsetDateTime firstJobCreatedAt) {
    this.firstJobCreatedAt = firstJobCreatedAt;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setInfotrackExchangeReady(Boolean infotrackExchangeReady) {
    this.infotrackExchangeReady = infotrackExchangeReady;
  }

  public void setJobStatuses(List<String> jobStatuses) {
    this.jobStatuses = jobStatuses;
  }

  public void setLastJobCreatedAt(OffsetDateTime lastJobCreatedAt) {
    this.lastJobCreatedAt = lastJobCreatedAt;
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

  public void setPendingJobsCount(Integer pendingJobsCount) {
    this.pendingJobsCount = pendingJobsCount;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setStaff(Boolean staff) {
    this.staff = staff;
  }

  public void setSubscriptionPlan(String subscriptionPlan) {
    this.subscriptionPlan = subscriptionPlan;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void setWebsite(String website) {
    this.website = website;
  }
}
