package top.byteinfo.blog.x.model.vo;

import lombok.Data;

import java.util.List;
@Data
public class ArticleVO {
    private Integer id;

    private Integer userAuthId;

    private Integer categoryId;

    private String articleCover;

    private String articleTitle;

    private String articleContent;

    private String categoryName;

    private List<TagVO> tagList;
}
