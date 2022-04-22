package top.byteinfo.blog.mbg.mapper;

import top.byteinfo.blog.mbg.entity.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    Role getRoleByAuthId(Integer authId);
}