package top.byteinfo.blog.x;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.byteinfo.mogu.blog.mbg.mapper.TBlogMapper;

import javax.annotation.Resource;

@SpringBootTest
class CdBlogXApplicationTests {

    @Resource
    TBlogMapper blogMapper;
    @Test
    void contextLoads() {
        blogMapper.selectAll();
    }

}
