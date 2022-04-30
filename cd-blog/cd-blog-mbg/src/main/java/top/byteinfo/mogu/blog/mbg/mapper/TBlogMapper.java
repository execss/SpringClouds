package top.byteinfo.mogu.blog.mbg.mapper;

import org.apache.ibatis.annotations.Param;
import top.byteinfo.mogu.blog.mbg.entity.TBlog;

import java.util.List;

public interface TBlogMapper {
    int deleteByPrimaryKey(@Param("uid") String uid, @Param("oid") Integer oid);

    int insert(TBlog record);

    TBlog selectByPrimaryKey(@Param("uid") String uid, @Param("oid") Integer oid);

    List<TBlog> selectAll();

    int updateByPrimaryKey(TBlog record);
}