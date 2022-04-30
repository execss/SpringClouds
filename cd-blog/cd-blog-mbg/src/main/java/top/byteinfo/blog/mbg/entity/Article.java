package top.byteinfo.blog.mbg.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Article {
    private Integer id;

    private Integer userAuthId;

    private Integer categoryId;

    private String articleCover;

    private String articleTitle;

    private String articleContent;
}