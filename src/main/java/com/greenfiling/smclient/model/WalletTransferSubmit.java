package com.greenfiling.smclient.model;

import com.greenfiling.smclient.model.internal.WalletTransferBase;

public class WalletTransferSubmit extends WalletTransferBase {
  public static final String TYPE = "wallet_transfer";

  public WalletTransferSubmit() {
    super();
    setType(TYPE);
  }

  public WalletTransferSubmit(WalletTransfer walletTransfer) {
    super();
    setType(TYPE);
    setJobId(walletTransfer.getJobId());
    setProcessServerCompanyId(walletTransfer.getProcessServerCompanyId());
  }
}