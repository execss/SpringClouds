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
    Result<?> getHomeArticles() {
        List<ArticleVO> homeArticles = articleService.getHomeArticles();
        return Result.ok("ok", homeArticles);
    }

    @ApiOperation("查询推荐文章")
    @GetMapping("/articles")
    Result<?> getRecommendArticles() {
        return Result.ok("ok", articleService.getRecommendArticles());
    }

//    @GetMapping("/articles")
//    public Result<?> XlistArticles() {
//        return Result.ok("ok",articleService.XlistArticles());
//    }

}
