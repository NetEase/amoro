package com.netease.arctic.ams.server.mapper.derby;

import com.netease.arctic.ams.server.mapper.PlatformFileInfoMapper;
import com.netease.arctic.ams.server.model.PlatformFileInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Auth: hzwangtao6
 * @Time: 2022/11/16 18:49
 * @Description:
 */
public interface DerbyPlatformFileInfoMapper extends PlatformFileInfoMapper {
  String TABLE_NAME = "platform_file_info";
  /**
   * add a file with content encoded by base64
   */
  @Insert("insert into " + TABLE_NAME + "(file_name,file_content_b64)" +
          "values(#{fileInfo.fileName},#{fileInfo.fileContent})")
  void addFile(@Param("fileInfo") PlatformFileInfo platformFileInfo);

  // get file content encoded by base64 by fileId
  @Select("select id from " + TABLE_NAME + " where file_content_b64=#{content} limit 1")
  String getFileId(@Param("content") String content);
}
