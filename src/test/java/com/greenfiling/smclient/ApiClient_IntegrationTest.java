/**
 * Copyright 2023 Green Filing, LLC
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

import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greenfiling.smclient.model.Job;
import com.greenfiling.smclient.model.exchange.Show;

// ApiClient is abstract, so we'll actually test via JobClient, but we're really testing common code in ApiClient
// Much of the current content of JobClient tests belong here, but I'm not touching them for now
public class ApiClient_IntegrationTest {
  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(ApiClient_IntegrationTest.class);

  public static Job job;

  @BeforeClass
  public static void setUpClass() throws Exception {
    TestHelper.loadTestResources();

    ApiHandle handle = TestHelper.getApiHandle();
    JobClient client = new JobClient(handle);

    Job newJob = TestHelper.getTestJob();

    Show<Job> response = client.create(newJob);
    assertThat(response, not(equalTo(null)));
    job = response.getData();
  }

  @SuppressWarnings("null")
  @Test
  public void testMakeUpdateUrl_nullId() throws Exception {
    ApiHandle handle = TestHelper.getApiHandle();
    JobClient client = new JobClient(handle);
    Exception exception = null;

    job.setDueDate(LocalDate.now());

    try {
      client.update(null, job);
    } catch (IllegalArgumentException e) {
      exception = e;
    }
    assertThat(exception, not(equalTo(null)));
    assertThat(exception.getClass(), equalTo(IllegalArgumentException.class));
  }

}
