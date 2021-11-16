/**
 * Copyright 2021 Green Filing, LLC
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
import com.greenfiling.smclient.model.Employee;
import com.greenfiling.smclient.model.exchange.FilterBase;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;

public class EmployeeClient extends ApiClient<Employee, Employee, Employee> {
  public static final String ENDPOINT = "employees";

  public EmployeeClient(ApiHandle handle) {
    super(handle);
    setEndpoint(ENDPOINT);

    // @formatter:off
    setShowType(new TypeToken<Show<Employee>>() {}.getType());
    setIndexType(new TypeToken<Index<Employee>>() {}.getType());
    // @formatter:on
  }

  @Override
  @SuppressWarnings("unchecked")
  public Index<Employee> getNext(Index<Employee> index) throws Exception {
    return (Index<Employee>) toIndex(doGetNext(index));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Index<Employee> index(FilterBase filter) throws Exception {
    return (Index<Employee>) toIndex(doIndexRequest(filter));
  }

}
