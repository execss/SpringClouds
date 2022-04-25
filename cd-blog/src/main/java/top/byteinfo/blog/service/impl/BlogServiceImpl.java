package top.byteinfo.blog.service.impl;

import org.springframework.stereotype.Service;
import top.byteinfo.blog.mbg.entity.Role;
import top.byteinfo.blog.mbg.entity.RoleAuthority;
import top.byteinfo.blog.mbg.entity.UserAuth;
import top.byteinfo.blog.mbg.mapper.RoleAuthorityMapper;
import top.byteinfo.blog.mbg.mapper.RoleMapper;
import top.byteinfo.blog.mbg.mapper.UserAuthMapper;
import top.byteinfo.blog.model.result.Result;
import top.byteinfo.blog.service.BlogService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    private UserAuthMapper userAuthMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleAuthorityMapper roleAuthorityMapper;

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
