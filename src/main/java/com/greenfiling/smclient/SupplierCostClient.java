package com.greenfiling.smclient;

import com.google.gson.reflect.TypeToken;
import com.greenfiling.smclient.internal.ApiClient;
import com.greenfiling.smclient.model.SupplierCost;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.exchange.SupplierCostFilter;
import com.greenfiling.smclient.model.internal.FilterBase;

public class SupplierCostClient extends ApiClient<SupplierCost, SupplierCost, SupplierCost> {

  /**
   * consider this to be configurable in the event of multiple cost lookups
   */
  public static final String ENDPOINT = "itx/zipcodes";

  private SupplierCostFilter request;

  public SupplierCostClient(ApiHandle handle) {
    super(handle);
    setEndpoint(ENDPOINT);

    // @formatter:off
    setShowType(new TypeToken<Show<SupplierCost>>() {}.getType());
    setIndexType(new TypeToken<Index<SupplierCost>>() {}.getType());
    // @formatter:on
  }

  @Override
  @SuppressWarnings("unchecked")
  public Index<SupplierCost> getNext(Index<SupplierCost> index) throws Exception {
    return (Index<SupplierCost>) toIndex(doGetNext(index));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Index<SupplierCost> index(FilterBase filter) throws Exception {
    return (Index<SupplierCost>) toIndex(doIndexRequest(filter));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Show<SupplierCost> show(Integer id) throws Exception {
    return (Show<SupplierCost>) toShow(doShowRequest(id));
  }

  public Show<SupplierCost> show(SupplierCostFilter request) throws Exception {
    this.request = request;
    return show(request.getZipCode());
  }

  @Override
  protected String makeShowUrl(Integer id) {
    String baseUrl = super.makeShowUrl(id) + "/supplier_costs";
    if (request == null || (request.getJobType() == null && request.getServiceLevel() == null)) {
      return baseUrl;
    }

    return baseUrl + "?" + request.getQueryString();
  }
}
