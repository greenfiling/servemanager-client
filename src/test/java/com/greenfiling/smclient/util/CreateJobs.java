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

package com.greenfiling.smclient.util;

import com.greenfiling.smclient.ApiHandle;
import com.greenfiling.smclient.JobClient;
import com.greenfiling.smclient.TestHelper;
import com.greenfiling.smclient.model.Job;
import com.greenfiling.smclient.model.Links;
import com.greenfiling.smclient.model.exchange.Show;

public class CreateJobs {

  public static void main(String[] args) {
    final String VALID_API_KEY = TestHelper.VALID_API_KEY;

    for (int i = 0; i < 200; i++) {
      ApiHandle apiHandle = new ApiHandle.Builder().apiKey(VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE).build();
      JobClient client = new JobClient(apiHandle);

      Job newJob = new Job();

      Show<Job> response = null;
      try {
        response = client.create(newJob);
      } catch (Exception e) {
        System.err.println("Error creating job " + i + ": " + e.getClass() + " " + e.getMessage());
        System.exit(1);
      }
      if (response == null) {
        continue;
      }
      Links links = response.getData().getLinks();
      System.out.println(i + ") job created, links.self = " + links.getSelf());

      try {
        Thread.sleep(2000);
      } catch (Exception e) {
        System.err.println("Error sleeping after job " + i + ": " + e.getClass() + " " + e.getMessage());
      }
    }

    System.exit(0);
  }

}
