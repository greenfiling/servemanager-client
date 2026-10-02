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
import com.greenfiling.smclient.internal.ApiClient;
import com.greenfiling.smclient.model.Affidavit;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.internal.FilterBase;

public class AffidavitClient extends ApiClient<Affidavit, Affidavit, Affidavit> {
  public static final String ENDPOINT = "affidavits";

  public AffidavitClient(ApiHandle handle) {
    super(handle);
    setEndpoint(ENDPOINT);

    // @formatter:off
    setShowType(new TypeToken<Show<Affidavit>>() {}.getType());
    setIndexType(new TypeToken<Index<Affidavit>>() {}.getType());
    // @formatter:on
  }

  @Override
  @SuppressWarnings("unchecked")
  public Show<Affidavit> create(Affidavit record) throws Exception {
    return (Show<Affidavit>) toShow(doCreateRequest(record));
  }

  /**
   * Lists the affidavits shared with the firm's job, with each one's review state. job_id is the firm's job id. You can filter by document_id or
   * attachment_id instead. Always pass one of the three filters.
   * 
   * @param filter
   *          - com.greenfiling.smclient.model.exchange.AffidavitFilter
   */
  @Override
  @SuppressWarnings("unchecked")
  public Index<Affidavit> index(FilterBase filter) throws Exception {
    return (Index<Affidavit>) toIndex(doIndexRequest(filter));
  }

  /**
   * Reads one affidavit id.
   */
  @Override
  @SuppressWarnings("unchecked")
  public Show<Affidavit> show(Object id) throws Exception {
    return (Show<Affidavit>) toShow(doShowRequest(id));
  }

}