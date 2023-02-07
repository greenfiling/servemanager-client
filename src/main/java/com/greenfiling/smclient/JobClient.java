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

import java.io.File;

import com.google.gson.reflect.TypeToken;
import com.greenfiling.smclient.internal.ApiClient;
import com.greenfiling.smclient.internal.JsonHandle;
import com.greenfiling.smclient.model.Job;
import com.greenfiling.smclient.model.JobSubmit;
import com.greenfiling.smclient.model.Note;
import com.greenfiling.smclient.model.Upload;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.internal.FilterBase;
import com.greenfiling.smclient.model.internal.JobBase;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class JobClient extends ApiClient<JobBase, Job, JobSubmit> {
  public static final String ENDPOINT = "jobs";

  public JobClient(ApiHandle handle) {
    super(handle);
    setEndpoint(ENDPOINT);

    // @formatter:off
    setShowType(new TypeToken<Show<Job>>() {}.getType());
    setIndexType(new TypeToken<Index<Job>>() {}.getType());
    // @formatter:on
  }

  public void completeUpload(Upload upload, String contentType, File file) throws Exception {
    if (upload == null || upload.getLinks() == null) {
      throw new IllegalStateException("upload or upload.links null");
    }
    if (contentType == null || "".equals(contentType)) {
      throw new IllegalStateException("contentType empty or null");
    }
    if (file == null) {
      throw new IllegalStateException("file null");
    }

    RequestBody requestBody = RequestBody.create(file, MediaType.parse(contentType));

    getHandle().doPutFile(upload.getLinks().getPutUrl(), requestBody);
  }

  @Override
  @SuppressWarnings("unchecked")
  public Show<Job> create(JobBase record) throws Exception {
    JobSubmit submitRecord = (record instanceof JobSubmit) ? (JobSubmit) record : new JobSubmit((Job) record);
    return (Show<Job>) toShow(doCreateRequest(submitRecord));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Index<Job> getNext(Index<Job> index) throws Exception {
    return (Index<Job>) toIndex(doGetNext(index));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Index<Job> index(FilterBase filter) throws Exception {
    return (Index<Job>) toIndex(doIndexRequest(filter));
  }

  // This gets all Notes associated with the given JobId
  // This is a little more complete because it's the only API endpoint that makes a request to one endpoint (jobs) and expects a response in a
  // different object type (Note)
  public Index<Note> indexNotes(Integer jobId) throws Exception {
    if (jobId == null) {
      throw new IllegalStateException("jobId null");
    }

    String url = makeShowBaseUrl(jobId) + "/notes";
    String responseJson = getHandle().doGet(url);

    // @formatter:off
    return JsonHandle.get().getGson().fromJson(responseJson, new TypeToken<Index<Note>>() {}.getType());
    // @formatter:on
  }

  // Create a note. Notes is confusing but ultimately I decided that it should go into the client class that is the api base URL.
  public Show<Note> createNote(Integer jobId, Note record) throws Exception {
    if (record == null) {
      throw new IllegalStateException("record null");
    }
    if (jobId == null) {
      throw new IllegalStateException("jobId null");
    }
    if (record.getJobId() == null) {
      throw new IllegalStateException("jobId not found");
    }

    Show<Note> showRecord = new Show<Note>(record);
    String url = makeShowBaseUrl(jobId) + "/notes";
    String responseJson = getHandle().doPost(url, showRecord);

    // @formatter:off
    return JsonHandle.get().getGson().fromJson(responseJson, new TypeToken<Show<Note>>() {}.getType());
    // @formatter:on
  }

  // Convenience method, if you already have the jobId in the Note object
  public Show<Note> createNote(Note record) throws Exception {
    Integer jobId = record == null ? null : record.getJobId();
    return createNote(jobId, record);
  }

  // =================

  @Override
  @SuppressWarnings("unchecked")
  public Show<Job> show(Integer id) throws Exception {
    return (Show<Job>) toShow(doShowRequest(id));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Show<Job> update(Integer id, JobBase record) throws Exception {
    JobSubmit submitRecord = (record instanceof JobSubmit) ? (JobSubmit) record : new JobSubmit((Job) record);
    return (Show<Job>) toShow(doUpdateRequest(id, submitRecord));
  }

}
