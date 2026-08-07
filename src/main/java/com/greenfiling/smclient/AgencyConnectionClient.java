package com.greenfiling.smclient;

import com.google.gson.reflect.TypeToken;
import com.greenfiling.smclient.internal.ApiClient;
import com.greenfiling.smclient.model.AgencyConnection;
import com.greenfiling.smclient.model.AgencyConnectionSubmit;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.internal.AgencyConnectionBase;

public class AgencyConnectionClient extends ApiClient<AgencyConnectionBase, AgencyConnection, AgencyConnectionSubmit> {
  public static final String ENDPOINT = "infotrack_exchange/agency_connections";

  public AgencyConnectionClient(ApiHandle handle) {
    super(handle);
    setEndpoint(ENDPOINT);

    // @formatter:off
    setShowType(new TypeToken<Show<AgencyConnection>>() {}.getType());
    setIndexType(new TypeToken<Index<AgencyConnection>>() {}.getType());
    // @formatter:on
  }
}