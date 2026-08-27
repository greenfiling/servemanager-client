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

package com.greenfiling.smclient.model.exchange;

import java.util.ArrayList;

import com.greenfiling.smclient.model.internal.FilterBase;

public class AgencyFilter extends FilterBase {

  private String zipcode;
  private String address;
  private String email;
  private String type;
  private Integer[] accountIds;
  private Boolean usingSuggestedRetailPricing;
  private Integer limit;

  public Integer[] getAccountIds() {
    return accountIds;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public ArrayList<FilterPair> getFilters() {
    ArrayList<FilterPair> pairs = super.getFilters();

    if (getZipcode() != null) {
      pairs.add(new FilterPair("zipcode", getZipcode()));
    }

    if (getAddress() != null) {
      pairs.add(new FilterPair("address", getAddress()));
    }

    if (getEmail() != null) {
      pairs.add(new FilterPair("email", getEmail()));
    }

    if (getType() != null) {
      pairs.add(new FilterPair("type", getType()));
    }

    if (getAccountIds() != null) {
      for (Integer accountId : getAccountIds()) {
        pairs.add(new FilterPair("account_ids[]", accountId.toString()));
      }
    }

    if (getUsingSuggestedRetailPricing() != null) {
      pairs.add(new FilterPair("using_suggested_retail_pricing", getUsingSuggestedRetailPricing().toString()));
    }

    if (getLimit() != null) {
      pairs.add(new FilterPair("limit", getLimit().toString()));
    }

    return pairs;
  }

  public Integer getLimit() {
    return limit;
  }

  public String getType() {
    return type;
  }

  public Boolean getUsingSuggestedRetailPricing() {
    return usingSuggestedRetailPricing;
  }

  public String getZipcode() {
    return zipcode;
  }

  public void setAccountIds(Integer[] accountIds) {
    this.accountIds = accountIds;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setUsingSuggestedRetailPricing(Boolean usingSuggestedRetailPricing) {
    this.usingSuggestedRetailPricing = usingSuggestedRetailPricing;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }
}