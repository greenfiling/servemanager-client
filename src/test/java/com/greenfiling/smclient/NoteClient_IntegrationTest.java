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

import static com.greenfiling.smclient.util.TestHelper.log;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greenfiling.smclient.model.Job;
import com.greenfiling.smclient.model.Links;
import com.greenfiling.smclient.model.Note;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.util.TestHelper;

public class NoteClient_IntegrationTest {
  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(NoteClient_IntegrationTest.class);

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
  // Create a new job each time. I had a version of this that used a single job, but job-note pagination is broken, so
  // it didn't really work. Creating our own job is cleaner than having a magic id anyway
  public void testCreateNote_HappyPath() throws Exception {
    Job job = TestHelper.getTestJob();
    Show<Job> jobResp = jobClient.create(job);
    assertThat(jobResp, not(equalTo(null)));
    assertThat(jobResp.getData(), not(equalTo(null)));

    Integer thisJobId = jobResp.getData().getId();
    log("created job id = %s", thisJobId);

    Note note = new Note();
    note.setLabel("Job Details");
    note.setBody("this is\nthe\nbody");
    note.setJobId(thisJobId);
    note.setCategoryId(Note.NOTE_CATEGORY_CANCEL);
    note.setCreatedBy("Created By Smith");
    note.setUserId(289268); // this is a magic number, it's the user id of sm_dev
    note.setSharedFrom("Shared From String");

    ArrayList<String> visibility = new ArrayList<String>();
    visibility.add("client");
    visibility.add("server");
    note.setVisibility(visibility);

    ArrayList<ArrayList<String>> emailedTo = new ArrayList<ArrayList<String>>();
    emailedTo.add(new ArrayList<String>());
    emailedTo.get(0).add("Emailed To Jones");
    emailedTo.get(0).add("joe@example.com");
    note.setEmailedTo(emailedTo);

    Index<Note> indexResponse = jobClient.indexNotes(thisJobId);
    assertThat(indexResponse, not(equalTo(null)));
    assertThat(indexResponse.getData(), not(equalTo(null)));
    assertThat(indexResponse.getLinks(), not(equalTo(null)));

    Integer sizeBefore = indexResponse.getData().size();
    log("Number of notes on jobId %s before create: %s", thisJobId, sizeBefore);

    Show<Note> createResponse = jobClient.createNote(note);
    assertThat(createResponse, not(equalTo(null)));
    assertThat(createResponse.getData(), not(equalTo(null)));
    // log("re-serialized: %s", JsonHandle.get().getGson().toJson(createResponse));
    Note newNote = createResponse.getData();
    log("created note id = %s", newNote.getId());
    assertThat(newNote.getCategoryId(), equalTo(Note.NOTE_CATEGORY_CANCEL));
    assertThat(newNote.getUserId(), equalTo(289268));
    assertThat(newNote.getCreatedBy(), equalTo("Eric Eastman")); // magic, username associated with 289268
    assertThat(newNote.getSharedFrom(), equalTo(null)); // This field RO, error if it starts being honored and we can update javadoc

    ArrayList<ArrayList<String>> newEmailedTo = newNote.getEmailedTo();
    assertThat(newEmailedTo, not(equalTo(null)));
    assertThat(newEmailedTo.size(), equalTo(0)); // This field is read only, add this test as a canary in case they change it

    ArrayList<String> newVis = newNote.getVisibility();
    assertThat(newVis, not(equalTo(null)));
    assertThat(newVis.size(), equalTo(2));
    assertThat(newVis.get(0), equalTo("client"));
    assertThat(newVis.get(1), equalTo("server"));

    indexResponse = jobClient.indexNotes(thisJobId);
    assertThat(indexResponse, not(equalTo(null)));
    assertThat(indexResponse.getData(), not(equalTo(null)));
    assertThat(indexResponse.getLinks(), not(equalTo(null)));

    Integer sizeAfter = indexResponse.getData().size();
    log("Number of notes on jobId %s after create: %s", thisJobId, sizeAfter);

    assertThat(sizeAfter, equalTo(sizeBefore + 1));
  }

  @Test
  public void testIndexNote_ByJob_HappyPath() throws Exception {
    Index<Note> response = jobClient.indexNotes(jobId);
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getLinks(), not(equalTo(null)));

    Links links = response.getLinks();
    log("links.self = %s", links.getSelf());

    ArrayList<Note> notes = response.getData();
    log("Number of notes in response: %s", notes.size());

    // log("re-serialized: %s", JsonHandle.get().getGsonWithNulls().toJson(response));
  }

  @Test
  public void testIndexNote_HappyPath() throws Exception {
    Index<Note> response = client.index();
    assertThat(response, not(equalTo(null)));
    assertThat(response.getData(), not(equalTo(null)));
    assertThat(response.getLinks(), not(equalTo(null)));

    Links links = response.getLinks();
    log("links.self = %s", links.getSelf());

    ArrayList<Note> notes = response.getData();
    log("Number of notes in response: %s", notes.size());

    // log("re-serialized: %s", JsonHandle.get().getGsonWithNulls().toJson(response));
  }

}
