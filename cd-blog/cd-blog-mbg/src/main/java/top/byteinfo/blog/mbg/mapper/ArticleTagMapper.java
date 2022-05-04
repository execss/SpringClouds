package top.byteinfo.blog.mbg.mapper;

import org.apache.ibatis.annotations.Param;
import top.byteinfo.blog.mbg.entity.Article;
import top.byteinfo.blog.mbg.entity.ArticleTag;

import java.util.List;

public interface ArticleTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleTag record);

    ArticleTag selectByPrimaryKey(Integer id);

    List<ArticleTag> getTagByArticleId(@Param("articleId") Integer articleId);

    List<ArticleTag> getTagByArticleIds(@Param("articleIds") List<Integer> articleIds);


    int updateByPrimaryKey(ArticleTag record);
}