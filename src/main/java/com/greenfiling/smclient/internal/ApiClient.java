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

package com.greenfiling.smclient.internal;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greenfiling.smclient.ApiHandle;
import com.greenfiling.smclient.JobClient;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.internal.FilterBase;

/**
 * Base class for all Serve Manager API endpoints
 * 
 * This class is never directly instantiated. Use instead, eg, {@link JobClient}
 *
 * @param <BASE>
 *          The base version of the interface object
 * @param <READ>
 *          The read version of the interface object
 * @param <CREATE>
 *          The create version of the interface object
 * @author jetmore
 * @since 1.0.0
 */
public abstract class ApiClient<BASE, READ, CREATE> {
  private static final Logger logger = LoggerFactory.getLogger(ApiClient.class);
  private ApiHandle apiHandle = null;
  private String endpoint;
  private Type showType = null;
  private Type indexType = null;

  public ApiClient() {
    throw new UnsupportedOperationException();
  }

  public ApiClient(ApiHandle handle) {
    setHandle(handle);
  }

  public Show<READ> create(BASE record) throws Exception {
    throw new UnsupportedOperationException("The extending class did not implement the create method");
  }

  public void getFile(String Url, String filePath) throws Exception {
    getHandle().doGetFile(Url, filePath);
  }

  public Index<READ> getNext(Index<READ> index) throws Exception {
    throw new UnsupportedOperationException("The extending class did not implement the getNext method");
  }

  /**
   * Convenience method to get the {@link Transaction}s from the client's {@link ApiHandle}
   * 
   * @return list of {@link Transaction}s associated with the client's {@link ApiHandle}
   * @since 1.0.4
   */
  public ArrayList<Transaction> getTransactions() {
    return apiHandle.getTransactions();
  }

  public Index<READ> index() throws Exception {
    return index(null);
  }

  public Index<READ> index(FilterBase filter) throws Exception {
    throw new UnsupportedOperationException("The extending class did not implement the index(filter) method");
  }

  public Show<READ> show(Integer id) throws Exception {
    throw new UnsupportedOperationException("The extending class did not implement the show method");
  }

  public Show<READ> show(Integer id, FilterBase filter) throws Exception {
    throw new UnsupportedOperationException("The extending class did not implement the show(filter) method");
  }

  public Show<READ> update(Integer id, BASE record) throws Exception {
    throw new UnsupportedOperationException("The extending class did not implement the update method");
  }

  private String getEndpoint() {
    return this.endpoint;
  }

  private Type getIndexType() {
    return this.indexType;
  }

  private Type getShowType() {
    return this.showType;
  }

  private String makeBaseUrl() {
    return apiHandle.getApiEndpointBase() + "/" + getEndpoint();
  }

  private String makeUrlWithFilter(String baseUrl, String filter) {
    if (filter == null || "".equals(filter)) {
      return baseUrl;
    }

    return baseUrl + "?" + filter;
  }

  private void setHandle(ApiHandle handle) {
    this.apiHandle = handle;
  }

  protected String doCreateRequest(CREATE record) throws Exception {
    Show<CREATE> showRecord = new Show<CREATE>(record);
    String url = makeCreateUrl();
    String responseJson = getHandle().doPost(url, showRecord);
    return responseJson;
  }

  protected String doGetNext(Index<READ> index) throws Exception {
    if (index == null || index.getLinks() == null || index.getLinks().getNext() == null || index.getData() == null || index.getData().size() == 0) {
      return null;
    }
    String responseJson = getHandle().doGet(index.getLinks().getNext());
    return responseJson;
  }

  protected String doIndexRequest(FilterBase filter) throws Exception {
    String url = makeIndexUrl(filter == null ? null : filter.getQueryString());
    String responseJson = getHandle().doGet(url);
    return responseJson;
  }

  protected String doShowRequest(Integer id) throws Exception {
    return doShowRequest(id, null);
  }

  protected String doShowRequest(Integer id, FilterBase filter) throws Exception {
    String url = makeShowUrl(id, filter);
    String responseJson = getHandle().doGet(url);
    return responseJson;
  }

  protected String doUpdateRequest(Integer id, CREATE record) throws Exception {
    Show<CREATE> showRecord = new Show<CREATE>(record);
    String url = makeUpdateUrl(id);
    String responseJson = getHandle().doPut(url, showRecord);
    return responseJson;
  }

  protected ApiHandle getHandle() {
    return this.apiHandle;
  }

  protected String makeCreateUrl() {
    return makeBaseUrl();
  }

  protected String makeIndexUrl(String filter) {
    String baseUrl = makeBaseUrl();

    return makeUrlWithFilter(baseUrl, filter);
  }

  /**
   * @return URL w/o the filter options. Abstracted to be allowed to be overridden
   */
  protected String makeShowBaseUrl(Integer id) {
    String baseUrl = makeBaseUrl();

    if (id == null) {
      return baseUrl;
    }
    return baseUrl + "/" + id.toString();

  }

  protected String makeShowUrl(Integer id, FilterBase filter) {
    return makeUrlWithFilter(makeShowBaseUrl(id), filter == null ? null : filter.getQueryString());
  }

  protected String makeUpdateUrl(Integer id) throws Exception {
    if (id == null) {
      throw new IllegalArgumentException("Argument id cannot be null");
    }
    return makeBaseUrl() + "/" + id.toString();
  }

  protected void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  protected void setIndexType(Type indexType) {
    this.indexType = indexType;
  }

  protected void setShowType(Type showType) {
    this.showType = showType;
  }

  protected Index<?> toIndex(String json) {
    logger.trace("toIndex - type = {}, value = {}", getIndexType().toString(), json);
    Index<?> showPojo = JsonHandle.get().getGson().fromJson(json, getIndexType());
    return showPojo;
  }

  protected Show<?> toShow(String json) {
    logger.trace("toShow - type = {}, value = {}", getShowType().toString(), json);
    Show<?> showPojo = JsonHandle.get().getGson().fromJson(json, getShowType());
    return showPojo;
  }

}
