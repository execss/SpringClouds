package top.byteinfo.blog.x.service.impl;

import org.springframework.stereotype.Service;
import top.byteinfo.blog.mbg.entity.Article;
import top.byteinfo.blog.mbg.mapper.ArticleMapper;
import top.byteinfo.blog.x.service.BlogArticleService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogArticleServiceImpl implements BlogArticleService {
    @Resource
    private ArticleMapper articleMapper;

    public List<Article> getHomeArticles() {

        return articleMapper.getHomeArticles();
    }
}
