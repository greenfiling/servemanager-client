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

import static com.greenfiling.smclient.util.TestHelper.log;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greenfiling.smclient.model.RegisteredAgent;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.RegisteredAgentFilter;
import com.greenfiling.smclient.util.TestHelper;

public class RegisteredAgentClient_IntegrationTest {
  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(RegisteredAgentClient_IntegrationTest.class);

  private static ApiHandle apiHandle = null;
  private static RegisteredAgentClient client = null;

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();

    apiHandle = TestHelper.getApiHandle();
    client = new RegisteredAgentClient(apiHandle);
  }

  @Test
  public void testIndexRegisteredAgent_HappyPath() throws Exception {
    Index<RegisteredAgent> response = client.index();

    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    // assertThat(response.getLinks(), not(equalTo(null)));

    // Links links = response.getLinks();
    // log("links.self = %s", links.getSelf());

    ArrayList<RegisteredAgent> rAgent = response.getData();
    log("Number of RegisterdAgents in response: %s", rAgent.size());

    // log("re-serialized: %s", JsonHandle.get().getGsonWithNulls().toJson(response));
  }

  @Test
  public void testIndexRegisteredAgentFilter() throws Exception {
    RegisteredAgentFilter filter = new RegisteredAgentFilter();
    filter.setState("CA");
    filter.setArchiveState(RegisteredAgentFilter.ARCHIVE_STATE_ACTIVE);
    // log("filterString: %s", filter.getQueryString());
    Index<RegisteredAgent> response = client.index(filter);

    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));

    ArrayList<RegisteredAgent> rAgent = response.getData();
    log("Number of RegisterdAgents in response: %s", rAgent.size());

    // log("re-serialized: %s", JsonHandle.get().getGsonWithNulls().toJson(response));
  }

  @Test
  public void testIndexRegisteredAgentFilterAgentList() throws Exception {
    RegisteredAgentFilter filter = new RegisteredAgentFilter();
    filter.getRegisteredAgentCompanies().add("1");
    filter.getRegisteredAgentCompanies().add("2");
    // log("filterString: %s", filter.getQueryString());
    Index<RegisteredAgent> response = client.index(filter);

    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));

    ArrayList<RegisteredAgent> rAgent = response.getData();
    log("Number of RegisterdAgents in response: %s", rAgent.size());

    // log("re-serialized: %s", JsonHandle.get().getGsonWithNulls().toJson(response));
  }
}
