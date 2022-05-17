package top.byteinfo.cdblogsearch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.byteinfo.blog.mbg.mapper.ArticleMapper;
import top.byteinfo.blog.mbg.mapper.TagMapper;
import top.byteinfo.mogu.blog.mbg.mapper.TBlogMapper;
import top.byteinfo.x.blog.mbg.mapper.TbArticleMapper;

import javax.annotation.Resource;

@SpringBootTest
class CdBlogSearchApplicationTests {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    TbArticleMapper tbArticleMapper;

    @Resource
    TBlogMapper tBlogMapper;
    @Test
    void contextLoads() {
        articleMapper.getArticles();
        tbArticleMapper.selectAll();
        tBlogMapper.selectAll();
    }

}
