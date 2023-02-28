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

package com.netease.arctic.ams.server.repair;

import com.netease.arctic.ams.server.repair.command.CallCommand;
import com.netease.arctic.ams.server.repair.command.CommandParser;
import com.netease.arctic.ams.server.repair.command.IllegalCommandException;
import com.netease.arctic.catalog.ArcticCatalog;
import com.netease.arctic.catalog.CatalogLoader;

public class CallCommandHandler implements CommandHandler {

  private String amsAddress;

  private CommandParser commandParser;

  private Context context;

  public CallCommandHandler(RepairConfig repairConfig) {
    this.amsAddress = repairConfig.getThriftUrl();

    this.context = new Context();
    if (repairConfig.getCatalogName() != null) {
      context.setCatalog(repairConfig.getCatalogName());
    }
    //todo init commandParser
  }

  @Override
  public void dispatch(String line, TerminalOutput terminalOutput) throws IllegalCommandException {
    CallCommand callCommand = commandParser.parse(line);
    String result = callCommand.call(context);
    terminalOutput.output(result);
  }

  @Override
  public void close() {

  }

  @Override
  public String welcome() {
    //todo
    return null;
  }

  @Override
  public String[] keyWord() {
    //todo
    return commandParser.keywords();
  }
}
