package top.byteinfo.blog.x.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.byteinfo.blog.x.model.x.dto.ArticlePreviewDTO;

import java.util.List;


/**
 * 文章预览列表
 *
 * @author yezhiqiu
 * @date 2021/08/10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePreviewListVO {

    /**
     * 文章列表
     */
    private List<ArticlePreviewDTO> articlePreviewDTOList;

    /**
     * 条件名
     */
    private String name;

}
