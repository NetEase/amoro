/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.netease.arctic.ams.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)", date = "2023-05-08")
public class UpdateColumn implements org.apache.thrift.TBase<UpdateColumn, UpdateColumn._Fields>, java.io.Serializable, Cloneable, Comparable<UpdateColumn> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("UpdateColumn");

  private static final org.apache.thrift.protocol.TField NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("name", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField PARENT_FIELD_DESC = new org.apache.thrift.protocol.TField("parent", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("type", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField DOC_FIELD_DESC = new org.apache.thrift.protocol.TField("doc", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField OPERATE_FIELD_DESC = new org.apache.thrift.protocol.TField("operate", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField IS_OPTIONAL_FIELD_DESC = new org.apache.thrift.protocol.TField("isOptional", org.apache.thrift.protocol.TType.STRING, (short)6);
  private static final org.apache.thrift.protocol.TField NEW_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("newName", org.apache.thrift.protocol.TType.STRING, (short)7);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new UpdateColumnStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new UpdateColumnTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable java.lang.String name; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String parent; // optional
  public @org.apache.thrift.annotation.Nullable java.lang.String type; // optional
  public @org.apache.thrift.annotation.Nullable java.lang.String doc; // optional
  public @org.apache.thrift.annotation.Nullable java.lang.String operate; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String isOptional; // optional
  public @org.apache.thrift.annotation.Nullable java.lang.String newName; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    NAME((short)1, "name"),
    PARENT((short)2, "parent"),
    TYPE((short)3, "type"),
    DOC((short)4, "doc"),
    OPERATE((short)5, "operate"),
    IS_OPTIONAL((short)6, "isOptional"),
    NEW_NAME((short)7, "newName");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // NAME
          return NAME;
        case 2: // PARENT
          return PARENT;
        case 3: // TYPE
          return TYPE;
        case 4: // DOC
          return DOC;
        case 5: // OPERATE
          return OPERATE;
        case 6: // IS_OPTIONAL
          return IS_OPTIONAL;
        case 7: // NEW_NAME
          return NEW_NAME;
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
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final _Fields optionals[] = {_Fields.PARENT,_Fields.TYPE,_Fields.DOC,_Fields.IS_OPTIONAL,_Fields.NEW_NAME};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NAME, new org.apache.thrift.meta_data.FieldMetaData("name", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PARENT, new org.apache.thrift.meta_data.FieldMetaData("parent", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TYPE, new org.apache.thrift.meta_data.FieldMetaData("type", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DOC, new org.apache.thrift.meta_data.FieldMetaData("doc", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.OPERATE, new org.apache.thrift.meta_data.FieldMetaData("operate", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.IS_OPTIONAL, new org.apache.thrift.meta_data.FieldMetaData("isOptional", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.NEW_NAME, new org.apache.thrift.meta_data.FieldMetaData("newName", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(UpdateColumn.class, metaDataMap);
  }

  public UpdateColumn() {
  }

  public UpdateColumn(
    java.lang.String name,
    java.lang.String operate)
  {
    this();
    this.name = name;
    this.operate = operate;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public UpdateColumn(UpdateColumn other) {
    if (other.isSetName()) {
      this.name = other.name;
    }
    if (other.isSetParent()) {
      this.parent = other.parent;
    }
    if (other.isSetType()) {
      this.type = other.type;
    }
    if (other.isSetDoc()) {
      this.doc = other.doc;
    }
    if (other.isSetOperate()) {
      this.operate = other.operate;
    }
    if (other.isSetIsOptional()) {
      this.isOptional = other.isOptional;
    }
    if (other.isSetNewName()) {
      this.newName = other.newName;
    }
  }

  public UpdateColumn deepCopy() {
    return new UpdateColumn(this);
  }

  @Override
  public void clear() {
    this.name = null;
    this.parent = null;
    this.type = null;
    this.doc = null;
    this.operate = null;
    this.isOptional = null;
    this.newName = null;
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getName() {
    return this.name;
  }

  public UpdateColumn setName(@org.apache.thrift.annotation.Nullable java.lang.String name) {
    this.name = name;
    return this;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been assigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getParent() {
    return this.parent;
  }

  public UpdateColumn setParent(@org.apache.thrift.annotation.Nullable java.lang.String parent) {
    this.parent = parent;
    return this;
  }

  public void unsetParent() {
    this.parent = null;
  }

  /** Returns true if field parent is set (has been assigned a value) and false otherwise */
  public boolean isSetParent() {
    return this.parent != null;
  }

  public void setParentIsSet(boolean value) {
    if (!value) {
      this.parent = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getType() {
    return this.type;
  }

  public UpdateColumn setType(@org.apache.thrift.annotation.Nullable java.lang.String type) {
    this.type = type;
    return this;
  }

  public void unsetType() {
    this.type = null;
  }

  /** Returns true if field type is set (has been assigned a value) and false otherwise */
  public boolean isSetType() {
    return this.type != null;
  }

  public void setTypeIsSet(boolean value) {
    if (!value) {
      this.type = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getDoc() {
    return this.doc;
  }

  public UpdateColumn setDoc(@org.apache.thrift.annotation.Nullable java.lang.String doc) {
    this.doc = doc;
    return this;
  }

  public void unsetDoc() {
    this.doc = null;
  }

  /** Returns true if field doc is set (has been assigned a value) and false otherwise */
  public boolean isSetDoc() {
    return this.doc != null;
  }

  public void setDocIsSet(boolean value) {
    if (!value) {
      this.doc = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getOperate() {
    return this.operate;
  }

  public UpdateColumn setOperate(@org.apache.thrift.annotation.Nullable java.lang.String operate) {
    this.operate = operate;
    return this;
  }

  public void unsetOperate() {
    this.operate = null;
  }

  /** Returns true if field operate is set (has been assigned a value) and false otherwise */
  public boolean isSetOperate() {
    return this.operate != null;
  }

  public void setOperateIsSet(boolean value) {
    if (!value) {
      this.operate = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getIsOptional() {
    return this.isOptional;
  }

  public UpdateColumn setIsOptional(@org.apache.thrift.annotation.Nullable java.lang.String isOptional) {
    this.isOptional = isOptional;
    return this;
  }

  public void unsetIsOptional() {
    this.isOptional = null;
  }

  /** Returns true if field isOptional is set (has been assigned a value) and false otherwise */
  public boolean isSetIsOptional() {
    return this.isOptional != null;
  }

  public void setIsOptionalIsSet(boolean value) {
    if (!value) {
      this.isOptional = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getNewName() {
    return this.newName;
  }

  public UpdateColumn setNewName(@org.apache.thrift.annotation.Nullable java.lang.String newName) {
    this.newName = newName;
    return this;
  }

  public void unsetNewName() {
    this.newName = null;
  }

  /** Returns true if field newName is set (has been assigned a value) and false otherwise */
  public boolean isSetNewName() {
    return this.newName != null;
  }

  public void setNewNameIsSet(boolean value) {
    if (!value) {
      this.newName = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((java.lang.String)value);
      }
      break;

    case PARENT:
      if (value == null) {
        unsetParent();
      } else {
        setParent((java.lang.String)value);
      }
      break;

    case TYPE:
      if (value == null) {
        unsetType();
      } else {
        setType((java.lang.String)value);
      }
      break;

    case DOC:
      if (value == null) {
        unsetDoc();
      } else {
        setDoc((java.lang.String)value);
      }
      break;

    case OPERATE:
      if (value == null) {
        unsetOperate();
      } else {
        setOperate((java.lang.String)value);
      }
      break;

    case IS_OPTIONAL:
      if (value == null) {
        unsetIsOptional();
      } else {
        setIsOptional((java.lang.String)value);
      }
      break;

    case NEW_NAME:
      if (value == null) {
        unsetNewName();
      } else {
        setNewName((java.lang.String)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case NAME:
      return getName();

    case PARENT:
      return getParent();

    case TYPE:
      return getType();

    case DOC:
      return getDoc();

    case OPERATE:
      return getOperate();

    case IS_OPTIONAL:
      return getIsOptional();

    case NEW_NAME:
      return getNewName();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case NAME:
      return isSetName();
    case PARENT:
      return isSetParent();
    case TYPE:
      return isSetType();
    case DOC:
      return isSetDoc();
    case OPERATE:
      return isSetOperate();
    case IS_OPTIONAL:
      return isSetIsOptional();
    case NEW_NAME:
      return isSetNewName();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof UpdateColumn)
      return this.equals((UpdateColumn)that);
    return false;
  }

  public boolean equals(UpdateColumn that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    boolean this_present_parent = true && this.isSetParent();
    boolean that_present_parent = true && that.isSetParent();
    if (this_present_parent || that_present_parent) {
      if (!(this_present_parent && that_present_parent))
        return false;
      if (!this.parent.equals(that.parent))
        return false;
    }

    boolean this_present_type = true && this.isSetType();
    boolean that_present_type = true && that.isSetType();
    if (this_present_type || that_present_type) {
      if (!(this_present_type && that_present_type))
        return false;
      if (!this.type.equals(that.type))
        return false;
    }

    boolean this_present_doc = true && this.isSetDoc();
    boolean that_present_doc = true && that.isSetDoc();
    if (this_present_doc || that_present_doc) {
      if (!(this_present_doc && that_present_doc))
        return false;
      if (!this.doc.equals(that.doc))
        return false;
    }

    boolean this_present_operate = true && this.isSetOperate();
    boolean that_present_operate = true && that.isSetOperate();
    if (this_present_operate || that_present_operate) {
      if (!(this_present_operate && that_present_operate))
        return false;
      if (!this.operate.equals(that.operate))
        return false;
    }

    boolean this_present_isOptional = true && this.isSetIsOptional();
    boolean that_present_isOptional = true && that.isSetIsOptional();
    if (this_present_isOptional || that_present_isOptional) {
      if (!(this_present_isOptional && that_present_isOptional))
        return false;
      if (!this.isOptional.equals(that.isOptional))
        return false;
    }

    boolean this_present_newName = true && this.isSetNewName();
    boolean that_present_newName = true && that.isSetNewName();
    if (this_present_newName || that_present_newName) {
      if (!(this_present_newName && that_present_newName))
        return false;
      if (!this.newName.equals(that.newName))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetName()) ? 131071 : 524287);
    if (isSetName())
      hashCode = hashCode * 8191 + name.hashCode();

    hashCode = hashCode * 8191 + ((isSetParent()) ? 131071 : 524287);
    if (isSetParent())
      hashCode = hashCode * 8191 + parent.hashCode();

    hashCode = hashCode * 8191 + ((isSetType()) ? 131071 : 524287);
    if (isSetType())
      hashCode = hashCode * 8191 + type.hashCode();

    hashCode = hashCode * 8191 + ((isSetDoc()) ? 131071 : 524287);
    if (isSetDoc())
      hashCode = hashCode * 8191 + doc.hashCode();

    hashCode = hashCode * 8191 + ((isSetOperate()) ? 131071 : 524287);
    if (isSetOperate())
      hashCode = hashCode * 8191 + operate.hashCode();

    hashCode = hashCode * 8191 + ((isSetIsOptional()) ? 131071 : 524287);
    if (isSetIsOptional())
      hashCode = hashCode * 8191 + isOptional.hashCode();

    hashCode = hashCode * 8191 + ((isSetNewName()) ? 131071 : 524287);
    if (isSetNewName())
      hashCode = hashCode * 8191 + newName.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(UpdateColumn other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetName()).compareTo(other.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.name, other.name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetParent()).compareTo(other.isSetParent());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetParent()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.parent, other.parent);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetType()).compareTo(other.isSetType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.type, other.type);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetDoc()).compareTo(other.isSetDoc());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDoc()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.doc, other.doc);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetOperate()).compareTo(other.isSetOperate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOperate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.operate, other.operate);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetIsOptional()).compareTo(other.isSetIsOptional());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIsOptional()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.isOptional, other.isOptional);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetNewName()).compareTo(other.isSetNewName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNewName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.newName, other.newName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("UpdateColumn(");
    boolean first = true;

    sb.append("name:");
    if (this.name == null) {
      sb.append("null");
    } else {
      sb.append(this.name);
    }
    first = false;
    if (isSetParent()) {
      if (!first) sb.append(", ");
      sb.append("parent:");
      if (this.parent == null) {
        sb.append("null");
      } else {
        sb.append(this.parent);
      }
      first = false;
    }
    if (isSetType()) {
      if (!first) sb.append(", ");
      sb.append("type:");
      if (this.type == null) {
        sb.append("null");
      } else {
        sb.append(this.type);
      }
      first = false;
    }
    if (isSetDoc()) {
      if (!first) sb.append(", ");
      sb.append("doc:");
      if (this.doc == null) {
        sb.append("null");
      } else {
        sb.append(this.doc);
      }
      first = false;
    }
    if (!first) sb.append(", ");
    sb.append("operate:");
    if (this.operate == null) {
      sb.append("null");
    } else {
      sb.append(this.operate);
    }
    first = false;
    if (isSetIsOptional()) {
      if (!first) sb.append(", ");
      sb.append("isOptional:");
      if (this.isOptional == null) {
        sb.append("null");
      } else {
        sb.append(this.isOptional);
      }
      first = false;
    }
    if (isSetNewName()) {
      if (!first) sb.append(", ");
      sb.append("newName:");
      if (this.newName == null) {
        sb.append("null");
      } else {
        sb.append(this.newName);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class UpdateColumnStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public UpdateColumnStandardScheme getScheme() {
      return new UpdateColumnStandardScheme();
    }
  }

  private static class UpdateColumnStandardScheme extends org.apache.thrift.scheme.StandardScheme<UpdateColumn> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, UpdateColumn struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.name = iprot.readString();
              struct.setNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PARENT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.parent = iprot.readString();
              struct.setParentIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.type = iprot.readString();
              struct.setTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // DOC
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.doc = iprot.readString();
              struct.setDocIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // OPERATE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.operate = iprot.readString();
              struct.setOperateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // IS_OPTIONAL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.isOptional = iprot.readString();
              struct.setIsOptionalIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // NEW_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.newName = iprot.readString();
              struct.setNewNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, UpdateColumn struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.name != null) {
        oprot.writeFieldBegin(NAME_FIELD_DESC);
        oprot.writeString(struct.name);
        oprot.writeFieldEnd();
      }
      if (struct.parent != null) {
        if (struct.isSetParent()) {
          oprot.writeFieldBegin(PARENT_FIELD_DESC);
          oprot.writeString(struct.parent);
          oprot.writeFieldEnd();
        }
      }
      if (struct.type != null) {
        if (struct.isSetType()) {
          oprot.writeFieldBegin(TYPE_FIELD_DESC);
          oprot.writeString(struct.type);
          oprot.writeFieldEnd();
        }
      }
      if (struct.doc != null) {
        if (struct.isSetDoc()) {
          oprot.writeFieldBegin(DOC_FIELD_DESC);
          oprot.writeString(struct.doc);
          oprot.writeFieldEnd();
        }
      }
      if (struct.operate != null) {
        oprot.writeFieldBegin(OPERATE_FIELD_DESC);
        oprot.writeString(struct.operate);
        oprot.writeFieldEnd();
      }
      if (struct.isOptional != null) {
        if (struct.isSetIsOptional()) {
          oprot.writeFieldBegin(IS_OPTIONAL_FIELD_DESC);
          oprot.writeString(struct.isOptional);
          oprot.writeFieldEnd();
        }
      }
      if (struct.newName != null) {
        if (struct.isSetNewName()) {
          oprot.writeFieldBegin(NEW_NAME_FIELD_DESC);
          oprot.writeString(struct.newName);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class UpdateColumnTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public UpdateColumnTupleScheme getScheme() {
      return new UpdateColumnTupleScheme();
    }
  }

  private static class UpdateColumnTupleScheme extends org.apache.thrift.scheme.TupleScheme<UpdateColumn> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, UpdateColumn struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetName()) {
        optionals.set(0);
      }
      if (struct.isSetParent()) {
        optionals.set(1);
      }
      if (struct.isSetType()) {
        optionals.set(2);
      }
      if (struct.isSetDoc()) {
        optionals.set(3);
      }
      if (struct.isSetOperate()) {
        optionals.set(4);
      }
      if (struct.isSetIsOptional()) {
        optionals.set(5);
      }
      if (struct.isSetNewName()) {
        optionals.set(6);
      }
      oprot.writeBitSet(optionals, 7);
      if (struct.isSetName()) {
        oprot.writeString(struct.name);
      }
      if (struct.isSetParent()) {
        oprot.writeString(struct.parent);
      }
      if (struct.isSetType()) {
        oprot.writeString(struct.type);
      }
      if (struct.isSetDoc()) {
        oprot.writeString(struct.doc);
      }
      if (struct.isSetOperate()) {
        oprot.writeString(struct.operate);
      }
      if (struct.isSetIsOptional()) {
        oprot.writeString(struct.isOptional);
      }
      if (struct.isSetNewName()) {
        oprot.writeString(struct.newName);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, UpdateColumn struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(7);
      if (incoming.get(0)) {
        struct.name = iprot.readString();
        struct.setNameIsSet(true);
      }
      if (incoming.get(1)) {
        struct.parent = iprot.readString();
        struct.setParentIsSet(true);
      }
      if (incoming.get(2)) {
        struct.type = iprot.readString();
        struct.setTypeIsSet(true);
      }
      if (incoming.get(3)) {
        struct.doc = iprot.readString();
        struct.setDocIsSet(true);
      }
      if (incoming.get(4)) {
        struct.operate = iprot.readString();
        struct.setOperateIsSet(true);
      }
      if (incoming.get(5)) {
        struct.isOptional = iprot.readString();
        struct.setIsOptionalIsSet(true);
      }
      if (incoming.get(6)) {
        struct.newName = iprot.readString();
        struct.setNewNameIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

