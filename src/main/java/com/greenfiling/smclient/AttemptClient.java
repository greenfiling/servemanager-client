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
import com.greenfiling.smclient.model.Attempt;
import com.greenfiling.smclient.model.AttemptSubmit;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.internal.AttemptBase;
import com.greenfiling.smclient.model.internal.FilterBase;

public class AttemptClient extends ApiClient<AttemptBase, Attempt, AttemptSubmit> {
  public static final String ENDPOINT = "attempts";

  public AttemptClient(ApiHandle handle) {
    super(handle);
    setEndpoint(ENDPOINT);

    // @formatter:off
    setShowType(new TypeToken<Show<Attempt>>() {}.getType());
    setIndexType(new TypeToken<Index<Attempt>>() {}.getType());
    // @formatter:on
  }

  @Override
  @SuppressWarnings("unchecked")
  public Show<Attempt> create(AttemptBase record) throws Exception {
    AttemptSubmit submitRecord = (record instanceof AttemptSubmit) ? (AttemptSubmit) record : new AttemptSubmit((Attempt) record);
    return (Show<Attempt>) toShow(doCreateRequest(submitRecord));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Index<Attempt> getNext(Index<Attempt> index) throws Exception {
    return (Index<Attempt>) toIndex(doGetNext(index));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Index<Attempt> index(FilterBase filter) throws Exception {
    return (Index<Attempt>) toIndex(doIndexRequest(filter));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Show<Attempt> show(Integer id) throws Exception {
    return (Show<Attempt>) toShow(doShowRequest(id));
  }

}
