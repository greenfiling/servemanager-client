package com.greenfiling.smclient;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
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
import com.greenfiling.smclient.model.WalletTransfer;
import com.greenfiling.smclient.model.WalletTransferSubmit;
import com.greenfiling.smclient.model.exchange.AgencyFilter;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.util.TestHelper;

public class WalletTransferClient_IntegrationTest {
  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(WalletTransferClient_IntegrationTest.class);

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();
  }

  @Test
  public void testCreateWalletTransfer() throws Exception {
    ApiHandle exchangeStaffHandle = TestHelper.getApiHandle_SopExchange(TestHelper.getExchangeStaffApiKey());
    FirmClient firmSetupClient = new FirmClient(exchangeStaffHandle);

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
    AgencyClient agencySetupClient = new AgencyClient(exchangeStaffHandle);
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

    // create job to test
    JobClient jobClient = new JobClient(firmHandle);

    JobSubmit newJob = TestHelper.getTestJobSubmit();
    newJob.setProcessServerCompanyId(processServerCompanyId);
    Show<Job> jobResponse = jobClient.create(newJob);

    assertThat(jobResponse, not(equalTo(null)));
    assertThat(jobResponse.getData(), not(equalTo(null)));
    assertThat(createFirmResponse.getData().getId(), greaterThan(0));

    Integer jobId = jobResponse.getData().getId();

    // add invoice to job
    SopExchangeJobManagementClient jobManagementClient = new SopExchangeJobManagementClient(exchangeStaffHandle);
    ServerInvoiceSubmit invoiceSubmit = new ServerInvoiceSubmit();
    invoiceSubmit.setTerms("Test Terms");
    ArrayList<LineItem> lineItems = new ArrayList<LineItem>();
    invoiceSubmit.setLineItemsAttributes(lineItems);
    Show<Invoice> invoiceResponse = jobManagementClient.createServerInvoice(jobId, invoiceSubmit);

    assertThat(invoiceResponse, not(equalTo(null)));
    assertThat(invoiceResponse.getData(), not(equalTo(null)));
    assertThat(invoiceResponse.getData().getId(), greaterThan(0));

    // test wallet transfer
    WalletTransferClient transferClient = new WalletTransferClient(exchangeStaffHandle);
    WalletTransferSubmit transferRecord = new WalletTransferSubmit();
    transferRecord.setJobId(jobId);
    transferRecord.setProcessServerCompanyId(processServerCompanyId);
    Show<WalletTransfer> transferResponse = transferClient.create(transferRecord);

    assertThat(transferResponse, not(equalTo(null)));
    assertThat(transferResponse.getData(), not(equalTo(null)));
    assertThat(transferResponse.getData().getInvoiceId(), equalTo(invoiceResponse.getData().getId()));
  }
}