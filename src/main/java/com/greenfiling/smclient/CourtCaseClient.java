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
import com.greenfiling.smclient.internal.ApiClient;
import com.greenfiling.smclient.model.CourtCase;
import com.greenfiling.smclient.model.CourtCaseSubmit;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.internal.CourtCaseBase;
import com.greenfiling.smclient.model.internal.FilterBase;

public class CourtCaseClient extends ApiClient<CourtCaseBase, CourtCase, CourtCaseSubmit> {
  public static final String ENDPOINT = "court_cases";

  public CourtCaseClient(ApiHandle handle) {
    super(handle);
    setEndpoint(ENDPOINT);

    // @formatter:off
    setShowType(new TypeToken<Show<CourtCase>>() {}.getType());
    setIndexType(new TypeToken<Index<CourtCase>>() {}.getType());
    // @formatter:on
  }

  @Override
  @SuppressWarnings("unchecked")
  public Show<CourtCase> create(CourtCaseBase record) throws Exception {
    CourtCaseSubmit submitRecord = (record instanceof CourtCaseSubmit) ? (CourtCaseSubmit) record : new CourtCaseSubmit((CourtCase) record);
    return (Show<CourtCase>) toShow(doCreateRequest(submitRecord));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Index<CourtCase> getNext(Index<CourtCase> index) throws Exception {
    return (Index<CourtCase>) toIndex(doGetNext(index));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Index<CourtCase> index(FilterBase filter) throws Exception {
    return (Index<CourtCase>) toIndex(doIndexRequest(filter));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Show<CourtCase> show(Integer id) throws Exception {
    return (Show<CourtCase>) toShow(doShowRequest(id));
  }

}
