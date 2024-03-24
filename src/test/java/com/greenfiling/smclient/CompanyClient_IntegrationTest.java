/**
 * Copyright 2024 Green Filing, LLC
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
import static org.hamcrest.Matchers.greaterThan;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greenfiling.smclient.model.Company;
import com.greenfiling.smclient.model.Links;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.util.TestHelper;

public class CompanyClient_IntegrationTest {
  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(CompanyClient_IntegrationTest.class);

  private static ApiHandle apiHandle = null;
  private static CompanyClient client = null;

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();

    apiHandle = TestHelper.getApiHandle();
    client = new CompanyClient(apiHandle);
  }

  @Test
  public void testIndexCompany_HappyPath() throws Exception {
    Index<Company> response = client.index();
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getLinks(), not(equalTo(null)));

    Links links = response.getLinks();
    log("links.self = %s", links.getSelf());

    ArrayList<Company> courts = response.getData();
    log("Number of jobs in response: %s", courts.size());

    // log("re-serialized: %s", JsonHandle.get().getGsonWithNulls().toJson(response));
  }

  @Test
  public void testShowCompany_HappyPath() throws Exception {
    Show<Company> response = client.show(917465);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getData().getLinks(), not(equalTo(null)));
    assertThat(response.getData().getType(), equalTo("company"));
    assertThat(response.getData().getId(), greaterThan(0));

    Links links = response.getData().getLinks();
    log("links.self = %s", links.getSelf());
    log("type = %s", response.getData().getType());
    log("updated_at = %s", response.getData().getUpdatedAt());

    // log("re-serialized: %s", JsonHandle.get().getGsonWithNulls().toJson(response));
  }

}
