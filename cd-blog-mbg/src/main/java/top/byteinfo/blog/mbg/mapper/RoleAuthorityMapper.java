package top.byteinfo.blog.mbg.mapper;

import top.byteinfo.blog.mbg.entity.RoleAuthority;

import java.util.List;

public interface RoleAuthorityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleAuthority record);

    RoleAuthority selectByPrimaryKey(Integer id);

    List<RoleAuthority> selectAll();

    int updateByPrimaryKey(RoleAuthority record);

    List<RoleAuthority> getRoleAuthorityByRoleId(Integer roleId);
}