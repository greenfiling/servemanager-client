/**
 * Copyright 2021-2024 Green Filing, LLC
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
import java.util.HashMap;

import com.google.gson.annotations.SerializedName;
import com.greenfiling.smclient.model.internal.JobBase;

public class Job extends JobBase {

  private Links links;
  private Integer id;
  @SerializedName(value = "servemanager_job_number")
  private String serveManagerJobNumber;
  private Boolean assignedByCollaboratingServer;
  private OffsetDateTime archivedAt;
  private String serviceStatus;
  private OffsetDateTime updatedAt;
  private OffsetDateTime createdAt;
  private Integer createdById;
  private Recipient recipient;
  private Integer dupedFromJobId;
  private ArrayList<Integer> dupedToJobIds;
  private OffsetDateTime mailingDate;
  private Integer mailedById;
  private String mailingLocation;
  private Boolean mailingRequired;
  private ServerAcceptance serverAcceptance;
  private Company clientCompany;
  private Contact clientContact;
  private Company processServerCompany;
  private Contact processServerContact;
  private Employee employeeProcessServer;
  private CourtCase courtCase;
  private Invoice invoice;
  private Invoice processServerInvoice;
  private Integer addressesCount;
  private ArrayList<Address> addresses;
  @SerializedName(value = "affidavit_count")
  private Integer documentsCount;
  private ArrayList<Document> documents;
  @SerializedName(value = "document_to_be_served_count")
  private Integer documentsToBeServedCount;
  private Integer documentToBeServedTotalPageCount;
  private ArrayList<ServiceDocument> documentsToBeServed;
  private Integer miscAttachmentsCount;
  private ArrayList<Attachment> miscAttachments;
  @SerializedName(value = "attempt_count")
  private Integer attemptsCount;
  private ArrayList<Attempt> attempts;
  private OffsetDateTime lastAttemptServedAt;
  private String lastAttemptServedAtTimezone;
  private HashMap<String, String> custom; // I have no idea what might be in this field
  private String attorneyName;
  private String attorneyEmail;
  private String sbn;

  public Job() {
    super();
  }

  public ArrayList<Address> getAddresses() {
    return this.addresses;
  }

  public Integer getAddressesCount() {
    return this.addressesCount;
  }

  public OffsetDateTime getArchivedAt() {
    return this.archivedAt;
  }

  public Boolean getAssignedByCollaboratingServer() {
    return this.assignedByCollaboratingServer;
  }

  public ArrayList<Attempt> getAttempts() {
    return this.attempts;
  }

  public Integer getAttemptsCount() {
    return this.attemptsCount;
  }

  public String getAttorneyEmail() {
    return attorneyEmail;
  }

  public String getAttorneyName() {
    return attorneyName;
  }

  public Company getClientCompany() {
    return this.clientCompany;
  }

  public Contact getClientContact() {
    return this.clientContact;
  }

  public CourtCase getCourtCase() {
    return this.courtCase;
  }

  public OffsetDateTime getCreatedAt() {
    return this.createdAt;
  }

  public Integer getCreatedById() {
    return this.createdById;
  }

  public HashMap<String, String> getCustom() {
    return custom;
  }

  public ArrayList<Document> getDocuments() {
    return this.documents;
  }

  public Integer getDocumentsCount() {
    return this.documentsCount;
  }

  public ArrayList<ServiceDocument> getDocumentsToBeServed() {
    return this.documentsToBeServed;
  }

  public Integer getDocumentsToBeServedCount() {
    return this.documentsToBeServedCount;
  }

  public Integer getDocumentToBeServedTotalPageCount() {
    return this.documentToBeServedTotalPageCount;
  }

  public Integer getDupedFromJobId() {
    return this.dupedFromJobId;
  }

  public ArrayList<Integer> getDupedToJobIds() {
    if (this.dupedToJobIds == null) {
      this.dupedToJobIds = new ArrayList<Integer>();
    }
    return this.dupedToJobIds;
  }

  public Employee getEmployeeProcessServer() {
    return this.employeeProcessServer;
  }

  public Integer getId() {
    return this.id;
  }

  public Invoice getInvoice() {
    return this.invoice;
  }

  public OffsetDateTime getLastAttemptServedAt() {
    return this.lastAttemptServedAt;
  }

  public String getLastAttemptServedAtTimezone() {
    return this.lastAttemptServedAtTimezone;
  }

  public Links getLinks() {
    return this.links;
  }

  public Integer getMailedById() {
    return this.mailedById;
  }

  public OffsetDateTime getMailingDate() {
    return this.mailingDate;
  }

  public String getMailingLocation() {
    return this.mailingLocation;
  }

  public Boolean getMailingRequired() {
    return this.mailingRequired;
  }

  public ArrayList<Attachment> getMiscAttachments() {
    return this.miscAttachments;
  }

  public Integer getMiscAttachmentsCount() {
    return this.miscAttachmentsCount;
  }

  public Company getProcessServerCompany() {
    return this.processServerCompany;
  }

  public Contact getProcessServerContact() {
    return this.processServerContact;
  }

  public Invoice getProcessServerInvoice() {
    return this.processServerInvoice;
  }

  public Recipient getRecipient() {
    return this.recipient;
  }

  public String getSbn() {
    return sbn;
  }

  public String getServeManagerJobNumber() {
    return this.serveManagerJobNumber;
  }

  public ServerAcceptance getServerAcceptance() {
    return this.serverAcceptance;
  }

  public String getServiceStatus() {
    return this.serviceStatus;
  }

  public OffsetDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  public void setAddresses(ArrayList<Address> addresses) {
    this.addresses = addresses;
  }

  public void setAddressesCount(Integer addressesCount) {
    this.addressesCount = addressesCount;
  }

  public void setArchivedAt(OffsetDateTime archivedAt) {
    this.archivedAt = archivedAt;
  }

  public void setAssignedByCollaboratingServer(Boolean assignedByCollaboratingServer) {
    this.assignedByCollaboratingServer = assignedByCollaboratingServer;
  }

  public void setAttempts(ArrayList<Attempt> attempts) {
    this.attempts = attempts;
  }

  public void setAttemptsCount(Integer attemptsCount) {
    this.attemptsCount = attemptsCount;
  }

  public void setAttorneyEmail(String attorneyEmail) {
    this.attorneyEmail = attorneyEmail;
  }

  public void setAttorneyName(String attorneyName) {
    this.attorneyName = attorneyName;
  }

  public void setClientCompany(Company clientCompany) {
    this.clientCompany = clientCompany;
  }

  public void setClientContact(Contact clientContact) {
    this.clientContact = clientContact;
  }

  public void setCourtCase(CourtCase courtCase) {
    this.courtCase = courtCase;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setCreatedById(Integer createdById) {
    this.createdById = createdById;
  }

  public void setCustom(HashMap<String, String> custom) {
    this.custom = custom;
  }

  public void setDocuments(ArrayList<Document> documents) {
    this.documents = documents;
  }

  public void setDocumentsCount(Integer documentsCount) {
    this.documentsCount = documentsCount;
  }

  public void setDocumentsToBeServed(ArrayList<ServiceDocument> documentsToBeServed) {
    this.documentsToBeServed = documentsToBeServed;
  }

  public void setDocumentsToBeServedCount(Integer documentsToBeServedCount) {
    this.documentsToBeServedCount = documentsToBeServedCount;
  }

  public void setDocumentToBeServedTotalPageCount(Integer documentToBeServedTotalPageCount) {
    this.documentToBeServedTotalPageCount = documentToBeServedTotalPageCount;
  }

  public void setDupedFromJobId(Integer dupedFromJobId) {
    this.dupedFromJobId = dupedFromJobId;
  }

  public void setDupedToJobIds(ArrayList<Integer> dupedToJobIds) {
    this.dupedToJobIds = dupedToJobIds;
  }

  public void setEmployeeProcessServer(Employee employeeProcessServer) {
    this.employeeProcessServer = employeeProcessServer;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setInvoice(Invoice invoice) {
    this.invoice = invoice;
  }

  public void setLastAttemptServedAt(OffsetDateTime lastAttemptServedAt) {
    this.lastAttemptServedAt = lastAttemptServedAt;
  }

  public void setLastAttemptServedAtTimezone(String lastAttemptServedAtTimezone) {
    this.lastAttemptServedAtTimezone = lastAttemptServedAtTimezone;
  }

  public void setLinks(Links links) {
    this.links = links;
  }

  public void setMailedById(Integer mailedById) {
    this.mailedById = mailedById;
  }

  public void setMailingDate(OffsetDateTime mailingDate) {
    this.mailingDate = mailingDate;
  }

  public void setMailingLocation(String mailingLocation) {
    this.mailingLocation = mailingLocation;
  }

  public void setMailingRequired(Boolean mailingRequired) {
    this.mailingRequired = mailingRequired;
  }

  public void setMiscAttachments(ArrayList<Attachment> miscAttachments) {
    this.miscAttachments = miscAttachments;
  }

  public void setMiscAttachmentsCount(Integer miscAttachmentsCount) {
    this.miscAttachmentsCount = miscAttachmentsCount;
  }

  public void setProcessServerCompany(Company processServerCompany) {
    this.processServerCompany = processServerCompany;
  }

  public void setProcessServerContact(Contact processServerContact) {
    this.processServerContact = processServerContact;
  }

  public void setProcessServerInvoice(Invoice processServerInvoice) {
    this.processServerInvoice = processServerInvoice;
  }

  public void setRecipient(Recipient recipient) {
    this.recipient = recipient;
  }

  public void setSbn(String sbn) {
    this.sbn = sbn;
  }

  public void setServeManagerJobNumber(String serveManagerJobNumber) {
    this.serveManagerJobNumber = serveManagerJobNumber;
  }

  public void setServerAcceptance(ServerAcceptance serverAcceptance) {
    this.serverAcceptance = serverAcceptance;
  }

  public void setServiceStatus(String serviceStatus) {
    this.serviceStatus = serviceStatus;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
