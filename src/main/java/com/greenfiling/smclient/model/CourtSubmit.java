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

import com.greenfiling.smclient.model.internal.CourtBase;

public class CourtSubmit extends CourtBase {
  private Address addressAttributes;

  public CourtSubmit() {
    super();
  }

  public CourtSubmit(Court court) {
    super(court);

    // These field names are different from the read version of the object
    setAddressAttributes(court.getAddress());
  }

  public Address getAddressAttributes() {
    return this.addressAttributes;
  }

  public void setAddressAttributes(Address addressAttributes) {
    this.addressAttributes = addressAttributes;
  }

}
