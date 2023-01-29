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

package com.greenfiling.smclient;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greenfiling.smclient.model.Address;
import com.greenfiling.smclient.model.Recipient;

public class TestHelper {
  private static final Logger logger = LoggerFactory.getLogger(TestHelper.class);

  private static Properties properties = null;
  private static final String API_KEY_FILE_NAME = "/testing.properties";
  public static final String VALID_API_KEY = getProperty("api-key");
  public static final Boolean QUIET_TESTS = getProperty("quiet-tests").equalsIgnoreCase("true") ? true : false;
  // public static final Boolean LOG_AT_TRACE = getProperty("log-trace").equalsIgnoreCase("true") ? true : false;

  public static final String VALID_FILE_NAME_1 = "small-1.pdf";
  public static final String VALID_FILE_PATH_1 = getResourcePath(VALID_FILE_NAME_1);

  public static final String VALID_FILE_NAME_2 = "small-2.pdf";
  public static final String VALID_FILE_PATH_2 = getResourcePath(VALID_FILE_NAME_2);

  public static final Integer VALID_CLIENT_COMPANY_ID = 783056;
  public static final Integer VALID_CLIENT_CONTACT_ID = 984328;
  public static final Integer VALID_PROCESS_SERVER_COMPANY_ID = 917465;
  public static final Integer VALID_PROCESS_SERVER_CONTACT_ID = null;
  public static final Integer VALID_EMPLOYEE_PROCESS_SERVER_ID = 289268;
  public static final Integer VALID_COURT_CASE_ID = 3109987;

  public static Address getAddress() {
    Address a = new Address();

    a.setLabel("TestHelper default label");
    a.setAddress1("TestHelper Address 1");
    a.setAddress2("TestHelper Address 2");
    a.setCity("Beverly Hills");
    a.setState("CA");
    a.setPostalCode("90210");
    a.setCounty("Los Angeles County");
    a.setPrimary(true);

    return a;
  }

  public static ApiHandle getApiHandle() {
    return new ApiHandle.Builder().apiKey(VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE).build();
  }

  public static Recipient getRecipient() {
    Recipient r = new Recipient();

    r.setName("TestHelper DefaultRecipient");
    r.setDescription("TestHelper default description");
    r.setAge("35");
    r.setEthnicity(Recipient.ETHNICITY_HISPANIC);
    r.setGender(Recipient.GENDER_FEMALE);
    r.setWeight("200");
    r.setHeight1(Recipient.HEIGHT_7_FT);
    r.setHeight2(Recipient.HEIGHT_11_IN);
    r.setHair(Recipient.HAIR_OTHER);
    r.setEyes(Recipient.EYES_BLACK);
    r.setRelationship(Recipient.RELATIONSHIP_GRANDFATHER);
    r.setEmail("foo@bar.com");
    r.setPhone("111-111-1111");

    return r;
  }

  public static void log(String template, Object... args) {
    if (QUIET_TESTS) {
      return;
    }
    System.out.println(String.format(template, args));
  }

  public static void logE(String template, Object... args) {
    if (QUIET_TESTS) {
      return;
    }
    System.err.println(String.format(template, args));
  }

  private static String getProperty(String string) {
    if (properties == null) {
      properties = loadProperties();
    }

    return properties.getProperty(string);
  }

  private static String getResourcePath(String filename) {
    URL fileUrl = TestHelper.class.getClassLoader().getResource(filename);

    if (fileUrl == null) {
      logger.error("getResourcePath - unable to find file {} as a test resource, tests will probably not run correctly", filename);
      return null;
    }

    String absolutePath = new File(fileUrl.getFile()).getAbsolutePath();

    return absolutePath;
  }

  private static Properties loadProperties() {
    InputStream in = TestHelper.class.getResourceAsStream(API_KEY_FILE_NAME);

    if (in == null) {
      logger.error("getApiKey - unable to select file {} as the input resource, tests will probably not run correctly", API_KEY_FILE_NAME);
      System.exit(1);
    }

    properties = new Properties();
    try {
      properties.load(in);
    } catch (Exception e) {
      logger.error("getApiKey - exception loading {}, tests will probably not run correctly: {}, {}", API_KEY_FILE_NAME, e.getClass(),
          e.getMessage());
      System.exit(1);
    }

    return properties;
  }

  // public static void setLogLevel() {
  // if (LOG_AT_TRACE) {
  // System.out.println("setting log level to TRACE");
  // System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "Trace");
  // }
  // }
}
