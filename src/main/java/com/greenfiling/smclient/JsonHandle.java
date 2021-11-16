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

import java.lang.reflect.Type;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Class for handling JSON de/serialization for the Serve Manager API
 * 
 * This is a singleton class, with the functionality accessible via {@link #get()}
 * 
 * @author jetmore
 * @since 1.0.0
 */
public class JsonHandle {
  /**
   * Handle the Serve Manager Date format
   * 
   * Serve Manager has Date strings that look like YYYY-MM-DD. The client stores these internally as {@link LocalDate} objects. This class converts
   * between the String and LocalDate representation.
   */
  private static class GsonLocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    /**
     * method for deserializing YYYY-MM-DD string into {@link LocalDate} objects
     */
    @Override
    public synchronized LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
      try {
        return LocalDate.parse(jsonElement.getAsString());
      } catch (DateTimeParseException e) {
        throw new JsonParseException(e);
      }
    }

    /**
     * method for serializing {@link LocalDate} objects into YYYY-MM-DD string
     */
    @Override
    public synchronized JsonElement serialize(LocalDate date, Type type, JsonSerializationContext jsonSerializationContext) {
      try {
        return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // ISO_LOCAL_DATE = YYYY-MM-DD
      } catch (DateTimeException e) {
        throw new JsonParseException(e);
      }
    }
  }

  /**
   * Handle the Serve Manager DateTime format
   * 
   * Serve Manager has DateTime strings that look like "2013-02-27T15:18:23-04:00". The client stores these internally as {@link OffsetDateTime}
   * objects. This class converts between the String and OffsetDateTime representation.
   */
  private static class GsonOffsetDateTimeAdapter implements JsonSerializer<OffsetDateTime>, JsonDeserializer<OffsetDateTime> {
    /**
     * method for deserializing SM DateTime string into {@link OffsetDateTime} objects
     */
    @Override
    public synchronized OffsetDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
      try {
        return OffsetDateTime.parse(jsonElement.getAsString());
      } catch (DateTimeParseException e) {
        throw new JsonParseException(e);
      }
    }

    /**
     * method for serializing {@link OffsetDateTime} objects into SM DateTime string
     */
    @Override
    public synchronized JsonElement serialize(OffsetDateTime date, Type type, JsonSerializationContext jsonSerializationContext) {
      try {
        return new JsonPrimitive(date.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)); // ISO_OFFSET_DATE_TIME = YYYY-MM-DDTHH:MM:SS-ZZ:ZZ
      } catch (DateTimeException e) {
        throw new JsonParseException(e);
      }
    }
  }

  private static JsonHandle handle = new JsonHandle();

  /**
   * Get the JsonHandle singleton object
   * 
   * @return the JsonHandle object
   */
  public static JsonHandle get() {
    return handle;
  }

  private Gson gson = null;
  private Gson gsonWithNulls = null;

  /**
   * @return the instantiated and configured Gson object
   */
  public Gson getGson() {
    if (this.gson == null) {
      this.gson = getGsonBase().create();
    }
    return this.gson;
  }

  /**
   * @return the instantiated and configured Gson object
   */
  public Gson getGsonWithNulls() {
    if (this.gsonWithNulls == null) {
      this.gsonWithNulls = getGsonBase().serializeNulls().create();
    }
    return this.gsonWithNulls;
  }

  /**
   * Serialize an object to JSON
   * 
   * @param object
   *          the object to be serialized
   * @return the object serialized to JSON
   */
  public String toJson(Object object) {
    String json = getGson().toJson(object);
    return json;
  }

  private GsonBuilder getGsonBase() {
    // @formatter:off
    return new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new GsonLocalDateAdapter())
        .registerTypeAdapter(OffsetDateTime.class, new GsonOffsetDateTimeAdapter())
        .setFieldNamingPolicy(com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
    // @formatter:on
  }
}
