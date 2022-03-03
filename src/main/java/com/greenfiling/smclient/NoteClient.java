/**
 * Copyright 2021 Green Filing, LLC
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

import com.google.gson.reflect.TypeToken;
import com.greenfiling.smclient.internal.ApiClient;
import com.greenfiling.smclient.model.Note;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;

public class NoteClient extends ApiClient<Note, Note, Note> {
  public static final String ENDPOINT = "notes";

  public NoteClient(ApiHandle handle) {
    super(handle);
    setEndpoint(ENDPOINT);

    // @formatter:off
    setShowType(new TypeToken<Show<Note>>() {}.getType());
    setIndexType(new TypeToken<Index<Note>>() {}.getType());
    // @formatter:on
  }

  @Override
  @SuppressWarnings("unchecked")
  public Index<Note> getNext(Index<Note> index) throws Exception {
    return (Index<Note>) toIndex(doGetNext(index));
  }

  // Because notes.index doesn't support filtering, we'll override the version that doesn't use a filter. That will leave the version with a filter
  // throwing an unimplemented exception
  @Override
  @SuppressWarnings("unchecked")
  public Index<Note> index() throws Exception {
    return (Index<Note>) toIndex(doIndexRequest(null));
  }

}
