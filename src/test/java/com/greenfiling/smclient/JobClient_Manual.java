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

package com.greenfiling.smclient;

import static com.greenfiling.smclient.TestHelper.log;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greenfiling.smclient.internal.JsonHandle;
import com.greenfiling.smclient.model.Address;
import com.greenfiling.smclient.model.Invoice;
import com.greenfiling.smclient.model.Job;
import com.greenfiling.smclient.model.JobSubmit;
import com.greenfiling.smclient.model.LineItem;
import com.greenfiling.smclient.model.Links;
import com.greenfiling.smclient.model.Note;
import com.greenfiling.smclient.model.Recipient;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;

public class JobClient_Manual {
  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(JobClient_Manual.class);

  private static ApiHandle apiHandle = null;
  private static JobClient client = null;

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();

    apiHandle = TestHelper.getApiHandle();
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

    JobSubmit job = TestHelper.getTestJobSubmit();
    job.setClientCompanyId(TestHelper.VALID_CLIENT_COMPANY_ID);
    job.setClientContactId(TestHelper.VALID_CLIENT_CONTACT_ID);
    job.setProcessServerCompanyId(TestHelper.VALID_PROCESS_SERVER_COMPANY_ID);
    // job.setProcessServerContactId(TestHelper.VALID_PROCESS_SERVER_CONTACT_ID);
    // job.setEmployeeProcessServerId(TestHelper.VALID_EMPLOYEE_PROCESS_SERVER_ID);
    job.setCourtCaseId(TestHelper.VALID_COURT_CASE_ID);
    job.setDueDate(LocalDate.parse("2021-12-01"));
    job.setServiceInstructions(job.getServiceInstructions() + "\nfrom testCreateJob_Full");
    job.setClientJobNumber(job.getClientJobNumber() + " A623948z");
    job.setRush(true);
    job.setJobStatus(Job.JOB_STATUS_FILED);
    job.setRecipientAttributes(r);
    job.setAddressesAttributes(addresses);
    job.setDocumentsToBeServedAttributes(null);
    job.setMiscAttachmentsAttributes(null);

    Show<Job> response = client.create(job);
    Links links = response.getData().getLinks();
    System.out.println("links.self = " + links.getSelf());
    System.out.println("type = " + response.getData().getType());
    System.out.println("updated_at = " + response.getData().getUpdatedAt());
    System.out.println("recipient.name = " + response.getData().getRecipient().getName());
    System.out.println("dueDate = " + response.getData().getDueDate());
    // System.out.println(Util.printObject(response.getData()));
  }

  @Test
  public void testIndexJob_HappyPath() throws Exception {
    Index<Job> response = client.index();
    Links links = response.getLinks();
    System.out.println("links.self = " + links.getSelf());

    ArrayList<Job> courts = response.getData();
    System.out.println("Number of jobs in response: " + courts.size());

    System.out.println("re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(response));
  }

  @Test
  public void testIndexNotesJob_HappyPath() throws Exception {
    Index<Note> response = client.indexNotes(6566221);
    Links links = response.getLinks();
    System.out.println("links.self = " + links.getSelf());

    ArrayList<Note> courts = response.getData();
    System.out.println("Number of jobs in response: " + courts.size());

    System.out.println("re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(response));
  }

  @Test
  public void testShowJob_HappyPath() throws Exception {
    Show<Job> response = client.show(6566221);
    Links links = response.getData().getLinks();
    System.out.println("links.self = " + links.getSelf());
    System.out.println("type = " + response.getData().getType());
    System.out.println("updated_at = " + response.getData().getUpdatedAt());

    System.out.println("re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(response));
  }

  @Test
  public void testShowJob_InvoiceLineItems() throws Exception {
    Show<Job> response = client.show(11487024);
    Links links = response.getData().getLinks();
    log("links.self = %s", links.getSelf());
    log("type = %s", response.getData().getType());
    log("updated_at = %s", response.getData().getUpdatedAt());
    log("re-serialized: %s", JsonHandle.get().getGsonWithNulls().toJson(response));

    assertThat(response, notNullValue());
    assertThat(response.getData(), notNullValue());
    Invoice invoice = response.getData().getProcessServerInvoice();
    assertThat(invoice, notNullValue());
    assertThat(invoice.getLineItems(), notNullValue());
    assertThat(invoice.getLineItems().isEmpty(), not(equalTo(true)));

    for (LineItem li : invoice.getLineItems()) {
      log("line item: %s, name: %s, description: %s, %s*%s, %.02f/%.02f, %.02f, %.02f", li.getId(), li.getName(), li.getDescription(),
          li.getUnitCost(), li.getQuantity(), li.getTaxRate(), li.getTaxAmount(), li.getSubtotal(), li.getTotal());
    }
  }

}
