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

import java.io.File;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.greenfiling.smclient.model.Address;
import com.greenfiling.smclient.model.Job;
import com.greenfiling.smclient.model.JobSubmit;
import com.greenfiling.smclient.model.Links;
import com.greenfiling.smclient.model.Recipient;
import com.greenfiling.smclient.model.ServiceDocument;
import com.greenfiling.smclient.model.exchange.Show;

public class JobClient_FlexUpload {
  private static ApiHandle apiHandle = null;
  private static JobClient client = null;

  @BeforeClass
  public static void setUpClass() {
    apiHandle = new ApiHandle.Builder().apiKey(TestHelper.VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE).build();
    client = new JobClient(apiHandle);
  }

  @Test
  public void testCreateJob_Full() throws Exception {

    Recipient r = TestHelper.getRecipient();
    r.setDescription("Recipient created by testCreateJob_Full");

    ArrayList<Address> addresses = new ArrayList<>();
    Address a = TestHelper.getAddress();
    a.setLabel("Address created by testCreateJob_Full (1)");
    a.setPrimary(true);
    addresses.add(a);
    a = TestHelper.getAddress();
    a.setLabel("Address created by testCreateJob_Full (2)");
    a.setPrimary(false);
    addresses.add(a);

    ArrayList<ServiceDocument> docs = new ArrayList<>();
    ServiceDocument doc = new ServiceDocument();
    doc.setTitle("Subpoena");
    doc.setReceivedAt(OffsetDateTime.now());
    doc.setFileName("file_name.pdf");
    docs.add(doc);

    JobSubmit job = new JobSubmit();
    job.setClientCompanyId(TestHelper.VALID_CLIENT_COMPANY_ID);
    job.setClientContactId(TestHelper.VALID_CLIENT_CONTACT_ID);
    job.setProcessServerCompanyId(TestHelper.VALID_PROCESS_SERVER_COMPANY_ID);
    // job.setProcessServerContactId(TestHelper.VALID_PROCESS_SERVER_CONTACT_ID);
    // job.setEmployeeProcessServerId(TestHelper.VALID_EMPLOYEE_PROCESS_SERVER_ID);
    job.setCourtCaseId(TestHelper.VALID_COURT_CASE_ID);
    job.setDueDate(LocalDate.parse("2021-12-01"));
    job.setServiceInstructions("from testCreateJob_Full");
    job.setClientJobNumber("A623948z");
    job.setRush(true);
    job.setJobStatus(Job.JOB_STATUS_FILED);
    job.setRecipientAttributes(r);
    job.setAddressesAttributes(addresses);
    job.setDocumentsToBeServedAttributes(docs);
    job.setMiscAttachmentsAttributes(null);

    Show<Job> response = client.create(job);
    Links links = response.getData().getLinks();
    System.out.println("links.self = " + links.getSelf());
    System.out.println("type = " + response.getData().getType());
    System.out.println("updated_at = " + response.getData().getUpdatedAt());
    System.out.println("recipient.name = " + response.getData().getRecipient().getName());
    System.out.println("dueDate = " + response.getData().getDueDate());
    // System.out.println(Util.printObject(response.getData()));

    for (ServiceDocument d : response.getData().getDocumentsToBeServed()) {
      client.completeUpload(d.getUpload(), "application/pdf", new File(TestHelper.VALID_FILE_PATH_1));
    }

    response = client.show(response.getData().getId());
    System.out.println("links.self = " + links.getSelf());
    for (ServiceDocument d : response.getData().getDocumentsToBeServed()) {
      System.out.println("document_to_be_served, " + d.getUpload().getLinks().getDownloadUrl());
    }

    // JobSubmit newJob = new JobSubmit();
    // job.setCourtCaseId(1234);
    // job.setRush(true);
    // job.setDueDate(LocalDate.parse("2021-11-15"));
    //
    // ArrayList<ServiceDocument> docs = new ArrayList<ServiceDocument>();
    // ServiceDocument doc = new ServiceDocument();
    // doc.setTitle("Subpoena");
    // doc.setReceivedAt(OffsetDateTime.now());
    // doc.setFileName("file_name.pdf");
    // docs.add(doc);
    // job.setDocumentsToBeServedAttributes(docs);
    //
    // JobClient jobClient = new JobClient(apiHandle);
    // Show<Job> resp = client.create(job);
    //
    // ServiceDocument docObj = resp.getData().getDocumentsToBeServed().get(0);
    // jobClient.completeUpload(docObj.getUpload(), "application/pdf", new File("/path/to/file_name.pdf"));

    // JobSubmit newJob = new JobSubmit();
    // Recipient recipient = new Recipient();
    // recipient.setGender(Recipient.GENDER_MALE);
    // newJob.setRecipientAttributes(recipient);

  }

}
