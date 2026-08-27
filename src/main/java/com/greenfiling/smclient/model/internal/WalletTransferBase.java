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

package com.greenfiling.smclient.model.internal;

public class WalletTransferBase {

  private String type;
  private Integer jobId;
  private Integer processServerCompanyId;

  public Integer getJobId() {
    return this.jobId;
  }

  public Integer getProcessServerCompanyId() {
    return this.processServerCompanyId;
  }

  public String getType() {
    return this.type;
  }

  public void setJobId(Integer jobId) {
    this.jobId = jobId;
  }

  public void setProcessServerCompanyId(Integer processServerCompanyId) {
    this.processServerCompanyId = processServerCompanyId;
  }

  public void setType(String type) {
    this.type = type;
  }

}