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

/**
 * Represents pagination links. Can also represent a single self-link if not part of an index
 * 
 * @author jetmore
 *
 */
public class Links {
  /**
   * Always contains the URL for the request that generated this current request
   */
  private String self;
  /**
   * In a pagination scenario, contains the URL of the first page
   */
  private String first;
  /**
   * In a pagination scenario, contains the URL of the last page
   */
  private String last;
  /**
   * In a pagination scenario, contains the URL of the previous page, or null if already on the first page
   */
  private String prev;
  /**
   * In a pagination scenario, contains the URL of the last page, or null if already on the last page
   */
  private String next;

  public String getFirst() {
    return this.first;
  }

  public String getLast() {
    return this.last;
  }

  public String getNext() {
    return this.next;
  }

  public String getPrev() {
    return this.prev;
  }

  public String getSelf() {
    return this.self;
  }

  public void setFirst(String first) {
    this.first = first;
  }

  public void setLast(String last) {
    this.last = last;
  }

  public void setNext(String next) {
    this.next = next;
  }

  public void setPrev(String prev) {
    this.prev = prev;
  }

  public void setSelf(String self) {
    this.self = self;
  }
}
