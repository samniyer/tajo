/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.tajo.storage.json;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Map;

public class GsonHelper {
  private final GsonBuilder builder;
  private final Gson gson;

  public GsonHelper(Map<Type, GsonSerDerAdapter> adapters) {
    builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
    registerAdapters(builder, adapters);
    gson = builder.create();
  }

  public static void registerAdapters(GsonBuilder builder, Map<Type, GsonSerDerAdapter> adapters) {
    for (Map.Entry<Type, GsonSerDerAdapter> entry : adapters.entrySet()) {
      try {
        builder.registerTypeAdapter(entry.getKey(), entry.getValue());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public Gson getGson() {
    return gson;
  }
}