package top.byteinfo.blog.restApi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.byteinfo.blog.mbg.entity.Role;
import top.byteinfo.blog.mbg.entity.RoleAuthority;
import top.byteinfo.blog.mbg.entity.UserAuth;
import top.byteinfo.blog.mbg.mapper.RoleAuthorityMapper;
import top.byteinfo.blog.mbg.mapper.RoleMapper;
import top.byteinfo.blog.mbg.mapper.UserAuthMapper;
import top.byteinfo.blog.model.result.Result;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BlogApi {
    @Resource
    private UserAuthMapper userAuthMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleAuthorityMapper roleAuthorityMapper;

    @GetMapping("/blog")
    Result<?> getBlogList(){
        UserAuth userAuth = userAuthMapper.getUserAuthByName("admin");
        // TODO  entity Valid
        if (userAuth == null) {
            return Result.fail("error");
        }
        Role role = roleMapper.getRoleByAuthId(userAuth.getId());

        List<RoleAuthority> roleAuthorityList = roleAuthorityMapper.getRoleAuthorityByRoleId(role.getId());
        return Result.ok("ok",roleAuthorityList);
    }

}
