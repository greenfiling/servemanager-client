/**
 * Copyright 2021-2024 Green Filing, LLC
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

import com.greenfiling.smclient.model.internal.DocumentBase;

public class Attachment extends DocumentBase {
  public static final String TYPE = "misc_attachment";

  private Upload upload;
  private boolean affidavit;
  private String externalUrl;
  private Integer referenceNumber;

  public Attachment() {
    super();
    setType(TYPE);
  }

  public boolean getAffidavit() {
    return this.affidavit;
  }

  public String getExternalUrl() {
    return externalUrl;
  }

  public Integer getReferenceNumber() {
    return this.referenceNumber;
  }

  public Upload getUpload() {
    return this.upload;
  }

  public void setAffidavit(boolean affidavit) {
    this.affidavit = affidavit;
  }

  public void setExternalUrl(String externalUrl) {
    this.externalUrl = externalUrl;
  }

  public void setReferenceNumber(Integer referenceNumber) {
    this.referenceNumber = referenceNumber;
  }

  public void setUpload(Upload upload) {
    this.upload = upload;
  }

}
