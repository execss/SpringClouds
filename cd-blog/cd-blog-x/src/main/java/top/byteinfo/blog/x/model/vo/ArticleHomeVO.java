package top.byteinfo.blog.x.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.byteinfo.blog.x.model.x.dto.TagDTO;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleHomeVO {

    /**
     * id
     */
    private Integer id;

    /**
     * 文章缩略图
     */
    private String articleCover;

    /**
     * 标题
     */
    private String articleTitle;

    /**
     * 内容
     */
    private String articleContent;

    /**
     * 发表时间
     */
    private LocalDateTime createTime;

    /**
     * 文章分类id
     */
    private Integer categoryId;

    /**
     * 文章分类名
     */
    private String categoryName;

    /**
     * 文章标签
     */
    private List<TagDTO> tagDTOList;
}
