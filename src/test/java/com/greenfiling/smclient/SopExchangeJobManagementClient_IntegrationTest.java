/**
 * Copyright 2026 Green Filing, LLC
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
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greenfiling.smclient.model.Account;
import com.greenfiling.smclient.model.Agency;
import com.greenfiling.smclient.model.AgencyConnection;
import com.greenfiling.smclient.model.AgencyConnectionSubmit;
import com.greenfiling.smclient.model.FirmApiKey;
import com.greenfiling.smclient.model.FirmSubmit;
import com.greenfiling.smclient.model.Invoice;
import com.greenfiling.smclient.model.Job;
import com.greenfiling.smclient.model.JobSubmit;
import com.greenfiling.smclient.model.LineItem;
import com.greenfiling.smclient.model.ServerInvoiceSubmit;
import com.greenfiling.smclient.model.exchange.AgencyFilter;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.util.TestHelper;

public class SopExchangeJobManagementClient_IntegrationTest {
  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(SopExchangeJobManagementClient_IntegrationTest.class);

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();
  }

  private static Show<Invoice> addInvoiceToJob(Integer jobId) throws Exception {
    ServerInvoiceSubmit invoiceSubmit = new ServerInvoiceSubmit();
    invoiceSubmit.setTerms("Custom Terms");
    ArrayList<LineItem> lineItems = new ArrayList<LineItem>();
    invoiceSubmit.setLineItemsAttributes(lineItems);
    ApiHandle itxStaffHandle = TestHelper.getApiHandle_SopExchange(TestHelper.getExchangeStaffApiKey());
    SopExchangeJobManagementClient exchangeJobClient = new SopExchangeJobManagementClient(itxStaffHandle);
    Show<Invoice> invoiceResponse = exchangeJobClient.createServerInvoice(jobId, invoiceSubmit);
    // TODO: throw exception if does not return 200
    return invoiceResponse;
  }

  /**
   * Create a new SopExchange job and return its jobId.
   * 
   * @return
   * @throws Exception
   */
  private static Integer getNewJobId() throws Exception {
    ApiHandle setupHandle = TestHelper.getApiHandle_SopExchange(TestHelper.getExchangeStaffApiKey());
    FirmClient firmSetupClient = new FirmClient(setupHandle);

    // create firm to test
    FirmSubmit createRecord = new FirmSubmit();
    String companyName = "Test Firm";
    createRecord.setCompanyName(companyName);

    Show<Account> createFirmResponse = firmSetupClient.create(createRecord);

    assertThat(createFirmResponse, not(equalTo(null)));
    assertThat(createFirmResponse.getData(), not(equalTo(null)));
    assertThat(createFirmResponse.getData().getLinks(), not(equalTo(null)));
    assertThat(createFirmResponse.getData().getId(), greaterThan(0));

    Integer firmId = createFirmResponse.getData().getId();

    // create firm api key to test
    Show<FirmApiKey> createKeyResponse = firmSetupClient.createFirmApiKey(firmId);

    assertThat(createKeyResponse, not(equalTo(null)));
    assertThat(createKeyResponse.getData(), not(equalTo(null)));
    assertThat(createKeyResponse.getData().getId(), not(equalTo(null)));
    assertThat(createKeyResponse.getData().getKey(), not(equalTo(null)));

    String firmKey = createKeyResponse.getData().getKey();
    ApiHandle firmHandle = TestHelper.getApiHandle_SopExchange(firmKey);

    // get agency to test
    AgencyClient agencySetupClient = new AgencyClient(setupHandle);
    AgencyFilter filter = new AgencyFilter();

    Index<Agency> agencyListResponse = agencySetupClient.index(filter);
    assertThat(agencyListResponse, not(equalTo(null)));
    assertThat(agencyListResponse.getLinks(), not(equalTo(null)));
    assertThat(agencyListResponse.getData(), not(equalTo(null)));
    assertTrue(agencyListResponse.getData().size() > 0);

    Integer testAgencyId = agencyListResponse.getData().get(0).getId();

    // create agency connection to test
    AgencyConnectionClient connectionClient = new AgencyConnectionClient(firmHandle);
    AgencyConnectionSubmit connectionRecord = new AgencyConnectionSubmit();
    connectionRecord.setAgencyAccountId(testAgencyId);

    Show<AgencyConnection> setupConnectionResponse = connectionClient.create(connectionRecord);

    assertThat(setupConnectionResponse, not(equalTo(null)));
    assertThat(setupConnectionResponse.getData(), not(equalTo(null)));
    assertThat(setupConnectionResponse.getData().getProcessServerCompanyId(), not(equalTo(null)));
    assertThat(setupConnectionResponse.getData().getStatus(), equalTo("connected"));

    Integer processServerCompanyId = setupConnectionResponse.getData().getProcessServerCompanyId();

    JobClient jobClient = new JobClient(firmHandle);

    JobSubmit newJob = TestHelper.getTestJobSubmit();
    newJob.setProcessServerCompanyId(processServerCompanyId);
    Show<Job> jobResponse = jobClient.create(newJob);

    assertThat(jobResponse, not(equalTo(null)));
    assertThat(jobResponse.getData(), not(equalTo(null)));
    assertTrue(jobResponse.getData().getId() > 0);

    Integer jobId = jobResponse.getData().getId();

    return jobId;
  }

  @Test
  public void testCreateServerInvoice() throws Exception {

    Integer jobId = getNewJobId();

    ApiHandle itxStaffHandle = TestHelper.getApiHandle_SopExchange(TestHelper.getExchangeStaffApiKey());
    SopExchangeJobManagementClient exchangeJobClient = new SopExchangeJobManagementClient(itxStaffHandle);
    ServerInvoiceSubmit invoiceSubmit = new ServerInvoiceSubmit();
    invoiceSubmit.setTerms("Custom Terms");
    ArrayList<LineItem> lineItems = new ArrayList<LineItem>();
    LineItem li = new LineItem();
    li.setName("Routine service of process");
    li.setDescription("Personal service, Denver County");
    li.setUnitCost("60.50");
    li.setQuantity("1");
    lineItems.add(li);
    invoiceSubmit.setLineItemsAttributes(lineItems);

    Show<Invoice> invoiceResponse = exchangeJobClient.createServerInvoice(jobId, invoiceSubmit);

    assertThat(invoiceResponse, not(equalTo(null)));
    assertThat(invoiceResponse.getData(), not(equalTo(null)));
    assertFalse(invoiceResponse.getData().getLocked());
  }

  @Test
  public void testLockServerInvoice() throws Exception {
    Integer jobId = getNewJobId();
    Show<Invoice> addedInvoice = addInvoiceToJob(jobId);

    Integer invoiceId = addedInvoice.getData().getId();
    assertFalse(addedInvoice.getData().getLocked());

    ApiHandle itxStaffHandle = TestHelper.getApiHandle_SopExchange(TestHelper.getExchangeStaffApiKey());
    SopExchangeJobManagementClient exchangeJobClient = new SopExchangeJobManagementClient(itxStaffHandle);

    Show<Invoice> invoiceResponse = exchangeJobClient.lockServerInvoice(jobId);

    assertThat(invoiceResponse, not(equalTo(null)));
    assertThat(invoiceResponse.getData(), not(equalTo(null)));
    assertThat(invoiceResponse.getData().getId(), equalTo(invoiceId));
    assertTrue(invoiceResponse.getData().getLocked());

  }

}