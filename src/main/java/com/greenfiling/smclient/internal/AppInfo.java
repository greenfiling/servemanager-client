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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.westemeyer.version.model.Artifact;
import de.westemeyer.version.service.ArtifactVersionCollector;

/**
 * Application information available at runtime
 * <P>
 * Because much of the information is looked up dynamically and we may ask for it many time, but it is not expected to change, this is a singleton
 * class that caches the information
 * 
 * @author jetmore
 * @since 1.0.1
 */
public class AppInfo {
  private static final Logger logger = LoggerFactory.getLogger(AppInfo.class);
  public final static String GROUP_ID = "com.greenfiling.smclient";
  public final static String ARTIFACT_ID = "servemanager-client";
  private static AppInfo handle = new AppInfo();

  public static AppInfo get() {
    return handle;
  }

  private Artifact appInfo;

  public Artifact getAppInfo() {
    if (appInfo == null) {
      appInfo = ArtifactVersionCollector.findArtifact(GROUP_ID, ARTIFACT_ID);
      if (appInfo == null) {
        logger.error("getAppInfo - unable to find artifact {}.{}, using manufactured information");
        appInfo = new Artifact(GROUP_ID, ARTIFACT_ID, "UNKNOWN", 0, "smclient");
      }
    }

    return appInfo;
  }

  public String getName() {
    return getAppInfo().getName();
  }

  public String getUrl() {
    return getAppInfo().getUrl();
  }

  public String getVersion() {
    return getAppInfo().getVersion();
  }
}
