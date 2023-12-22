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

package com.greenfiling.smclient.model.exchange;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.greenfiling.smclient.model.internal.FilterBase;

public class CompanyFilter extends FilterBase {
  private FilterDateRange dateRange = null;

  public FilterDateRange getDateRange() {
    return this.dateRange;
  }

  @Override
  public ArrayList<FilterPair> getFilters() {
    ArrayList<FilterPair> pairs = super.getFilters();
    if (getDateRange() != null) {
      if (getDateRange().getType() != null) {
        pairs.add(new FilterPair("filter[date_range][type]", getDateRange().getType()));
      }
      if (getDateRange().getMin() != null) {
        pairs.add(new FilterPair("filter[date_range][min]", getDateRange().getMin().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
      }
      if (getDateRange().getMax() != null) {
        pairs.add(new FilterPair("filter[date_range][max]", getDateRange().getMax().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
      }
    }

    return pairs;
  }

  public void setDateRange(FilterDateRange dateRange) {
    this.dateRange = dateRange;
  }
}
