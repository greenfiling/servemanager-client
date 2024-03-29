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

import static com.greenfiling.smclient.util.TestHelper.log;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greenfiling.smclient.model.Employee;
import com.greenfiling.smclient.model.Links;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.util.TestHelper;

public class EmployeeClient_IntegrationTest {
  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(EmployeeClient_IntegrationTest.class);

  private static ApiHandle apiHandle = null;
  private static EmployeeClient client = null;

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();

    apiHandle = TestHelper.getApiHandle();
    client = new EmployeeClient(apiHandle);
  }

  @Test
  public void testIndexEmployee_HappyPath() throws Exception {
    Index<Employee> response = client.index();
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getLinks(), not(equalTo(null)));

    Links links = response.getLinks();
    log("links.self = %s", links.getSelf());

    ArrayList<Employee> employees = response.getData();
    log("Number of jobs in response: %s", employees.size());

    // log("re-serialized: %s", JsonHandle.get().getGsonWithNulls().toJson(response));
  }
}
