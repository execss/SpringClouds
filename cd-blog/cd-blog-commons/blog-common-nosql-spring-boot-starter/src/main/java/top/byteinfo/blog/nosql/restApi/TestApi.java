package top.byteinfo.blog.nosql.restApi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.byteinfo.blog.common.core.util.Jackson;
import top.byteinfo.blog.mbg.entity.Comment;
import top.byteinfo.blog.nosql.repository.CommentRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@RestController
public class TestApi {
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    void TestMongo() {
        commentRepository.save(Comment.builder().id(20).build());

        Iterable<Comment> commentIterable = commentRepository.findAll();

        Iterator<Comment> iterator = commentIterable.iterator();
        List<Comment> comments = new ArrayList<>();
        while (iterator.hasNext()){
            comments.add(iterator.next());
        }
        Comment comment = commentIterable.iterator().next();
        String S = Jackson.toString(comment);
        log.info(S+"\n"+ Jackson.toString(comments));
    }

}
