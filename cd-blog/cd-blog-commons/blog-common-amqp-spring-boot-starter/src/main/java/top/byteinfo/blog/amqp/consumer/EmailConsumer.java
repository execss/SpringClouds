package top.byteinfo.blog.amqp.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import top.byteinfo.blog.common.core.util.Jackson;
import top.byteinfo.blog.mbg.entity.Comment;

@Slf4j
@Component
@RabbitListener(queues = "EMAIL_QUEUE")
public class EmailConsumer {

    @RabbitHandler
    public void process(byte[] data) {
        Comment comment = Jackson.readValue(new String(data), Comment.class);
        String s = Jackson.toString(comment);
        System.out.println("email consumer"+s);
        log.info("email consumer"+s);
    }
}
