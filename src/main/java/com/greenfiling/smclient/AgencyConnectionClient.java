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

  // POST /agency_connections
  @Override
  @SuppressWarnings("unchecked")
  public Show<AgencyConnection> create(AgencyConnectionBase record) throws Exception {
    AgencyConnectionSubmit submitRecord = (record instanceof AgencyConnectionSubmit) ? (AgencyConnectionSubmit) record
        : new AgencyConnectionSubmit((AgencyConnection) record);
    return (Show<AgencyConnection>) toShow(doCreateRequest(submitRecord));
  }

  // GET /agency_connections/:id
  @Override
  @SuppressWarnings("unchecked")
  public Show<AgencyConnection> show(Integer id) throws Exception {
    return (Show<AgencyConnection>) toShow(doShowRequest(id));
  }
}