/**
 * Copyright 2021-2025 Green Filing, LLC
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

import java.time.OffsetDateTime;
import java.util.ArrayList;

public class Note extends Data {
  public static final String TYPE = "note";

  public static final Integer NOTE_CATEGORY_NONE = null;
  public static final Integer NOTE_CATEGORY_CANCEL = 1;
  public static final Integer NOTE_CATEGORY_ADD_REMOVE_DOCUMENTS = 2;
  public static final Integer NOTE_CATEGORY_UPDATE_ADDRESS = 3;
  public static final Integer NOTE_CATEGORY_SERVICE_INSTRUCTIONS = 4;
  public static final Integer NOTE_CATEGORY_STATUS_REQUEST = 5;
  public static final Integer NOTE_CATEGORY_AFFIDAVIT_ISSUE = 6;
  public static final Integer NOTE_CATEGORY_QUESTION_ABOUT_FEES = 7;
  public static final Integer NOTE_CATEGORY_OTHER = 8;

  private Links links;
  private Integer id;
  private String label;
  private String body;
  private Integer jobId;
  private OffsetDateTime updatedAt;
  private OffsetDateTime createdAt;
  private Integer categoryId;
  private String createdBy;
  private ArrayList<ArrayList<String>> emailedTo;
  private String sharedFrom;
  private Integer userId;
  private ArrayList<String> visibility;
  private String clientJobNumber;

  public Note() {
    super();
    setType(TYPE);
  }

  public String getBody() {
    return this.body;
  }

  public Integer getCategoryId() {
    return this.categoryId;
  }

  public OffsetDateTime getCreatedAt() {
    return this.createdAt;
  }

  // This is the name of the logged in user that created the note
  public String getCreatedBy() {
    return this.createdBy;
  }

  /**
   * The list of people who were emailed about this note.
   * <P>
   * This is a list of lists. Every member of the outer list represents one email sent. The contents of the inner array represent the details about
   * that email. If both the recipient's name and email address are known, element [0] will be the recipient's name (e.g. "Joe Smith") and element [1]
   * will be the recipient's email address. If only the recipient's address is known, element [0] will hold the address.
   * <P>
   * This information reverse engineered from the UI/API and not documented to the best of my knowledge
   * 
   * @return list of information about people emailed by ServeManager about this note
   */
  public ArrayList<ArrayList<String>> getEmailedTo() {
    return this.emailedTo;
  }

  public Integer getId() {
    return this.id;
  }

  public Integer getJobId() {
    return this.jobId;
  }

  public String getLabel() {
    return this.label;
  }

  public Links getLinks() {
    return this.links;
  }

  public String getSharedFrom() {
    return this.sharedFrom;
  }

  public OffsetDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  // This is the SM user id of the logged in user that created the note
  public Integer getUserId() {
    return this.userId;
  }

  // I've not seen a formal definition of this but I've seen the values "client" and "server"
  public ArrayList<String> getVisibility() {
    return this.visibility;
  }

  public String getClientJobNumber() {
    return this.clientJobNumber;
  }

  public void setBody(String body) {
    this.body = body;
  }

  /**
   * Sets the note category
   * <P>
   * The "known" values are
   * <UL>
   * <LI>{@link #NOTE_CATEGORY_NONE}</LI>
   * <LI>{@link #NOTE_CATEGORY_CANCEL}</LI>
   * <LI>{@link #NOTE_CATEGORY_ADD_REMOVE_DOCUMENTS}</LI>
   * <LI>{@link #NOTE_CATEGORY_UPDATE_ADDRESS}</LI>
   * <LI>{@link #NOTE_CATEGORY_SERVICE_INSTRUCTIONS}</LI>
   * <LI>{@link #NOTE_CATEGORY_STATUS_REQUEST}</LI>
   * <LI>{@link #NOTE_CATEGORY_AFFIDAVIT_ISSUE}</LI>
   * <LI>{@link #NOTE_CATEGORY_QUESTION_ABOUT_FEES}</LI>
   * <LI>{@link #NOTE_CATEGORY_OTHER}</LI>
   * </UL>
   * 
   * @param categoryId
   *          note category
   */
  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * read only - you can set this for the local object but the API will not honor it on create/update. It will instead be set to whatever name
   * ServeManager has associated with the userId for this note, if one is set
   * 
   * @param createdBy
   *          The username of the ServeManager user who created this note
   */
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  /**
   * read only - you can set this for the local object but the API will not honor it on create/update
   * 
   * @param emailedTo
   *          list of information about emailed recipients of this note
   */
  public void setEmailedTo(ArrayList<ArrayList<String>> emailedTo) {
    this.emailedTo = emailedTo;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setJobId(Integer jobId) {
    this.jobId = jobId;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public void setLinks(Links links) {
    this.links = links;
  }

  /**
   * read only - you can set this for the local object but the API will not honor it on create/update
   * 
   * @param sharedFrom
   *          ??
   */
  public void setSharedFrom(String sharedFrom) {
    this.sharedFrom = sharedFrom;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public void setVisibility(ArrayList<String> visibility) {
    this.visibility = visibility;
  }

  public void setClientJobNumber(String clientJobNumber) {
    this.clientJobNumber = clientJobNumber;
  }
}
