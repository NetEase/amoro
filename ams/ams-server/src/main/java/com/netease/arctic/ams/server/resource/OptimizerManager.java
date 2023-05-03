package com.netease.arctic.ams.server.resource;

import com.netease.arctic.ams.api.resource.ResourceManager;

import java.util.List;

public interface OptimizerManager extends ResourceManager {
  List<OptimizerInstance> listOptimizers();

  List<OptimizerInstance> listOptimizers(String groupName);
}
