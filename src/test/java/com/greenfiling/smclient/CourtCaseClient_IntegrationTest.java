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

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.greenfiling.smclient.model.CourtCase;
import com.greenfiling.smclient.model.Links;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;

public class CourtCaseClient_IntegrationTest {
  private static ApiHandle apiHandle = null;
  private static CourtCaseClient client = null;

  @BeforeClass
  public static void setUpClass() {
    apiHandle = new ApiHandle.Builder().apiKey(TestHelper.VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE).build();
    client = new CourtCaseClient(apiHandle);
  }

  @Test
  public void testIndexCourtCase_HappyPath() throws Exception {
    Index<CourtCase> response = client.index();
    Links links = response.getLinks();
    System.out.println("links.self = " + links.getSelf());

    ArrayList<CourtCase> courts = response.getData();
    System.out.println("Number of jobs in response: " + courts.size());

    System.out.println("re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(response));
  }

  @Test
  public void testShowCourtCase_HappyPath() throws Exception {
    Show<CourtCase> response = client.show(3968864);
    Links links = response.getData().getLinks();
    System.out.println("links.self = " + links.getSelf());
    System.out.println("type = " + response.getData().getType());
    System.out.println("updated_at = " + response.getData().getUpdatedAt());

    System.out.println("re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(response));
  }

}
