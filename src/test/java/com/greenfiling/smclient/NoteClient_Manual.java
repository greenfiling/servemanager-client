/**
 * Copyright 2021-2023 Green Filing, LLC
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

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.greenfiling.smclient.internal.JsonHandle;
import com.greenfiling.smclient.model.Links;
import com.greenfiling.smclient.model.Note;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;

public class NoteClient_Manual {
  private static ApiHandle apiHandle = null;
  private static NoteClient client = null;
  private static JobClient jobClient = null;
  private final static Integer jobId = 9281185; // TODO: magic number

  @BeforeClass
  public static void setUpClass() {
    apiHandle = new ApiHandle.Builder().apiKey(TestHelper.VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE).build();
    client = new NoteClient(apiHandle);
    jobClient = new JobClient(apiHandle);
  }

  @Test
  public void testIndexNote_HappyPath() throws Exception {
    Index<Note> response = client.index();
    Links links = response.getLinks();
    System.out.println("links.self = " + links.getSelf());

    ArrayList<Note> notes = response.getData();
    System.out.println("Number of notes in response: " + notes.size());

    System.out.println("re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(response));
  }

  @Test
  public void testIndexNote_ByJob_HappyPath() throws Exception {
    Index<Note> response = jobClient.indexNotes(jobId);

    Links links = response.getLinks();
    System.out.println("links.self = " + links.getSelf());

    ArrayList<Note> notes = response.getData();
    System.out.println("Number of notes in response: " + notes.size());

    System.out.println("re-serialized: " + JsonHandle.get().getGsonWithNulls().toJson(response));
  }

  @Test
  public void testCreateNote_HappyPath() throws Exception {
    Note note = new Note();
    note.setLabel("Job Details");
    note.setBody("this is\nthe\nbody");
    note.setJobId(jobId);

    Index<Note> indexResponse = jobClient.indexNotes(jobId);
    System.out.println("Number of notes before create: " + indexResponse.getData().size());

    Show<Note> createResponse = jobClient.createNote(note);
    System.out.println("re-serialized: " + JsonHandle.get().getGson().toJson(createResponse));
    System.out.println("created note id = " + createResponse.getData().getId());

    indexResponse = jobClient.indexNotes(jobId);
    System.out.println("Number of notes after create: " + indexResponse.getData().size());
  }

}
