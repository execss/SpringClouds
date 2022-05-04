package top.byteinfo.blog.mbg.mapper;

import org.apache.ibatis.annotations.Param;
import top.byteinfo.blog.mbg.entity.Tag;

import java.util.List;

public interface TagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tag record);

    Tag selectByPrimaryKey(Integer id);

//    List<Tag> selectAll();

    List<Tag> getTagByTagId(Integer tagId);
    List<Tag> getTagByTagIds(@Param("tagIds") List<Integer> tagIds);
    int updateByPrimaryKey(Tag record);
}