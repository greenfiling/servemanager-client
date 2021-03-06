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

package com.greenfiling.smclient.model;

import java.time.OffsetDateTime;

import com.greenfiling.smclient.model.internal.DocumentBase;

public class ServiceDocument extends DocumentBase {
  public static final String TYPE = "document_to_be_served";

  private OffsetDateTime receivedAt;
  private String externalUrl;
  private Upload upload;

  public ServiceDocument() {
    super();
    setType(TYPE);
  }

  public String getExternalUrl() {
    return externalUrl;
  }

  public OffsetDateTime getReceivedAt() {
    return this.receivedAt;
  }

  public Upload getUpload() {
    return this.upload;
  }

  public void setExternalUrl(String externalUrl) {
    this.externalUrl = externalUrl;
  }

  public void setReceivedAt(OffsetDateTime receivedAt) {
    this.receivedAt = receivedAt;
  }

  public void setUpload(Upload upload) {
    this.upload = upload;
  }
}
