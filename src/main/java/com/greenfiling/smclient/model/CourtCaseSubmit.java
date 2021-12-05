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

import com.greenfiling.smclient.model.internal.CourtCaseBase;

public class CourtCaseSubmit extends CourtCaseBase {
  private Integer courtId; // update = court_id

  public CourtCaseSubmit() {
    super();
  }

  public CourtCaseSubmit(CourtCase courtCase) {
    super(courtCase);

    // During create, these fields are sent as IDs instead of full objects
    if (courtCase.getCourt() != null) {
      setCourtId(courtCase.getCourt().getId());
    }
  }

  public Integer getCourtId() {
    return this.courtId;
  }

  public void setCourtId(Integer courtId) {
    this.courtId = courtId;
  }

}
