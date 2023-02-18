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
import static org.hamcrest.Matchers.greaterThan;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greenfiling.smclient.model.Job;
import com.greenfiling.smclient.model.Links;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.util.TestHelper;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

// We are testing ApiHandle, though we are testing it via JobClient
public class ApiHandle_IntegrationTest {
  class SemaphoreInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
      Request request = chain.request();
      okhttpSemaphore = SEMAPHORE;
      Response response = chain.proceed(request);
      return response;
    }
  }

  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(ApiHandle_IntegrationTest.class);
  public static String okhttpSemaphore = null;
  private static final String SEMAPHORE = "I am in the interceptor";

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();
    // System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
  }

  @Test
  public void testGetFile_HappyPath() throws Exception {
    ApiHandle apiHandle = TestHelper.getApiHandle();
    JobClient client = new JobClient(apiHandle);

    String localPath = System.getProperty("java.io.tmpdir") + "/" + TestHelper.getUniqueString() + ".out";

    client.getFile(TestHelper.REMOTE_FILE, localPath);
    log("Downloaded to %s", localPath);
    File file = new File(localPath);
    assertThat("Downloaded file does not exist", file.exists(), equalTo(true));
    assertThat("Downloaded file is not empty", (int) file.length(), greaterThan(0));

  }

  @Test
  public void testIndexJob_ExternalBuilder() throws Exception {
    // This is a bit obtuse, but to prove that we're using an external okhttp builder, we add an interceptor that
    // does nothing but set the value of a known variable to a known value. If that happens, our external builder got used properly
    okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder().addInterceptor(new SemaphoreInterceptor());
    ApiHandle apiHandle = new ApiHandle.Builder().builder(builder).apiKey(TestHelper.VALID_API_KEY).build();
    JobClient client = new JobClient(apiHandle);

    okhttpSemaphore = "interceptor didn't run";
    Index<Job> response = client.index();
    assertThat(okhttpSemaphore, equalTo(SEMAPHORE));
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getLinks(), not(equalTo(null)));

    Links links = response.getLinks();
    log("links.self = %s", links.getSelf());

    ArrayList<Job> jobs = response.getData();
    log("Number of jobs in response: %s", jobs.size());
    assertThat(jobs.size(), greaterThan(0));
  }

  @Test
  public void testShowJob_BadAuth() throws Exception {
    boolean caughtException = false;
    ApiHandle apiHandle = new ApiHandle.Builder().apiKey("foo").apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE).build();
    JobClient client = new JobClient(apiHandle);
    Show<Job> showResp = null;
    try {
      showResp = client.show(8559826);
    } catch (Exceptions.InvalidCredentialsException e) {
      caughtException = true;
    }
    assertThat(caughtException, equalTo(true));
    assertThat(showResp, equalTo(null));
  }

  @Test
  public void testShowJob_BadEndpointBase() throws Exception {
    boolean caughtException = false;
    ApiHandle apiHandle = new ApiHandle.Builder().apiKey(TestHelper.VALID_API_KEY).apiEndpoint("https://jetmore.org/api").build();
    JobClient client = new JobClient(apiHandle);
    Show<Job> showResp = null;
    try {
      showResp = client.show(8559826);
    } catch (Exceptions.InvalidEndpointException e) {
      caughtException = true;
    }
    assertThat(caughtException, equalTo(true));
    assertThat(showResp, equalTo(null));
  }

  @Test
  public void testShowJob_BadEndpointServer() throws Exception {
    boolean caughtException = false;
    ApiHandle apiHandle = new ApiHandle.Builder().connectTimeout(2).apiKey(TestHelper.VALID_API_KEY)
        .apiEndpoint("https://adfadsf234iukjawdfkhwe3333333.org/api").build();
    JobClient client = new JobClient(apiHandle);
    Show<Job> showResp = null;
    try {
      showResp = client.show(8559826);
    } catch (UnknownHostException | ConnectException e) {
      caughtException = true;
    }
    assertThat(caughtException, equalTo(true));
    assertThat(showResp, equalTo(null));
  }

}
