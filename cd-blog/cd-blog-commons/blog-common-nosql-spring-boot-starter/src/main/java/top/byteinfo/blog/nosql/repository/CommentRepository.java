package top.byteinfo.blog.nosql.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import top.byteinfo.blog.mbg.entity.Comment;

@Component
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
