package top.byteinfo.blog.x.service;

import top.byteinfo.blog.x.model.vo.ArticleVO;
import top.byteinfo.blog.x.model.x.dto.ArticleHomeDTO;

import java.util.List;

public interface BlogArticleService {
    List<ArticleVO> getHomeArticles();

    List<ArticleVO> getRecommendArticles();

    List<ArticleHomeDTO> XlistArticles();
}
