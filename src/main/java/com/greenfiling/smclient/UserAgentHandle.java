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

package com.greenfiling.smclient;

import java.util.Locale;

import okhttp3.OkHttp;

/**
 * Class to build and store the User-Agent
 * <P>
 * Because the User-Agent string is built with dynamically-generated information and we will likely get the User-Agent multiple times per application
 * life cycle, but the User-Agent isn't expected to change during the application life cycle, this is a singleton class which builds the string once
 * and then caches it. If there is some reason that the User-Agent might change, it can be forced to be rebuilt with {@link #buildUserAgent()}.
 * 
 * @author jetmore
 * @since 1.0.1
 */
public class UserAgentHandle {
  private static UserAgentHandle handle = new UserAgentHandle();

  public static UserAgentHandle get() {
    return handle;
  }

  private String userAgent;

  public String getUserAgent() {
    if (userAgent == null) {
      buildUserAgent();
    }
    return userAgent;
  }

  private void buildUserAgent() {
    // @formatter:off
    userAgent = String.format(Locale.US, "%s/%s (%s) %s/%s",
        AppInfo.get().getName(),
        AppInfo.get().getVersion(),
        AppInfo.get().getUrl(),
        "okhttp",
        OkHttp.VERSION);
    // @formatter:on
  }
}
