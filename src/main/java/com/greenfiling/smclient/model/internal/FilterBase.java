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

package com.greenfiling.smclient.model.internal;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class FilterBase {

  public class FilterPair {
    private String key;
    private String value;

    public FilterPair(String key, String value) {
      super();
      this.key = key;
      this.value = value;
    }

    public String getKey() {
      return this.key;
    }

    public String getValue() {
      return this.value;
    }

    public void setKey(String key) {
      this.key = key;
    }

    public void setValue(String value) {
      this.value = value;
    }
  }

  private static final Logger logger = LoggerFactory.getLogger(FilterBase.class);

  public static final String ARCHIVE_STATE_ALL = "all";
  public static final String ARCHIVE_STATE_ACTIVE = "active";
  public static final String ARCHIVE_STATE_ARCHIVED = "archived";

  private String q = null;
  private Integer page = null;
  private String archiveState = null;

  public String getArchiveState() {
    return archiveState;
  }

  public ArrayList<FilterPair> getFilters() {
    ArrayList<FilterPair> pairs = new ArrayList<FilterPair>();

    if (getQ() != null) {
      pairs.add(new FilterPair("q", getQ()));
    }
    if (getPage() != null) {
      pairs.add(new FilterPair("page", getPage().toString()));
    }
    if (getArchiveState() != null) {
      pairs.add(new FilterPair("filter[archive_state]", getArchiveState()));
    }

    return pairs;
  }

  public Integer getPage() {
    return this.page;
  }

  public String getQ() {
    return this.q;
  }

  public String getQueryString() {
    String queryString = "";

    ArrayList<FilterPair> pairs = getFilters();
    if (pairs == null) {
      return queryString;
    }

    for (FilterPair pair : pairs) {
      queryString = queryString + pair.getKey() + "=" + pair.getValue() + "&";
    }

    if (queryString.endsWith("&")) {
      queryString = queryString.substring(0, queryString.length() - 1);
    }
    logger.trace("getQueryString - queryString = {}", queryString);
    return queryString;
  }

  /**
   * Sets the filter[archived_state]
   * <P>
   * This field uses pre-defined values. Allowed values are:
   * <UL>
   * <LI>{@link #ARCHIVE_STATE_ALL} (default)</LI>
   * <LI>{@link #ARCHIVE_STATE_ACTIVE}</LI>
   * <LI>{@link #ARCHIVE_STATE_ARCHIVED}</LI>
   * </UL>
   * 
   * @param archiveState
   *          archive state
   */
  public void setArchiveState(String archiveState) {
    this.archiveState = archiveState;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public void setQ(String q) {
    this.q = q;
  }

}
