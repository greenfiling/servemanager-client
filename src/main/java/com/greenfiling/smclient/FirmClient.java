package com.greenfiling.smclient;

import com.google.gson.reflect.TypeToken;
import com.greenfiling.smclient.internal.ApiClient;
import com.greenfiling.smclient.model.Firm;
import com.greenfiling.smclient.model.FirmSubmit;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.internal.FirmBase;

public class FirmClient extends ApiClient<FirmBase, Firm, FirmSubmit> {
  public static final String ENDPOINT = "infotrack_exchange/firms";

  public FirmClient(ApiHandle handle) {
    super(handle);
    setEndpoint(ENDPOINT);

  // @formatter:off
  setShowType(new TypeToken<Show<Firm>>() {}.getType());
  setIndexType(new TypeToken<Index<Firm>>() {}.getType());
  // @formatter:on
  }

}
