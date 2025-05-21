/**
 * Copyright 2025 Green Filing, LLC
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
import com.greenfiling.smclient.model.RegisteredAgent;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.internal.FilterBase;

public class RegisteredAgentClient extends ApiClient<RegisteredAgent, RegisteredAgent, RegisteredAgent> {
  public static final String ENDPOINT = "registered_agents";

  public RegisteredAgentClient(ApiHandle handle) {
    super(handle);
    setEndpoint(ENDPOINT);

    // @formatter:off
    setShowType(new TypeToken<Show<RegisteredAgent>>() {}.getType());
    setIndexType(new TypeToken<Index<RegisteredAgent>>() {}.getType());
    // @formatter:on
  }

  @Override
  @SuppressWarnings("unchecked")
  public Index<RegisteredAgent> getNext(Index<RegisteredAgent> index) throws Exception {
    return (Index<RegisteredAgent>) toIndex(doGetNext(index));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Index<RegisteredAgent> index(FilterBase filter) throws Exception {
    return (Index<RegisteredAgent>) toIndex(doIndexRequest(filter));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Show<RegisteredAgent> show(Integer id) throws Exception {
    return (Show<RegisteredAgent>) toShow(doShowRequest(id));
  }
}
