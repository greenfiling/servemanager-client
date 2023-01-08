/**
 * Copyright 2021-2023 Green Filing, LLC
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

package com.greenfiling.smclient.model.exchange;

import java.time.OffsetDateTime;

public class FilterDateRange {
  public static final String TYPE_CREATED_AT = "created_at";
  public static final String TYPE_SERVED_AT = "served_at";
  public static final String TYPE_ARCHIVED_AT = "archived_at";
  public static final String TYPE_DUE_DATE = "due_date";
  public static final String TYPE_FILED_DATE = "filed_date";
  public static final String TYPE_COURT_DATE = "court_date";

  private String type = null;
  private OffsetDateTime min = null;
  private OffsetDateTime max = null;

  public OffsetDateTime getMax() {
    return this.max;
  }

  public OffsetDateTime getMin() {
    return this.min;
  }

  public String getType() {
    return this.type;
  }

  public void setMax(OffsetDateTime max) {
    this.max = max;
  }

  public void setMin(OffsetDateTime min) {
    this.min = min;
  }

  /**
   * Sets the date_range[type] field
   * <P>
   * This field uses pre-defined values. Allowed values are:
   * <UL>
   * <LI>{@link #TYPE_CREATED_AT}</LI>
   * <LI>{@link #TYPE_SERVED_AT}</LI>
   * <LI>{@link #TYPE_ARCHIVED_AT}</LI>
   * <LI>{@link #TYPE_DUE_DATE}</LI>
   * <LI>{@link #TYPE_FILED_DATE}</LI>
   * <LI>{@link #TYPE_COURT_DATE}</LI>
   * </UL>
   * 
   * @param type
   *          type
   */
  public void setType(String type) {
    this.type = type;
  }
}
