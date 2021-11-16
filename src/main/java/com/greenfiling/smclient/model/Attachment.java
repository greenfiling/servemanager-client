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

public class Attachment extends DocumentBase {
  public static final String TYPE = "misc_attachment";

  private Upload upload;

  public Attachment() {
    super();
    setType(TYPE);
  }

  public Upload getUpload() {
    return this.upload;
  }

  public void setUpload(Upload upload) {
    this.upload = upload;
  }

}
