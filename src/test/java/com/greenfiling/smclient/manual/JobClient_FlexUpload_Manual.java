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

package com.greenfiling.smclient.manual;

import static com.greenfiling.smclient.util.TestHelper.log;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import java.io.File;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greenfiling.smclient.ApiHandle;
import com.greenfiling.smclient.JobClient;
import com.greenfiling.smclient.model.Job;
import com.greenfiling.smclient.model.JobSubmit;
import com.greenfiling.smclient.model.Links;
import com.greenfiling.smclient.model.ServiceDocument;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.util.TestHelper;

// These are manual test because it can take a very long time for SM to process the uploaded file (up to a minute in my testing). All of the 
// asserts make it an automated test, just in Manual so that the extreme wait doesn't leak into deploying, etc. It can also be fast, 
// I suspect it's on a timer and the length of the wait just depends on where the submission hits in the timer cycle
public class JobClient_FlexUpload_Manual {
  // @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(JobClient_FlexUpload_Manual.class);

  private static ApiHandle apiHandle = null;
  private static JobClient client = null;

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();

    apiHandle = TestHelper.getApiHandle();
    client = new JobClient(apiHandle);
  }

  @Test
  public void testCreateJob_ExternalUrl() throws Exception {
    ArrayList<ServiceDocument> docs = new ArrayList<>();
    ServiceDocument doc = new ServiceDocument();
    doc.setTitle("Subpoena");
    doc.setReceivedAt(OffsetDateTime.now());
    doc.setFileName("file_name.pdf");
    doc.setExternalUrl(TestHelper.REMOTE_FILE);
    docs.add(doc);

    JobSubmit job = TestHelper.getTestJobSubmit();
    job.setDocumentsToBeServedAttributes(docs);

    Show<Job> response = client.create(job);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getData().getLinks(), not(equalTo(null)));
    assertThat(response.getData().getType(), equalTo("job"));
    assertThat(response.getData().getId(), greaterThan(0));

    Links links = response.getData().getLinks();
    log("links.self = %s", links.getSelf());
    log("type = %s", response.getData().getType());
    log("updated_at = %s", response.getData().getUpdatedAt());
    log("recipient.name = %s", response.getData().getRecipient().getName());
    log("dueDate = %s", response.getData().getDueDate());
    // log(Util.printObject(response.getData()));

    // This out-of-band upload doesn't get associated back with the job immediately, so build in some wait time
    for (int i = 0; i < 1000; i++) {
      response = client.show(response.getData().getId());
      assertThat(response, not(equalTo(null)));
      assertThat(response.getData(), not(equalTo(null)));
      assertThat(response.getData().getDocumentsToBeServed(), not(equalTo(null)));
      assertThat(response.getData().getDocumentsToBeServed().size(), greaterThan(0));
      ServiceDocument d = response.getData().getDocumentsToBeServed().get(0);
      assertThat(d.getUpload(), not(equalTo(null)));
      assertThat(d.getUpload().getLinks(), not(equalTo(null)));
      if (d.getUpload().getLinks().getDownloadUrl() != null) {
        break;
      }
      logger.info("testCreateJob_ExternalUrl - download_url not yet populated, sleeping");
      Thread.sleep(5000);
    }
    log("links.self = %s", links.getSelf());

    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getData().getDocumentsToBeServed(), not(equalTo(null)));
    assertThat(response.getData().getDocumentsToBeServed().size(), greaterThan(0));
    ServiceDocument d = response.getData().getDocumentsToBeServed().get(0);
    assertThat(d.getUpload(), not(equalTo(null)));
    assertThat(d.getUpload().getLinks(), not(equalTo(null)));
    for (ServiceDocument td : response.getData().getDocumentsToBeServed()) {
      log("document_to_be_served, %s", td.getUpload().getLinks().getDownloadUrl());
    }
    assertThat(d.getUpload().getLinks().getDownloadUrl(), not(equalTo(null)));
    assertThat(response.getData().getDocumentsToBeServed().size(), equalTo(docs.size()));
    assertThat(d.getTitle(), equalTo(docs.get(0).getTitle()));
    assertThat(d.getUpload().getFileName(), equalTo(docs.get(0).getFileName()));
  }

  @Test
  public void testCreateJob_SeparateUpload() throws Exception {
    ArrayList<ServiceDocument> docs = new ArrayList<>();
    ServiceDocument doc = new ServiceDocument();
    doc.setTitle("Subpoena");
    doc.setReceivedAt(OffsetDateTime.now());
    doc.setFileName("file_name.pdf");
    docs.add(doc);

    JobSubmit job = TestHelper.getTestJobSubmit();
    job.setDocumentsToBeServedAttributes(docs);

    Show<Job> response = client.create(job);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getData().getLinks(), not(equalTo(null)));
    assertThat(response.getData().getType(), equalTo("job"));
    assertThat(response.getData().getId(), greaterThan(0));

    Links links = response.getData().getLinks();
    log("links.self = %s", links.getSelf());
    log("type = %s", response.getData().getType());
    log("updated_at = %s", response.getData().getUpdatedAt());
    log("recipient.name = %s", response.getData().getRecipient().getName());
    log("dueDate = %s", response.getData().getDueDate());
    // log(Util.printObject(response.getData()));

    boolean caughtException = false;
    for (ServiceDocument d : response.getData().getDocumentsToBeServed()) {
      try {
        client.completeUpload(d.getUpload(), "application/pdf", new File(TestHelper.VALID_FILE_PATH_1));
      } catch (Exception e) {
        logger.error("Caught an error in completeUpload for document {}", d.getFileName(), e);
        caughtException = true;
      }
    }
    assertThat(caughtException, not(equalTo(true)));

    // This upload doesn't get associated back with the job immediately, so build in some wait time
    for (int i = 0; i < 1000; i++) {
      response = client.show(response.getData().getId());
      assertThat(response, not(equalTo(null)));
      assertThat(response.getData(), not(equalTo(null)));
      assertThat(response.getData().getDocumentsToBeServed(), not(equalTo(null)));
      assertThat(response.getData().getDocumentsToBeServed().size(), greaterThan(0));
      ServiceDocument d = response.getData().getDocumentsToBeServed().get(0);
      assertThat(d.getUpload(), not(equalTo(null)));
      assertThat(d.getUpload().getLinks(), not(equalTo(null)));
      if (d.getUpload().getLinks().getDownloadUrl() != null) {
        break;
      }
      logger.info("testCreateJob_SeparateUpload - download_url not yet populated, sleeping");
      Thread.sleep(5000);
    }
    log("links.self = %s", links.getSelf());

    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getData().getDocumentsToBeServed(), not(equalTo(null)));
    assertThat(response.getData().getDocumentsToBeServed().size(), greaterThan(0));
    ServiceDocument d = response.getData().getDocumentsToBeServed().get(0);
    assertThat(d.getUpload(), not(equalTo(null)));
    assertThat(d.getUpload().getLinks(), not(equalTo(null)));
    for (ServiceDocument td : response.getData().getDocumentsToBeServed()) {
      log("document_to_be_served, %s", td.getUpload().getLinks().getDownloadUrl());
    }
    assertThat(d.getUpload().getLinks().getDownloadUrl(), not(equalTo(null)));
    assertThat(response.getData().getDocumentsToBeServed().size(), equalTo(docs.size()));
    assertThat(d.getTitle(), equalTo(docs.get(0).getTitle()));
    assertThat(d.getUpload().getFileName(), equalTo(docs.get(0).getFileName()));
  }

}
