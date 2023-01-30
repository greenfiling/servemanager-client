package com.greenfiling.smclient;

import com.google.gson.reflect.TypeToken;
import com.greenfiling.smclient.internal.ApiClient;
import com.greenfiling.smclient.model.SupplierCost;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.exchange.SupplierCostFilter;
import com.greenfiling.smclient.model.internal.FilterBase;

/**
 * Index lookup for ZipCode pricing is not supported since we never expect to get more than one ServiceCost object per response.
 * 
 * @author elenasergienko
 *
 */
public class SupplierCostClient extends ApiClient<SupplierCost, SupplierCost, SupplierCost> {

  /**
   * consider this to be configurable in the event of multiple cost lookups
   */
  public static final String ENDPOINT = "itx/zipcodes";

  public SupplierCostClient(ApiHandle handle) {
    super(handle);
    setEndpoint(ENDPOINT);

    // @formatter:off
    setShowType(new TypeToken<Show<SupplierCost>>() {}.getType());
    // @formatter:on
  }

  /**
   * expects {@linkplain SupplierCostFilter} with optional {@linkplain SupplierCostFilter#getJobType} and
   * {@linkplain SupplierCostFilter#getServiceLevel}
   */
  @Override
  @SuppressWarnings("unchecked")
  public Show<SupplierCost> show(Integer id, FilterBase filter) throws Exception {
    return (Show<SupplierCost>) toShow(doShowRequest(id, filter));
  }

  @Override
  protected String makeShowBaseUrl(Integer id) {
    return super.makeShowBaseUrl(id) + "/supplier_costs";
  }
}
