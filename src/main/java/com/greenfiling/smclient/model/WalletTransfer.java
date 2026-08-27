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