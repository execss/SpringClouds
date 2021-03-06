package top.byteinfo.blog.x.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleVO {
    private Integer id;

    private Integer userAuthId;

    private Integer categoryId;

    private String articleCover;

    private String articleTitle;

    private String articleContent;

    private String categoryName;

    private List<TagVO> tagVOList;
}
