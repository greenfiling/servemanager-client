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

public class Upload {
  public class Links {
    private String downloadUrl;
    private String putUrl;

    public String getDownloadUrl() {
      return this.downloadUrl;
    }

    public String getPutUrl() {
      return putUrl;
    }

    public void setDownloadUrl(String downloadLink) {
      this.downloadUrl = downloadLink;
    }

    public void setPutUrl(String putUrl) {
      this.putUrl = putUrl;
    }
  }

  private String fileName;
  private String contentType;
  private String fileSize; // Should be double?
  private Integer recordId;
  private String recordType;
  private Upload.Links links;

  public String getContentType() {
    return this.contentType;
  }

  public String getFileName() {
    return this.fileName;
  }

  public String getFileSize() {
    return this.fileSize;
  }

  public Upload.Links getLinks() {
    return this.links;
  }

  public Integer getRecordId() {
    return recordId;
  }

  public String getRecordType() {
    return recordType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public void setFileSize(String fileSize) {
    this.fileSize = fileSize;
  }

  public void setLinks(Upload.Links links) {
    this.links = links;
  }

  public void setRecordId(Integer recordId) {
    this.recordId = recordId;
  }

  public void setRecordType(String recordType) {
    this.recordType = recordType;
  }
}
