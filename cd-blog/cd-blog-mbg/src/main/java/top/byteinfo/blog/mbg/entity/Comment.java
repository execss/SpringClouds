package top.byteinfo.blog.mbg.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Comment {
    private Integer id;

    private Integer blogId;

    private Integer userId;

    private Integer replyUserId;

    private Integer parentId;

    private Date createTime;

    private Date updateTime;

    private Byte isDelete;

    private Byte isReview;

    private String commentContent;
}