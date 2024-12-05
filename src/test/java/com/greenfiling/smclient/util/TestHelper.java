/**
 * Copyright 2021-2024 Green Filing, LLC
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

package com.greenfiling.smclient.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greenfiling.smclient.ApiHandle;
import com.greenfiling.smclient.model.Address;
import com.greenfiling.smclient.model.Job;
import com.greenfiling.smclient.model.JobSubmit;
import com.greenfiling.smclient.model.Recipient;
import com.greenfiling.smclient.model.internal.JobBase;

public class TestHelper {
  private static final Logger logger = LoggerFactory.getLogger(TestHelper.class);

  private static final String TESTING_PROPERTIES_FILE = "testing.properties";
  private static final String QUIET_TESTS_PROPERTY_NAME = "quiet-tests";
  private static final String API_KEY_PROPERTY_NAME = "api-key";
  private static final String VALID_FILE_NAME_1 = "small-1.pdf";
  private static final String VALID_FILE_NAME_2 = "small-2.pdf";
  private static final String DEFAULT_API_KEY = "REPLACE_WITH_WORKING_API_KEY";
  private static final String SOURCE_RESOURCE_PATH = "src/test/resources";

  public static final String REMOTE_FILE = "https://github.com/greenfiling/servemanager-client/raw/main/src/test/resources/small-1.pdf";
  public static final String DEFAULT_STATUS = "TestSuite";
  public static final String PAGINATION_STATUS = "ForPaginationTest";
  public static final Integer VALID_CLIENT_COMPANY_ID = 783056;
  public static final Integer VALID_CLIENT_CONTACT_ID = 984328;
  public static final Integer VALID_PROCESS_SERVER_COMPANY_ID = 917465;
  public static final Integer VALID_PROCESS_SERVER_CONTACT_ID = null;
  public static final Integer VALID_EMPLOYEE_PROCESS_SERVER_ID = 289268;
  public static final Integer VALID_COURT_CASE_ID = 3109987;
  public static final Integer TEST_RUN_ID = getRandom(); // will be unique for every test run, allowing results created in the same test run to be
                                                         // grouped

  private static Properties properties = null;
  public static String VALID_API_KEY;
  private static Boolean QUIET_TESTS = false; // Run with this at false until we get through the actual configuration
  // public static Boolean LOG_AT_TRACE;
  public static String VALID_FILE_PATH_1;
  public static String VALID_FILE_PATH_2;

  public static String fmt(String template, Object... args) {
    return String.format(template, args);
  }

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

  public static Integer getRandom() {
    return ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE - 1);
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
    r.setType(Recipient.RECIPIENT_TYPE_ORGANIZATION);
    r.setAgentForService("Name of Agent");

    return r;
  }

  public static Job getTestJob() {
    return getTestJob(null);
  }

  // Generate a standard Job with recipient, status, and due date populated
  public static Job getTestJob(String desc) {
    Job job = new Job();
    job.setRecipient(getRecipient());
    decorateTestJob(job, desc);
    return job;
  }

  public static JobSubmit getTestJobSubmit() {
    return getTestJobSubmit(null);
  }

  // Generate a standard JobSubit with recipient, status, and due date populated
  public static JobSubmit getTestJobSubmit(String desc) {
    JobSubmit job = new JobSubmit();
    job.setRecipientAttributes(getRecipient());
    decorateTestJob(job, desc);
    return job;
  }

  public static String getUniqueString() {
    return "FOO-BAR-" + getRandom() + "-RAB-OOF";
  }

  public static void loadTestResources() {
    String resourcePath = getResourcePath(TESTING_PROPERTIES_FILE);
    if (resourcePath == null || resourcePath.isEmpty()) {
      logger.error("Unable to locate resource file {}.", TESTING_PROPERTIES_FILE);
      logger.error("This file contains configuration information that these tests cannot run without.");
      logger.error("Did you copy {}/{}.example to {}/{} and edit it as needed?", SOURCE_RESOURCE_PATH, TESTING_PROPERTIES_FILE, SOURCE_RESOURCE_PATH,
          TESTING_PROPERTIES_FILE);
      fail(fmt("unable to locate required resource property file %s", TESTING_PROPERTIES_FILE));
    }

    // if quiet-tests doesn't exist, proceed as if it was set to false
    String quietTests = getProperty(QUIET_TESTS_PROPERTY_NAME);
    QUIET_TESTS = quietTests == null ? false : quietTests.equalsIgnoreCase("true");

    VALID_API_KEY = getProperty(API_KEY_PROPERTY_NAME);
    assertNotNull(fmt("unable to find required test property %s", API_KEY_PROPERTY_NAME), VALID_API_KEY);
    assertThat(fmt("unable to find required test property %s", API_KEY_PROPERTY_NAME), VALID_API_KEY, not(equalTo("")));
    assertThat(fmt("test property %s must be changed from the default value", API_KEY_PROPERTY_NAME), VALID_API_KEY, not(equalTo(DEFAULT_API_KEY)));

    // This is an experiment I might get back to some day
    // LOG_AT_TRACE = getProperty("log-trace").equalsIgnoreCase("true") ? true : false;

    VALID_FILE_PATH_1 = getResourcePath(VALID_FILE_NAME_1);
    assertNotNull(fmt("unable to find required test resource %s", VALID_FILE_NAME_1), VALID_FILE_PATH_1);
    assertThat(fmt("unable to find required test resource %s", VALID_FILE_NAME_1), VALID_FILE_PATH_1, not(equalTo("")));

    VALID_FILE_PATH_2 = getResourcePath(VALID_FILE_NAME_2);
    assertNotNull(fmt("unable to find required test resource %s", VALID_FILE_NAME_2), VALID_FILE_PATH_2);
    assertThat(fmt("unable to find required test resource %s", VALID_FILE_NAME_2), VALID_FILE_PATH_2, not(equalTo("")));

  }

  public static void log(String msg) {
    if (QUIET_TESTS) {
      return;
    }
    System.out.println(msg);
  }

  public static void log(String template, Object... args) {
    if (QUIET_TESTS) {
      return;
    }
    System.out.println(String.format(template, args));
  }

  public static void logt(String msg) {
    log("%s: %s", LocalTime.now(), msg);
  }

  public static Integer pageToInt(String url) {
    // breaking this out into multiple pieces so that if/when it breaks the stack trace will be useful
    int index = url.lastIndexOf("page=");
    if (index == -1) {
      // The nature of this method assumes we're passing in a pagination link which should have a page= in it. Therefore, if there is no
      // page=, assume we're on the response to the very first call, meaning this is page 1
      return 1;
    }
    String pageStr = url.substring(index + 5);
    Integer page = Integer.valueOf(pageStr);

    return page;
  }

  private static void decorateTestJob(JobBase job, String desc) {
    job.setJobStatus(DEFAULT_STATUS);
    job.setDueDate(LocalDate.now().plusDays(1));
    String details = fmt("created by %s during job run %d", getCallerInfo(), TEST_RUN_ID);
    if (desc != null) {
      details += fmt(", caller reference %s", desc);
    }
    job.setClientJobNumber(details);
    job.setServiceInstructions(details);
  }

  private static String getCallerInfo() {
    String thisClass = null;
    StackTraceElement element = null;

    for (StackTraceElement el : Arrays.asList(new Exception().getStackTrace())) {
      if (thisClass == null) {
        thisClass = el.getClassName();
      }
      if (!thisClass.equals(el.getClassName())) {
        element = el;
        break;
      }
    }

    if (element != null) {
      String clazz = element.getClassName().substring(element.getClassName().lastIndexOf(".") + 1);
      return fmt("%s.%s()", clazz, element.getMethodName());
    }

    logger.error("getCallerInfo - unable to find last caller before class {}", thisClass);
    return "[unknown caller]";
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
      logger.error("getResourcePath - unable to find file {} as a test resource", filename);
      return null;
    }

    String absolutePath = new File(fileUrl.getFile()).getAbsolutePath();

    return absolutePath;
  }

  private static Properties loadProperties() {
    try (InputStream in = TestHelper.class.getClassLoader().getResourceAsStream(TESTING_PROPERTIES_FILE)) {
      assertNotNull(fmt("unable to locate and open testing resource %s", TESTING_PROPERTIES_FILE), in);

      properties = new Properties();
      assertNotNull("unable to instantiate properties object", properties);
      try {
        properties.load(in);
      } catch (Exception e) {
        logger.error("loadProperties - exception loading {}: {}, {}", TESTING_PROPERTIES_FILE, e.getClass(), e.getMessage());
        fail("unable to load properties from file");
      }

    } catch (IOException e) {
      logger.error("loadProperties - exception closing {}: {}, {}", TESTING_PROPERTIES_FILE, e.getClass(), e.getMessage());
      fail("unable to autoclose properties file");
    }
    return properties;
  }

  // public static void setLogLevel() {
  // if (LOG_AT_TRACE) {
  // log("setting log level to TRACE");
  // System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "Trace");
  // }
  // }
}
