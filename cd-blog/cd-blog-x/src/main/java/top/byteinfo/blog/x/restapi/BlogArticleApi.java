package top.byteinfo.blog.x.restapi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.byteinfo.blog.common.core.model.result.Result;
import top.byteinfo.blog.x.model.vo.ArticleVO;
import top.byteinfo.blog.x.service.BlogArticleService;

import java.util.List;

@Api(tags = "文章模块")
@RestController
@RequestMapping("blog")
public class BlogArticleApi {

    @Autowired
    private BlogArticleService articleService;

    @ApiOperation("查看blog首页文章")
    @GetMapping("/article")
    Result<?> getHomeArticle() {
        List<ArticleVO> homeArticles = articleService.getHomeArticle();
        return Result.ok("ok", homeArticles);
    }

    @ApiOperation("查看首页文章列表")
    @GetMapping("/articles")
    Result<?> getHomeArticles() {
        return Result.ok("ok",articleService.getHomeArticles());
    }


    @GetMapping("/articles/getArticleHomeVoList")
    Result<?> getArticleHomeDTOList(){
        articleService.getArticleHomeDTOList();
        return Result.ok("");
    }
//    @GetMapping("/articles")
//    public Result<?> XlistArticles() {
//        return Result.ok("ok",articleService.XlistArticles());
//    }

}
