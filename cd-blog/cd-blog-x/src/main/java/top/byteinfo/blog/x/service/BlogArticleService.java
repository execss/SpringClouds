package top.byteinfo.blog.x.service;

import top.byteinfo.blog.x.model.vo.ArticleVO;

import java.util.List;

public interface BlogArticleService {
    List<ArticleVO> getHomeArticle();

    List<ArticleVO> getHomeArticles();


    List<ArticleVO> getArticleHomeDTOList();
}
