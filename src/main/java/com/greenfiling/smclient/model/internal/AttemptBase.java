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

package com.greenfiling.smclient.model.internal;

import java.time.OffsetDateTime;

public abstract class AttemptBase {
  public static final String TYPE = "attempt";

  public static final String SERVE_TYPE_SUCCESS_AUTHORIZED = "Authorized";
  public static final String SERVE_TYPE_SUCCESS_BUSINESS = "Business";
  public static final String SERVE_TYPE_SUCCESS_CORPORATION = "Corporation";
  public static final String SERVE_TYPE_SUCCESS_GOVERNMENT_AGENCY = "Government Agency";
  public static final String SERVE_TYPE_SUCCESS_MAIL = "Mail";
  public static final String SERVE_TYPE_SUCCESS_PERSONAL = "Personal/Individual";
  public static final String SERVE_TYPE_SUCCESS_POSTED = "Posted";
  public static final String SERVE_TYPE_SUCCESS_REGISTERED_AGENT = "Registered Agent";
  public static final String SERVE_TYPE_SUCCESS_SECRETARY_OF_STATE = "Secretary of State";
  public static final String SERVE_TYPE_SUCCESS_SUBSTITUTE_ABODE = "Substitute Service - Abode";
  public static final String SERVE_TYPE_SUCCESS_SUBSTITUTE_BUSINESS = "Substitute Service - Business";
  public static final String SERVE_TYPE_SUCCESS_SUBSTITUTE_PERSONAL = "Substitute Service - Personal";
  public static final String SERVE_TYPE_FAILURE_BAD_ADDRESS = "Bad Address";
  public static final String SERVE_TYPE_FAILURE_NON_SERVICE = "Non-Service";
  public static final String SERVE_TYPE_FAILURE_UNSUCCESSFUL_ATTEMPT = "Unsuccessful Attempt";

  private String type;
  private String description;
  private String lat;
  private String lng;
  private String serveType;
  private String gpsTimestamp; // eg 1599853569436 (epoch + ms)
  private String deviceTimestamp; // eg Fri Sep 11 2020 15:46:56 GMT-0400
  private String gpsAccuracy;
  private String gpsHeading;
  private String gpsSpeed;
  private String gpsAltitude;
  private String gpsAltitudeAccuracy;
  private String gpsUserAgent;
  private String serverName;
  private OffsetDateTime servedAt;

  public AttemptBase() {
    super();
    setType(TYPE);
  }

  public AttemptBase(AttemptBase attempt) {
    super();
    setType(TYPE);

    setType(attempt.getType());
    setDescription(attempt.getDescription());
    setLat(attempt.getLat());
    setLng(attempt.getLng());
    setServeType(attempt.getServeType());
    setGpsTimestamp(attempt.getGpsTimestamp());
    setDeviceTimestamp(attempt.getDeviceTimestamp());
    setGpsAccuracy(attempt.getGpsAccuracy());
    setGpsHeading(attempt.getGpsHeading());
    setGpsSpeed(attempt.getGpsSpeed());
    setGpsAltitude(attempt.getGpsAltitude());
    setGpsAltitudeAccuracy(attempt.getGpsAltitudeAccuracy());
    setGpsUserAgent(attempt.getGpsUserAgent());
    setServerName(attempt.getServerName());
    setServedAt(attempt.getServedAt());
  }

  public String getDescription() {
    return this.description;
  }

  public String getDeviceTimestamp() {
    return this.deviceTimestamp;
  }

  public String getGpsAccuracy() {
    return this.gpsAccuracy;
  }

  public String getGpsAltitude() {
    return this.gpsAltitude;
  }

  public String getGpsAltitudeAccuracy() {
    return this.gpsAltitudeAccuracy;
  }

  public String getGpsHeading() {
    return this.gpsHeading;
  }

  public String getGpsSpeed() {
    return this.gpsSpeed;
  }

  public String getGpsTimestamp() {
    return this.gpsTimestamp;
  }

  public String getGpsUserAgent() {
    return gpsUserAgent;
  }

  public String getLat() {
    return this.lat;
  }

  public String getLng() {
    return this.lng;
  }

  public OffsetDateTime getServedAt() {
    return this.servedAt;
  }

  public String getServerName() {
    return this.serverName;
  }

  public String getServeType() {
    return this.serveType;
  }

  public String getType() {
    return this.type;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setDeviceTimestamp(String deviceTimestamp) {
    this.deviceTimestamp = deviceTimestamp;
  }

  public void setGpsAccuracy(String gpsAccuracy) {
    this.gpsAccuracy = gpsAccuracy;
  }

  public void setGpsAltitude(String gpsAltitude) {
    this.gpsAltitude = gpsAltitude;
  }

  public void setGpsAltitudeAccuracy(String gpsAltitudeAccuracy) {
    this.gpsAltitudeAccuracy = gpsAltitudeAccuracy;
  }

  public void setGpsHeading(String gpsHeading) {
    this.gpsHeading = gpsHeading;
  }

  public void setGpsSpeed(String gpsSpeed) {
    this.gpsSpeed = gpsSpeed;
  }

  public void setGpsTimestamp(String gpsTimestamp) {
    this.gpsTimestamp = gpsTimestamp;
  }

  public void setGpsUserAgent(String gpsUserAgent) {
    this.gpsUserAgent = gpsUserAgent;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }

  public void setLng(String lng) {
    this.lng = lng;
  }

  public void setServedAt(OffsetDateTime servedAt) {
    this.servedAt = servedAt;
  }

  public void setServerName(String serverName) {
    this.serverName = serverName;
  }

  /**
   * Sets the serve type
   * <P>
   * This field uses pre-defined values.
   * <P>
   * <B>Successful Values</B>
   * <UL>
   * <LI>{@link #SERVE_TYPE_SUCCESS_AUTHORIZED}</LI>
   * <LI>{@link #SERVE_TYPE_SUCCESS_BUSINESS}</LI>
   * <LI>{@link #SERVE_TYPE_SUCCESS_CORPORATION}</LI>
   * <LI>{@link #SERVE_TYPE_SUCCESS_GOVERNMENT_AGENCY}</LI>
   * <LI>{@link #SERVE_TYPE_SUCCESS_MAIL}</LI>
   * <LI>{@link #SERVE_TYPE_SUCCESS_PERSONAL}</LI>
   * <LI>{@link #SERVE_TYPE_SUCCESS_POSTED}</LI>
   * <LI>{@link #SERVE_TYPE_SUCCESS_REGISTERED_AGENT}</LI>
   * <LI>{@link #SERVE_TYPE_SUCCESS_SECRETARY_OF_STATE}</LI>
   * <LI>{@link #SERVE_TYPE_SUCCESS_SUBSTITUTE_ABODE}</LI>
   * <LI>{@link #SERVE_TYPE_SUCCESS_SUBSTITUTE_BUSINESS}</LI>
   * <LI>{@link #SERVE_TYPE_SUCCESS_SUBSTITUTE_PERSONAL}</LI>
   * </UL>
   * <B>Unsuccessful Values</B>
   * <UL>
   * <LI>{@link #SERVE_TYPE_FAILURE_BAD_ADDRESS}</LI>
   * <LI>{@link #SERVE_TYPE_FAILURE_NON_SERVICE}</LI>
   * <LI>{@link #SERVE_TYPE_FAILURE_UNSUCCESSFUL_ATTEMPT}</LI>
   * </UL>
   * 
   * @param serveType
   */
  public void setServeType(String serveType) {
    this.serveType = serveType;
  }

  public void setType(String type) {
    this.type = type;
  }
}
