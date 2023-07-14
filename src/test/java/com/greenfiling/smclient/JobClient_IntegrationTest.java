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
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.greaterThan;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greenfiling.smclient.internal.JsonHandle;
import com.greenfiling.smclient.model.Address;
import com.greenfiling.smclient.model.Attachment;
import com.greenfiling.smclient.model.Invoice;
import com.greenfiling.smclient.model.Job;
import com.greenfiling.smclient.model.JobSubmit;
import com.greenfiling.smclient.model.LineItem;
import com.greenfiling.smclient.model.Links;
import com.greenfiling.smclient.model.Recipient;
import com.greenfiling.smclient.model.ServiceDocument;
import com.greenfiling.smclient.model.exchange.FilterDateRange;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.JobFilter;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.internal.FilterBase;
import com.greenfiling.smclient.model.internal.JobBase;
import com.greenfiling.smclient.util.TestHelper;

public class JobClient_IntegrationTest {
  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(JobClient_IntegrationTest.class);

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();
  }

  @Test
  public void testCreateJob_Full_JobSubmit() throws Exception {
    ApiHandle apiHandle = TestHelper.getApiHandle();
    JobClient client = new JobClient(apiHandle);

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

    ArrayList<ServiceDocument> serveDocs = new ArrayList<>();
    ServiceDocument serveDoc = new ServiceDocument();
    serveDoc.setTitle("Service Document Title");
    serveDoc.setReceivedAt(OffsetDateTime.now());
    serveDoc.setFileName("file_name.pdf");
    serveDoc.setExternalUrl(TestHelper.REMOTE_FILE);
    serveDocs.add(serveDoc);

    ArrayList<Attachment> miscDocs = new ArrayList<>();
    Attachment miscDoc = new Attachment();
    miscDoc.setTitle("Attachment Title");
    miscDoc.setFileName("file_name.pdf");
    miscDocs.add(miscDoc);
    Attachment affidavit = new Attachment();
    affidavit.setTitle("Attachment Title (affidavit)");
    affidavit.setFileName("file_name2.pdf");
    affidavit.setAffidavit(true);
    affidavit.setSigned(true);
    miscDocs.add(affidavit);

    Integer transactionRef = TestHelper.getRandom();
    Integer supplierCostId = 28551; // 90210, sla_id=1, zone_id=1, job_type_id=1
    Integer pageCount = 101;
    Double retailPrice = 202.02;

    JobSubmit newJob = TestHelper.getTestJobSubmit();
    newJob.setClientCompanyId(TestHelper.VALID_CLIENT_COMPANY_ID);
    newJob.setClientContactId(TestHelper.VALID_CLIENT_CONTACT_ID);
    newJob.setProcessServerCompanyId(TestHelper.VALID_PROCESS_SERVER_COMPANY_ID);
    // newJob.setProcessServerContactId(TestHelper.VALID_PROCESS_SERVER_CONTACT_ID);
    // newJob.setEmployeeProcessServerId(TestHelper.VALID_EMPLOYEE_PROCESS_SERVER_ID);
    newJob.setCourtCaseId(TestHelper.VALID_COURT_CASE_ID);
    newJob.setDueDate(LocalDate.parse("2021-12-01"));
    newJob.setServiceInstructions(newJob.getServiceInstructions() + "\nfrom testCreateJob_Full");
    newJob.setClientJobNumber(newJob.getClientJobNumber() + " A623948z");
    newJob.setRush(true);
    newJob.setRecipientAttributes(r);
    newJob.setAddressesAttributes(addresses);
    newJob.setDocumentsToBeServedAttributes(serveDocs);
    newJob.setMiscAttachmentsAttributes(miscDocs);
    newJob.setClientTransactionRef(transactionRef);
    newJob.setQuotedSupplierCostId(supplierCostId);
    newJob.setQuotedRetailPrice(retailPrice);
    newJob.setQuotedPageCount(pageCount);

    Show<Job> response = client.create(newJob);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getData().getLinks(), not(equalTo(null)));
    assertThat(response.getData().getType(), equalTo("job"));
    assertThat(response.getData().getId(), greaterThan(0));

    Links links = response.getData().getLinks();
    log("links.self = %s", links.getSelf());

    // Make sure, as much as we are able, that the data returned is the data we provided
    Job job = response.getData();
    assertThat(Integer.valueOf(job.getServeManagerJobNumber()), greaterThan(0));
    assertThat(job.getJobStatus(), equalTo(newJob.getJobStatus()));
    assertThat(job.getClientJobNumber(), equalTo(newJob.getClientJobNumber()));
    assertThat(job.getServiceInstructions(), equalTo(newJob.getServiceInstructions()));

    // TODO disabled for now because setting due date doesn't work for ITX partner accounts. See
    // https://github.com/greenfiling/servemanager-client/issues/60
    // assertThat(job.getDueDate(), equalTo(newJob.getDueDate()));
    assertThat(job.getRush(), equalTo(newJob.getRush()));

    assertThat(job.getClientTransactionRef(), equalTo(transactionRef));
    assertThat(job.getQuotedSupplierCostId(), equalTo(supplierCostId));
    assertThat(job.getQuotedPageCount(), equalTo(pageCount));
    // There is a bug in the API such that the value returned for quoted_retail_price us that QRP from the supplier_cost_id, NOT the value provided.
    // Per chad the correct value is set in the DB, the API just displays the wrong value. When this issue is fixed, re-enable this test (see github
    // issue #55
    // assertThat(job.getQuotedRetailPrice(), equalTo(retailPrice));

    assertThat(job.getRecipient(), not(equalTo(null)));
    assertThat(job.getRecipient().getAge(), equalTo(newJob.getRecipientAttributes().getAge()));
    assertThat(job.getRecipient().getName(), equalTo(newJob.getRecipientAttributes().getName()));
    assertThat(job.getRecipient().getType(), equalTo(newJob.getRecipientAttributes().getType()));
    assertThat(job.getRecipient().getAgentForService(), equalTo(newJob.getRecipientAttributes().getAgentForService()));

    assertThat(job.getClientCompany(), not(equalTo(null)));
    assertThat(job.getClientCompany().getLinks(), not(equalTo(null)));
    assertThat(job.getClientCompany().getLinks().getSelf(), endsWith(String.valueOf(newJob.getClientCompanyId())));

    assertThat(job.getClientContact(), not(equalTo(null)));
    assertThat(job.getClientContact().getId(), equalTo(newJob.getClientContactId()));

    assertThat(job.getProcessServerCompany(), not(equalTo(null)));
    assertThat(job.getProcessServerCompany().getLinks(), not(equalTo(null)));
    assertThat(job.getProcessServerCompany().getLinks().getSelf(), endsWith(String.valueOf(newJob.getProcessServerCompanyId())));

    assertThat(job.getCourtCase(), not(equalTo(null)));
    assertThat(job.getCourtCase().getId(), equalTo(newJob.getCourtCaseId()));

    // This operates on the assumption that the created addresses will be returned in the same order we sent them, which is true so far but might
    // break
    assertThat(job.getAddressesCount(), equalTo(2));
    assertThat(job.getAddresses(), not(equalTo(null)));
    assertThat(job.getAddresses().size(), equalTo(2));
    Address a1 = job.getAddresses().get(0);
    assertThat(a1.getLabel(), equalTo(addresses.get(0).getLabel()));
    assertThat(a1.getPrimary(), equalTo(addresses.get(0).getPrimary()));
    Address a2 = job.getAddresses().get(1);
    assertThat(a2.getLabel(), equalTo(addresses.get(1).getLabel()));
    assertThat(a2.getPrimary(), equalTo(addresses.get(1).getPrimary()));

    assertThat(job.getDocumentsToBeServedCount(), equalTo(1));
    assertThat(job.getDocumentsToBeServed(), not(equalTo(null)));
    assertThat(job.getDocumentsToBeServed().size(), equalTo(1));
    assertThat(job.getDocumentsToBeServed().get(0), not(equalTo(null)));
    assertThat(job.getDocumentsToBeServed().get(0).getTitle(), equalTo(serveDoc.getTitle()));

    assertThat(job.getMiscAttachmentsCount(), equalTo(2));
    assertThat(job.getMiscAttachments(), not(equalTo(null)));
    assertThat(job.getMiscAttachments().size(), equalTo(2));
    assertThat(job.getMiscAttachments().get(0), not(equalTo(null)));
    assertThat(job.getMiscAttachments().get(0).getTitle(), equalTo(miscDoc.getTitle()));
    assertThat(job.getMiscAttachments().get(0).getAffidavit(), equalTo(miscDoc.getAffidavit()));
    assertThat(job.getMiscAttachments().get(0).getSigned(), equalTo(miscDoc.getSigned()));
    assertThat(job.getMiscAttachments().get(1), not(equalTo(null)));
    assertThat(job.getMiscAttachments().get(1).getTitle(), equalTo(affidavit.getTitle()));
    // These two tests should be turned back on when these fields are settable in the API, see github issue #54
    // assertThat(job.getMiscAttachments().get(1).getAffidavit(), equalTo(affidavit.getAffidavit()));
    // assertThat(job.getMiscAttachments().get(1).getSigned(), equalTo(affidavit.getSigned()));
  }

  @Test
  public void testCreateJob_HappyPath() throws Exception {
    ApiHandle apiHandle = TestHelper.getApiHandle();
    JobClient client = new JobClient(apiHandle);

    Job newJob = TestHelper.getTestJob();

    Show<Job> response = client.create(newJob);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getData().getLinks(), not(equalTo(null)));
    assertThat(response.getData().getType(), equalTo("job"));
    assertThat(response.getData().getId(), greaterThan(0));

    Links links = response.getData().getLinks();
    log("job created, links.self = %s", links.getSelf());
  }

  @Test
  public void testCreateJob_JobTypeId() throws Exception {

    ApiHandle apiHandle = TestHelper.getApiHandle();
    JobClient client = new JobClient(apiHandle);

    Job newJob = TestHelper.getTestJob();
    newJob.setJobTypeId(JobBase.JOB_TYPE_SOP);

    Show<Job> response = client.create(newJob);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getData().getLinks(), not(equalTo(null)));
    assertThat(response.getData().getJobTypeId(), equalTo(JobBase.JOB_TYPE_SOP));

    Links links = response.getData().getLinks();
    log("job created, links.self = %s", links.getSelf());
  }

  // TODO disabled for now because setting due date doesn't work for ITX partner accounts. See
  // https://github.com/greenfiling/servemanager-client/issues/60
  // @Test
  // public void testCreateJob_DueDate() throws Exception {
  //
  // ApiHandle apiHandle = TestHelper.getApiHandle();
  // JobClient client = new JobClient(apiHandle);
  //
  // Job newJob = TestHelper.getTestJob();
  // newJob.setDueDate(LocalDate.parse("2021-07-18"));
  //
  // Show<Job> response = client.create(newJob);
  // assertThat(response, not(equalTo(null)));
  // assertThat(response.getData(), not(equalTo(null)));
  // assertThat(response.getData().getLinks(), not(equalTo(null)));
  // assertThat(response.getData().getDueDate(), equalTo("2021-07-18"));
  //
  // Links links = response.getData().getLinks();
  // log("job created, links.self = %s", links.getSelf());
  // }

  @Test
  public void testIndexJob_Filter_DateRange() throws Exception {
    ApiHandle apiHandle = TestHelper.getApiHandle();
    JobClient client = new JobClient(apiHandle);

    JobFilter filter = new JobFilter();
    filter.setDateRange(new FilterDateRange());
    filter.getDateRange().setType(FilterDateRange.TYPE_CREATED_AT);
    filter.getDateRange().setMax(OffsetDateTime.parse("2021-11-01T00:00:00-00:00"));

    Index<Job> response = client.index(filter);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getLinks(), not(equalTo(null)));
    Links links = response.getLinks();
    log("links.self = %s", links.getSelf());

    ArrayList<Job> jobs = response.getData();
    log("Number of jobs in response: %s", jobs.size());

    // TODO this could be improved to demonstrate that the filter is actually working

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
    // log("Matching jobs:");
    // while (resp != null) {
    // for (Job j : resp.getData()) {
    // log(" - JobId: " + j.getId() + ", URL = " + j.getLinks().getSelf());
    // }
    // resp = jobClient.getNext(resp);
    // }

  }

  // Create a job that has a unique client job number, then filter on it + un-archived
  @Test
  public void testIndexJob_Filter_FreeText() throws Exception {
    ApiHandle apiHandle = TestHelper.getApiHandle();
    JobClient client = new JobClient(apiHandle);

    String uniqueString = TestHelper.getUniqueString();

    Job newJob = TestHelper.getTestJob();
    newJob.setClientJobNumber(newJob.getClientJobNumber() + " " + uniqueString);
    Show<Job> createResponse = client.create(newJob);
    assertThat(createResponse, not(equalTo(null)));
    assertThat(createResponse.getData(), not(equalTo(null)));
    assertThat(createResponse.getData().getLinks(), not(equalTo(null)));
    assertThat(createResponse.getData().getId(), greaterThan(0));
    log("created for testing job links.self = %s", createResponse.getData().getLinks().getSelf());

    log("filtering for unique string %s", uniqueString);
    JobFilter filter = new JobFilter();
    // filter.setArchiveState(FilterBase.ARCHIVE_STATE_ACTIVE);
    filter.setQ(uniqueString);

    Index<Job> response = client.index(filter);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getLinks(), not(equalTo(null)));
    Links links = response.getLinks();
    log("links.self = %s", links.getSelf());

    ArrayList<Job> jobs = response.getData();
    log("Number of jobs in response: %s", jobs.size());

    // There should only be one job matching our unique string
    assertThat(jobs.size(), equalTo(1));
    Job job = jobs.get(0);
    log("Found job %s, client job number: %s", job.getId(), job.getClientJobNumber());
  }

  // Simplest test I could think of. filter on archived and active. For each filter, make sure no job in the first page of listing has a different
  // status. This feels like two tests, but I think it's only really valid if you run both in the same method.
  @Test
  public void testIndexJob_Filter_HappyPath() throws Exception {
    ApiHandle apiHandle = TestHelper.getApiHandle();
    JobClient client = new JobClient(apiHandle);

    JobFilter filter = new JobFilter();
    filter.setArchiveState(FilterBase.ARCHIVE_STATE_ARCHIVED);

    Index<Job> response = client.index(filter);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getLinks(), not(equalTo(null)));

    Links links = response.getLinks();
    log("links.self = %s", links.getSelf());

    boolean sawIncorrect = false;
    for (Job j : response.getData()) {
      if (j.getArchivedAt() == null) {
        sawIncorrect = true;
        break;
      }
    }
    assertThat("Saw active job in archived-only filter", sawIncorrect, equalTo(false));

    filter.setArchiveState(FilterBase.ARCHIVE_STATE_ACTIVE);

    response = client.index(filter);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getLinks(), not(equalTo(null)));

    links = response.getLinks();
    log("links.self = %s", links.getSelf());

    sawIncorrect = false;
    for (Job j : response.getData()) {
      if (j.getArchivedAt() != null) {
        sawIncorrect = true;
        break;
      }
    }
    assertThat("Saw archived job in active-only filter", sawIncorrect, equalTo(false));

  }

  @Test
  public void testIndexJob_HappyPath() throws Exception {
    ApiHandle apiHandle = TestHelper.getApiHandle();
    JobClient client = new JobClient(apiHandle);

    Index<Job> response = client.index();
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getLinks(), not(equalTo(null)));

    Links links = response.getLinks();
    log("links.self = %s", links.getSelf());

    ArrayList<Job> jobs = response.getData();
    log("Number of jobs in response: %s", jobs.size());
  }

  @Test
  public void testShowJob_HappyPath() throws Exception {
    ApiHandle apiHandle = TestHelper.getApiHandle();
    JobClient client = new JobClient(apiHandle);

    Job newJob = TestHelper.getTestJob();
    Show<Job> createResponse = client.create(newJob);
    assertThat(createResponse, not(equalTo(null)));
    assertThat(createResponse.getData(), not(equalTo(null)));
    assertThat(createResponse.getData().getLinks(), not(equalTo(null)));
    assertThat(createResponse.getData().getId(), greaterThan(0));
    log("created for testing job links.self = %s", createResponse.getData().getLinks().getSelf());

    Integer jobId = createResponse.getData().getId();

    Show<Job> response = client.show(jobId);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getData().getLinks(), not(equalTo(null)));
    assertThat(response.getData().getId(), equalTo(jobId));

    Links links = response.getData().getLinks();
    log("links.self = %s", links.getSelf());
    log("type = %s", response.getData().getType());
    log("updated_at = %s", response.getData().getUpdatedAt());
    log("recipient.name = %s", response.getData().getRecipient().getName());
    log("dueDate = %s", response.getData().getDueDate());
  }

  @Test
  public void testShowJob_InvoiceLineItems() throws Exception {
    ApiHandle apiHandle = TestHelper.getApiHandle();
    JobClient client = new JobClient(apiHandle);

    // The correct fix for this is to filter on jobs that have a non-null process_server_invoice, but that filter isn't available. Chad said he'd look
    // into it
    Show<Job> response = client.show(11487024); // TODO magic number
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

  @Test
  public void testShowJob_NoSuchObject() throws Exception {
    boolean caughtException = false;
    ApiHandle apiHandle = TestHelper.getApiHandle();
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

  // TODO should create a copy of testCreateJob_Full_JobSubmit called testCreateJob_Full_Job which uses Job object instead of JobSubmit object to
  // create the job to test the conversion of Job -> JobSubmit for the purposes of creation
  @Test
  public void testUpdateJob_HappyPath() throws Exception {
    ApiHandle apiHandle = TestHelper.getApiHandle();
    JobClient client = new JobClient(apiHandle);

    Job newJob = TestHelper.getTestJob();
    Show<Job> createResponse = client.create(newJob);
    assertThat(createResponse, not(equalTo(null)));
    assertThat(createResponse.getData(), not(equalTo(null)));
    assertThat(createResponse.getData().getLinks(), not(equalTo(null)));
    assertThat(createResponse.getData().getId(), greaterThan(0));
    log("created for testing job links.self = %s", createResponse.getData().getLinks().getSelf());

    Integer jobId = createResponse.getData().getId();
    String uniqueString = TestHelper.getUniqueString();

    newJob.setServiceInstructions(newJob.getServiceInstructions() + " -- " + uniqueString);

    Show<Job> response = client.update(jobId, newJob);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getData().getLinks(), not(equalTo(null)));
    assertThat(response.getData().getId(), equalTo(jobId));
    assertThat(response.getData().getServiceInstructions(), endsWith(uniqueString));
    Links links = response.getData().getLinks();
    log("job created, links.self = %s", links.getSelf());
  }
}
