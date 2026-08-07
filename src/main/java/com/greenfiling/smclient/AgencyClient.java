package com.greenfiling.smclient;

import com.google.gson.reflect.TypeToken;
import com.greenfiling.smclient.internal.ApiClient;
import com.greenfiling.smclient.model.Agency;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;

public class AgencyClient extends ApiClient<Agency, Agency, Agency> {
  public static final String ENDPOINT = "infotrack_exchange/agencies";

  public AgencyClient(ApiHandle handle) {
    super(handle);
    setEndpoint(ENDPOINT);

  // @formatter:off
  setShowType(new TypeToken<Show<Agency>>() {}.getType());
  setIndexType(new TypeToken<Index<Agency>>() {}.getType());
  // @formatter:on
  }
}