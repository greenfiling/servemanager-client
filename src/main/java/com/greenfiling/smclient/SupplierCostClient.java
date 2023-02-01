/**
 * Copyright 2023 Green Filing, LLC
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
