package top.byteinfo.blog.mbg.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Tag {
    private Integer id;

    private String tagName;

    private Date createTime;

    private Date updateTime;
}