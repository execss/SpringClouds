package top.byteinfo.blog.restApi;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.byteinfo.blog.mbg.entity.Role;
import top.byteinfo.blog.mbg.entity.RoleAuthority;
import top.byteinfo.blog.mbg.entity.UserAuth;
import top.byteinfo.blog.mbg.mapper.RoleAuthorityMapper;
import top.byteinfo.blog.mbg.mapper.RoleMapper;
import top.byteinfo.blog.mbg.mapper.UserAuthMapper;
import top.byteinfo.blog.model.bo.UserAuthBOUserDetails;
import top.byteinfo.blog.model.result.Result;
import top.byteinfo.blog.util.JwtTokenUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LoginApi {
    @Resource
    private UserAuthMapper userAuthMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleAuthorityMapper roleAuthorityMapper;


    @PostMapping("/auth/login")
    public Result<?> login(@RequestParam String username, @RequestParam String password) {

        UserAuth userAuth = userAuthMapper.getUserAuthByName(username);
        // TODO  entity Valid
        if (userAuth == null) {
            return Result.fail("error");
        }
        Role role = roleMapper.getRoleByAuthId(userAuth.getId());

        List<RoleAuthority> roleAuthorityList = roleAuthorityMapper.getRoleAuthorityByRoleId(role.getId());

        HashMap payloadClaimsMap = new HashMap<>();
        payloadClaimsMap.put("username",username);

        String token = JwtTokenUtils.tokenBuilder()
                .issuer("LoginApi:login")
                .subject("Authentication")
                .ExpiresAt(new Date(2022, 04, 21, 17, 35))
                .audience(roleAuthorityList.stream()
                        .map(RoleAuthority::getAuthorityType).toArray(String[]::new))
                .JWTId("1")
                .secret("auth/role")
                .claim("claimK","claimV")
                .payloadClaims(payloadClaimsMap)
                .build();
        UserAuthBOUserDetails userAuthBOUserDetails = UserAuthBOUserDetails.builder()
                .username(username)
                .enabled(true)
                .authorities(roleAuthorityList.stream()
                        .map(RoleAuthority::getAuthorityType)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()))
                .build();
        SecurityContextHolder
                .getContext()
                .setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                userAuthBOUserDetails, null, userAuthBOUserDetails.getAuthorities()
                        )
                );

        HashMap map =new HashMap<>();
        map.put("token",token);


        return Result.ok("login successful",map );
    }


}
