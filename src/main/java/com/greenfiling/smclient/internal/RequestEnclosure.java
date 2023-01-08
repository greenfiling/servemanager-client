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

package com.greenfiling.smclient.internal;

import okhttp3.Request.Builder;

/**
 * Used to encapsulate both a {@link Builder} object and a request body
 * 
 * Several places in the client used to just pass around a Builder object, but we it is not possible to get a String representation of the request
 * body back out of a Builder object. This class is now passed around instead, and the request body is kept along side the Builder object, allowing
 * for access to the request body at later points for debugging
 * 
 * @author jetmore
 * @since 1.0.4
 */
public class RequestEnclosure {
  private Builder builder;
  private String requestBody;

  public RequestEnclosure(Builder builder, String requestBody) {
    this.builder = builder;
    this.requestBody = requestBody;
  }

  public Builder getBuilder() {
    return builder;
  }

  public String getRequestBody() {
    return requestBody;
  }
}
