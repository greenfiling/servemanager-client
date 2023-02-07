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
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.internal.FilterBase;

public class SupplierCostClient extends ApiClient<SupplierCost, SupplierCost, SupplierCost> {

  public static final String ENDPOINT = "itx/supplier_costs";

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
}
