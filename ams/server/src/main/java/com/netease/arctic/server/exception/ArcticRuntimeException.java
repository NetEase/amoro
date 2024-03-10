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

package com.netease.arctic.server.exception;

import com.netease.arctic.ErrorCodes;
import com.netease.arctic.ams.api.ArcticException;
import com.netease.arctic.ams.api.InvalidObjectException;
import com.netease.arctic.ams.api.MetaException;
import com.netease.arctic.ams.api.NoSuchObjectException;
import com.netease.arctic.ams.api.OperationConflictException;
import com.netease.arctic.ams.api.TableIdentifier;
import com.netease.arctic.server.table.ServerTableIdentifier;
import org.apache.thrift.TException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class ArcticRuntimeException extends RuntimeException {

  private static final Map<Class<? extends ArcticRuntimeException>, Integer> CODE_MAP =
      new HashMap<>();

  static {
    CODE_MAP.put(PersistenceException.class, ErrorCodes.PERSISTENCE_ERROR_CODE);
    CODE_MAP.put(ObjectNotExistsException.class, ErrorCodes.OBJECT_NOT_EXISTS_ERROR_CODE);
    CODE_MAP.put(AlreadyExistsException.class, ErrorCodes.ALREADY_EXISTS_ERROR_CODE);
    CODE_MAP.put(IllegalMetadataException.class, ErrorCodes.ILLEGAL_METADATA_ERROR_CODE);
    CODE_MAP.put(ForbiddenException.class, ErrorCodes.FORBIDDEN_ERROR_CODE);

    CODE_MAP.put(TaskNotFoundException.class, ErrorCodes.TASK_NOT_FOUND_ERROR_CODE);
    CODE_MAP.put(DuplicateRuntimeException.class, ErrorCodes.DUPLICATED_TASK_ERROR_CODE);
    CODE_MAP.put(OptimizingClosedException.class, ErrorCodes.OPTIMIZING_CLOSED_ERROR_CODE);
    CODE_MAP.put(IllegalTaskStateException.class, ErrorCodes.ILLEGAL_TASK_STATE_ERROR_CODE);
    CODE_MAP.put(PluginAuthException.class, ErrorCodes.PLUGIN_AUTH_ERROR_CODE);
    CODE_MAP.put(PluginRetryAuthException.class, ErrorCodes.PLUGIN_RETRY_AUTH_ERROR_CODE);

    CODE_MAP.put(BlockerConflictException.class, ErrorCodes.BLOCKER_CONFLICT_ERROR_CODE);
  }

  private static final int UNDEFINED = -1;

  private final int errorCode;
  private final String errorName;

  protected ArcticRuntimeException() {
    this.errorCode = Optional.ofNullable(CODE_MAP.get(getClass())).orElse(UNDEFINED);
    this.errorName = getClass().getSimpleName();
  }

  protected ArcticRuntimeException(String message) {
    super(message);
    this.errorCode = Optional.ofNullable(CODE_MAP.get(getClass())).orElse(UNDEFINED);
    this.errorName = getClass().getSimpleName();
  }

  protected ArcticRuntimeException(Throwable throwable) {
    super(throwable);
    this.errorCode = Optional.ofNullable(CODE_MAP.get(getClass())).orElse(UNDEFINED);
    this.errorName = getClass().getSimpleName();
  }

  protected ArcticRuntimeException(String message, Throwable throwable) {
    super(message, throwable);
    this.errorCode = Optional.ofNullable(CODE_MAP.get(getClass())).orElse(UNDEFINED);
    this.errorName = getClass().getSimpleName();
  }

  public int getErrorCode() {
    return errorCode;
  }

  public String getErrorName() {
    return errorName;
  }

  private ArcticException transform() {
    return new ArcticException(errorCode, errorName, getMessage());
  }

  protected static String getObjectName(TableIdentifier tableIdentifier) {
    return "Table "
        + tableIdentifier.getCatalog()
        + '.'
        + tableIdentifier.getDatabase()
        + '.'
        + tableIdentifier.getTableName();
  }

  protected static String getObjectName(ServerTableIdentifier tableIdentifier) {
    return "Table "
        + tableIdentifier.getCatalog()
        + '.'
        + tableIdentifier.getDatabase()
        + '.'
        + tableIdentifier.getTableName();
  }

  public static ArcticException normalize(Throwable throwable) {
    return wrap(throwable).transform();
  }

  public static TException normalizeCompatibly(Throwable throwable) {
    if (throwable.getClass().equals(ObjectNotExistsException.class)) {
      return new NoSuchObjectException(throwable.getMessage());
    } else if (throwable.getClass().equals(AlreadyExistsException.class)) {
      return new com.netease.arctic.ams.api.AlreadyExistsException(throwable.getMessage());
    } else if (throwable.getClass().equals(IllegalMetadataException.class)
        || throwable.getClass().equals(PersistenceException.class)) {
      return new MetaException(throwable.getMessage());
    } else if (throwable.getClass().equals(IllegalArgumentException.class)) {
      return new InvalidObjectException(throwable.getMessage());
    } else if (throwable.getClass().equals(BlockerConflictException.class)) {
      return new OperationConflictException(throwable.getMessage());
    }
    return new TException(throwable.getMessage());
  }

  public static ArcticRuntimeException wrap(
      Throwable throwable, Function<Throwable, ArcticRuntimeException> exceptionTransform) {
    if (throwable instanceof ArcticRuntimeException) {
      return (ArcticRuntimeException) throwable;
    } else {
      return exceptionTransform.apply(throwable);
    }
  }

  private static ArcticRuntimeException wrap(Throwable throwable) {
    return wrap(throwable, UndefinedException::new);
  }
}
