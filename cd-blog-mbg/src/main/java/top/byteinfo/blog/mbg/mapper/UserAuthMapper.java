package top.byteinfo.blog.mbg.mapper;

import top.byteinfo.blog.mbg.entity.UserAuth;

import java.util.List;

public interface UserAuthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAuth record);

    UserAuth selectByPrimaryKey(Integer id);

    List<UserAuth> selectAll();

    int updateByPrimaryKey(UserAuth record);

    /**
     *
     * @param username
     * @return
     */
    UserAuth getUserAuthByName(String username);
}