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
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.net.URLDecoder;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.greenfiling.smclient.internal.JsonHandle;
import com.greenfiling.smclient.model.Links;
import com.greenfiling.smclient.model.SupplierCost;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.exchange.SupplierCostFilter;
import com.greenfiling.smclient.model.internal.JobBase;
import com.greenfiling.smclient.util.TestHelper;

public class SupplierCostClient_IntegrationTest {
  private static ApiHandle apiHandle = null;
  private static SupplierCostClient client = null;

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();

    apiHandle = TestHelper.getApiHandle();
    client = new SupplierCostClient(apiHandle);
  }

  @Test
  public void testIndex_GetAll() throws Exception {
    Index<SupplierCost> response = client.index();
    TestHelper.log("testIndex_GetAll re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(response));

    assertThat(response.getData().size() > 0, equalTo(true));

    assertThat(response.getData().get(0).getAmount(), notNullValue());
    assertThat(response.getData().get(0).getJobTypeId(), notNullValue());
    assertThat(response.getData().get(0).getSlaId(), notNullValue());
    assertThat(response.getData().get(0).getType(), equalTo("supplier_cost"));
    assertThat(response.getData().get(0).getZipcode(), notNullValue());
    assertThat(response.getData().get(0).getZipcodeZoneId(), notNullValue());

    TestHelper.log("list length = " + response.getData().size());
  }

  @Test
  public void testIndex_GetAll_GetNext() throws Exception {
    Index<SupplierCost> response = client.index();

    int iterationCount = 0;
    while (iterationCount < 3) {
      Links links = response.getLinks();
      TestHelper.log("links.self = " + links.getSelf());

      ArrayList<SupplierCost> jobs = response.getData();
      TestHelper.log("Number of jobs in response: " + jobs.size());

      response = client.getNext(response);

      iterationCount++;
    }

    assertThat(iterationCount, equalTo(3));
  }

  /**
   * I expect 8 elements, 4 combinations of jobType and SlaId for each zipcode. The data configuration may return less (e.g. CCD data is not plugged
   * in)
   * 
   * @throws Exception
   */
  @Test
  public void testIndex_ListOfZips() throws Exception {
    SupplierCostFilter filter = new SupplierCostFilter();
    filter.getZipCodes().add("80204");
    filter.getZipCodes().add("54304");

    Index<SupplierCost> response = client.index(filter);

    TestHelper.log("testIndex_ListOfZips re-serialized: " + URLDecoder.decode(response.getLinks().getSelf(), "UTF-8"));

    assertThat(response.getData().size() > 0 && response.getData().size() <= 8, equalTo(true));

    assertThat(response.getData().get(0).getAmount(), notNullValue());
    assertThat(response.getData().get(0).getType(), equalTo("supplier_cost"));
    assertThat(response.getData().get(0).getZipcode(), equalTo("80204"));
    assertThat(response.getData().get(0).getZipcodeZoneId(), notNullValue());

    assertThat(response.getData().stream().filter(x -> x.getZipcode().equals("80204")).count() >= 1, equalTo(true));
    assertThat(response.getData().stream().filter(x -> x.getZipcode().equals("54304")).count() >= 1, equalTo(true));

  }

  @Test
  public void testIndex_PageLookup() throws Exception {
    SupplierCostFilter filter = new SupplierCostFilter();
    filter.setPage(400);
    Index<SupplierCost> response = client.index(filter);

    TestHelper.log("testIndex_PageLookup re-serialized: " + URLDecoder.decode(response.getLinks().getSelf(), "UTF-8"));

    assertThat(response.getData().size(), equalTo(100));

  }

  @Test
  public void testIndex_UpdatedSince() throws Exception {
    SupplierCostFilter request = new SupplierCostFilter();
    request.setUpdatedSince(OffsetDateTime.parse("2023-02-01T19:00:49-05:00"));

    Index<SupplierCost> response = client.index(request);
    int pageCount = 0;
    while (pageCount < 3) {
      pageCount++;
      response = client.getNext(response);
    }
    TestHelper.log("number of pages " + pageCount);
    assertThat(pageCount > 0 && pageCount <= 3, equalTo(true));
  }

  @Test
  public void testIndex_WitJobTypeFilterSOP_HappyPath() throws Exception {

    SupplierCostFilter request = new SupplierCostFilter();
    request.setJobType(JobBase.JOB_TYPE_SOP);

    Index<SupplierCost> response = client.index(request);
    TestHelper.log("re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(response));

    assertThat(response.getData().size(), equalTo(100));
    assertThat(response.getData().get(0).getAmount(), notNullValue());
    assertThat(response.getData().stream().filter(x -> x.getJobTypeId().equals(2)).count(), equalTo(Long.valueOf(0)));

  }

  @Test
  public void testIndex_WitServiceTypeFilterRoutine_HappyPath() throws Exception {

    SupplierCostFilter request = new SupplierCostFilter();
    request.setServiceLevel(SupplierCostFilter.SERVICE_LEVEL_ROUTINE);

    Index<SupplierCost> response = client.index(request);
    TestHelper.log("re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(response));

    assertThat(response.getData().size(), equalTo(100));
    assertThat(response.getData().get(0).getAmount(), notNullValue());
    assertThat(response.getData().stream().filter(x -> x.getSlaId().equals(2)).count(), equalTo(Long.valueOf(0)));

  }

  @Test
  public void testIndex_WithServiceTypeFilterRush_HappyPath() throws Exception {

    SupplierCostFilter request = new SupplierCostFilter();
    request.setServiceLevel(SupplierCostFilter.SERVICE_LEVEL_RUSH);

    Index<SupplierCost> response = client.index(request);
    TestHelper.log("re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(response));

    assertThat(response.getData().size(), equalTo(100));
    assertThat(response.getData().get(0).getAmount(), notNullValue());
    assertThat(response.getData().stream().filter(x -> x.getSlaId().equals(1)).count(), equalTo(Long.valueOf(0)));

  }

  @Test
  public void testIndex_WithSpecificFilter_HappyPath() throws Exception {

    SupplierCostFilter request = new SupplierCostFilter();
    request.setPageCount(275);
    request.setJobType(JobBase.JOB_TYPE_SOP);
    request.setServiceLevel(SupplierCostFilter.SERVICE_LEVEL_RUSH);
    request.getZipCodes().add("90210");

    Index<SupplierCost> response = client.index(request);
    // TestHelper.log("re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(response));

    assertThat(response.getData().size(), equalTo(1));
    assertThat(response.getData().get(0).getAmount(), notNullValue());
    assertThat(response.getData().get(0).getPageCountPrice(), notNullValue());

    TestHelper.log("page_count_price = " + response.getData().get(0).getPageCountPrice());
  }

  @Test
  public void testShowCost_HappyPath() throws Exception {

    Show<SupplierCost> response = client.show(28551);

    TestHelper.log("testShowCost_HappyPath re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(response));

    assertThat(response.getData().getAmount(), notNullValue());
    assertThat(response.getData().getJobTypeId(), equalTo(1));
    assertThat(response.getData().getSlaId(), equalTo(1));
    assertThat(response.getData().getType(), equalTo("supplier_cost"));
    assertThat(response.getData().getZipcode(), notNullValue());
    assertThat(response.getData().getSuggestedRetailPrice(), notNullValue());
    assertThat(response.getData().getPageCountPrice(), nullValue());
    assertThat(response.getData().getZipcodeZoneId(), notNullValue());

    TestHelper.log("amount = " + response.getData().getAmount());
    TestHelper.log("suggested_retail_price = " + response.getData().getSuggestedRetailPrice());
    TestHelper.log("page_count_price = " + response.getData().getPageCountPrice());
    TestHelper.log("updated_at = " + response.getData().getUpdatedAt());
    TestHelper.log("created_at = " + response.getData().getCreatedAt());
    TestHelper.log("zipcode = " + response.getData().getZipcode());
    TestHelper.log("zipcode_zone_id = " + response.getData().getZipcodeZoneId());
  }

  @Test
  public void testShowCost_NoSuchObject() throws Exception {
    boolean caughtException = false;

    Show<SupplierCost> showResp = null;
    try {
      showResp = client.show(0);
    } catch (Exceptions.RecordNotFoundException e) {
      caughtException = true;
    }
    assertThat(caughtException, equalTo(true));
    assertThat(showResp, equalTo(null));
  }

  @Test
  public void testIndex_WitJobTypeFilterCCD_HappyPath() throws Exception {

    SupplierCostFilter request = new SupplierCostFilter();
    request.setJobType(JobBase.JOB_TYPE_CCD);

    Index<SupplierCost> response = client.index(request);
    TestHelper.log("re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(response));

    assertThat(response.getData().size(), equalTo(100));
    assertThat(response.getData().get(0).getAmount(), notNullValue());
    assertThat(response.getData().stream().filter(x -> x.getJobTypeId().equals(JobBase.JOB_TYPE_SOP)).count(), equalTo(Long.valueOf(0)));

  }
}
