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

public class Webhook {
  public static final String TYPE = "webhook";

  public static String JOBS_CREATED = "jobs:created";
  public static String JOBS_UPDATED = "jobs:updated";
  public static String JOBS_DELETED = "jobs:deleted";
  public static String ATTEMPTS_CREATED = "attempts:created";
  public static String ATTEMPTS_UPDATED = "attempts:updated";
  public static String ATTEMPTS_DELETED = "attempts:deleted";
  public static String NOTES_CREATED = "notes:created";
  public static String NOTES_UPDATED = "notes:updated";
  public static String NOTES_DELETED = "notes:deleted";
  public static String ATTACHMENTS_CREATED = "attachments:created";
  public static String ATTACHMENTS_UPDATED = "attachments:updated";
  public static String ATTACHMENTS_DELETED = "attachments:deleted";
  public static String DOCUMENTS_CREATED = "documents:created";
  public static String DOCUMENTS_UPDATED = "documents:updated";
  public static String DOCUMENTS_DELETED = "documents:deleted";
  public static String AFFIDAVITS_SIGNED = "affidavits:signed";
  public static String INVOICES_CREATED = "invoices:created";
  public static String INVOICES_UPDATED = "invoices:updated";
  public static String INVOICES_ISSUED = "invoices:issued";
  public static String INVOICES_DELETED = "invoices:deleted";
  public static String SERVER_INVOICES_CREATED = "server_invoices:created";
  public static String SERVER_INVOICES_UPDATED = "server_invoices:updated";
  public static String SERVER_INVOICES_DELETED = "server_invoices:deleted";
  public static String DILIGENCE_REVIEWS_CREATED = "diligence_reviews:created";
  public static String DILIGENCE_REVIEWS_UPDATED = "diligence_reviews:updated";

  public String type = TYPE;
  public String id;
  public String name;
  public String targetUrl;
  public int batchIntervalInSeconds;
  public String clientReferenceKey;
  public boolean enabled;
  public String[] events;
  public boolean locked;
  public String secretKey;

  public int getBatchIntervalInSeconds() {
    return batchIntervalInSeconds;
  }

  public String getClientReferenceKey() {
    return clientReferenceKey;
  }

  public String[] getEvents() {
    return events;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getSecretKey() {
    return secretKey;
  }

  public String getTargetUrl() {
    return targetUrl;
  }

  public String getType() {
    return type;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public boolean isLocked() {
    return locked;
  }

  public void setBatchIntervalInSeconds(int batchIntervalInSeconds) {
    this.batchIntervalInSeconds = batchIntervalInSeconds;
  }

  public void setClientReferenceKey(String clientReferenceKey) {
    this.clientReferenceKey = clientReferenceKey;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public void setEvents(String[] events) {
    this.events = events;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  public void setTargetUrl(String targetUrl) {
    this.targetUrl = targetUrl;
  }

  public void setType(String type) {
    this.type = type;
  }

}