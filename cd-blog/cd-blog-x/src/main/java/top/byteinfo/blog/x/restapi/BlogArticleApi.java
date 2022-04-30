package top.byteinfo.blog.x.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.byteinfo.blog.common.core.model.result.Result;
import top.byteinfo.blog.mbg.entity.Article;
import top.byteinfo.blog.x.service.BlogArticleService;

import java.util.List;

@RestController
public class BlogArticleApi {

    @Autowired
    private BlogArticleService articleService;

    @GetMapping
    Result<?> getHomeArticles() {
        List<Article> homeArticles = articleService.getHomeArticles();
        return Result.ok("ok",homeArticles);
    }

}
