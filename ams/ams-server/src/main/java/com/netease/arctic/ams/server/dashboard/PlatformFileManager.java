package com.netease.arctic.ams.server.dashboard;

import com.netease.arctic.ams.server.ArcticManagementConf;
import com.netease.arctic.ams.server.dashboard.model.PlatformFileInfo;
import com.netease.arctic.ams.server.persistence.PersistentBase;
import com.netease.arctic.ams.server.persistence.mapper.PlatformFileMapper;
import com.netease.arctic.ams.server.utils.Configurations;

import java.util.Base64;

public class PlatformFileManager extends PersistentBase {

  private final Configurations serviceConfig;

  public PlatformFileManager(Configurations serviceConfig) {
    this.serviceConfig = serviceConfig;
  }

  /**
   * add some file
   */
  public Integer addFile(String name, String content) {
    PlatformFileInfo platformFileInfo = new PlatformFileInfo(name, content);
    doAs(PlatformFileMapper.class, e -> e.addFile(platformFileInfo));
    if (serviceConfig.getString(ArcticManagementConf.DB_TYPE).equals("derby")) {
      return getAs(PlatformFileMapper.class, e -> e.getFileId(content));
    }
    return platformFileInfo.getFileId();
  }

  /**
   * get file content
   */
  public String getFileContentB64ById(Integer fileId) {
    return getAs(PlatformFileMapper.class, e -> e.getFileById(fileId));
  }

  /**
   * get file content
   */
  public String getFileContentById(Integer fileId) {
    String fileContent = getAs(PlatformFileMapper.class, e -> e.getFileById(fileId));
    return new String(Base64.getDecoder().decode(fileContent));
  }
}
