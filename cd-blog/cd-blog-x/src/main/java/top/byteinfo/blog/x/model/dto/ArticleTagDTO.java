package top.byteinfo.blog.x.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleTagDTO {
    Integer articleId;
    List<Integer> tagIdList;
}
