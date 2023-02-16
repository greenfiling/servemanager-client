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

import static com.greenfiling.smclient.TestHelper.log;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greenfiling.smclient.internal.JsonHandle;
import com.greenfiling.smclient.model.Account;
import com.greenfiling.smclient.model.Links;
import com.greenfiling.smclient.model.exchange.Show;

public class AccountClient_Manual {
  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(AccountClient_Manual.class);

  private static ApiHandle apiHandle = null;
  private static AccountClient client = null;

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();

    apiHandle = TestHelper.getApiHandle();
    client = new AccountClient(apiHandle);
  }

  @Test
  public void testShowAccount_HappyPath() throws Exception {
    Show<Account> response = client.show();
    Links links = response.getData().getLinks();
    log("links.self = %s", links.getSelf());
    log("type = %s", response.getData().getType());
    log("updated_at = %s", response.getData().getUpdatedAt().toString());
    log("re-serialized: %s", JsonHandle.get().getGsonWithNulls().toJson(response));
  }

}
