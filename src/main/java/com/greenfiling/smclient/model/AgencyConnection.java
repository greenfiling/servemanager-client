/**
 * Copyright 2026 Green Filing, LLC
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

import java.util.List;

public class AgencyConnection {

  private Integer processServerCompanyId;
  private String processServerCompanyName;
  private String apiKey;
  private String status;
  private List<Object> webhookData;

  public String getApiKey() {
    return this.apiKey;
  }

  public Integer getProcessServerCompanyId() {
    return this.processServerCompanyId;
  }

  public String getProcessServerCompanyName() {
    return this.processServerCompanyName;
  }

  public String getStatus() {
    return this.status;
  }

  public List<Object> getWebhookData() {
    return this.webhookData;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public void setProcessServerCompanyId(Integer processServerCompanyId) {
    this.processServerCompanyId = processServerCompanyId;
  }

  public void setProcessServerCompanyName(String processServerCompanyName) {
    this.processServerCompanyName = processServerCompanyName;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setWebhookData(List<Object> webhookData) {
    this.webhookData = webhookData;
  }
}