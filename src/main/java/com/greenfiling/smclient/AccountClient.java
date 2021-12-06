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

import com.google.gson.reflect.TypeToken;
import com.greenfiling.smclient.internal.ApiClient;
import com.greenfiling.smclient.model.Account;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;

/**
 * @author jetmore
 * @since 1.0.0
 */
public class AccountClient extends ApiClient<Account, Account, Account> {
  /**
   * API endpoint for account information
   */
  public static final String ENDPOINT = "account";

  public AccountClient(ApiHandle handle) {
    super(handle);
    setEndpoint(ENDPOINT);

    // @formatter:off
    setShowType(new TypeToken<Show<Account>>() {}.getType());
    setIndexType(new TypeToken<Index<Account>>() {}.getType());
    // @formatter:on
  }

  public Show<Account> show() throws Exception {
    return show(null);
  }

  // The standard show() interface is a little non-sensical because there's only one account, but we'll leave it live anyway in case the user just
  // wants to call it with null
  @Override
  @SuppressWarnings("unchecked")
  public Show<Account> show(Integer id) throws Exception {
    return (Show<Account>) toShow(doShowRequest(null));
  }

}
