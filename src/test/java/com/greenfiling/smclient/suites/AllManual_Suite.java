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

import com.greenfiling.smclient.AccountClient_Manual;
import com.greenfiling.smclient.AttemptClient_Manual;
import com.greenfiling.smclient.CourtCaseClient_Manual;
import com.greenfiling.smclient.CourtClient_Manual;
import com.greenfiling.smclient.EmployeeClient_Manual;
import com.greenfiling.smclient.JobClient_FlexUpload_Manual;
import com.greenfiling.smclient.JobClient_Manual;
import com.greenfiling.smclient.JobClient_Unit_Manual;
import com.greenfiling.smclient.JsonDate_Manual;
import com.greenfiling.smclient.NoteClient_Manual;

@RunWith(Suite.class)

//@formatter:off
@Suite.SuiteClasses({
  AccountClient_Manual.class, 
  AttemptClient_Manual.class, 
  CourtCaseClient_Manual.class, 
  CourtClient_Manual.class,
  EmployeeClient_Manual.class, 
  JobClient_FlexUpload_Manual.class, 
  JobClient_Manual.class, JobClient_Unit_Manual.class, 
  JsonDate_Manual.class,
  NoteClient_Manual.class
})
//@formatter:on

public class AllManual_Suite {

}
