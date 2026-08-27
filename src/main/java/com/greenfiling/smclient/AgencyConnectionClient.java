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
import com.greenfiling.smclient.model.AgencyConnection;
import com.greenfiling.smclient.model.AgencyConnectionSubmit;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.internal.AgencyConnectionBase;

public class AgencyConnectionClient extends ApiClient<AgencyConnectionBase, AgencyConnection, AgencyConnectionSubmit> {
  public static final String ENDPOINT = "infotrack_exchange/agency_connections";

  public AgencyConnectionClient(ApiHandle handle) {
    super(handle);
    setEndpoint(ENDPOINT);

    // @formatter:off
    setShowType(new TypeToken<Show<AgencyConnection>>() {}.getType());
    setIndexType(new TypeToken<Index<AgencyConnection>>() {}.getType());
    // @formatter:on
  }

  /**
   * Connects the calling firm to an agency and returns the process_server_company_id you need in order to route a job to that agency. <br>
   * Calling this repeatedly for the same firm and agency pair creates nothing new. It resolves the existing link, returns the same
   * process_server_company_id with "status": "connected" <br>
   * On the first call back-clones the firm's existing jobs into the new collaboration as a background task.
   * 
   * @param record
   *          - AgencyConnectionSubmit
   */
  @Override
  @SuppressWarnings("unchecked")
  public Show<AgencyConnection> create(AgencyConnectionBase record) throws Exception {
    AgencyConnectionSubmit submitRecord = (record instanceof AgencyConnectionSubmit) ? (AgencyConnectionSubmit) record : null;

    if (submitRecord == null) {
      throw new Exception("Invalid AgencyConnectionSubmit parameter");
    }

    return (Show<AgencyConnection>) toShow(doCreateRequest(submitRecord));
  }

  /**
   * Reads a agency connection's current status.
   */
  @Override
  @SuppressWarnings("unchecked")
  public Show<AgencyConnection> show(Object id) throws Exception {
    return (Show<AgencyConnection>) toShow(doShowRequest(id));
  }
}