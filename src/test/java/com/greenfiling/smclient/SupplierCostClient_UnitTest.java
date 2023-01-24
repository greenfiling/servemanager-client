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
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

import com.greenfiling.smclient.internal.JsonHandle;
import com.greenfiling.smclient.model.SupplierCost;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.exchange.SupplierCostFilter;

public class SupplierCostClient_UnitTest {
  private static ApiHandle apiHandle = null;
  private static SupplierCostClient client = null;

  @BeforeClass
  public static void setUpClass() {
    apiHandle = new ApiHandle.Builder().apiKey(TestHelper.VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE).build();
    client = new SupplierCostClient(apiHandle);
  }

  @Test
  public void testShowCostByZip_HappyPath() throws Exception {

    Show<SupplierCost> response = client.show(47374, null);

    TestHelper.log("testShowCostByZip_HappyPath re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(response));

    assertThat(response.getData().getAmount(), notNullValue());
    assertThat(response.getData().getJobTypeId(), equalTo("1"));
    assertThat(response.getData().getSlaId(), equalTo("1"));

    TestHelper.log("amount = " + response.getData().getAmount());
    TestHelper.log("updated_at = " + response.getData().getUpdatedAt());
    TestHelper.log("created_at = " + response.getData().getCreatedAt());

  }

  @Test
  public void testShowCostByZip_WithAllFilters_HappyPath() throws Exception {

    SupplierCostFilter request = new SupplierCostFilter();
    request.setJobType(SupplierCostFilter.JOB_TYPE_CCD);
    request.setServiceLevel(SupplierCostFilter.SERVICE_LEVEL_RUSH);

    Show<SupplierCost> response = client.show(47374, request);
    TestHelper.log("re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(response));

    assertThat(response.getData().getAmount(), notNullValue());
    assertThat(response.getData().getJobTypeId(), equalTo("2"));
    assertThat(response.getData().getSlaId(), equalTo("2"));

    TestHelper.log("amount = " + response.getData().getAmount());
    TestHelper.log("updated_at = " + response.getData().getUpdatedAt());
    TestHelper.log("created_at = " + response.getData().getCreatedAt());

  }

  @Test
  public void testShowCostByZip_WithJobTypeFilter_HappyPath() throws Exception {

    SupplierCostFilter request = new SupplierCostFilter();
    request.setJobType(SupplierCostFilter.JOB_TYPE_CCD);

    Show<SupplierCost> response = client.show(47374, request);
    TestHelper.log("re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(response));

    assertThat(response.getData().getAmount(), notNullValue());
    assertThat(response.getData().getJobTypeId(), equalTo("2"));
    assertThat(response.getData().getSlaId(), equalTo("1"));

    TestHelper.log("amount = " + response.getData().getAmount());
    TestHelper.log("updated_at = " + response.getData().getUpdatedAt());
    TestHelper.log("created_at = " + response.getData().getCreatedAt());
  }

  @Test
  public void testShowCostByZip_WitServiceTypeFilter_HappyPath() throws Exception {

    SupplierCostFilter request = new SupplierCostFilter();
    request.setServiceLevel(SupplierCostFilter.SERVICE_LEVEL_RUSH);

    Show<SupplierCost> response = client.show(47374, request);
    TestHelper.log("re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(response));

    assertThat(response.getData().getAmount(), notNullValue());
    assertThat(response.getData().getJobTypeId(), equalTo("1"));
    assertThat(response.getData().getSlaId(), equalTo("2"));

    TestHelper.log("amount = " + response.getData().getAmount());
    TestHelper.log("updated_at = " + response.getData().getUpdatedAt());
    TestHelper.log("created_at = " + response.getData().getCreatedAt());

  }

  /**
   * TODO: uncomment once we start getting real data back. <br>
   * This test is currently failing b/c currently we get dummy data passed regardless of our API parameters.
   */
  // @Test
  // public void testShowCostByZip_NoSuchObject() throws Exception {
  // boolean caughtException = false;
  //
  // Show<SupplierCost> showResp = null;
  // try {
  // showResp = client.show(1);
  // } catch (Exceptions.RecordNotFoundException e) {
  // caughtException = true;
  // }
  // assertThat(caughtException, equalTo(true));
  // assertThat(showResp, equalTo(null));
  // }
}
