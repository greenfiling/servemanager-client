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
import java.util.ArrayList;

public class WebhookEvent {
  public String type;
  public int id;
  public String event;
  public String eventReference;
  public String action;
  public ArrayList<String> changed;
  public OffsetDateTime createdAt;

  public String getAction() {
    return action;
  }

  public ArrayList<String> getChanged() {
    return changed;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public String getEvent() {
    return event;
  }

  public String getEventReference() {
    return eventReference;
  }

  public int getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public void setChanged(ArrayList<String> changed) {
    this.changed = changed;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public void setEventReference(String eventReference) {
    this.eventReference = eventReference;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setType(String type) {
    this.type = type;
  }
}