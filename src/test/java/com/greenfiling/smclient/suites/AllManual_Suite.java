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

package com.greenfiling.smclient.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.greenfiling.smclient.manual.JobClient_FlexUpload_Manual;
import com.greenfiling.smclient.manual.ApiHandle_Manual;

@RunWith(Suite.class)

// @formatter:off
@Suite.SuiteClasses({
  ApiHandle_Manual.class,
  JobClient_FlexUpload_Manual.class
})
// @formatter:on

public class AllManual_Suite {

}
