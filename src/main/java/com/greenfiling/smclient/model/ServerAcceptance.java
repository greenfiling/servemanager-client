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

import com.greenfiling.smclient.model.internal.CourtCaseBase;

public class ServerAcceptance extends CourtCaseBase {

  public static final String RESPONSE_PENDING = "pending";
  public static final String RESPONSE_DECLINED = "declined";
  public static final String RESPONSE_ACCEPTED = "accepted";

  private String response;
  private String declineReason;
  private String declineDescription;
  private OffsetDateTime declinedAt;

  public ServerAcceptance() {
    super();
  }

  public String getResponse() {
    return this.response;
  }

  public String getDeclineReason() {
    return this.declineReason;
  }

  public String getDeclineDescription() {
    return this.declineDescription;
  }

  public OffsetDateTime getDeclinedAt() {
    return this.declinedAt;
  }

  /**
   * Sets the response
   * <P>
   * This field is Either null, "pending", "declined", or "accepted"
   * <UL>
   * 
   * <LI>{@link #RESPONSE_PENDING}</LI>
   * <LI>{@link #RESPONSE_DECLINED}</LI>
   * <LI>{@link #RESPONSE_ACCEPTED}</LI>
   * </UL>
   * 
   * @param response
   *          response
   */
  public void setResponse(String response) {
    this.response = response;
  }

  public void setDeclineReason(String declineReason) {
    this.declineReason = declineReason;
  }

  public void setDeclineDescription(String declineDescription) {
    this.declineDescription = declineDescription;
  }

  public void setDeclinedAt(OffsetDateTime declinedAt) {
    this.declinedAt = declinedAt;
  }
}
