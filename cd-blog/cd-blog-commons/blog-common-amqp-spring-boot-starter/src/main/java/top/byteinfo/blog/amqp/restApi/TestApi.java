package top.byteinfo.blog.amqp.restApi;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.byteinfo.blog.common.core.util.Jackson;
import top.byteinfo.blog.mbg.entity.Comment;

@RestController
public class TestApi {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping
    void mqSend() {
        Comment comment = Comment.builder().id(200).commentContent("多谢博主").build();
        rabbitTemplate.send("EMAIL_EXCHANGE", "*", new Message(
                        Jackson.toBytes(comment),
                        new MessageProperties()
                )
        );
    }
}
