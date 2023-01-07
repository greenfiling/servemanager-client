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

package com.greenfiling.smclient.model;

import java.time.OffsetDateTime;

public class Address {
  public static final String TYPE = "address";

  public static final String LABEL_COMPANY = "Company";
  public static final String LABEL_CORPORATE = "Corporate";
  public static final String LABEL_BRANCH = "Branch";
  public static final String LABEL_HOME = "Home";
  public static final String LABEL_BLANK = "";

  private String type;
  private Integer id;
  private String label;
  private String address1;
  private String address2;
  private String city;
  private String state;
  private String postalCode;
  private String county;
  private String lat;
  private String lng;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
  private Boolean primary;

  public Address() {
    super();
    setType(TYPE);
  }

  public String getAddress1() {
    return this.address1;
  }

  public String getAddress2() {
    return this.address2;
  }

  public String getCity() {
    return this.city;
  }

  public String getCounty() {
    return this.county;
  }

  public OffsetDateTime getCreatedAt() {
    return this.createdAt;
  }

  public Integer getId() {
    return this.id;
  }

  public String getLabel() {
    return this.label;
  }

  public String getLat() {
    return this.lat;
  }

  public String getLng() {
    return this.lng;
  }

  public String getPostalCode() {
    return this.postalCode;
  }

  public Boolean getPrimary() {
    return this.primary;
  }

  public String getState() {
    return this.state;
  }

  public String getType() {
    return this.type;
  }

  public OffsetDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setCounty(String county) {
    this.county = county;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Sets the address label
   * <P>
   * This field typically uses pre-defined values, but can use a custom value if desired. The "known" values are
   * <UL>
   * <LI>{@link #LABEL_COMPANY}</LI>
   * <LI>{@link #LABEL_CORPORATE}</LI>
   * <LI>{@link #LABEL_BRANCH}</LI>
   * <LI>{@link #LABEL_HOME}</LI>
   * <LI>{@link #LABEL_BLANK}</LI>
   * </UL>
   *
   * @param label
   *          label
   */
  public void setLabel(String label) {
    this.label = label;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }

  public void setLng(String lng) {
    this.lng = lng;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public void setPrimary(Boolean primary) {
    this.primary = primary;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
