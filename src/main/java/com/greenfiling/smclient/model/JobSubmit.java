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

import com.greenfiling.smclient.model.internal.JobBase;

public class JobSubmit extends JobBase {
  private Recipient recipientAttributes;
  private Integer clientCompanyId;
  private Integer clientContactId;
  private Integer processServerCompanyId;
  private Integer processServerContactId;
  private Integer employeeProcessServerId;
  private Integer courtCaseId;
  private ArrayList<Address> addressesAttributes;
  private ArrayList<ServiceDocument> documentsToBeServedAttributes;
  private ArrayList<Attachment> miscAttachmentsAttributes;
  private String attorneyName;
  private String attorneyEmail;
  private String sbn;

  public JobSubmit() {
    super();
  }

  public JobSubmit(Job job) {
    super(job);

    // These field names are different from the read version of the object
    setRecipientAttributes(job.getRecipient());
    setAddressesAttributes(job.getAddresses());
    setDocumentsToBeServedAttributes(job.getDocumentsToBeServed());
    setMiscAttachmentsAttributes(job.getMiscAttachments());

    // During create, these fields are sent as IDs instead of full objects
    if (job.getClientCompany() != null) {
      setClientCompanyId(job.getClientCompany().getId());
    }
    if (job.getClientContact() != null) {
      setClientContactId(job.getClientContact().getId());
    }
    if (job.getProcessServerCompany() != null) {
      setProcessServerCompanyId(job.getProcessServerCompany().getId());
    }
    if (job.getProcessServerContact() != null) {
      setProcessServerContactId(job.getProcessServerContact().getId());
    }
    if (job.getEmployeeProcessServer() != null) {
      setEmployeeProcessServerId(job.getEmployeeProcessServer().getId());
    }
    if (job.getCourtCase() != null) {
      setCourtCaseId(job.getCourtCase().getId());
    }
    setAttorneyName(job.getAttorneyName());
    setAttorneyEmail(job.getAttorneyEmail());
    setSbn(job.getSbn());
  }

  public ArrayList<Address> getAddressesAttributes() {
    return this.addressesAttributes;
  }

  public String getAttorneyEmail() {
    return attorneyEmail;
  }

  public String getAttorneyName() {
    return attorneyName;
  }

  public Integer getClientCompanyId() {
    return this.clientCompanyId;
  }

  public Integer getClientContactId() {
    return this.clientContactId;
  }

  public Integer getCourtCaseId() {
    return this.courtCaseId;
  }

  public ArrayList<ServiceDocument> getDocumentsToBeServedAttributes() {
    return this.documentsToBeServedAttributes;
  }

  public Integer getEmployeeProcessServerId() {
    return this.employeeProcessServerId;
  }

  public ArrayList<Attachment> getMiscAttachmentsAttributes() {
    return this.miscAttachmentsAttributes;
  }

  public Integer getProcessServerCompanyId() {
    return this.processServerCompanyId;
  }

  public Integer getProcessServerContactId() {
    return this.processServerContactId;
  }

  public Recipient getRecipientAttributes() {
    return recipientAttributes;
  }

  public String getSbn() {
    return sbn;
  }

  public void setAddressesAttributes(ArrayList<Address> addressesAttributes) {
    this.addressesAttributes = addressesAttributes;
  }

  public void setAttorneyEmail(String attorneyEmail) {
    this.attorneyEmail = attorneyEmail;
  }

  public void setAttorneyName(String attorneyName) {
    this.attorneyName = attorneyName;
  }

  public void setClientCompanyId(Integer clientCompanyId) {
    this.clientCompanyId = clientCompanyId;
  }

  public void setClientContactId(Integer clientContactId) {
    this.clientContactId = clientContactId;
  }

  public void setCourtCaseId(Integer courtCaseId) {
    this.courtCaseId = courtCaseId;
  }

  public void setDocumentsToBeServedAttributes(ArrayList<ServiceDocument> documentsToBeServedAttributes) {
    this.documentsToBeServedAttributes = documentsToBeServedAttributes;
  }

  public void setEmployeeProcessServerId(Integer employeeProcessServerId) {
    this.employeeProcessServerId = employeeProcessServerId;
  }

  public void setMiscAttachmentsAttributes(ArrayList<Attachment> miscAttachmentsAttributes) {
    this.miscAttachmentsAttributes = miscAttachmentsAttributes;
  }

  public void setProcessServerCompanyId(Integer processServerCompanyId) {
    this.processServerCompanyId = processServerCompanyId;
  }

  public void setProcessServerContactId(Integer processServerContactId) {
    this.processServerContactId = processServerContactId;
  }

  public void setRecipientAttributes(Recipient recipientAttributes) {
    this.recipientAttributes = recipientAttributes;
  }

  public void setSbn(String sbn) {
    this.sbn = sbn;
  }

}
