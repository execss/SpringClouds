package top.byteinfo.blog.amqp;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.byteinfo.blog.common.core.util.Jackson;
import top.byteinfo.blog.mbg.entity.Comment;
@Slf4j
@SpringBootTest
class BlogCommonAmqpSpringBootStarterApplicationTests {

    @Test
    void contextLoads() {

        byte[] s = Jackson.writeValueAsBytes(Comment.builder().id(1).commentContent("写得好 ，高质量文章").build());
        Comment comment = Jackson.readValue(new String(s), Comment.class);
        log.info(comment.getId()+comment.getCommentContent());
        log.info(Jackson.writeValueAsString(comment));
        byte[] bytes1 =new byte[]{};

        byte[] bytes = JSON.toJSONBytes(Comment.builder().id(1).commentContent("写得好 ，高质量文章").build());

    }

}
