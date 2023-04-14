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
import com.greenfiling.smclient.model.Document;
import com.greenfiling.smclient.model.Job;
import com.greenfiling.smclient.model.JobSubmit;
import com.greenfiling.smclient.model.Links;
import com.greenfiling.smclient.model.ServiceDocument;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.util.TestHelper;

// These are manual tests only because they might take a while to run.  They still do error detection properly via asserts
public class ApiHandle_Manual {
  private static final Logger logger = LoggerFactory.getLogger(ApiHandle_Manual.class);

  private static ApiHandle apiHandle = null;
  private static JobClient client = null;

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();

    apiHandle = TestHelper.getApiHandle();
    client = new JobClient(apiHandle);
  }

  @Test
  public void testGetFile_FromServeManager() throws Exception {

    ArrayList<ServiceDocument> docs = new ArrayList<>();
    ServiceDocument doc = new ServiceDocument();
    doc.setTitle("Subpoena");
    doc.setReceivedAt(OffsetDateTime.now());
    doc.setFileName(TestHelper.getUniqueString() + ".pdf");
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

    // This upload doesn't get associated back with the job immediately, so build in some wait time
    Integer jobId = response.getData().getId();
    for (int i = 0; i < 1000; i++) {
      response = client.show(jobId);
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
      logger.info("testGetFile_FromServeManager - download_url not yet populated, sleeping");
      Thread.sleep(5000);
    }
    log("links.self = %s", links.getSelf());

    ServiceDocument smDoc = response.getData().getDocumentsToBeServed().get(0);
    String smUrl = smDoc.getUpload().getLinks().getDownloadUrl();

    String localPath = System.getProperty("java.io.tmpdir") + "/" + smDoc.getUpload().getFileName();
    log("Downloading from %s", smUrl);

    client.getFile(smUrl, localPath);
    log("Downloaded to %s", localPath);
    File file = new File(localPath);
    assertThat("Downloaded file does not exist", file.exists(), equalTo(true));
    assertThat("Downloaded file is not empty", (int) file.length(), greaterThan(0));
  }

  // this tests downloading an affidavit from the API, which requires API auth (though as currently implemented it then redirects to AWS for
  // download). Since you can't programmatically create an affidavit, you have to have a pre-existing job to test it with. As such, this test
  // can't be run automatically.
  // @Test
  public void testGetFileApi() throws Exception {
    Integer jobId = 12141464; // this is a servemanager job that has at least one affidavit associated with it

    Show<Job> response = client.show(jobId);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getData().getLinks(), not(equalTo(null)));
    assertThat(response.getData().getId(), equalTo(jobId));

    Job job = response.getData();
    assertThat(job.getDocuments(), not(equalTo(null)));
    assertThat(job.getDocuments().size(), not(equalTo(0)));

    Document d = job.getDocuments().get(0);
    assertThat(d, not(equalTo(null)));

    String smUrl = d.getPdfDownloadUrl();
    String localPath = System.getProperty("java.io.tmpdir") + "/" + d.getId().toString() + ".pdf";
    log("Downloading from %s", smUrl);

    client.getFileApi(smUrl, localPath);
    log("Downloaded to %s", localPath);
    File file = new File(localPath);
    assertThat("Downloaded file does not exist", file.exists(), equalTo(true));
    assertThat("Downloaded file is not empty", (int) file.length(), greaterThan(0));

  }

  // @Test
  public void testGetFile() throws Exception {
    // Integer jobId = 12141464; // this is a servemanager job that has at least one affidavit associated with it
    //
    // Show<Job> response = client.show(jobId);
    // assertThat(response, not(equalTo(null)));
    // assertThat(response.getData(), not(equalTo(null)));
    // assertThat(response.getData().getLinks(), not(equalTo(null)));
    // assertThat(response.getData().getId(), equalTo(jobId));
    //
    // Job job = response.getData();
    // assertThat(job.getDocuments(), not(equalTo(null)));
    // assertThat(job.getDocuments().size(), not(equalTo(0)));
    //
    // Document d = job.getDocuments().get(0);
    // assertThat(d, not(equalTo(null)));

    String smUrl = "https://sm-attachments.s3.amazonaws.com/rahdakdrd7tviertlgye4d226mi7?response-content-disposition=attachment%3B%20filename%3D%22smbc-1681060021-5132-image-6888662134da85aae764088b798d56b0.png%22%3B%20filename%2A%3DUTF-8%27%27smbc-1681060021-5132-image-6888662134da85aae764088b798d56b0.png&response-content-type=image%2Fpng&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAZKFIACFP4R3MB2O4%2F20230410%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20230410T151344Z&X-Amz-Expires=1800&X-Amz-SignedHeaders=host&X-Amz-Signature=5b34bd7fec17dabc19861d2975d6c4b61d8b850d97668d2b2677b7c2d2e358f4";
    String localPath = System.getProperty("java.io.tmpdir") + "/" + "test" + ".pdf";
    log("Downloading from %s", smUrl);

    client.getFile(smUrl, localPath);
    log("Downloaded to %s", localPath);
    File file = new File(localPath);
    assertThat("Downloaded file does not exist", file.exists(), equalTo(true));
    assertThat("Downloaded file is not empty", (int) file.length(), greaterThan(0));

  }
}
