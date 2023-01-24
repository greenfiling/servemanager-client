package com.greenfiling.smclient;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

import com.greenfiling.smclient.internal.JsonHandle;
import com.greenfiling.smclient.model.SupplierCost;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.exchange.SupplierCostFilter;
import com.greenfiling.smclient.model.exchange.SupplierCostFilter.JobType;
import com.greenfiling.smclient.model.exchange.SupplierCostFilter.ServiceLevel;

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
    Show<SupplierCost> response = client.show(47374);

    assertThat(response.getData().getAmount(), notNullValue());

    System.out.println("amount = " + response.getData().getAmount());
    System.out.println("updated_at = " + response.getData().getUpdatedAt());
    System.out.println("created_at = " + response.getData().getCreatedAt());

    System.out.println("re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(response));
  }

  @Test
  public void testShowCostByZip_WithQueryParameters_HappyPath() throws Exception {
    SupplierCostFilter request = new SupplierCostFilter();
    request.setZipCode(47374);
    request.setJobType(JobType.CCD);
    request.setServiceLevel(ServiceLevel.ROUTINE);

    Show<SupplierCost> response = client.show(request);

    assertThat(response.getData().getAmount(), notNullValue());

    System.out.println("amount = " + response.getData().getAmount());
    System.out.println("updated_at = " + response.getData().getUpdatedAt());
    System.out.println("created_at = " + response.getData().getCreatedAt());

    System.out.println("re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(response));
  }

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
