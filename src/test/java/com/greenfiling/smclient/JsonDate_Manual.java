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
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonDate_Manual {

  public class TestObj {
    private String name;
    private Date date;
    private String multiNameField;

    public Date getDate() {
      return this.date;
    }

    public String getMultiNameField() {
      return multiNameField;
    }

    public String getName() {
      return this.name;
    }

    public void setDate(Date date) {
      this.date = date;
    }

    public void setMultiNameField(String multiNameField) {
      this.multiNameField = multiNameField;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

  // @Test
  // I think this was verifying the change in name policy and converting dates, but I'm not sure this is something to make a unit test
  // or leave as manual
  public void testDate_From() throws Exception {
    String objJson = "{\"name\":\"this is a name\",\"date\":\"2021-10-26T15:32:34-00:00\",\"multi_name_field\":\"field value\"}";
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
        .setFieldNamingPolicy(com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    TestObj obj = gson.fromJson(objJson, TestObj.class);
    assertThat(obj, not(equalTo(null)));
    log("name = %s, date = %s, multiNameField = %s", obj.getName(), obj.getDate(), obj.getMultiNameField());

    String newJson = gson.toJson(obj);
    assertThat(newJson, equalTo(objJson));
    log("re-encoded json: %s", newJson);
  }
}
