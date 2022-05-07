package top.byteinfo.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.blog.mbg.entity.OssFileInfo;

public interface OssFileInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(OssFileInfo record);

    OssFileInfo selectByPrimaryKey(String id);

    List<OssFileInfo> selectAll();

    int updateByPrimaryKey(OssFileInfo record);
}