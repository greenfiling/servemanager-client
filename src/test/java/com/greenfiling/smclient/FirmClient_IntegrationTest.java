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
import com.greenfiling.smclient.model.FirmApiKey;
import com.greenfiling.smclient.model.FirmSubmit;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.util.TestHelper;

public class FirmClient_IntegrationTest {
  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(FirmClient_IntegrationTest.class);

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();
  }

  @Test
  public void testCreateFirm() throws Exception {
    ApiHandle handle = TestHelper.getApiHandle_SopExchange(TestHelper.getExchangeStaffApiKey());
    FirmClient client = new FirmClient(handle);

    FirmSubmit record = new FirmSubmit();
    record.setCompanyName("Test Firm " + TestHelper.getUniqueString());

    Show<Account> response = client.create(record);

    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getData().getLinks(), not(equalTo(null)));
    assertTrue(response.getData().getInfotrackExchangeReady());
  }

  @Test
  public void testCreateFirmApiKey() throws Exception {

    ApiHandle handle = TestHelper.getApiHandle_SopExchange(TestHelper.getExchangeStaffApiKey());
    FirmClient client = new FirmClient(handle);

    // create firm to test
    FirmSubmit createRecord = new FirmSubmit();
    String companyName = "Test Firm";
    createRecord.setCompanyName(companyName);

    Show<Account> createResponse = client.create(createRecord);

    assertThat(createResponse, not(equalTo(null)));
    assertThat(createResponse.getData(), not(equalTo(null)));
    assertThat(createResponse.getData().getLinks(), not(equalTo(null)));
    assertThat(createResponse.getData().getId(), greaterThan(0));

    Integer firmId = createResponse.getData().getId();

    // test create firm api key
    Show<FirmApiKey> response = client.createFirmApiKey(firmId);

    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getData().getId(), not(equalTo(null)));
    assertThat(response.getData().getKey(), not(equalTo(null)));
  }

  @Test
  public void testShowFirm() throws Exception {
    ApiHandle handle = TestHelper.getApiHandle_SopExchange(TestHelper.getExchangeStaffApiKey());
    FirmClient client = new FirmClient(handle);

    // create firm to test
    FirmSubmit createRecord = new FirmSubmit();
    String companyName = "Test Firm " + TestHelper.getUniqueString();
    createRecord.setCompanyName(companyName);

    Show<Account> createResponse = client.create(createRecord);

    assertThat(createResponse, not(equalTo(null)));
    assertThat(createResponse.getData(), not(equalTo(null)));
    assertThat(createResponse.getData().getLinks(), not(equalTo(null)));
    assertThat(createResponse.getData().getId(), greaterThan(0));

    Integer firmId = createResponse.getData().getId();

    // test show firm
    Show<Account> response = client.show(firmId);

    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getData().getLinks(), not(equalTo(null)));
    assertThat(response.getData().getId(), equalTo(firmId));
    assertThat(response.getData().getCompanyName(), equalTo(companyName));
  }

  @Test
  public void testShowFirmApiKey() throws Exception {
    ApiHandle handle = TestHelper.getApiHandle_SopExchange(TestHelper.getExchangeStaffApiKey());
    FirmClient client = new FirmClient(handle);

    // create firm to test
    FirmSubmit createRecord = new FirmSubmit();
    String companyName = "Test Firm";
    createRecord.setCompanyName(companyName);

    Show<Account> createFirmResponse = client.create(createRecord);

    assertThat(createFirmResponse, not(equalTo(null)));
    assertThat(createFirmResponse.getData(), not(equalTo(null)));
    assertThat(createFirmResponse.getData().getLinks(), not(equalTo(null)));
    assertThat(createFirmResponse.getData().getId(), greaterThan(0));

    Integer firmId = createFirmResponse.getData().getId();

    // create firm api key to test
    Show<FirmApiKey> createKeyResponse = client.createFirmApiKey(firmId);

    assertThat(createKeyResponse, not(equalTo(null)));
    assertThat(createKeyResponse.getData(), not(equalTo(null)));
    assertThat(createKeyResponse.getData().getId(), not(equalTo(null)));
    assertThat(createKeyResponse.getData().getKey(), not(equalTo(null)));

    Integer firmKeyId = createKeyResponse.getData().getId();
    String firmKey = createKeyResponse.getData().getKey();

    // test show firm api key
    Show<FirmApiKey> response = client.showFirmApiKey(firmId, firmKeyId);

    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getData().getId(), not(equalTo(null)));
    assertThat(response.getData().getKey(), not(equalTo(null)));
    assertThat(response.getData().getId(), equalTo(firmKeyId));
    assertThat(response.getData().getKey(), equalTo(firmKey));
  }

  @Test
  public void testUpdateFirm() throws Exception {
    ApiHandle handle = TestHelper.getApiHandle_SopExchange(TestHelper.getExchangeStaffApiKey());
    FirmClient client = new FirmClient(handle);

    // create firm to test
    FirmSubmit createRecord = new FirmSubmit();
    String companyName = "Test Firm";
    createRecord.setCompanyName(companyName);

    Show<Account> createResponse = client.create(createRecord);

    assertThat(createResponse, not(equalTo(null)));
    assertThat(createResponse.getData(), not(equalTo(null)));
    assertThat(createResponse.getData().getLinks(), not(equalTo(null)));
    assertThat(createResponse.getData().getId(), greaterThan(0));

    Integer firmId = createResponse.getData().getId();

    // test update firm
    FirmSubmit record = new FirmSubmit();
    String updatedCompanyName = "Updated Firm " + TestHelper.getUniqueString();
    record.setCompanyName(updatedCompanyName);

    Show<Account> response = client.update(firmId, record);

    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getData().getLinks(), not(equalTo(null)));
    assertThat(response.getData().getId(), equalTo(firmId));
    assertThat(response.getData().getCompanyName(), equalTo(updatedCompanyName));
  }

}