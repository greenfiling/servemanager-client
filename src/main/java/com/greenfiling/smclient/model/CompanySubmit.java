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

import java.util.ArrayList;

import com.greenfiling.smclient.model.internal.CompanyBase;

public class CompanySubmit extends CompanyBase {
  private ArrayList<PhoneNumber> phoneNumbersAttributes;
  private ArrayList<EmailAddress> emailAddressesAttributes;
  private ArrayList<Address> addressesAttributes;
  private ArrayList<Contact> contactsAttributes;

  public CompanySubmit() {
    super();
  }

  public CompanySubmit(Company company) {
    super(company);

    // During create, these fields are sent as IDs instead of full objects
    setPhoneNumbersAttributes(company.getPhoneNumbers());
    setEmailAddressesAttributes(company.getEmailAddresses());
    setAddressesAttributes(company.getAddresses());
    setContactsAttributes(company.getContacts());
  }

  public ArrayList<Address> getAddressesAttributes() {
    return this.addressesAttributes;
  }

  public ArrayList<Contact> getContactsAttributes() {
    return this.contactsAttributes;
  }

  public ArrayList<EmailAddress> getEmailAddressesAttributes() {
    return this.emailAddressesAttributes;
  }

  public ArrayList<PhoneNumber> getPhoneNumbersAttributes() {
    return this.phoneNumbersAttributes;
  }

  public void setAddressesAttributes(ArrayList<Address> addressesAttributes) {
    this.addressesAttributes = addressesAttributes;
  }

  public void setContactsAttributes(ArrayList<Contact> contactsAttributes) {
    this.contactsAttributes = contactsAttributes;
  }

  public void setEmailAddressesAttributes(ArrayList<EmailAddress> emailAddressesAttributes) {
    this.emailAddressesAttributes = emailAddressesAttributes;
  }

  public void setPhoneNumbersAttributes(ArrayList<PhoneNumber> phoneNumbersAttributes) {
    this.phoneNumbersAttributes = phoneNumbersAttributes;
  }

}
