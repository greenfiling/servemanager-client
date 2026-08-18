package com.greenfiling.smclient;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greenfiling.smclient.model.Agency;
import com.greenfiling.smclient.model.exchange.AgencyFilter;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.util.TestHelper;

public class AgencyClient_IntegrationTest {
  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(AgencyClient_IntegrationTest.class);

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();
  }

  @Test
  public void testIndexAgency_withFilter() throws Exception {
    ApiHandle handle = TestHelper.getApiHandle_SopExchange(TestHelper.getExchangeStaffApiKey());
    AgencyClient client = new AgencyClient(handle);
    AgencyFilter filter = new AgencyFilter();
    filter.setZipcode("12345");

    Index<Agency> response = client.index(filter);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getLinks(), not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertTrue(response.getData().size() > 0);
  }

  @Test
  public void testShowAgency() throws Exception {
    ApiHandle handle = TestHelper.getApiHandle_SopExchange(TestHelper.getExchangeStaffApiKey());
    AgencyClient client = new AgencyClient(handle);

    // get existing test agency

    // Use filter with no params to pull any available test agencies
    AgencyFilter filter = new AgencyFilter();

    Index<Agency> agencyListResponse = client.index(filter);
    assertThat(agencyListResponse, not(equalTo(null)));
    assertThat(agencyListResponse.getLinks(), not(equalTo(null)));
    assertThat(agencyListResponse.getData(), not(equalTo(null)));
    assertTrue(agencyListResponse.getData().size() > 0);

    Integer agencyId = agencyListResponse.getData().get(0).getId();

    // test show agency
    Show<Agency> response = client.show(agencyId);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
  }

  // Stage environment does not currently return multiple pages of agencies
  // @Test
  // public void testGetNext() throws Exception {
  //
  // }

}