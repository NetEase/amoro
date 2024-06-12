/**
 * Autogenerated by Thrift Compiler (0.20.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.amoro.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.20.0)", date = "2024-06-11")
public class TableIdentifier implements org.apache.amoro.shade.thrift.org.apache.thrift.TBase<TableIdentifier, TableIdentifier._Fields>, java.io.Serializable, Cloneable, Comparable<TableIdentifier> {
  private static final org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TStruct("TableIdentifier");

  private static final org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TField CATALOG_FIELD_DESC = new org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TField("catalog", org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TField DATABASE_FIELD_DESC = new org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TField("database", org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TField TABLE_NAME_FIELD_DESC = new org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TField("tableName", org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final org.apache.amoro.shade.thrift.org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TableIdentifierStandardSchemeFactory();
  private static final org.apache.amoro.shade.thrift.org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TableIdentifierTupleSchemeFactory();

  public @org.apache.amoro.shade.thrift.org.apache.thrift.annotation.Nullable java.lang.String catalog; // required
  public @org.apache.amoro.shade.thrift.org.apache.thrift.annotation.Nullable java.lang.String database; // required
  public @org.apache.amoro.shade.thrift.org.apache.thrift.annotation.Nullable java.lang.String tableName; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.amoro.shade.thrift.org.apache.thrift.TFieldIdEnum {
    CATALOG((short)1, "catalog"),
    DATABASE((short)2, "database"),
    TABLE_NAME((short)3, "tableName");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.amoro.shade.thrift.org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // CATALOG
          return CATALOG;
        case 2: // DATABASE
          return DATABASE;
        case 3: // TABLE_NAME
          return TABLE_NAME;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.amoro.shade.thrift.org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    @Override
    public short getThriftFieldId() {
      return _thriftId;
    }

    @Override
    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.amoro.shade.thrift.org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.amoro.shade.thrift.org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.amoro.shade.thrift.org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CATALOG, new org.apache.amoro.shade.thrift.org.apache.thrift.meta_data.FieldMetaData("catalog", org.apache.amoro.shade.thrift.org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.amoro.shade.thrift.org.apache.thrift.meta_data.FieldValueMetaData(org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DATABASE, new org.apache.amoro.shade.thrift.org.apache.thrift.meta_data.FieldMetaData("database", org.apache.amoro.shade.thrift.org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.amoro.shade.thrift.org.apache.thrift.meta_data.FieldValueMetaData(org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TABLE_NAME, new org.apache.amoro.shade.thrift.org.apache.thrift.meta_data.FieldMetaData("tableName", org.apache.amoro.shade.thrift.org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.amoro.shade.thrift.org.apache.thrift.meta_data.FieldValueMetaData(org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.amoro.shade.thrift.org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TableIdentifier.class, metaDataMap);
  }

  public TableIdentifier() {
  }

  public TableIdentifier(
    java.lang.String catalog,
    java.lang.String database,
    java.lang.String tableName)
  {
    this();
    this.catalog = catalog;
    this.database = database;
    this.tableName = tableName;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TableIdentifier(TableIdentifier other) {
    if (other.isSetCatalog()) {
      this.catalog = other.catalog;
    }
    if (other.isSetDatabase()) {
      this.database = other.database;
    }
    if (other.isSetTableName()) {
      this.tableName = other.tableName;
    }
  }

  @Override
  public TableIdentifier deepCopy() {
    return new TableIdentifier(this);
  }

  @Override
  public void clear() {
    this.catalog = null;
    this.database = null;
    this.tableName = null;
  }

  @org.apache.amoro.shade.thrift.org.apache.thrift.annotation.Nullable
  public java.lang.String getCatalog() {
    return this.catalog;
  }

  public TableIdentifier setCatalog(@org.apache.amoro.shade.thrift.org.apache.thrift.annotation.Nullable java.lang.String catalog) {
    this.catalog = catalog;
    return this;
  }

  public void unsetCatalog() {
    this.catalog = null;
  }

  /** Returns true if field catalog is set (has been assigned a value) and false otherwise */
  public boolean isSetCatalog() {
    return this.catalog != null;
  }

  public void setCatalogIsSet(boolean value) {
    if (!value) {
      this.catalog = null;
    }
  }

  @org.apache.amoro.shade.thrift.org.apache.thrift.annotation.Nullable
  public java.lang.String getDatabase() {
    return this.database;
  }

  public TableIdentifier setDatabase(@org.apache.amoro.shade.thrift.org.apache.thrift.annotation.Nullable java.lang.String database) {
    this.database = database;
    return this;
  }

  public void unsetDatabase() {
    this.database = null;
  }

  /** Returns true if field database is set (has been assigned a value) and false otherwise */
  public boolean isSetDatabase() {
    return this.database != null;
  }

  public void setDatabaseIsSet(boolean value) {
    if (!value) {
      this.database = null;
    }
  }

  @org.apache.amoro.shade.thrift.org.apache.thrift.annotation.Nullable
  public java.lang.String getTableName() {
    return this.tableName;
  }

  public TableIdentifier setTableName(@org.apache.amoro.shade.thrift.org.apache.thrift.annotation.Nullable java.lang.String tableName) {
    this.tableName = tableName;
    return this;
  }

  public void unsetTableName() {
    this.tableName = null;
  }

  /** Returns true if field tableName is set (has been assigned a value) and false otherwise */
  public boolean isSetTableName() {
    return this.tableName != null;
  }

  public void setTableNameIsSet(boolean value) {
    if (!value) {
      this.tableName = null;
    }
  }

  @Override
  public void setFieldValue(_Fields field, @org.apache.amoro.shade.thrift.org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case CATALOG:
      if (value == null) {
        unsetCatalog();
      } else {
        setCatalog((java.lang.String)value);
      }
      break;

    case DATABASE:
      if (value == null) {
        unsetDatabase();
      } else {
        setDatabase((java.lang.String)value);
      }
      break;

    case TABLE_NAME:
      if (value == null) {
        unsetTableName();
      } else {
        setTableName((java.lang.String)value);
      }
      break;

    }
  }

  @org.apache.amoro.shade.thrift.org.apache.thrift.annotation.Nullable
  @Override
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case CATALOG:
      return getCatalog();

    case DATABASE:
      return getDatabase();

    case TABLE_NAME:
      return getTableName();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  @Override
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case CATALOG:
      return isSetCatalog();
    case DATABASE:
      return isSetDatabase();
    case TABLE_NAME:
      return isSetTableName();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof TableIdentifier)
      return this.equals((TableIdentifier)that);
    return false;
  }

  public boolean equals(TableIdentifier that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_catalog = true && this.isSetCatalog();
    boolean that_present_catalog = true && that.isSetCatalog();
    if (this_present_catalog || that_present_catalog) {
      if (!(this_present_catalog && that_present_catalog))
        return false;
      if (!this.catalog.equals(that.catalog))
        return false;
    }

    boolean this_present_database = true && this.isSetDatabase();
    boolean that_present_database = true && that.isSetDatabase();
    if (this_present_database || that_present_database) {
      if (!(this_present_database && that_present_database))
        return false;
      if (!this.database.equals(that.database))
        return false;
    }

    boolean this_present_tableName = true && this.isSetTableName();
    boolean that_present_tableName = true && that.isSetTableName();
    if (this_present_tableName || that_present_tableName) {
      if (!(this_present_tableName && that_present_tableName))
        return false;
      if (!this.tableName.equals(that.tableName))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetCatalog()) ? 131071 : 524287);
    if (isSetCatalog())
      hashCode = hashCode * 8191 + catalog.hashCode();

    hashCode = hashCode * 8191 + ((isSetDatabase()) ? 131071 : 524287);
    if (isSetDatabase())
      hashCode = hashCode * 8191 + database.hashCode();

    hashCode = hashCode * 8191 + ((isSetTableName()) ? 131071 : 524287);
    if (isSetTableName())
      hashCode = hashCode * 8191 + tableName.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(TableIdentifier other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetCatalog(), other.isSetCatalog());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCatalog()) {
      lastComparison = org.apache.amoro.shade.thrift.org.apache.thrift.TBaseHelper.compareTo(this.catalog, other.catalog);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetDatabase(), other.isSetDatabase());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDatabase()) {
      lastComparison = org.apache.amoro.shade.thrift.org.apache.thrift.TBaseHelper.compareTo(this.database, other.database);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetTableName(), other.isSetTableName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTableName()) {
      lastComparison = org.apache.amoro.shade.thrift.org.apache.thrift.TBaseHelper.compareTo(this.tableName, other.tableName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.amoro.shade.thrift.org.apache.thrift.annotation.Nullable
  @Override
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  @Override
  public void read(org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TProtocol iprot) throws org.apache.amoro.shade.thrift.org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  @Override
  public void write(org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TProtocol oprot) throws org.apache.amoro.shade.thrift.org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TableIdentifier(");
    boolean first = true;

    sb.append("catalog:");
    if (this.catalog == null) {
      sb.append("null");
    } else {
      sb.append(this.catalog);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("database:");
    if (this.database == null) {
      sb.append("null");
    } else {
      sb.append(this.database);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("tableName:");
    if (this.tableName == null) {
      sb.append("null");
    } else {
      sb.append(this.tableName);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.amoro.shade.thrift.org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TCompactProtocol(new org.apache.amoro.shade.thrift.org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.amoro.shade.thrift.org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TCompactProtocol(new org.apache.amoro.shade.thrift.org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.amoro.shade.thrift.org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TableIdentifierStandardSchemeFactory implements org.apache.amoro.shade.thrift.org.apache.thrift.scheme.SchemeFactory {
    @Override
    public TableIdentifierStandardScheme getScheme() {
      return new TableIdentifierStandardScheme();
    }
  }

  private static class TableIdentifierStandardScheme extends org.apache.amoro.shade.thrift.org.apache.thrift.scheme.StandardScheme<TableIdentifier> {

    @Override
    public void read(org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TProtocol iprot, TableIdentifier struct) throws org.apache.amoro.shade.thrift.org.apache.thrift.TException {
      org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CATALOG
            if (schemeField.type == org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TType.STRING) {
              struct.catalog = iprot.readString();
              struct.setCatalogIsSet(true);
            } else { 
              org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // DATABASE
            if (schemeField.type == org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TType.STRING) {
              struct.database = iprot.readString();
              struct.setDatabaseIsSet(true);
            } else { 
              org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TABLE_NAME
            if (schemeField.type == org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TType.STRING) {
              struct.tableName = iprot.readString();
              struct.setTableNameIsSet(true);
            } else { 
              org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    @Override
    public void write(org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TProtocol oprot, TableIdentifier struct) throws org.apache.amoro.shade.thrift.org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.catalog != null) {
        oprot.writeFieldBegin(CATALOG_FIELD_DESC);
        oprot.writeString(struct.catalog);
        oprot.writeFieldEnd();
      }
      if (struct.database != null) {
        oprot.writeFieldBegin(DATABASE_FIELD_DESC);
        oprot.writeString(struct.database);
        oprot.writeFieldEnd();
      }
      if (struct.tableName != null) {
        oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
        oprot.writeString(struct.tableName);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TableIdentifierTupleSchemeFactory implements org.apache.amoro.shade.thrift.org.apache.thrift.scheme.SchemeFactory {
    @Override
    public TableIdentifierTupleScheme getScheme() {
      return new TableIdentifierTupleScheme();
    }
  }

  private static class TableIdentifierTupleScheme extends org.apache.amoro.shade.thrift.org.apache.thrift.scheme.TupleScheme<TableIdentifier> {

    @Override
    public void write(org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TProtocol prot, TableIdentifier struct) throws org.apache.amoro.shade.thrift.org.apache.thrift.TException {
      org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetCatalog()) {
        optionals.set(0);
      }
      if (struct.isSetDatabase()) {
        optionals.set(1);
      }
      if (struct.isSetTableName()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetCatalog()) {
        oprot.writeString(struct.catalog);
      }
      if (struct.isSetDatabase()) {
        oprot.writeString(struct.database);
      }
      if (struct.isSetTableName()) {
        oprot.writeString(struct.tableName);
      }
    }

    @Override
    public void read(org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TProtocol prot, TableIdentifier struct) throws org.apache.amoro.shade.thrift.org.apache.thrift.TException {
      org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.catalog = iprot.readString();
        struct.setCatalogIsSet(true);
      }
      if (incoming.get(1)) {
        struct.database = iprot.readString();
        struct.setDatabaseIsSet(true);
      }
      if (incoming.get(2)) {
        struct.tableName = iprot.readString();
        struct.setTableNameIsSet(true);
      }
    }
  }

  private static <S extends org.apache.amoro.shade.thrift.org.apache.thrift.scheme.IScheme> S scheme(org.apache.amoro.shade.thrift.org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.amoro.shade.thrift.org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

