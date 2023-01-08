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

import java.io.File;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.greenfiling.smclient.internal.Transaction;
import com.greenfiling.smclient.model.Job;
import com.greenfiling.smclient.model.JobSubmit;
import com.greenfiling.smclient.model.Links;
import com.greenfiling.smclient.model.ServiceDocument;
import com.greenfiling.smclient.model.exchange.Show;

public class Transactions_Manual {
  public static final String VALID_API_KEY = TestHelper.VALID_API_KEY;
  public static Job job;

  @BeforeClass
  public static void setUpClass() throws Exception {
    // System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
    ApiHandle handle = TestHelper.getApiHandle();
    JobClient client = new JobClient(handle);

    Job newJob = new Job();

    Show<Job> response = client.create(newJob);
    assertThat(response, not(equalTo(null)));
    job = response.getData();
  }

  private static void showTransaction(Transaction txn) {
    if (txn == null) {
      System.err.println("txn was null, nothing to show");
      return;
    }

    System.out.println(" REQUEST " + txn.getRequestType() + " " + txn.getRequestUrl());
    System.out.println(" REQUEST BODY " + txn.getRequestBody());
    System.out.println("RESPONSE  " + txn.getResponseCode() + " " + txn.getResponseLine());
    System.out.println("RESPONSE BODY " + txn.getResponseBody());
  }

  @Test
  public void testTransactions_GET_HappyPath() throws Exception {
    ApiHandle handle = TestHelper.getApiHandle();
    JobClient client = new JobClient(handle);

    Show<Job> response = client.show(job.getId());
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));

    ArrayList<Transaction> txns = handle.getTransactions();
    assertThat(txns, not(equalTo(null)));
    assertThat(txns.size(), equalTo(1));
    Transaction txn = txns.get(0);
    showTransaction(txn);
  }

  @Test
  public void testTransactions_accessibleViaApiClient() throws Exception {
    // the "correct" way to access transactions is via ApiHandle.getTransactions(), but I know there are cases where calling code will only have the
    // client, so I want to make sure that transactions are available via the ApiClient classes also
    ApiHandle handle = TestHelper.getApiHandle();
    JobClient client = new JobClient(handle);

    Show<Job> response = client.show(job.getId());
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));

    ArrayList<Transaction> txns = client.getTransactions();
    assertThat(txns, not(equalTo(null)));
    assertThat(txns.size(), equalTo(1));
    Transaction txn = txns.get(0);
    showTransaction(txn);
  }

  @Test
  public void testTransactions_multipleTransactionsAreDistinct() throws Exception {
    ApiHandle handle = TestHelper.getApiHandle();
    JobClient client = new JobClient(handle);

    Job newJob = new Job();

    // transaction 1 = POST to create the job
    Show<Job> response = client.create(newJob);
    assertThat(response, not(equalTo(null)));
    Links links = response.getData().getLinks();
    System.out.println("job created, links.self = " + links.getSelf());

    ArrayList<Transaction> txns = handle.getTransactions();
    assertThat(txns, not(equalTo(null)));
    assertThat(txns.size(), equalTo(1));
    Transaction txn = txns.get(0);
    assertThat(txn.getRequestType(), equalTo("POST"));
    showTransaction(txn);

    // transaction 2 = PUT to update the job
    newJob = response.getData();
    newJob.setDueDate(LocalDate.now());
    response = client.update(newJob.getId(), newJob);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));

    txns = handle.getTransactions();
    assertThat(txns, not(equalTo(null)));
    assertThat(txns.size(), equalTo(2));
    Transaction txn2 = txns.get(0);
    assertThat(txn2.getRequestType(), equalTo("PUT"));
    showTransaction(txn2);
  }

  @Test
  public void testTransactions_POST_HappyPath() throws Exception {
    ApiHandle handle = TestHelper.getApiHandle();
    JobClient client = new JobClient(handle);

    Job newJob = new Job();

    Show<Job> response = client.create(newJob);
    assertThat(response, not(equalTo(null)));
    Links links = response.getData().getLinks();
    System.out.println("job created, links.self = " + links.getSelf());

    ArrayList<Transaction> txns = handle.getTransactions();
    assertThat(txns, not(equalTo(null)));
    assertThat(txns.size(), equalTo(1));
    Transaction txn = txns.get(0);
    showTransaction(txn);
  }

  @Test

  public void testTransactions_PUT_doPutFile() throws Exception {
    ApiHandle handle = TestHelper.getApiHandle();
    JobClient client = new JobClient(handle);

    ArrayList<ServiceDocument> docs = new ArrayList<>();
    ServiceDocument doc = new ServiceDocument();
    doc.setTitle("Subpoena");
    doc.setReceivedAt(OffsetDateTime.now());
    doc.setFileName("file_name.pdf");
    docs.add(doc);

    JobSubmit job = new JobSubmit();
    job.setDocumentsToBeServedAttributes(docs);
    job.setMiscAttachmentsAttributes(null);

    Show<Job> response = client.create(job);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getData().getDocumentsToBeServed(), not(equalTo(null)));
    assertThat(response.getData().getDocumentsToBeServed().isEmpty(), equalTo(false));

    doc = response.getData().getDocumentsToBeServed().get(0);
    client.completeUpload(doc.getUpload(), "application/pdf", new File(TestHelper.VALID_FILE_PATH_1));

    ArrayList<Transaction> txns = handle.getTransactions();
    assertThat(txns, not(equalTo(null)));
    assertThat(txns.size(), equalTo(2));
    Transaction txn = txns.get(0);
    showTransaction(txn);

  }

  @Test
  public void testTransactions_PUT_HappyPath() throws Exception {
    ApiHandle handle = TestHelper.getApiHandle();
    JobClient client = new JobClient(handle);

    job.setDueDate(LocalDate.now());
    Show<Job> response = client.update(job.getId(), job);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));

    ArrayList<Transaction> txns = handle.getTransactions();
    assertThat(txns, not(equalTo(null)));
    assertThat(txns.size(), equalTo(1));
    Transaction txn = txns.get(0);
    showTransaction(txn);
  }

  @Test
  public void testTransactions_testConfigurableMaxTransactions() throws Exception {
    ApiHandle handle = new ApiHandle.Builder().apiKey(Transactions_Manual.VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE)
        .keepTransactions(2).build();
    JobClient client = new JobClient(handle);

    // transaction 1
    Show<Job> response = client.show(job.getId());
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));

    ArrayList<Transaction> txns = handle.getTransactions();
    assertThat(txns, not(equalTo(null)));
    assertThat(txns.size(), equalTo(1));
    Transaction txn = txns.get(0);
    showTransaction(txn);

    // transaction 2
    response = client.show(job.getId());
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));

    txns = handle.getTransactions();
    assertThat(txns, not(equalTo(null)));
    assertThat(txns.size(), equalTo(2));
    txn = txns.get(0);
    showTransaction(txn);

    // transaction 3
    response = client.show(job.getId());
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));

    txns = handle.getTransactions();
    assertThat(txns, not(equalTo(null)));
    assertThat(txns.size(), equalTo(2)); // still equal to 2
    txn = txns.get(0);
    showTransaction(txn);
  }

  @Test
  public void testTransactions_testConfigurableMaxTransactions_keepNone() throws Exception {
    ApiHandle handle = new ApiHandle.Builder().apiKey(Transactions_Manual.VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE)
        .keepTransactions(0).build();
    JobClient client = new JobClient(handle);

    // transaction 1
    Show<Job> response = client.show(job.getId());
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));

    ArrayList<Transaction> txns = handle.getTransactions();
    assertThat(txns, not(equalTo(null)));
    assertThat(txns.size(), equalTo(0)); // still equal to 2

    // transaction 2
    response = client.show(job.getId());
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));

    txns = handle.getTransactions();
    assertThat(txns, not(equalTo(null)));
    assertThat(txns.size(), equalTo(0)); // still equal to 2
  }

  @Test
  public void testTransactions_testDefaultMaxTransactions() throws Exception {
    ApiHandle handle = TestHelper.getApiHandle();
    JobClient client = new JobClient(handle);

    // transaction 1
    Show<Job> response = client.show(job.getId());
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));

    ArrayList<Transaction> txns = handle.getTransactions();
    assertThat(txns, not(equalTo(null)));
    assertThat(txns.size(), equalTo(1));
    Transaction txn = txns.get(0);
    showTransaction(txn);

    // transaction 2
    response = client.show(job.getId());
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));

    txns = handle.getTransactions();
    assertThat(txns, not(equalTo(null)));
    assertThat(txns.size(), equalTo(2));
    txn = txns.get(0);
    showTransaction(txn);

    // transaction 3
    response = client.show(job.getId());
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));

    txns = handle.getTransactions();
    assertThat(txns, not(equalTo(null)));
    assertThat(txns.size(), equalTo(3));
    txn = txns.get(0);
    showTransaction(txn);

    // transaction 4
    response = client.show(job.getId());
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));

    txns = handle.getTransactions();
    assertThat(txns, not(equalTo(null)));
    assertThat(txns.size(), equalTo(3)); // still equal to 3
    txn = txns.get(0);
    showTransaction(txn);
  }

  @Test
  public void testTransactions_transactionsArePerApiHandle() throws Exception {
    // two separate clients, backed by the same ApiHandle
    ApiHandle handle = TestHelper.getApiHandle();
    JobClient client = new JobClient(handle);

    Job newJob = new Job();

    // transaction 1 = POST to create the job
    Show<Job> response = client.create(newJob);
    assertThat(response, not(equalTo(null)));
    Links links = response.getData().getLinks();
    System.out.println("job created, links.self = " + links.getSelf());

    ArrayList<Transaction> txns = handle.getTransactions();
    assertThat(txns, not(equalTo(null)));
    assertThat(txns.size(), equalTo(1));
    Transaction txn = txns.get(0);
    assertThat(txn.getRequestType(), equalTo("POST"));
    showTransaction(txn);

    // transaction 2 = PUT to update the job
    JobClient client2 = new JobClient(handle);

    newJob = response.getData();
    newJob.setDueDate(LocalDate.now());
    response = client2.update(newJob.getId(), newJob);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));

    txns = handle.getTransactions();
    assertThat(txns, not(equalTo(null)));
    assertThat(txns.size(), equalTo(2));
    Transaction txn2 = txns.get(0);
    assertThat(txn2.getRequestType(), equalTo("PUT"));
    showTransaction(txn2);
  }
}
