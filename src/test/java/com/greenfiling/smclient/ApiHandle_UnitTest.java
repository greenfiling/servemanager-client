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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiHandle_UnitTest {
  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(ApiHandle_UnitTest.class);

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();
  }

  @Test
  public void testConstructor_HappyPath() throws Exception {
    boolean caughtException = false;
    ApiHandle client = null;
    try {
      client = new ApiHandle.Builder().apiKey(TestHelper.VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE).build();
    } catch (IllegalStateException e) {
      caughtException = true;
    }
    assertThat(caughtException, equalTo(false));
    assertThat(client, not(equalTo(null)));
  }

  @SuppressWarnings("null")
  @Test
  public void testConstructor_MissingApiEndpoint() throws Exception {
    boolean caughtException = false;
    ApiHandle client = null;
    try {
      client = new ApiHandle.Builder().apiKey(TestHelper.VALID_API_KEY).build();
    } catch (IllegalStateException e) {
      caughtException = true;
    }
    assertThat(caughtException, equalTo(false));
    assertThat(client, not(equalTo(null)));
    assertThat(client.getApiEndpointBase(), equalTo(ApiHandle.DEFAULT_ENDPOINT_BASE));
  }

  @Test
  public void testConstructor_MissingApiKey() throws Exception {
    boolean caughtException = false;
    ApiHandle client = null;
    try {
      client = new ApiHandle.Builder().apiEndpoint("foo").build();
    } catch (IllegalStateException e) {
      caughtException = true;
    }
    assertThat(caughtException, equalTo(true));
    assertThat(client, equalTo(null));
  }

}
