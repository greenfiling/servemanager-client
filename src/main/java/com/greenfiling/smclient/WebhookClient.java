/**
 * Copyright 2026 Green Filing, LLC
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
import com.greenfiling.smclient.Exceptions.NoContentException;
import com.greenfiling.smclient.internal.ApiClient;
import com.greenfiling.smclient.model.Webhook;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;

/**
 * @author wiseman
 * @since 1.1.1
 */
public class WebhookClient extends ApiClient<Webhook, Webhook, Webhook> {
  /**
   * API endpoint for web hooks
   */
  public static final String ENDPOINT = "webhooks";

  public WebhookClient(ApiHandle handle) {
    super(handle);
    setEndpoint(ENDPOINT);

    // @formatter:off
    setShowType(new TypeToken<Show<Webhook>>() {}.getType());
    setIndexType(new TypeToken<Index<Webhook>>() {}.getType());
    // @formatter:on
  }

  @Override
  @SuppressWarnings("unchecked")
  public Show<Webhook> create(Webhook record) throws Exception {
    return (Show<Webhook>) toShow(doCreateRequest(record));
  }

  public String delete(String id) throws Exception {
    String response = null;
    try {
      response = doDeleteRequest(id);
    } catch (NoContentException e) {
      // 204 No Content is expected for a successful delete, so we can ignore this exception
    }
    return response;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Index<Webhook> index() throws Exception {
    return (Index<Webhook>) toIndex(doIndexRequest(null));
  }

  public Show<Webhook> show() throws Exception {
    return show(null);
  }

  @Override
  @SuppressWarnings("unchecked")
  public Show<Webhook> update(Object id, Webhook record) throws Exception {
    return (Show<Webhook>) toShow(doUpdateRequest(id, record));
  }
}