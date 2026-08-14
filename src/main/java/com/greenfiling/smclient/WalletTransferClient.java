package com.greenfiling.smclient;

import com.google.gson.reflect.TypeToken;
import com.greenfiling.smclient.internal.ApiClient;
import com.greenfiling.smclient.model.WalletTransfer;
import com.greenfiling.smclient.model.WalletTransferSubmit;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.internal.WalletTransferBase;

public class WalletTransferClient extends ApiClient<WalletTransferBase, WalletTransfer, WalletTransferSubmit> {

  public static final String ENDPOINT = "wallet_transfers";

  public WalletTransferClient(ApiHandle handle) {
    super(handle);
    setEndpoint(ENDPOINT);

    // @formatter:off
    setShowType(new TypeToken<Show<WalletTransfer>>() {}.getType());
    setIndexType(new TypeToken<Index<WalletTransfer>>() {}.getType());
    // @formatter:on
  }

  // POST - /wallet_transfers
  @Override
  @SuppressWarnings("unchecked")
  public Show<WalletTransfer> create(WalletTransferBase record) throws Exception {
    WalletTransferSubmit submitRecord = (record instanceof WalletTransferSubmit) ? (WalletTransferSubmit) record
        : new WalletTransferSubmit((WalletTransfer) record);
    return (Show<WalletTransfer>) toShow(doCreateRequest(submitRecord));
  }

}