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

import static com.greenfiling.smclient.util.TestHelper.log;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.reflect.TypeToken;
import com.greenfiling.smclient.internal.ApiClient;
import com.greenfiling.smclient.model.Employee;
import com.greenfiling.smclient.model.Job;
import com.greenfiling.smclient.model.Links;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.JobFilter;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.internal.FilterBase;
import com.greenfiling.smclient.util.TestHelper;

// ApiClient is abstract, so we'll actually test via JobClient, but we're really testing common code in ApiClient
// Much of the current content of JobClient tests belong here, but I'm not touching them for now
public class ApiClient_IntegrationTest {
  public class BogusEmployeeClient extends ApiClient<Employee, Employee, Employee> {
    public static final String ENDPOINT = "bogus_endpoint";

    public BogusEmployeeClient(ApiHandle handle) {
      super(handle);
      setEndpoint(ENDPOINT);

      // @formatter:off
      setShowType(new TypeToken<Show<Employee>>() {}.getType());
      setIndexType(new TypeToken<Index<Employee>>() {}.getType());
      // @formatter:on
    }

    @Override
    @SuppressWarnings("unchecked")
    public Index<Employee> index(FilterBase filter) throws Exception {
      return (Index<Employee>) toIndex(doIndexRequest(filter));
    }

  }

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

  @Test
  // THis is using JobClient, but it's really testing the ApiClient functionality
  public void testConstructor_HappyPath() throws Exception {
    boolean caughtException = false;
    JobClient client = null;
    ApiHandle apiHandle = null;
    try {
      apiHandle = TestHelper.getApiHandle();
    } catch (IllegalStateException e) {
      caughtException = true;
    }
    assertThat(caughtException, equalTo(false));
    assertThat(apiHandle, not(equalTo(null)));

    caughtException = false;
    try {
      client = new JobClient(apiHandle);
    } catch (Exception e) {
      caughtException = true;
    }
    assertThat(caughtException, equalTo(false));
    assertThat(client, not(equalTo(null)));
  }

  // This is not a perfect test, but paginate through jobs with a specific status, making sure paginate at least once, that we don't see any dupe
  // jobs, and that we end in a sane amount of time
  @Test
  public void testGetNext_HappyPath() throws Exception {
    ApiHandle apiHandle = TestHelper.getApiHandle();
    JobClient client = new JobClient(apiHandle);

    JobFilter filter = new JobFilter();
    filter.getJobStatus().add(TestHelper.PAGINATION_STATUS);

    Index<Job> response = client.index(filter);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getLinks(), not(equalTo(null)));

    Links links = response.getLinks();
    log("links.self = %s", links.getSelf());

    Integer firstPage = TestHelper.pageToInt(links.getFirst());
    Integer lastPage = TestHelper.pageToInt(links.getLast());
    Integer previousPage = 0;
    Integer currentPage = 0;
    HashSet<Integer> seenIds = new HashSet<Integer>();
    log("after initial index, pagination is first = %s, last = %s", firstPage, lastPage);
    do {
      assertThat(response, not(equalTo(null)));
      assertThat(response.getData(), not(equalTo(null)));
      assertThat(response.getLinks(), not(equalTo(null)));

      currentPage = TestHelper.pageToInt(response.getLinks().getSelf());
      log("current page=%s links.self = %s", currentPage, response.getLinks().getSelf());

      // Make sure the pagination is incrementing as expected
      assertThat(currentPage, equalTo(previousPage + 1));

      for (Job j : response.getData()) {
        assertThat(j, not(equalTo(null)));
        assertThat(j.getId(), not(equalTo(null)));
        assertThat(String.format("job_id %s seen multiple times in filter output", j.getId()), seenIds.contains(j.getId()), equalTo(false));
        seenIds.add(j.getId());
      }

      response = client.getNext(response);
      previousPage++;
    } while (response != null);

    // make sure we actually saw all the pages
    assertThat(currentPage, equalTo(lastPage));
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

  // because setEndPoint() is protected, we have to do this with a test class that has extended ApiClient with a bogus endpoint
  @Test
  public void testShowJob_BadApiEndpoint() throws Exception {
    boolean caughtException = false;
    ApiHandle handle = TestHelper.getApiHandle();
    BogusEmployeeClient client = new BogusEmployeeClient(handle);
    Index<Employee> response = null;
    try {
      response = client.index();
    } catch (Exceptions.InvalidEndpointException e) {
      caughtException = true;
    }
    assertThat(caughtException, equalTo(true));
    assertThat(response, equalTo(null));
  }

}
