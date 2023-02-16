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
      logger.info("testCreateJob_RemoteUrl - download_url not yet populated, sleeping");
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

}
