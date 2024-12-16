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

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class Invoice {
  public static final String TYPE = "invoice";

  private String type;
  private Integer id;
  private String balanceDue;
  private LocalDate issuedOn;
  private String totalPaid;
  private String total;
  private String terms;
  private LocalDate paidOn;
  private String token;
  private Boolean taxesEnabled;
  private OffsetDateTime lastIssuedAt;
  private Integer clientId;
  @SerializedName(value = "servemanager_job_number")
  private Integer serveManagerJobNumber;
  private String pdfDownloadUrl;
  private OffsetDateTime updatedAt;
  private OffsetDateTime createdAt;
  private ArrayList<LineItem> lineItems;
  private ArrayList<Payment> payments;

  public Invoice() {
    super();
    setType(TYPE);
  }

  public String getBalanceDue() {
    return this.balanceDue;
  }

  public Integer getClientId() {
    return this.clientId;
  }

  public OffsetDateTime getCreatedAt() {
    return this.createdAt;
  }

  public Integer getId() {
    return this.id;
  }

  public LocalDate getIssuedOn() {
    return this.issuedOn;
  }

  public OffsetDateTime getLastIssuedAt() {
    return this.lastIssuedAt;
  }

  public ArrayList<LineItem> getLineItems() {
    return this.lineItems;
  }

  public LocalDate getPaidOn() {
    return this.paidOn;
  }

  public ArrayList<Payment> getPayments() {
    return this.payments;
  }

  public String getPdfDownloadUrl() {
    return this.pdfDownloadUrl;
  }

  public Integer getServeManagerJobNumber() {
    return this.serveManagerJobNumber;
  }

  public Boolean getTaxesEnabled() {
    return this.taxesEnabled;
  }

  public String getTerms() {
    return this.terms;
  }

  public String getToken() {
    return this.token;
  }

  public String getTotal() {
    return this.total;
  }

  public String getTotalPaid() {
    return this.totalPaid;
  }

  public String getType() {
    return this.type;
  }

  public OffsetDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  public void setBalanceDue(String balanceDue) {
    this.balanceDue = balanceDue;
  }

  public void setClientId(Integer clientId) {
    this.clientId = clientId;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setIssuedOn(LocalDate issuedOn) {
    this.issuedOn = issuedOn;
  }

  public void setLastIssuedAt(OffsetDateTime lastIssuedAt) {
    this.lastIssuedAt = lastIssuedAt;
  }

  public void setLineItems(ArrayList<LineItem> lineItems) {
    this.lineItems = lineItems;
  }

  public void setPaidOn(LocalDate paidOn) {
    this.paidOn = paidOn;
  }

  public void setPayments(ArrayList<Payment> payments) {
    this.payments = payments;
  }

  public void setPdfDownloadUrl(String pdfDownloadUrl) {
    this.pdfDownloadUrl = pdfDownloadUrl;
  }

  public void setServeManagerJobNumber(Integer serveManagerJobNumber) {
    this.serveManagerJobNumber = serveManagerJobNumber;
  }

  public void setTaxesEnabled(Boolean taxesEnabled) {
    this.taxesEnabled = taxesEnabled;
  }

  public void setTerms(String terms) {
    this.terms = terms;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public void setTotal(String total) {
    this.total = total;
  }

  public void setTotalPaid(String totalPaid) {
    this.totalPaid = totalPaid;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
