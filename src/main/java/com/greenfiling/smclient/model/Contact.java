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

public class Contact {
  private Integer id;
  private String firstName;
  private String lastName;
  private String title;
  private String email;
  private String phone;
  private String licenseNumber;
  private String licenseExpiration;
  private String dob;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
  private Boolean primary;

  public OffsetDateTime getCreatedAt() {
    return this.createdAt;
  }

  public String getDob() {
    return this.dob;
  }

  public String getEmail() {
    return this.email;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public Integer getId() {
    return this.id;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getLicenseExpiration() {
    return this.licenseExpiration;
  }

  public String getLicenseNumber() {
    return this.licenseNumber;
  }

  public String getPhone() {
    return this.phone;
  }

  public Boolean getPrimary() {
    return this.primary;
  }

  public String getTitle() {
    return this.title;
  }

  public OffsetDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setLicenseExpiration(String licenseExpiration) {
    this.licenseExpiration = licenseExpiration;
  }

  public void setLicenseNumber(String licenseNumber) {
    this.licenseNumber = licenseNumber;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setPrimary(Boolean primary) {
    this.primary = primary;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
