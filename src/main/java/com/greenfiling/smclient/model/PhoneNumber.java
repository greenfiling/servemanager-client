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

import com.greenfiling.smclient.model.internal.ContactPoint;

public class PhoneNumber extends ContactPoint {
  public static final String TYPE = "phone_number";

  public static final String LABEL_OFFICE = "Work";
  public static final String LABEL_MOBILE = "Mobile";
  public static final String LABEL_FAX = "Fax";
  public static final String LABEL_PAGER = "Pager";
  public static final String LABEL_HOME = "Home";
  public static final String LABEL_OTHER = "Other";

  private String number;

  public PhoneNumber() {
    super();
    this.setType(TYPE);
  }

  public String getNumber() {
    return number;
  }

  /**
   * Sets the phone number label
   * <P>
   * This field uses pre-defined values. Allowed values are:
   * <UL>
   * <LI>{@link #LABEL_OFFICE}</LI>
   * <LI>{@link #LABEL_MOBILE}</LI>
   * <LI>{@link #LABEL_FAX}</LI>
   * <LI>{@link #LABEL_PAGER}</LI>
   * <LI>{@link #LABEL_HOME}</LI>
   * <LI>{@link #LABEL_OTHER}</LI>
   * </UL>
   * 
   * @param label
   */
  @Override
  public void setLabel(String label) {
    super.setLabel(label);
  }

  public void setNumber(String number) {
    this.number = number;
  }

}
