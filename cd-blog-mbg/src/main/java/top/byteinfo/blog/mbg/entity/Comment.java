package top.byteinfo.blog.mbg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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