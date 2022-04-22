package top.byteinfo.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.blog.mbg.entity.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    List<UserRole> selectAll();

    int updateByPrimaryKey(UserRole record);
}