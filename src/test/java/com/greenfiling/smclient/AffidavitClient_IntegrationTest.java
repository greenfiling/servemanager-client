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
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greenfiling.smclient.model.Affidavit;
import com.greenfiling.smclient.model.exchange.AffidavitFilter;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.util.TestHelper;

public class AffidavitClient_IntegrationTest {
  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(AffidavitClient_IntegrationTest.class);

  private static ApiHandle apiHandle = null;
  private static AffidavitClient client = null;

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();

    apiHandle = TestHelper.getApiHandle();
    client = new AffidavitClient(apiHandle);
  }

  @Test
  public void testIndexAffidavit_withFilterDocumentId() throws Exception {
    AffidavitFilter filter = new AffidavitFilter();
    filter.setDocumentId("51899619");

    Index<Affidavit> response = client.index(filter);

    assertThat(response, not(equalTo(null)));
    assertThat(response.getData().get(0).getLinks(), not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertTrue(response.getData().size() > 0);
  }

  @Test
  public void testIndexAffidavit_withFilterJobId() throws Exception {
    AffidavitFilter filter = new AffidavitFilter();
    filter.setJobId("11487024");

    Index<Affidavit> response = client.index(filter);

    assertThat(response, not(equalTo(null)));
    assertThat(response.getData().get(0).getLinks(), not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertTrue(response.getData().size() > 0);
  }

  @Test
  public void testShowAffidavit() throws Exception {
    Show<Affidavit> response = client.show(14360239);

    assertThat(response, not(equalTo(null)));
    assertThat(response.getData().getId(), equalTo(14360239));
  }
  //
  // //Can't create a test firm with affidavits to test this
  // @Test
  // public void testAffidavitAccept() throws Exception {
  // Affidavit affidavit = new Affidavit();
  // affidavit.setId(14360239);
  // affidavit.setAffidavitAcceptance("accepted");
  //
  // ApiHandle firmHandle = TestHelper.getApiHandle_SopExchange(firmKey);
  // AffidavitClient affidavitClient = new AffidavitClient(firmHandle);
  // Show<Affidavit> response = affidavitClient.create(affidavit);
  //
  // assertThat(response, not(equalTo(null)));
  // assertThat(response.getData().getId(), equalTo(14360239));
  // }
}