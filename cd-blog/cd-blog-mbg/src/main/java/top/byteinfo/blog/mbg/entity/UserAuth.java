package top.byteinfo.blog.mbg.entity;

import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@Builder
public class UserAuth {
    private Integer id;

    private String uuid;

    private String username;

    private String password;

    private String ipAddress;

    private String ipSource;

    private Date createTime;

    private Date updateTime;

    private Date lastLoginTime;
}