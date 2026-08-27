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

  /**
   * Pays the agency's invoice for a shared job.
   */
  @Override
  @SuppressWarnings("unchecked")
  public Show<WalletTransfer> create(WalletTransferBase record) throws Exception {
    WalletTransferSubmit submitRecord = (record instanceof WalletTransferSubmit) ? (WalletTransferSubmit) record
        : new WalletTransferSubmit((WalletTransfer) record);
    return (Show<WalletTransfer>) toShow(doCreateRequest(submitRecord));
  }

}