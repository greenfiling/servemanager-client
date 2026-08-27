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

import com.greenfiling.smclient.model.internal.AccountBase;

public class FirmSubmit extends AccountBase {

  // FirmClient is different from other clients in that it takes a "firm" type CREATE object and returns an "account" type READ object
  public static final String TYPE_FIRM_SUBMIT = "firm";

  private String email;
  private String phone;
  private String registrationPracticeManagementSoftware;
  private Owner ownerAttributes;
  private Address addressAttributes;

  public FirmSubmit() {
    // overwrite "account" type from base
    setType(TYPE_FIRM_SUBMIT);
  }

  public FirmSubmit(Account account) {
    // overwrite "account" type from base
    setType(TYPE_FIRM_SUBMIT);
    setCompanyName(account.getCompanyName());
    setEmail(account.getEmail());
  }

  public Address getAddressAttributes() {
    return this.addressAttributes;
  }

  public String getEmail() {
    return this.email;
  }

  public Owner getOwnerAttributes() {
    return this.ownerAttributes;
  }

  public String getPhone() {
    return this.phone;
  }

  public String getRegistrationPracticeManagementSoftware() {
    return this.registrationPracticeManagementSoftware;
  }

  public void setAddressAttributes(Address addressAttributes) {
    this.addressAttributes = addressAttributes;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setOwnerAttributes(Owner ownerAttributes) {
    this.ownerAttributes = ownerAttributes;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setRegistrationPracticeManagementSoftware(String registrationPracticeManagementSoftware) {
    this.registrationPracticeManagementSoftware = registrationPracticeManagementSoftware;
  }
}