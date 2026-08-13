package com.greenfiling.smclient;

import com.google.gson.reflect.TypeToken;
import com.greenfiling.smclient.internal.ApiClient;
import com.greenfiling.smclient.model.Agency;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.internal.FilterBase;

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

  @Override
  @SuppressWarnings("unchecked")
  public Index<Agency> getNext(Index<Agency> index) throws Exception {
    return (Index<Agency>) toIndex(doGetNext(index));
  }

  // GET /agencies
  @Override
  @SuppressWarnings("unchecked")
  public Index<Agency> index(FilterBase filter) throws Exception {
    return (Index<Agency>) toIndex(doIndexRequest(filter));
  }

  // GET /agencies/:id
  @Override
  @SuppressWarnings("unchecked")
  public Show<Agency> show(Integer id) throws Exception {
    return (Show<Agency>) toShow(doShowRequest(id));
  }
}