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

package com.greenfiling.smclient.manual;

import static com.greenfiling.smclient.util.TestHelper.log;
import static com.greenfiling.smclient.util.TestHelper.logt;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greenfiling.smclient.ApiHandle;
import com.greenfiling.smclient.JobClient;
import com.greenfiling.smclient.model.Attachment;
import com.greenfiling.smclient.model.Job;
import com.greenfiling.smclient.model.JobSubmit;
import com.greenfiling.smclient.model.Links;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.JobFilter;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.util.TestHelper;

import de.westemeyer.version.model.Artifact;
import de.westemeyer.version.service.ArtifactVersionCollector;

// used as a dumping ground for places to run one-off code, experiments, etc.  Not real (or at least not useful) test cases.
// Do not add this class to the manual suite.
// It is acceptable for these tests not to have asserts, etc
public class Playground {
  public class TestObj {
    private String name;
    private Date date;
    private String multiNameField;

    public Date getDate() {
      return this.date;
    }

    public String getMultiNameField() {
      return multiNameField;
    }

    public String getName() {
      return this.name;
    }

    public void setDate(Date date) {
      this.date = date;
    }

    public void setMultiNameField(String multiNameField) {
      this.multiNameField = multiNameField;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(Playground.class);

  private static ApiHandle apiHandle = null;
  private static JobClient client = null;

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();

    apiHandle = TestHelper.getApiHandle();
    client = new JobClient(apiHandle);
  }

  @Test
  public void testArtifactVersionService() throws Exception {
    log("List of artifacts:");
    for (Artifact artifact : ArtifactVersionCollector.collectArtifacts()) {
      log("artifact = %s", artifact);
    }
    Artifact smclient = ArtifactVersionCollector.findArtifact("com.greenfiling.smclient", "servemanager-client");
    log("name = %s, version = %s", smclient.getName(), smclient.getVersion());
  }

  // "custom" is in their API but I have no idea how to use it or why. This was a failed attempt to interact with it
  @Test
  public void testCreateJob_CustomData() throws Exception {
    ApiHandle apiHandle = TestHelper.getApiHandle();
    JobClient client = new JobClient(apiHandle);

    Job newJob = TestHelper.getTestJob();

    // Doesn't seem to actually do anything. Still haven't heard what good this is
    HashMap<String, String> custom = new HashMap<String, String>();
    custom.put("test_custom_key", "test_custom_value");
    newJob.setCustom(custom);

    Show<Job> response = client.create(newJob);
    Links links = response.getData().getLinks();
    log("job created, links.self = %s", links.getSelf());
  }

  @Test
  public void testDate() throws Exception {
    // "updated_at":"2021-10-26T15:32:34-04:00"
    String dateString = "2021-10-26T15:32:34-06:00";
    String format = "yyyy-MM-dd'T'HH:mm:ssXXX";
    // format = "yyyy-MM-dd'T'HH:mm:ss";
    DateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
    Date date = dateFormat.parse(dateString);
    log("date = %s", date.toString());

    DateFormat dateFormatCur = new SimpleDateFormat(format, Locale.US);
    Date curDate = new Date();
    log(dateFormatCur.format(curDate));
  }

  @Test
  // I think this was verifying the change in name policy and converting dates. I think there might be value to it, but timezones are
  // going to prevent it from ever passing automated testing
  public void testDate_From() throws Exception {
    String objJson = "{\"name\":\"this is a name\",\"date\":\"2021-10-26T15:32:34-00:00\",\"multi_name_field\":\"field value\"}";
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
        .setFieldNamingPolicy(com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    TestObj obj = gson.fromJson(objJson, TestObj.class);
    assertThat(obj, not(equalTo(null)));
    log("name = %s, date = %s, multiNameField = %s", obj.getName(), obj.getDate(), obj.getMultiNameField());

    String newJson = gson.toJson(obj);
    assertThat(newJson, equalTo(objJson));
    log("re-encoded json: %s", newJson);
  }

  // "custom" is in their API but I have no idea how to use it or why. This was a failed attempt to interact with it
  @Test
  public void testFoo() throws Exception {
    ApiHandle apiHandle = TestHelper.getApiHandle();
    JobClient client = new JobClient(apiHandle);

    JobSubmit jobUpdate = new JobSubmit();
    jobUpdate.setJobStatus("Pending Archive");
    client.update(11749867, jobUpdate);
  }

  // This is a test demonstrating how different filter types relate to each other (and vs or)
  @Test
  public void testIndexJob_Filter_HappyPath() throws Exception {
    ApiHandle apiHandle = TestHelper.getApiHandle();
    JobClient client = new JobClient(apiHandle);

    JobFilter filter = new JobFilter();
    filter.getJobStatus().add("Pending Archive");
    filter.setQ(", Type: service of process");

    Index<Job> response = client.index(filter);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getLinks(), not(equalTo(null)));

    Links links = response.getLinks();
    log("links.self = %s", links.getSelf());

    for (Job j : response.getData()) {
      System.out.println(String.format("%s / %s :: %s", j.getJobStatus(), j.getServiceStatus(), j.getClientJobNumber()));
    }

    filter.setQ(", Type: courtesy copy");
    response = client.index(filter);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getLinks(), not(equalTo(null)));

    links = response.getLinks();
    log("links.self = %s", links.getSelf());

    for (Job j : response.getData()) {
      log("%s / %s :: %s", j.getJobStatus(), j.getServiceStatus(), j.getClientJobNumber());
    }

  }

  @Test
  public void testLoadConfig() throws Exception {
    log("VALID_API_KEY: %s", TestHelper.VALID_API_KEY);
    log("VALID_FILE_1: %s", TestHelper.VALID_FILE_PATH_1);
    log("VALID_FILE_2: %s", TestHelper.VALID_FILE_PATH_2);
  }

  // The affidavit and signed fields don't appear to be settable via the API, so for now do a manual set in the UI and then confirm we see the change.
  // See github issue #54 to get rid of this test and make it automated when the API is fixed
  @Test
  public void testMiscDocFields() throws Exception {
    ApiHandle apiHandle = TestHelper.getApiHandle();
    JobClient client = new JobClient(apiHandle);

    Show<Job> job = client.show(12412788);
    for (Attachment a : job.getData().getMiscAttachments()) {
      log("name = %s, affidavit = %s, signed = %s", a.getTitle(), a.getAffidavit(), a.getSigned());
    }
  }

  @Test
  // I had this theory at one point that in addition to the delay in uploaded files being processed there was also a delay in adding the upload to the
  // attachments. THis was an attempt to prove or disprove that. This test plus other work disproved that theory
  public void testUpdateJob_CheckForDelayOnAttachmentUpload() throws Exception {
    Job newJob = TestHelper.getTestJob();

    Show<Job> response = client.create(newJob);
    Links links = response.getData().getLinks();
    logt(String.format("job created, links.self = %s", links.getSelf()));

    ArrayList<Attachment> docs = new ArrayList<>();
    Attachment doc = new Attachment();
    doc.setFileName("file_name.pdf");
    docs.add(doc);

    Job job = response.getData();
    job.setMiscAttachments(docs);
    response = client.update(job.getId(), job);
    logt("attachment added");
    for (Attachment a : response.getData().getMiscAttachments()) {
      client.completeUpload(a.getUpload(), "application/pdf", new File(TestHelper.VALID_FILE_PATH_1));
      logt("attachment uploaded");
    }

    // for (int i = 0; i < 100; i++) {
    response = client.show(job.getId());
    logt(String.format("polled for misc_attachments, _count = %d, real count = %d", response.getData().getMiscAttachmentsCount(),
        response.getData().getMiscAttachments().size()));
    // }
  }

}
