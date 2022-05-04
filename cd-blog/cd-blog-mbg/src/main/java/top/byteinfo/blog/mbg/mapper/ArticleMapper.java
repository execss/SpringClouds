package top.byteinfo.blog.mbg.mapper;

import top.byteinfo.blog.mbg.entity.Article;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    Article selectByPrimaryKey(Integer id);
    Article getHomeArticle();
//    List<Article> selectAll();
    List<Article> getHomeArticles();
    int updateByPrimaryKey(Article record);
}