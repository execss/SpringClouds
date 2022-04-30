package top.byteinfo.blog.mbg.mapper;

import java.util.List;
import top.byteinfo.blog.mbg.entity.RoleAuthority;

public interface RoleAuthorityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleAuthority record);

    RoleAuthority selectByPrimaryKey(Integer id);

    List<RoleAuthority> selectAll();

    int updateByPrimaryKey(RoleAuthority record);
}