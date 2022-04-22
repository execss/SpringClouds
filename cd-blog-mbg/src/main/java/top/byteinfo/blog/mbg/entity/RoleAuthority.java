package top.byteinfo.blog.mbg.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RoleAuthority {
    private Integer id;

    private Integer roleId;

    private Integer authorityId;

    private String authorityType;

    private Date createTime;

    private String createBy;
}