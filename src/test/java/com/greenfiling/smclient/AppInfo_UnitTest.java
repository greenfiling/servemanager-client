/**
 * Copyright 2023 Green Filing, LLC
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

import static com.greenfiling.smclient.util.TestHelper.log;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import org.junit.Test;

import com.greenfiling.smclient.internal.AppInfo;

import de.westemeyer.version.model.Artifact;

public class AppInfo_UnitTest {

  @Test
  public void testGetAppInfo() throws Exception {
    Artifact a = AppInfo.get().getAppInfo();
    log("retrieved artifact %s", a.toString());
    assertThat(a, not(equalTo(null)));
    // I should invent a new type of test class that only executes post-build when the properties are actually available
    // The artifact is built by maven. There are some times it won't be available (for instance 'mvn clean tests'). But if this fails during deploy,
    // something is actually wrong
    // the library uses 0 as its uninitialized value, so that's the flag that we didn't find what we were looking for
    assertThat("artifact info was not able to be looked up", (int) a.getTimestamp(), greaterThan(0));
  }
}
