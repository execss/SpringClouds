package top.byteinfo.blog.x.dao.druid1;

import org.apache.ibatis.annotations.Param;
import top.byteinfo.blog.x.model.dto.ArticlePreDTO;
import top.byteinfo.blog.x.model.vo.ArticleHomeVO;

import java.util.List;

public interface ArticleDao {
    List<ArticleHomeVO> getArticleHomeList(@Param("current") Integer current, @Param("size") Integer size);

    List<ArticlePreDTO> getPreviewArticleList(@Param("tagId") Integer tagId ,@Param("categoryId") Integer categoryId);
}
