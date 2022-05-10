package top.byteinfo.blog.mbg.entity;

import lombok.*;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OssFileInfo {
    private String id;

    private String filename;

    private Boolean isimg;

    private String contenttype;

    private String path;

    private String url;

    private String source;

    private Date createtime;

    private Date updatetime;

    private String tenantId;

    private Long size;
}