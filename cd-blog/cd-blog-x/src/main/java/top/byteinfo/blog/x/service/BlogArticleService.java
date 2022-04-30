package top.byteinfo.blog.x.service;

import top.byteinfo.blog.mbg.entity.Article;

import java.util.List;

public interface BlogArticleService {
    List<Article> getHomeArticles();
}
