/*
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

package com.netease.arctic.catalog;

import com.netease.arctic.ams.api.properties.TableFormat;
import org.apache.iceberg.exceptions.AlreadyExistsException;
import org.apache.iceberg.relocated.com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BaseCatalogTest extends CatalogTestBase {

  @Parameterized.Parameters(name = "testFormat = {0}")
  public static Object[] parameters() {
    return new Object[] {TableFormat.ICEBERG, TableFormat.MIXED_ICEBERG};
  }

  public BaseCatalogTest(TableFormat testFormat) {
    super(testFormat);
  }

  @Test
  public void testCreateAndDropDatabase() {
    Assert.assertEquals(Lists.newArrayList(), getCatalog().listDatabases());
    getCatalog().createDatabase("create_db");
    Assert.assertEquals(Lists.newArrayList( "create_db"), getCatalog().listDatabases());
    getCatalog().dropDatabase("create_db");
    Assert.assertEquals(Lists.newArrayList(), getCatalog().listDatabases());
  }

  @Test
  public void testCreateDuplicateDatabase() {
    Assert.assertEquals(Lists.newArrayList(), getCatalog().listDatabases());
    getCatalog().createDatabase("create_db");
    Assert.assertEquals(Lists.newArrayList( "create_db"), getCatalog().listDatabases());
    Assert.assertThrows(
        AlreadyExistsException.class,
        () -> getCatalog().createDatabase("create_db"));
    getCatalog().dropDatabase("create_db");
  }
}
