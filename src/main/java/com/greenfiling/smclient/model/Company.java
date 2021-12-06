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

import com.greenfiling.smclient.model.internal.CompanyBase;

public class Company extends CompanyBase {
  private Links links;
  private Integer id;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
  private Boolean collaborating;
  private ArrayList<PhoneNumber> phoneNumbers;
  private ArrayList<EmailAddress> emailAddresses;
  private ArrayList<Address> addresses;
  private ArrayList<Contact> contacts;

  public Company() {
    super();
    setType(TYPE);
  }

  public ArrayList<Address> getAddresses() {
    return this.addresses;
  }

  public Boolean getCollaborating() {
    return this.collaborating;
  }

  public ArrayList<Contact> getContacts() {
    return this.contacts;
  }

  public OffsetDateTime getCreatedAt() {
    return this.createdAt;
  }

  public ArrayList<EmailAddress> getEmailAddresses() {
    return this.emailAddresses;
  }

  public Integer getId() {
    return this.id;
  }

  public Links getLinks() {
    return this.links;
  }

  public ArrayList<PhoneNumber> getPhoneNumbers() {
    return this.phoneNumbers;
  }

  public OffsetDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  public void setAddresses(ArrayList<Address> addresses) {
    this.addresses = addresses;
  }

  public void setCollaborating(Boolean collaborating) {
    this.collaborating = collaborating;
  }

  public void setContacts(ArrayList<Contact> contacts) {
    this.contacts = contacts;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setEmailAddresses(ArrayList<EmailAddress> emailAddresses) {
    this.emailAddresses = emailAddresses;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setLinks(Links link) {
    this.links = link;
  }

  public void setPhoneNumbers(ArrayList<PhoneNumber> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
