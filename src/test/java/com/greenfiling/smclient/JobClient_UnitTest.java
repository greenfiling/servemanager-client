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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.junit.BeforeClass;
import org.junit.Test;

import com.greenfiling.smclient.DnsSelector.IpMode;
import com.greenfiling.smclient.model.Attempt;
import com.greenfiling.smclient.model.AttemptSubmit;
import com.greenfiling.smclient.model.Company;
import com.greenfiling.smclient.model.Job;
import com.greenfiling.smclient.model.Links;
import com.greenfiling.smclient.model.Recipient;
import com.greenfiling.smclient.model.ServiceDocument;
import com.greenfiling.smclient.model.exchange.FilterBase;
import com.greenfiling.smclient.model.exchange.FilterDateRange;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.JobFilter;
import com.greenfiling.smclient.model.exchange.Show;

import de.westemeyer.version.model.Artifact;
import de.westemeyer.version.service.ArtifactVersionCollector;

public class JobClient_UnitTest {
  public static final String VALID_API_KEY = TestHelper.VALID_API_KEY;

  @BeforeClass
  public static void setUpClass() {
    // System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
  }

  @Test
  public void testConstructor_HappyPath() throws Exception {
    boolean caughtException = false;
    JobClient client = null;
    ApiHandle apiHandle = null;
    try {
      apiHandle = new ApiHandle.Builder().apiKey(VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE).build();
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

  @Test
  public void testCreateJob_HappyPath() throws Exception {

    ApiHandle apiHandle = new ApiHandle.Builder().apiKey(VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE).build();
    JobClient client = new JobClient(apiHandle);

    Job newJob = new Job();

    Show<Job> response = client.create(newJob);
    Links links = response.getData().getLinks();
    System.out.println("job created, links.self = " + links.getSelf());

    // JobClient jobClient = new JobClient(apiHandle);
    // JobSubmit job = new JobSubmit();
    // job.setCourtCaseId(1234);
    // job.setRush(true);
    // job.setDueDate(LocalDate.parse("2021-11-15"));
    // Show<Job> createdJob = jobClient.create(job);
    // System.out.println("New job " + createdJob.getData().getId() + " visible at " + createdJob.getData().getLinks().getSelf());

  }

  @Test
  public void testDate() throws Exception {
    // "updated_at":"2021-10-26T15:32:34-04:00"
    String dateString = "2021-10-26T15:32:34-06:00";
    String format = "yyyy-MM-dd'T'HH:mm:ssXXX";
    // format = "yyyy-MM-dd'T'HH:mm:ss";
    DateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
    Date date = dateFormat.parse(dateString);
    System.out.println("date = " + date.toString());

    DateFormat dateFormatCur = new SimpleDateFormat(format, Locale.US);
    Date curDate = new Date();
    System.out.println(dateFormatCur.format(curDate));
  }

  @Test
  public void testFoo() throws Exception {
    Attempt attempt = new Attempt();
    attempt.setServeType(Attempt.SERVE_TYPE_SUCCESS_AUTHORIZED);

    AttemptSubmit attemptSubmit = new AttemptSubmit();
    attemptSubmit.setRecipientEthnicity(Recipient.ETHNICITY_CAUCASIAN);

    Job job = new Job();
    job.setJobStatus(Job.JOB_STATUS_CANCELED);

    Company company = new Company();
    company.setCompanyType(Company.COMPANY_TYPE_CONTRACTOR);

  }

  @Test
  public void testGetFile() throws Exception {
    ApiHandle apiHandle = new ApiHandle.Builder().apiKey(VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE).build();
    JobClient client = new JobClient(apiHandle);

    String url = "http://speedtest.ftp.otenet.gr/files/test100k.db";
    String localPath = System.getProperty("java.io.tmpdir") + "/test-file.out";

    client.getFile(url, localPath);
    System.out.println("Downloaded to " + localPath);
  }

  @Test
  public void testGetFile2() throws Exception {
    ApiHandle apiHandle = new ApiHandle.Builder().apiKey(VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE).build();
    JobClient client = new JobClient(apiHandle);

    Show<Job> resp = client.show(8624870);
    ServiceDocument doc = resp.getData().getDocumentsToBeServed().get(0);

    String localPath = System.getProperty("java.io.tmpdir") + "/" + doc.getUpload().getFileName();

    client.getFile(doc.getUpload().getLinks().getDownloadUrl(), localPath);
    System.out.println("Downloaded from " + doc.getUpload().getLinks().getDownloadUrl());
    System.out.println("Downloaded to " + localPath);
  }

  @Test
  public void testGetNext_HappyPath() throws Exception {
    ApiHandle apiHandle = new ApiHandle.Builder().apiKey(VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE).build();
    JobClient client = new JobClient(apiHandle);

    Index<Job> response = client.index();
    while (response != null) {
      Links links = response.getLinks();
      System.out.println("links.self = " + links.getSelf());

      ArrayList<Job> jobs = response.getData();
      System.out.println("Number of jobs in response: " + jobs.size());

      response = client.getNext(response);
    }

    // JobClient jobClient = new JobClient(apiHandle);
    //
    // Index<Job> resp = jobClient.index();
    // Integer pages = 0;
    // Integer total = 0;
    // while (resp != null) {
    // pages++;
    // total += resp.getData().size();
    // response = client.getNext(response);
    // }
    // System.out.println(total + " total objects returned across " + pages + " pages");

  }

  @Test
  public void testIndexJob_Filter_DateRange() throws Exception {
    ApiHandle apiHandle = new ApiHandle.Builder().apiKey(VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE).build();
    JobClient client = new JobClient(apiHandle);

    JobFilter filter = new JobFilter();
    filter.setDateRange(new FilterDateRange());
    filter.getDateRange().setType(FilterDateRange.TYPE_CREATED_AT);
    filter.getDateRange().setMax(OffsetDateTime.parse("2021-11-01T00:00:00-00:00"));

    Index<Job> response = client.index(filter);
    Links links = response.getLinks();
    System.out.println("links.self = " + links.getSelf());

    ArrayList<Job> jobs = response.getData();
    System.out.println("Number of jobs in response: " + jobs.size());

    // // Create a job filter that lists all jobs that have the test foobar in them and were created after October 1, 2021
    // JobFilter jobFilter = new JobFilter();
    // jobFilter.setDateRange(new FilterDateRange());
    // jobFilter.getDateRange().setType(FilterDateRange.TYPE_CREATED_AT);
    // jobFilter.getDateRange().setMin(OffsetDateTime.parse("2021-10-01T00:00:00-00:00"));
    // jobFilter.setQ("foobar");
    //
    // JobClient jobClient = new JobClient(apiHandle);
    // Index<Job> resp = jobClient.index(filter);
    //
    // System.out.println("Matching jobs:");
    // while (resp != null) {
    // for (Job j : resp.getData()) {
    // System.out.println(" - JobId: " + j.getId() + ", URL = " + j.getLinks().getSelf());
    // }
    // resp = jobClient.getNext(resp);
    // }

  }

  @Test
  public void testIndexJob_ExternalBuilder() throws Exception {
    okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder().dns(new DnsSelector(IpMode.IPV4_ONLY));
    ApiHandle apiHandle = new ApiHandle.Builder().builder(builder).apiKey(VALID_API_KEY).build();
    JobClient client = new JobClient(apiHandle);

    Index<Job> response = client.index();
    Links links = response.getLinks();
    System.out.println("links.self = " + links.getSelf());

    ArrayList<Job> jobs = response.getData();
    System.out.println("Number of jobs in response: " + jobs.size());
  }

  @Test
  public void testIndexJob_Filter_HappyPath() throws Exception {
    ApiHandle apiHandle = new ApiHandle.Builder().apiKey(VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE).build();
    JobClient client = new JobClient(apiHandle);

    JobFilter filter = new JobFilter();
    filter.setArchiveState(FilterBase.ARCHIVE_STATE_ARCHIVED);
    // filter.setQ("random text");
    filter.getJobStatus().add(JobFilter.JOB_STATUS_CANCELED);
    filter.getJobStatus().add(JobFilter.JOB_STATUS_FILED);

    Index<Job> response = client.index(filter);
    Links links = response.getLinks();
    System.out.println("links.self = " + links.getSelf());

    ArrayList<Job> jobs = response.getData();
    System.out.println("Number of jobs in response: " + jobs.size());
  }

  @Test
  public void testIndexJob_HappyPath() throws Exception {
    ApiHandle apiHandle = new ApiHandle.Builder().apiKey(VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE).build();
    JobClient client = new JobClient(apiHandle);

    Index<Job> response = client.index();
    Links links = response.getLinks();
    System.out.println("links.self = " + links.getSelf());

    ArrayList<Job> jobs = response.getData();
    System.out.println("Number of jobs in response: " + jobs.size());

    // JobClient jobClient = new JobClient(apiHandle);
    // Index<Job> resp = jobClient.index();
    // System.out.println("Number of jobs in response: " + resp.getData().size());
    // System.out.println("ServeManager Job Numbers: ");
    // for (Job j : resp.getData()) {
    // System.out.println(" - " + j.getServeManagerJobNumber());
    // }

  }

  @Test
  public void testLoadConfig() throws Exception {
    System.out.println("VALID_API_KEY: " + TestHelper.VALID_API_KEY);
    System.out.println("VALID_FILE_1: " + TestHelper.VALID_FILE_PATH_1);
    System.out.println("VALID_FILE_2: " + TestHelper.VALID_FILE_PATH_2);
  }

  @Test
  public void testShowJob_BadApiEndpoint() throws Exception {
    boolean caughtException = false;
    ApiHandle apiHandle = new ApiHandle.Builder().apiKey(VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE).build();
    JobClient client = new JobClient(apiHandle);
    client.setEndpoint("foo");
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
    ApiHandle apiHandle = new ApiHandle.Builder().apiKey(VALID_API_KEY).apiEndpoint("https://jetmore.org/api").build();
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
    ApiHandle apiHandle = new ApiHandle.Builder().connectTimeout(2).apiKey(VALID_API_KEY).apiEndpoint("https://adfadsf234iukjawdfkhwe3333333.org/api")
        .build();
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

  @Test
  public void testShowJob_HappyPath() throws Exception {
    ApiHandle apiHandle = new ApiHandle.Builder().apiKey(VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE).build();
    JobClient client = new JobClient(apiHandle);

    Show<Job> response = client.show(8559826);
    Links links = response.getData().getLinks();
    System.out.println("links.self = " + links.getSelf());
    System.out.println("type = " + response.getData().getType());
    System.out.println("updated_at = " + response.getData().getUpdatedAt());
    System.out.println("recipient.name = " + response.getData().getRecipient().getName());
    System.out.println("dueDate = " + response.getData().getDueDate());
    // System.out.println(Util.printObject(response.getData()));

    OffsetDateTime updatedAt = response.getData().getUpdatedAt().minusDays(3);
    System.out.println("3 days before updatedAt = " + updatedAt);

    // JobClient jobClient = new JobClient(apiHandle);
    // Show<Job> resp = jobClient.show(123);
    // System.out.println("Job Number: " + resp.getData().getServeManagerJobNumber());
    // System.out.println("Documents for download: ");
    // for (Document d : resp.getData().getDocuments()) {
    // System.out.println(" - " + d.getPdfDownloadUrl());
    // }

  }

  @Test
  public void testShowJob_NoSuchObject() throws Exception {
    boolean caughtException = false;
    ApiHandle apiHandle = new ApiHandle.Builder().apiKey(VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE).build();
    JobClient client = new JobClient(apiHandle);
    Show<Job> showResp = null;
    try {
      showResp = client.show(1);
    } catch (Exceptions.RecordNotFoundException e) {
      caughtException = true;
    }
    assertThat(caughtException, equalTo(true));
    assertThat(showResp, equalTo(null));
  }

  @Test
  public void testUpdateJob_HappyPath() throws Exception {
    ApiHandle apiHandle = new ApiHandle.Builder().apiKey(VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE).build();
    JobClient client = new JobClient(apiHandle);

    Job newJob = new Job();
    Integer jobId = 8595080;
    newJob.setJobStatus(Job.JOB_STATUS_ON_HOLD);
    newJob.setRush(true);
    newJob.setDueDate(LocalDate.parse("2021-11-07"));

    Show<Job> response = client.update(jobId, newJob);
    Links links = response.getData().getLinks();
    System.out.println("job created, links.self = " + links.getSelf());

    // JobSubmit job = new JobSubmit();
    // job.setRush(true);
    //
    // JobClient jobClient = new JobClient(apiHandle);
    // Show<Job> createdJob = jobClient.update(1234, job);
    // System.out.println("Job " + createdJob.getData().getId() + " updated, new rush = " + createdJob.getData().getRush());

  }

  @Test
  public void testArtifactVersionService() throws Exception {
    System.out.println("List of artifacts:");
    for (Artifact artifact : ArtifactVersionCollector.collectArtifacts()) {
      System.out.println("artifact = " + artifact);
    }
    Artifact smclient = ArtifactVersionCollector.findArtifact("com.greenfiling.smclient", "servemanager-client");
    System.out.println(String.format("name = %s, version = %s", smclient.getName(), smclient.getVersion()));
  }

}
