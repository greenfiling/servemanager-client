/**
 * Copyright 2024 Green Filing, LLC
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

public class Meta {
  public String webhookId;
  public String webhookName;
  public String reference;
  public OffsetDateTime createdAt;
  public int eventCount;
  public String targetUrl;
  public int accountId;
  public OffsetDateTime deliveredAt;

  public int getAccountId() {
    return accountId;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public OffsetDateTime getDeliveredAt() {
    return deliveredAt;
  }

  public int getEventCount() {
    return eventCount;
  }

  public String getReference() {
    return reference;
  }

  public String getTargetUrl() {
    return targetUrl;
  }

  public String getWebhookId() {
    return webhookId;
  }

  public String getWebhookName() {
    return webhookName;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setDeliveredAt(OffsetDateTime deliveredAt) {
    this.deliveredAt = deliveredAt;
  }

  public void setEventCount(int eventCount) {
    this.eventCount = eventCount;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public void setTargetUrl(String targetUrl) {
    this.targetUrl = targetUrl;
  }

  public void setWebhookId(String webhookId) {
    this.webhookId = webhookId;
  }

  public void setWebhookName(String webhookName) {
    this.webhookName = webhookName;
  }
}
