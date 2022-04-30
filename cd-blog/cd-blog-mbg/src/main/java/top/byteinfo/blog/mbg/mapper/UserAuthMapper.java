package top.byteinfo.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.blog.mbg.entity.UserAuth;

public interface UserAuthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAuth record);

    UserAuth selectByPrimaryKey(Integer id);

    List<UserAuth> selectAll();

    int updateByPrimaryKey(UserAuth record);
}