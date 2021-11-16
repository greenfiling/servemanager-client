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

import com.greenfiling.smclient.model.Employee;
import com.greenfiling.smclient.model.Links;
import com.greenfiling.smclient.model.exchange.Index;

public class EmployeeClient_IntegrationTest {
  private static ApiHandle apiHandle = null;
  private static EmployeeClient client = null;

  @BeforeClass
  public static void setUpClass() {
    apiHandle = new ApiHandle.Builder().apiKey(TestHelper.VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE).build();
    client = new EmployeeClient(apiHandle);
  }

  @Test
  public void testIndexEmployee_HappyPath() throws Exception {
    Index<Employee> response = client.index();
    Links links = response.getLinks();
    System.out.println("links.self = " + links.getSelf());

    ArrayList<Employee> employees = response.getData();
    System.out.println("Number of jobs in response: " + employees.size());

    System.out.println("re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(response));
  }
}
