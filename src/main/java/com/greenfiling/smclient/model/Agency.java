/**
 * Copyright 2026 Green Filing, LLC
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

public class Agency {
  public static final String TYPE = "exchange_profile";

  private Links links;
  private String type;
  private Integer id;
  private String companyName;
  private Boolean infotrackExchangeReady;
  private AgencyExchangeProfile exchangeProfile;
  private Double distanceInMiles;
  private Integer jobsInLocation;
  private Integer nearbyJobsLastNinetyDays;
  private Integer monthlyJobsQuota;
  private Double standardPricing;
  private Double defaultStandardPricing;
  private Double nationalStandardPricing;
  private Double rushPricing;
  private Double defaultRushPricing;
  private Double nationalRushPricing;
  private Integer rushDuration; // Hours
  private Boolean nationalCoverage;
  private Boolean localCoverage;
  private ArrayList<String> tags; // closest, great_value, choose_for_me, high_demand, plus one of agency_with_coverage or agency_without_coverage
  private ArrayList<Address> addresses;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  public ArrayList<Address> getAddresses() {
    return this.addresses;
  }

  public String getCompanyName() {
    return this.companyName;
  }

  public OffsetDateTime getCreatedAt() {
    return this.createdAt;
  }

  public Double getDefaultRushPricing() {
    return this.defaultRushPricing;
  }

  public Double getDefaultStandardPricing() {
    return this.defaultStandardPricing;
  }

  public Double getDistanceInMiles() {
    return this.distanceInMiles;
  }

  public AgencyExchangeProfile getExchangeProfile() {
    return this.exchangeProfile;
  }

  public Integer getId() {
    return this.id;
  }

  public Boolean getInfotrackExchangeReady() {
    return this.infotrackExchangeReady;
  }

  public Integer getJobsInLocation() {
    return this.jobsInLocation;
  }

  public Links getLinks() {
    return this.links;
  }

  public Boolean getLocalCoverage() {
    return this.localCoverage;
  }

  public Integer getMonthlyJobsQuota() {
    return this.monthlyJobsQuota;
  }

  public Boolean getNationalCoverage() {
    return this.nationalCoverage;
  }

  public Double getNationalRushPricing() {
    return this.nationalRushPricing;
  }

  public Double getNationalStandardPricing() {
    return this.nationalStandardPricing;
  }

  public Integer getNearbyJobsLastNinetyDays() {
    return this.nearbyJobsLastNinetyDays;
  }

  public Integer getRushDuration() {
    return this.rushDuration;
  }

  public Double getRushPricing() {
    return this.rushPricing;
  }

  public Double getStandardPricing() {
    return this.standardPricing;
  }

  public ArrayList<String> getTags() {
    return this.tags;
  }

  public String getType() {
    return this.type;
  }

  public OffsetDateTime getUpdatedAt() {
    return this.updatedAt;
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

  public void setDefaultRushPricing(Double defaultRushPricing) {
    this.defaultRushPricing = defaultRushPricing;
  }

  public void setDefaultStandardPricing(Double defaultStandardPricing) {
    this.defaultStandardPricing = defaultStandardPricing;
  }

  public void setDistanceInMiles(Double distanceInMiles) {
    this.distanceInMiles = distanceInMiles;
  }

  public void setExchangeProfile(AgencyExchangeProfile exchangeProfile) {
    this.exchangeProfile = exchangeProfile;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setInfotrackExchangeReady(Boolean infotrackExchangeReady) {
    this.infotrackExchangeReady = infotrackExchangeReady;
  }

  public void setJobsInLocation(Integer jobsInLocation) {
    this.jobsInLocation = jobsInLocation;
  }

  public void setLinks(Links links) {
    this.links = links;
  }

  public void setLocalCoverage(Boolean localCoverage) {
    this.localCoverage = localCoverage;
  }

  public void setMonthlyJobsQuota(Integer monthlyJobsQuota) {
    this.monthlyJobsQuota = monthlyJobsQuota;
  }

  public void setNationalCoverage(Boolean nationalCoverage) {
    this.nationalCoverage = nationalCoverage;
  }

  public void setNationalRushPricing(Double nationalRushPricing) {
    this.nationalRushPricing = nationalRushPricing;
  }

  public void setNationalStandardPricing(Double nationalStandardPricing) {
    this.nationalStandardPricing = nationalStandardPricing;
  }

  public void setNearbyJobsLastNinetyDays(Integer nearbyJobsLastNinetyDays) {
    this.nearbyJobsLastNinetyDays = nearbyJobsLastNinetyDays;
  }

  public void setRushDuration(Integer rushDuration) {
    this.rushDuration = rushDuration;
  }

  public void setRushPricing(Double rushPricing) {
    this.rushPricing = rushPricing;
  }

  public void setStandardPricing(Double standardPricing) {
    this.standardPricing = standardPricing;
  }

  public void setTags(ArrayList<String> tags) {
    this.tags = tags;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

}