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

  /**
   * Connects the calling firm to an agency and returns the process_server_company_id you need in order to route a job to that agency. <br>
   * Calling this repeatedly for the same firm and agency pair creates nothing new. It resolves the existing link, returns the same
   * process_server_company_id with "status": "connected" <br>
   * On the first call back-clones the firm's existing jobs into the new collaboration as a background task.
   * 
   */
  @Override
  @SuppressWarnings("unchecked")
  public Show<AgencyConnection> create(AgencyConnectionBase record) throws Exception {
    AgencyConnectionSubmit submitRecord = (record instanceof AgencyConnectionSubmit) ? (AgencyConnectionSubmit) record : new AgencyConnectionSubmit();

    return (Show<AgencyConnection>) toShow(doCreateRequest(submitRecord));
  }

  /**
   * Reads a agency connection's current status.
   */
  @Override
  @SuppressWarnings("unchecked")
  public Show<AgencyConnection> show(Object id) throws Exception {
    return (Show<AgencyConnection>) toShow(doShowRequest(id));
  }
}