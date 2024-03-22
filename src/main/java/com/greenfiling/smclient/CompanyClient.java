/**
 * Copyright 2024 Green Filing, LLC
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
import com.greenfiling.smclient.model.Company;
import com.greenfiling.smclient.model.CompanySubmit;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.internal.CompanyBase;
import com.greenfiling.smclient.model.internal.FilterBase;

public class CompanyClient extends ApiClient<CompanyBase, Company, CompanySubmit> {
  public static final String ENDPOINT = "companies";

  public CompanyClient(ApiHandle handle) {
    super(handle);
    setEndpoint(ENDPOINT);

    // @formatter:off
    setShowType(new TypeToken<Show<Company>>() {}.getType());
    setIndexType(new TypeToken<Index<Company>>() {}.getType());
    // @formatter:on
  }

  @Override
  @SuppressWarnings("unchecked")
  public Show<Company> create(CompanyBase record) throws Exception {
    CompanySubmit submitRecord = (record instanceof CompanySubmit) ? (CompanySubmit) record : new CompanySubmit((Company) record);
    return (Show<Company>) toShow(doCreateRequest(submitRecord));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Index<Company> getNext(Index<Company> index) throws Exception {
    return (Index<Company>) toIndex(doGetNext(index));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Index<Company> index(FilterBase filter) throws Exception {
    return (Index<Company>) toIndex(doIndexRequest(filter));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Show<Company> show(Integer id) throws Exception {
    return (Show<Company>) toShow(doShowRequest(id));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Show<Company> update(Integer id, CompanyBase record) throws Exception {
    CompanySubmit submitRecord = (record instanceof CompanySubmit) ? (CompanySubmit) record : new CompanySubmit((Company) record);
    return (Show<Company>) toShow(doUpdateRequest(id, submitRecord));
  }

}
