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

import com.greenfiling.smclient.model.internal.DocumentBase;

public class Document extends DocumentBase {
  public static final String TYPE = "document";

  private String pdfDownloadUrl;
  private String documentType;

  public Document() {
    super();
    setType(TYPE);
  }

  public String getDocumentType(){
    return documentType;
  }

  public String getPdfDownloadUrl() {
    return this.pdfDownloadUrl;
  }

  public void setDocumentType(String documentType){
    this.documentType = documentType;
  }

  public void setPdfDownloadUrl(String pdfDownloadUrl) {
    this.pdfDownloadUrl = pdfDownloadUrl;
  }

}
