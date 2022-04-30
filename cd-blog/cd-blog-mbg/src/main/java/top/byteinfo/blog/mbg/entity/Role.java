package top.byteinfo.blog.mbg.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Role {
    private Integer id;

    private String uuid;

    private Integer userAuthId;
}