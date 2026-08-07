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
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

import com.greenfiling.smclient.internal.JsonHandle;
import com.greenfiling.smclient.model.Webhook;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.util.TestHelper;

public class WebhookClient_IntegrationTest {
  private static ApiHandle apiHandle = null;
  private static WebhookClient client = null;

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();

    apiHandle = TestHelper.getApiHandle();
    client = new WebhookClient(apiHandle);
  }

  @Test
  public void testIndex_GetAll() throws Exception {
    Index<Webhook> response = client.index();
    TestHelper.log("testIndex_GetAll re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(response));

    assertThat(response.getData().size() > 0, equalTo(true));

    assertThat(response.getData().get(0).getBatchIntervalInSeconds(), notNullValue());
    assertThat(response.getData().get(0).getEvents().length > 0, equalTo(true));
    assertThat(response.getData().get(0).getId(), notNullValue());
    assertThat(response.getData().get(0).getName(), notNullValue());
    assertThat(response.getData().get(0).getSecretKey(), notNullValue());
    assertThat(response.getData().get(0).getTargetUrl(), notNullValue());
    assertThat(response.getData().get(0).getType(), notNullValue());

    TestHelper.log("list length = " + response.getData().size());
  }

  @Test
  public void testCreateUpdateDelete() throws Exception {
    Webhook webhook = new Webhook();
    webhook.setName("Test Webhook");
    webhook.setClientReferenceKey("Test Webhook");
    webhook.setTargetUrl("https://example.com/webhook");
    webhook.setBatchIntervalInSeconds(60);
    webhook.setEnabled(true);
    webhook.setEvents(new String[] { Webhook.JOBS_CREATED, Webhook.JOBS_UPDATED });
    Show<Webhook> show = client.create(webhook);
    TestHelper.log("testCreateUpdateDelete re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(show.getData()));
    assertThat(show.getData().getName(), equalTo("Test Webhook"));

    Webhook uWebhook = new Webhook();
    uWebhook.setName("Updated Test Webhook");
    Show<Webhook> update = client.update(show.getData().getId(), uWebhook);
    TestHelper.log("testCreateUpdateDelete re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(update.getData()));
    assertThat(update.getData().getName(), equalTo("Updated Test Webhook"));

    client.delete(show.getData().getId());
  }
}
