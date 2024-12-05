/**
 * Copyright 2024 Green Filing, LLC
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

package com.greenfiling.smclient.model.exchange;

import java.util.ArrayList;

import com.greenfiling.smclient.model.Data;
import com.greenfiling.smclient.model.Links;
import com.greenfiling.smclient.model.Meta;

public class PayLoad {
  Links links;
  Meta meta;
  ArrayList<Data> data = new ArrayList<>();

  public Meta getMeta() {
    return this.meta;
  }

  public Links getLinks() {
    return this.links;
  }

  public ArrayList<Data> getData() {
    return this.data;
  }

  public void setMeta(Meta meta) {
    this.meta = meta;
  }

  public void setLinks(Links links) {
    this.links = links;
  }

  public void setData(ArrayList<Data> data) {
    this.data = data;
  }
}
