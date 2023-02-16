/**
 * Copyright 2021-2023 Green Filing, LLC
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

import static com.greenfiling.smclient.TestHelper.log;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greenfiling.smclient.internal.JsonHandle;
import com.greenfiling.smclient.model.CourtCase;
import com.greenfiling.smclient.model.Links;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;

public class CourtCaseClient_Manual {
  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(CourtCaseClient_Manual.class);

  private static ApiHandle apiHandle = null;
  private static CourtCaseClient client = null;

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();

    apiHandle = TestHelper.getApiHandle();
    client = new CourtCaseClient(apiHandle);
  }

  @Test
  public void testIndexCourtCase_HappyPath() throws Exception {
    Index<CourtCase> response = client.index();
    Links links = response.getLinks();
    log("links.self = %s", links.getSelf());

    ArrayList<CourtCase> courts = response.getData();
    log("Number of jobs in response: %s", courts.size());

    log("re-serialized: %s", JsonHandle.get().getGsonWithNulls().toJson(response));
  }

  @Test
  public void testShowCourtCase_HappyPath() throws Exception {
    Show<CourtCase> response = client.show(3968864);
    Links links = response.getData().getLinks();
    log("links.self = %s", links.getSelf());
    log("type = %s", response.getData().getType());
    log("updated_at = %s", response.getData().getUpdatedAt());

    log("re-serialized: %s", JsonHandle.get().getGsonWithNulls().toJson(response));
  }

}
