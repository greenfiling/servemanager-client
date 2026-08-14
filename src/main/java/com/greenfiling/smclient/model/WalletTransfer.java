package com.greenfiling.smclient.model;

import com.greenfiling.smclient.model.internal.WalletTransferBase;

public class WalletTransfer extends WalletTransferBase {

  private Integer id;
  private String amount;
  private Integer invoiceId;
  private String fees;

  public String getAmount() {
    return this.amount;
  }

  public String getFees() {
    return this.fees;
  }

  public Integer getId() {
    return this.id;
  }

  public Integer getInvoiceId() {
    return this.invoiceId;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public void setFees(String fees) {
    this.fees = fees;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setInvoiceId(Integer invoiceId) {
    this.invoiceId = invoiceId;
  }

}