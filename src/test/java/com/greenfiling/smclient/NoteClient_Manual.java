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

import static com.greenfiling.smclient.TestHelper.log;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greenfiling.smclient.internal.JsonHandle;
import com.greenfiling.smclient.model.Links;
import com.greenfiling.smclient.model.Note;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;

public class NoteClient_Manual {
  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(NoteClient_Manual.class);

  private static ApiHandle apiHandle = null;
  private static NoteClient client = null;
  private static JobClient jobClient = null;
  private final static Integer jobId = 9281185; // TODO: magic number

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();

    apiHandle = TestHelper.getApiHandle();
    client = new NoteClient(apiHandle);
    jobClient = new JobClient(apiHandle);
  }

  @Test
  public void testIndexNote_HappyPath() throws Exception {
    Index<Note> response = client.index();
    Links links = response.getLinks();
    log("links.self = %s", links.getSelf());

    ArrayList<Note> notes = response.getData();
    log("Number of notes in response: %s", notes.size());

    log("re-serialized: %s", JsonHandle.get().getGsonWithNulls().toJson(response));
  }

  @Test
  public void testIndexNote_ByJob_HappyPath() throws Exception {
    Index<Note> response = jobClient.indexNotes(jobId);

    Links links = response.getLinks();
    log("links.self = %s", links.getSelf());

    ArrayList<Note> notes = response.getData();
    log("Number of notes in response: %s", notes.size());

    log("re-serialized: %s", JsonHandle.get().getGsonWithNulls().toJson(response));
  }

  @Test
  public void testCreateNote_HappyPath() throws Exception {
    Note note = new Note();
    note.setLabel("Job Details");
    note.setBody("this is\nthe\nbody");
    note.setJobId(jobId);

    Index<Note> indexResponse = jobClient.indexNotes(jobId);
    log("Number of notes before create: %s", indexResponse.getData().size());

    Show<Note> createResponse = jobClient.createNote(note);
    log("re-serialized: %s", JsonHandle.get().getGson().toJson(createResponse));
    log("created note id = %s", createResponse.getData().getId());

    indexResponse = jobClient.indexNotes(jobId);
    log("Number of notes after create: %s", indexResponse.getData().size());
  }

}
