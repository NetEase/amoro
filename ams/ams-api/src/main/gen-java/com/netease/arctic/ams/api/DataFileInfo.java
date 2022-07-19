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

/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.netease.arctic.ams.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)", date = "2022-06-30")
public class DataFileInfo implements org.apache.thrift.TBase<DataFileInfo, DataFileInfo._Fields>, java.io.Serializable, Cloneable, Comparable<DataFileInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("DataFileInfo");

  private static final org.apache.thrift.protocol.TField PATH_FIELD_DESC = new org.apache.thrift.protocol.TField("path", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("type", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField SIZE_FIELD_DESC = new org.apache.thrift.protocol.TField("size", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField MASK_FIELD_DESC = new org.apache.thrift.protocol.TField("mask", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField INDEX_FIELD_DESC = new org.apache.thrift.protocol.TField("index", org.apache.thrift.protocol.TType.I64, (short)5);
  private static final org.apache.thrift.protocol.TField SPEC_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("specId", org.apache.thrift.protocol.TType.I64, (short)6);
  private static final org.apache.thrift.protocol.TField PARTITION_FIELD_DESC = new org.apache.thrift.protocol.TField("partition", org.apache.thrift.protocol.TType.STRING, (short)7);
  private static final org.apache.thrift.protocol.TField COMMIT_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("commitTime", org.apache.thrift.protocol.TType.I64, (short)8);
  private static final org.apache.thrift.protocol.TField RECORD_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("recordCount", org.apache.thrift.protocol.TType.I64, (short)9);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new DataFileInfoStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new DataFileInfoTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable java.lang.String path; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String type; // required
  public long size; // required
  public long mask; // required
  public long index; // required
  public long specId; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String partition; // required
  public long commitTime; // required
  public long recordCount; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PATH((short)1, "path"),
    TYPE((short)2, "type"),
    SIZE((short)3, "size"),
    MASK((short)4, "mask"),
    INDEX((short)5, "index"),
    SPEC_ID((short)6, "specId"),
    PARTITION((short)7, "partition"),
    COMMIT_TIME((short)8, "commitTime"),
    RECORD_COUNT((short)9, "recordCount");

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
        case 1: // PATH
          return PATH;
        case 2: // TYPE
          return TYPE;
        case 3: // SIZE
          return SIZE;
        case 4: // MASK
          return MASK;
        case 5: // INDEX
          return INDEX;
        case 6: // SPEC_ID
          return SPEC_ID;
        case 7: // PARTITION
          return PARTITION;
        case 8: // COMMIT_TIME
          return COMMIT_TIME;
        case 9: // RECORD_COUNT
          return RECORD_COUNT;
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
  private static final int __SIZE_ISSET_ID = 0;
  private static final int __MASK_ISSET_ID = 1;
  private static final int __INDEX_ISSET_ID = 2;
  private static final int __SPECID_ISSET_ID = 3;
  private static final int __COMMITTIME_ISSET_ID = 4;
  private static final int __RECORDCOUNT_ISSET_ID = 5;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.PATH, new org.apache.thrift.meta_data.FieldMetaData("path", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TYPE, new org.apache.thrift.meta_data.FieldMetaData("type", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SIZE, new org.apache.thrift.meta_data.FieldMetaData("size", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.MASK, new org.apache.thrift.meta_data.FieldMetaData("mask", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.INDEX, new org.apache.thrift.meta_data.FieldMetaData("index", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.SPEC_ID, new org.apache.thrift.meta_data.FieldMetaData("specId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.PARTITION, new org.apache.thrift.meta_data.FieldMetaData("partition", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.COMMIT_TIME, new org.apache.thrift.meta_data.FieldMetaData("commitTime", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.RECORD_COUNT, new org.apache.thrift.meta_data.FieldMetaData("recordCount", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(DataFileInfo.class, metaDataMap);
  }

  public DataFileInfo() {
  }

  public DataFileInfo(
    java.lang.String path,
    java.lang.String type,
    long size,
    long mask,
    long index,
    long specId,
    java.lang.String partition,
    long commitTime,
    long recordCount)
  {
    this();
    this.path = path;
    this.type = type;
    this.size = size;
    setSizeIsSet(true);
    this.mask = mask;
    setMaskIsSet(true);
    this.index = index;
    setIndexIsSet(true);
    this.specId = specId;
    setSpecIdIsSet(true);
    this.partition = partition;
    this.commitTime = commitTime;
    setCommitTimeIsSet(true);
    this.recordCount = recordCount;
    setRecordCountIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public DataFileInfo(DataFileInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetPath()) {
      this.path = other.path;
    }
    if (other.isSetType()) {
      this.type = other.type;
    }
    this.size = other.size;
    this.mask = other.mask;
    this.index = other.index;
    this.specId = other.specId;
    if (other.isSetPartition()) {
      this.partition = other.partition;
    }
    this.commitTime = other.commitTime;
    this.recordCount = other.recordCount;
  }

  public DataFileInfo deepCopy() {
    return new DataFileInfo(this);
  }

  @Override
  public void clear() {
    this.path = null;
    this.type = null;
    setSizeIsSet(false);
    this.size = 0;
    setMaskIsSet(false);
    this.mask = 0;
    setIndexIsSet(false);
    this.index = 0;
    setSpecIdIsSet(false);
    this.specId = 0;
    this.partition = null;
    setCommitTimeIsSet(false);
    this.commitTime = 0;
    setRecordCountIsSet(false);
    this.recordCount = 0;
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getPath() {
    return this.path;
  }

  public DataFileInfo setPath(@org.apache.thrift.annotation.Nullable java.lang.String path) {
    this.path = path;
    return this;
  }

  public void unsetPath() {
    this.path = null;
  }

  /** Returns true if field path is set (has been assigned a value) and false otherwise */
  public boolean isSetPath() {
    return this.path != null;
  }

  public void setPathIsSet(boolean value) {
    if (!value) {
      this.path = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getType() {
    return this.type;
  }

  public DataFileInfo setType(@org.apache.thrift.annotation.Nullable java.lang.String type) {
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

  public long getSize() {
    return this.size;
  }

  public DataFileInfo setSize(long size) {
    this.size = size;
    setSizeIsSet(true);
    return this;
  }

  public void unsetSize() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __SIZE_ISSET_ID);
  }

  /** Returns true if field size is set (has been assigned a value) and false otherwise */
  public boolean isSetSize() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __SIZE_ISSET_ID);
  }

  public void setSizeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __SIZE_ISSET_ID, value);
  }

  public long getMask() {
    return this.mask;
  }

  public DataFileInfo setMask(long mask) {
    this.mask = mask;
    setMaskIsSet(true);
    return this;
  }

  public void unsetMask() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __MASK_ISSET_ID);
  }

  /** Returns true if field mask is set (has been assigned a value) and false otherwise */
  public boolean isSetMask() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __MASK_ISSET_ID);
  }

  public void setMaskIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __MASK_ISSET_ID, value);
  }

  public long getIndex() {
    return this.index;
  }

  public DataFileInfo setIndex(long index) {
    this.index = index;
    setIndexIsSet(true);
    return this;
  }

  public void unsetIndex() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __INDEX_ISSET_ID);
  }

  /** Returns true if field index is set (has been assigned a value) and false otherwise */
  public boolean isSetIndex() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __INDEX_ISSET_ID);
  }

  public void setIndexIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __INDEX_ISSET_ID, value);
  }

  public long getSpecId() {
    return this.specId;
  }

  public DataFileInfo setSpecId(long specId) {
    this.specId = specId;
    setSpecIdIsSet(true);
    return this;
  }

  public void unsetSpecId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __SPECID_ISSET_ID);
  }

  /** Returns true if field specId is set (has been assigned a value) and false otherwise */
  public boolean isSetSpecId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __SPECID_ISSET_ID);
  }

  public void setSpecIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __SPECID_ISSET_ID, value);
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getPartition() {
    return this.partition;
  }

  public DataFileInfo setPartition(@org.apache.thrift.annotation.Nullable java.lang.String partition) {
    this.partition = partition;
    return this;
  }

  public void unsetPartition() {
    this.partition = null;
  }

  /** Returns true if field partition is set (has been assigned a value) and false otherwise */
  public boolean isSetPartition() {
    return this.partition != null;
  }

  public void setPartitionIsSet(boolean value) {
    if (!value) {
      this.partition = null;
    }
  }

  public long getCommitTime() {
    return this.commitTime;
  }

  public DataFileInfo setCommitTime(long commitTime) {
    this.commitTime = commitTime;
    setCommitTimeIsSet(true);
    return this;
  }

  public void unsetCommitTime() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __COMMITTIME_ISSET_ID);
  }

  /** Returns true if field commitTime is set (has been assigned a value) and false otherwise */
  public boolean isSetCommitTime() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __COMMITTIME_ISSET_ID);
  }

  public void setCommitTimeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __COMMITTIME_ISSET_ID, value);
  }

  public long getRecordCount() {
    return this.recordCount;
  }

  public DataFileInfo setRecordCount(long recordCount) {
    this.recordCount = recordCount;
    setRecordCountIsSet(true);
    return this;
  }

  public void unsetRecordCount() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __RECORDCOUNT_ISSET_ID);
  }

  /** Returns true if field recordCount is set (has been assigned a value) and false otherwise */
  public boolean isSetRecordCount() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __RECORDCOUNT_ISSET_ID);
  }

  public void setRecordCountIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __RECORDCOUNT_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case PATH:
      if (value == null) {
        unsetPath();
      } else {
        setPath((java.lang.String)value);
      }
      break;

    case TYPE:
      if (value == null) {
        unsetType();
      } else {
        setType((java.lang.String)value);
      }
      break;

    case SIZE:
      if (value == null) {
        unsetSize();
      } else {
        setSize((java.lang.Long)value);
      }
      break;

    case MASK:
      if (value == null) {
        unsetMask();
      } else {
        setMask((java.lang.Long)value);
      }
      break;

    case INDEX:
      if (value == null) {
        unsetIndex();
      } else {
        setIndex((java.lang.Long)value);
      }
      break;

    case SPEC_ID:
      if (value == null) {
        unsetSpecId();
      } else {
        setSpecId((java.lang.Long)value);
      }
      break;

    case PARTITION:
      if (value == null) {
        unsetPartition();
      } else {
        setPartition((java.lang.String)value);
      }
      break;

    case COMMIT_TIME:
      if (value == null) {
        unsetCommitTime();
      } else {
        setCommitTime((java.lang.Long)value);
      }
      break;

    case RECORD_COUNT:
      if (value == null) {
        unsetRecordCount();
      } else {
        setRecordCount((java.lang.Long)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case PATH:
      return getPath();

    case TYPE:
      return getType();

    case SIZE:
      return getSize();

    case MASK:
      return getMask();

    case INDEX:
      return getIndex();

    case SPEC_ID:
      return getSpecId();

    case PARTITION:
      return getPartition();

    case COMMIT_TIME:
      return getCommitTime();

    case RECORD_COUNT:
      return getRecordCount();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case PATH:
      return isSetPath();
    case TYPE:
      return isSetType();
    case SIZE:
      return isSetSize();
    case MASK:
      return isSetMask();
    case INDEX:
      return isSetIndex();
    case SPEC_ID:
      return isSetSpecId();
    case PARTITION:
      return isSetPartition();
    case COMMIT_TIME:
      return isSetCommitTime();
    case RECORD_COUNT:
      return isSetRecordCount();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof DataFileInfo)
      return this.equals((DataFileInfo)that);
    return false;
  }

  public boolean equals(DataFileInfo that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_path = true && this.isSetPath();
    boolean that_present_path = true && that.isSetPath();
    if (this_present_path || that_present_path) {
      if (!(this_present_path && that_present_path))
        return false;
      if (!this.path.equals(that.path))
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

    boolean this_present_size = true;
    boolean that_present_size = true;
    if (this_present_size || that_present_size) {
      if (!(this_present_size && that_present_size))
        return false;
      if (this.size != that.size)
        return false;
    }

    boolean this_present_mask = true;
    boolean that_present_mask = true;
    if (this_present_mask || that_present_mask) {
      if (!(this_present_mask && that_present_mask))
        return false;
      if (this.mask != that.mask)
        return false;
    }

    boolean this_present_index = true;
    boolean that_present_index = true;
    if (this_present_index || that_present_index) {
      if (!(this_present_index && that_present_index))
        return false;
      if (this.index != that.index)
        return false;
    }

    boolean this_present_specId = true;
    boolean that_present_specId = true;
    if (this_present_specId || that_present_specId) {
      if (!(this_present_specId && that_present_specId))
        return false;
      if (this.specId != that.specId)
        return false;
    }

    boolean this_present_partition = true && this.isSetPartition();
    boolean that_present_partition = true && that.isSetPartition();
    if (this_present_partition || that_present_partition) {
      if (!(this_present_partition && that_present_partition))
        return false;
      if (!this.partition.equals(that.partition))
        return false;
    }

    boolean this_present_commitTime = true;
    boolean that_present_commitTime = true;
    if (this_present_commitTime || that_present_commitTime) {
      if (!(this_present_commitTime && that_present_commitTime))
        return false;
      if (this.commitTime != that.commitTime)
        return false;
    }

    boolean this_present_recordCount = true;
    boolean that_present_recordCount = true;
    if (this_present_recordCount || that_present_recordCount) {
      if (!(this_present_recordCount && that_present_recordCount))
        return false;
      if (this.recordCount != that.recordCount)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetPath()) ? 131071 : 524287);
    if (isSetPath())
      hashCode = hashCode * 8191 + path.hashCode();

    hashCode = hashCode * 8191 + ((isSetType()) ? 131071 : 524287);
    if (isSetType())
      hashCode = hashCode * 8191 + type.hashCode();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(size);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(mask);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(index);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(specId);

    hashCode = hashCode * 8191 + ((isSetPartition()) ? 131071 : 524287);
    if (isSetPartition())
      hashCode = hashCode * 8191 + partition.hashCode();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(commitTime);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(recordCount);

    return hashCode;
  }

  @Override
  public int compareTo(DataFileInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetPath()).compareTo(other.isSetPath());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPath()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.path, other.path);
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
    lastComparison = java.lang.Boolean.valueOf(isSetSize()).compareTo(other.isSetSize());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSize()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.size, other.size);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetMask()).compareTo(other.isSetMask());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMask()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.mask, other.mask);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetIndex()).compareTo(other.isSetIndex());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIndex()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.index, other.index);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetSpecId()).compareTo(other.isSetSpecId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSpecId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.specId, other.specId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetPartition()).compareTo(other.isSetPartition());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPartition()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.partition, other.partition);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetCommitTime()).compareTo(other.isSetCommitTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCommitTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.commitTime, other.commitTime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetRecordCount()).compareTo(other.isSetRecordCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRecordCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.recordCount, other.recordCount);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("DataFileInfo(");
    boolean first = true;

    sb.append("path:");
    if (this.path == null) {
      sb.append("null");
    } else {
      sb.append(this.path);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("type:");
    if (this.type == null) {
      sb.append("null");
    } else {
      sb.append(this.type);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("size:");
    sb.append(this.size);
    first = false;
    if (!first) sb.append(", ");
    sb.append("mask:");
    sb.append(this.mask);
    first = false;
    if (!first) sb.append(", ");
    sb.append("index:");
    sb.append(this.index);
    first = false;
    if (!first) sb.append(", ");
    sb.append("specId:");
    sb.append(this.specId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("partition:");
    if (this.partition == null) {
      sb.append("null");
    } else {
      sb.append(this.partition);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("commitTime:");
    sb.append(this.commitTime);
    first = false;
    if (!first) sb.append(", ");
    sb.append("recordCount:");
    sb.append(this.recordCount);
    first = false;
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class DataFileInfoStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public DataFileInfoStandardScheme getScheme() {
      return new DataFileInfoStandardScheme();
    }
  }

  private static class DataFileInfoStandardScheme extends org.apache.thrift.scheme.StandardScheme<DataFileInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, DataFileInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // PATH
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.path = iprot.readString();
              struct.setPathIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.type = iprot.readString();
              struct.setTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // SIZE
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.size = iprot.readI64();
              struct.setSizeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // MASK
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.mask = iprot.readI64();
              struct.setMaskIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // INDEX
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.index = iprot.readI64();
              struct.setIndexIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // SPEC_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.specId = iprot.readI64();
              struct.setSpecIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // PARTITION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.partition = iprot.readString();
              struct.setPartitionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // COMMIT_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.commitTime = iprot.readI64();
              struct.setCommitTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 9: // RECORD_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.recordCount = iprot.readI64();
              struct.setRecordCountIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, DataFileInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.path != null) {
        oprot.writeFieldBegin(PATH_FIELD_DESC);
        oprot.writeString(struct.path);
        oprot.writeFieldEnd();
      }
      if (struct.type != null) {
        oprot.writeFieldBegin(TYPE_FIELD_DESC);
        oprot.writeString(struct.type);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(SIZE_FIELD_DESC);
      oprot.writeI64(struct.size);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(MASK_FIELD_DESC);
      oprot.writeI64(struct.mask);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(INDEX_FIELD_DESC);
      oprot.writeI64(struct.index);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(SPEC_ID_FIELD_DESC);
      oprot.writeI64(struct.specId);
      oprot.writeFieldEnd();
      if (struct.partition != null) {
        oprot.writeFieldBegin(PARTITION_FIELD_DESC);
        oprot.writeString(struct.partition);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(COMMIT_TIME_FIELD_DESC);
      oprot.writeI64(struct.commitTime);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(RECORD_COUNT_FIELD_DESC);
      oprot.writeI64(struct.recordCount);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class DataFileInfoTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public DataFileInfoTupleScheme getScheme() {
      return new DataFileInfoTupleScheme();
    }
  }

  private static class DataFileInfoTupleScheme extends org.apache.thrift.scheme.TupleScheme<DataFileInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, DataFileInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetPath()) {
        optionals.set(0);
      }
      if (struct.isSetType()) {
        optionals.set(1);
      }
      if (struct.isSetSize()) {
        optionals.set(2);
      }
      if (struct.isSetMask()) {
        optionals.set(3);
      }
      if (struct.isSetIndex()) {
        optionals.set(4);
      }
      if (struct.isSetSpecId()) {
        optionals.set(5);
      }
      if (struct.isSetPartition()) {
        optionals.set(6);
      }
      if (struct.isSetCommitTime()) {
        optionals.set(7);
      }
      if (struct.isSetRecordCount()) {
        optionals.set(8);
      }
      oprot.writeBitSet(optionals, 9);
      if (struct.isSetPath()) {
        oprot.writeString(struct.path);
      }
      if (struct.isSetType()) {
        oprot.writeString(struct.type);
      }
      if (struct.isSetSize()) {
        oprot.writeI64(struct.size);
      }
      if (struct.isSetMask()) {
        oprot.writeI64(struct.mask);
      }
      if (struct.isSetIndex()) {
        oprot.writeI64(struct.index);
      }
      if (struct.isSetSpecId()) {
        oprot.writeI64(struct.specId);
      }
      if (struct.isSetPartition()) {
        oprot.writeString(struct.partition);
      }
      if (struct.isSetCommitTime()) {
        oprot.writeI64(struct.commitTime);
      }
      if (struct.isSetRecordCount()) {
        oprot.writeI64(struct.recordCount);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, DataFileInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(9);
      if (incoming.get(0)) {
        struct.path = iprot.readString();
        struct.setPathIsSet(true);
      }
      if (incoming.get(1)) {
        struct.type = iprot.readString();
        struct.setTypeIsSet(true);
      }
      if (incoming.get(2)) {
        struct.size = iprot.readI64();
        struct.setSizeIsSet(true);
      }
      if (incoming.get(3)) {
        struct.mask = iprot.readI64();
        struct.setMaskIsSet(true);
      }
      if (incoming.get(4)) {
        struct.index = iprot.readI64();
        struct.setIndexIsSet(true);
      }
      if (incoming.get(5)) {
        struct.specId = iprot.readI64();
        struct.setSpecIdIsSet(true);
      }
      if (incoming.get(6)) {
        struct.partition = iprot.readString();
        struct.setPartitionIsSet(true);
      }
      if (incoming.get(7)) {
        struct.commitTime = iprot.readI64();
        struct.setCommitTimeIsSet(true);
      }
      if (incoming.get(8)) {
        struct.recordCount = iprot.readI64();
        struct.setRecordCountIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

