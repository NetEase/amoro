/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netease.arctic.server.dashboard.component.reverser;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.paimon.schema.SchemaManager;
import org.apache.paimon.schema.TableSchema;
import org.apache.paimon.table.DataTable;
import org.apache.paimon.types.ArrayType;
import org.apache.paimon.types.DataField;
import org.apache.paimon.types.DataType;
import org.apache.paimon.types.DataTypes;
import org.apache.paimon.types.MapType;
import org.apache.paimon.types.RowType;

/**
 * TableMetaExtract for paomon table.
 */
public class PaimonTableMetaExtract implements TableMetaExtract<DataTable> {

  @Override
  public List<InternalTableMeta> extractTable(DataTable table) throws IOException {
    SchemaManager schemaManager = new SchemaManager(table.fileIO(), table.location());
    List<TableSchema> tableSchemas = schemaManager.listAll();
    return tableSchemas.stream()
        .map(schema -> new InternalTableMeta(
            schema.timeMillis(),
            transform(schema.fields()),
            schema.options()))
        .collect(Collectors.toList());
  }

  private List<InternalSchema> transform(List<DataField> fields) {
    return transform(null, new ArrayList<>(), new RowType(fields));
  }

  private List<InternalSchema> transform(Integer parent, List<String> parentName, DataType type) {
    List<InternalSchema> result = new ArrayList<>();
    if (type instanceof RowType) {
      RowType rowType = (RowType) type;
      for (DataField field : rowType.getFields()) {
        List<String> name = Lists.newArrayList(parentName);
        name.add(field.name());
        DataType fieldType = field.type();
        result.add(
            new InternalSchema(
                field.id(),
                parent,
                formatName(name),
                fieldType.toString(),
                field.description(),
                !field.type().isNullable()
            ));
        result.addAll(transform(field.id(), name, field.type()));
      }
    } else if (type instanceof ArrayType) {
      ArrayType arrayType = (ArrayType) type;
      List<String> name = Lists.newArrayList(parentName);
      name.add("element");
      DataType elementType = arrayType.getElementType();
      result.addAll(transform(parent, name, elementType));
    } else if (type instanceof MapType) {
      MapType mapType = (MapType) type;
      List<String> keyName = Lists.newArrayList(parentName);
      keyName.add("key");
      DataType keyType = mapType.getKeyType();
      result.addAll(transform(parent, keyName, keyType));

      List<String> valueName = Lists.newArrayList(parentName);
      valueName.add("value");
      DataType valueType = mapType.getValueType();
      result.addAll(transform(parent, valueName, valueType));
    }
    return result;
  }

  private String formatName(List<String> names) {
    return names.stream().collect(Collectors.joining("."));
  }

  public static void main(String[] args) {
    RowType row = DataTypes.ROW(
        DataTypes.FIELD(1, "id", DataTypes.INT()),
        DataTypes.FIELD(2, "array", DataTypes.ARRAY(DataTypes.ROW(
            DataTypes.FIELD(7, "id", DataTypes.INT()),
            DataTypes.FIELD(8, "name", DataTypes.STRING())
        )
        )),
        DataTypes.FIELD(3, "map", DataTypes.MAP(DataTypes.STRING(), DataTypes.INT())),
        DataTypes.FIELD(4, "row", DataTypes.ROW(
            DataTypes.FIELD(5, "id", DataTypes.INT()),
            DataTypes.FIELD(6, "name", DataTypes.STRING())
        ))
    );

    List<InternalSchema> result = new PaimonTableMetaExtract().transform(null, new ArrayList<>(), row);
    System.out.println(result);
  }
}