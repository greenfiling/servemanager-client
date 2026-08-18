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

  /**
   * Searches agencies by ZIP code, full address, email, or company name. This is the endpoint that answers "who can serve this address, and what do
   * they charge". Results are ordered by tier, then by age, and agencies marked do not use are excluded unless you look them up explicitly by id.
   * 
   * @param filter
   *          - com.greenfiling.smclient.model.exchange.AgencyFilter
   */
  @Override
  @SuppressWarnings("unchecked")
  public Index<Agency> index(FilterBase filter) throws Exception {
    return (Index<Agency>) toIndex(doIndexRequest(filter));
  }

  /**
   * Reads one agency profile by agency's account id.
   */
  @Override
  @SuppressWarnings("unchecked")
  public Show<Agency> show(Integer id) throws Exception {
    return (Show<Agency>) toShow(doShowRequest(id));
  }
}