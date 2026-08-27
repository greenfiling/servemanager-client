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
import static org.junit.Assert.assertTrue;

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
import com.greenfiling.smclient.model.exchange.AgencyFilter;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.util.TestHelper;

public class AgencyConnectionClient_IntegrationTest {
  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(AgencyConnectionClient_IntegrationTest.class);

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();
  }

  @Test
  public void testCreateAgencyConnection() throws Exception {
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

    // get agency to test
    AgencyClient agencySetupClient = new AgencyClient(setupHandle);
    AgencyFilter filter = new AgencyFilter();

    Index<Agency> agencyListResponse = agencySetupClient.index(filter);
    assertThat(agencyListResponse, not(equalTo(null)));
    assertThat(agencyListResponse.getLinks(), not(equalTo(null)));
    assertThat(agencyListResponse.getData(), not(equalTo(null)));
    assertTrue(agencyListResponse.getData().size() > 0);

    Integer testAgencyId = agencyListResponse.getData().get(0).getId();

    // test create agency connection
    ApiHandle handle = TestHelper.getApiHandle_SopExchange(firmKey);
    AgencyConnectionClient client = new AgencyConnectionClient(handle);
    AgencyConnectionSubmit record = new AgencyConnectionSubmit();
    record.setAgencyAccountId(testAgencyId);

    Show<AgencyConnection> response = client.create(record);

    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getData().getProcessServerCompanyId(), not(equalTo(null)));
    assertThat(response.getData().getStatus(), equalTo("connected"));
  }

  @Test
  public void testShowAgencyConnection() throws Exception {
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
    ApiHandle setupConnectionHandle = TestHelper.getApiHandle_SopExchange(firmKey);
    AgencyConnectionClient connectionClient = new AgencyConnectionClient(setupConnectionHandle);
    AgencyConnectionSubmit connectionRecord = new AgencyConnectionSubmit();
    connectionRecord.setAgencyAccountId(testAgencyId);

    Show<AgencyConnection> setupConnectionResponse = connectionClient.create(connectionRecord);

    assertThat(setupConnectionResponse, not(equalTo(null)));
    assertThat(setupConnectionResponse.getData(), not(equalTo(null)));
    assertThat(setupConnectionResponse.getData().getProcessServerCompanyId(), not(equalTo(null)));
    assertThat(setupConnectionResponse.getData().getStatus(), equalTo("connected"));

    Integer processServerCompanyId = setupConnectionResponse.getData().getProcessServerCompanyId();

    // test show agency connection

    Show<AgencyConnection> response = connectionClient.show(processServerCompanyId);

    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getData().getProcessServerCompanyId(), not(equalTo(null)));
    assertThat(response.getData().getApiKey(), not(equalTo(null)));
    assertThat(response.getData().getStatus(), equalTo("connected"));
  }

}