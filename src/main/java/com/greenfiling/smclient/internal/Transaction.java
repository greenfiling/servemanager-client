/**
 * Copyright 2023 Green Filing, LLC
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

package com.greenfiling.smclient.internal;

import com.greenfiling.smclient.ApiHandle;

/**
 * Holds low-level information about request/response pairs
 * <P>
 * Useful for debugging and logging outside of this client.
 * <P>
 * Every request/response creates a Transaction object and populates it as much as possible. The list of transactions is per-{@link ApiHandle}, and
 * can be accessed via {@link ApiHandle#getTransactions()} (and also as a convenience via client classes via {@link ApiClient#getTransactions()}.
 * <P>
 * By default, {@link ApiHandle#DEFAULT_KEEP_TRANSACTIONS} transactions are kept in memory at once. This value can be changed, including setting it to
 * zero to retain no transactions, via {@link ApiHandle.Builder#keepTransactions(int)} at {@link ApiHandle} build time.
 * 
 * @author jetmore
 * @since 1.0.4
 */
public class Transaction {
  private String requestType;
  private String requestUrl;
  private String requestBody;
  private Integer responseCode;
  private String responseLine;
  private String responseBody;

  public String getRequestBody() {
    return this.requestBody;
  }

  public String getRequestType() {
    return this.requestType;
  }

  public String getRequestUrl() {
    return this.requestUrl;
  }

  public String getResponseBody() {
    return this.responseBody;
  }

  public Integer getResponseCode() {
    return this.responseCode;
  }

  public String getResponseLine() {
    return this.responseLine;
  }

  public void setRequestBody(String requestBody) {
    this.requestBody = requestBody;
  }

  public void setRequestType(String requestType) {
    this.requestType = requestType;
  }

  public void setRequestUrl(String requestUrl) {
    this.requestUrl = requestUrl;
  }

  public void setResponseBody(String responseBody) {
    this.responseBody = responseBody;
  }

  public void setResponseCode(Integer responseCode) {
    this.responseCode = responseCode;
  }

  public void setResponseLine(String responseLine) {
    this.responseLine = responseLine;
  }
}
