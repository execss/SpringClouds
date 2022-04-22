package top.byteinfo.blog.common.core.model.bo;

import lombok.Builder;
import lombok.Data;
import top.byteinfo.blog.mbg.entity.UserAuth;
@Data
@Builder
public class UserAuthBO {
    private UserAuth userAuth;
}
