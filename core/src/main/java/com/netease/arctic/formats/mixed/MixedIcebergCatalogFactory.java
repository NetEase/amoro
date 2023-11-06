/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *  *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netease.arctic.formats.mixed;

import com.netease.arctic.FormatCatalog;
import com.netease.arctic.FormatCatalogFactory;
import com.netease.arctic.ams.api.TableFormat;
import com.netease.arctic.catalog.ArcticCatalog;
import com.netease.arctic.catalog.CatalogLoader;
import com.netease.arctic.table.TableMetaStore;
import com.netease.arctic.utils.CatalogUtil;
import org.apache.iceberg.relocated.com.google.common.annotations.VisibleForTesting;

import java.util.Map;

public class MixedIcebergCatalogFactory implements FormatCatalogFactory {
  @Override
  public FormatCatalog create(
      String catalogName,
      String metastoreType,
      Map<String, String> properties,
      TableMetaStore metaStore) {
    ArcticCatalog catalog = createMixedCatalog(catalogName, metastoreType, properties, metaStore);
    return new MixedCatalog(catalog, format());
  }

  @VisibleForTesting
  public static ArcticCatalog createMixedCatalog(
      String catalogName,
      String metastoreType,
      Map<String, String> properties,
      TableMetaStore metaStore) {
    String catalogImpl = CatalogLoader.catalogImpl(metastoreType, properties);
    ArcticCatalog catalog = CatalogLoader.buildCatalog(catalogImpl);
    properties = CatalogUtil.addIcebergCatalogProperties(metastoreType, properties);
    catalog.initialize(catalogName, properties, metaStore);
    return catalog;
  }

  @Override
  public TableFormat format() {
    return TableFormat.MIXED_ICEBERG;
  }
}
