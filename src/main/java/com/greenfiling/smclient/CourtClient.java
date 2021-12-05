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
import com.greenfiling.smclient.model.Court;
import com.greenfiling.smclient.model.CourtSubmit;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.internal.CourtBase;
import com.greenfiling.smclient.model.internal.FilterBase;

public class CourtClient extends ApiClient<CourtBase, Court, CourtSubmit> {
  public static final String ENDPOINT = "courts";

  public CourtClient(ApiHandle handle) {
    super(handle);
    setEndpoint(ENDPOINT);

    // @formatter:off
    setShowType(new TypeToken<Show<Court>>() {}.getType());
    setIndexType(new TypeToken<Index<Court>>() {}.getType());
    // @formatter:on
  }

  @Override
  @SuppressWarnings("unchecked")
  public Show<Court> create(CourtBase record) throws Exception {
    CourtSubmit submitRecord = (record instanceof CourtSubmit) ? (CourtSubmit) record : new CourtSubmit((Court) record);
    return (Show<Court>) toShow(doCreateRequest(submitRecord));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Index<Court> getNext(Index<Court> index) throws Exception {
    return (Index<Court>) toIndex(doGetNext(index));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Index<Court> index(FilterBase filter) throws Exception {
    return (Index<Court>) toIndex(doIndexRequest(filter));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Show<Court> show(Integer id) throws Exception {
    return (Show<Court>) toShow(doShowRequest(id));
  }

}
