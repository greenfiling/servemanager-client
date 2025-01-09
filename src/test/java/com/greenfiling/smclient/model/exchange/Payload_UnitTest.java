/**
 * Copyright 2024-2025 Green Filing, LLC
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

package com.greenfiling.smclient.model.exchange;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;

import com.greenfiling.smclient.internal.JsonHandle;
import com.greenfiling.smclient.model.Job;
import com.greenfiling.smclient.model.Note;
import com.greenfiling.smclient.model.ServerAcceptance;
import com.greenfiling.smclient.util.TestHelper;

public class Payload_UnitTest {

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();
  }

  @Test
  public void testDeserializePayLoad() throws IOException, URISyntaxException {
    String json = Files.readString(Paths.get(TestHelper.class.getResource("/Samples/PayLoad.json").toURI()));

    PayLoad payload = JsonHandle.get().getGson().fromJson(json, PayLoad.class);

    assertEquals("84ca113a-a28e-47c4-86ba-6bab77b92728", payload.getMeta().getWebhookId());
    assertEquals("My test hook", payload.getMeta().getWebhookName());
    assertEquals(
        "http://localhost:3000/api/jobs?q=jobs%3A3679689%2C3679689%2C3679689%2C3679689%2C3679689%2C3679689%2C3679689%2C3679689%2C3679689%2C3679689%2C3679689%2C3679689",
        payload.getLinks().getJobs());

    Note aNotes = (Note) payload.getData().get(0);
    assertEquals("this is a note i'm making", aNotes.getBody());
    assertEquals("notes:created", aNotes.getWebhookEvents().get(0).getEvent());
    assertEquals("create", aNotes.getWebhookEvents().get(0).getAction());

    Job aJob = (Job) payload.getData().get(1);
    assertEquals("3679689", aJob.getServeManagerJobNumber());
    assertEquals("Acme Process Serving", aJob.getProcessServerCompany().getName());

    assertEquals(11, aJob.getWebhookEvents().size());
    assertEquals("attachments:created", aJob.getWebhookEvents().get(0).getEvent());
    assertEquals("create", aJob.getWebhookEvents().get(0).getAction());
    assertEquals(Integer.valueOf(99997), aJob.getDupedFromJobId());

    assertEquals(2, aJob.getDupedToJobIds().size());
    assertEquals(Integer.valueOf(99998), aJob.getDupedToJobIds().get(1));

    assertEquals(false, aJob.getAssignedByCollaboratingServer());
    assertEquals(Integer.valueOf(99990), aJob.getMailedById());
    assertEquals(LocalDate.parse("2023-07-20"), aJob.getMailingDate());
    assertEquals("Test description", aJob.getMailingLocation());
    assertEquals(false, aJob.getMailingRequired());

    assertEquals(Integer.valueOf(271978), aJob.getProcessServerCompany().getCollaborationAccountId());

    assertEquals(1, aJob.getDocuments().size());
    assertEquals(true, aJob.getDocuments().get(0).getSigned());
    assertEquals(2, aJob.getMiscAttachments().size());
    assertEquals(false, aJob.getMiscAttachments().get(1).getSigned());

    assertEquals("RYWl8ZcWzVC5HoZ4rVwBwA", aJob.getProcessServerInvoice().getToken());
    assertEquals(false, aJob.getProcessServerInvoice().getTaxesEnabled());
    assertEquals(Integer.valueOf(858783), aJob.getProcessServerInvoice().getClientId());
    assertEquals(Integer.valueOf(3679689), aJob.getProcessServerInvoice().getServeManagerJobNumber());

    assertEquals(ServerAcceptance.RESPONSE_ACCEPTED, aJob.getServerAcceptance().getResponse());
  }
}
