package top.byteinfo.blog;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import top.byteinfo.blog.mbg.entity.Role;
import top.byteinfo.blog.mbg.entity.RoleAuthority;
import top.byteinfo.blog.mbg.entity.UserAuth;
import top.byteinfo.blog.mbg.mapper.RoleAuthorityMapper;
import top.byteinfo.blog.mbg.mapper.RoleMapper;
import top.byteinfo.blog.mbg.mapper.UserAuthMapper;
import top.byteinfo.blog.util.JacksonUtils;
import top.byteinfo.blog.util.JwtTokenUtils;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@SpringBootTest
class CdBlogApplicationTests {

    @Resource
    UserAuthMapper userAuthMapper;

    @Resource
    RoleMapper roleMapper;
    @Resource
    RoleAuthorityMapper roleAuthorityMapper;

    @Test
    void contextLoads() {
        //TODO source code
//        Arrays.stream(new String[]{"1-2-3", "12-12"})/*.map(i->{
//                    String[] split = i.split("-");
//                    return new HashMap(split[0].codePointAt(1),split[1].codePointAt(1));
//                }
//        )*/.collect(Collectors.toMap(p -> p.split("")[0]
//                , p -> p
//        ));
        UserAuth userAuth = userAuthMapper.getUserAuthByName("admin");
        log.info(JacksonUtils.writeValueAsString(userAuth));
        Role role = roleMapper.getRoleByAuthId(userAuth.getId());
        log.info(JacksonUtils.writeValueAsString(role));
        List<RoleAuthority> roleAuthorityList = roleAuthorityMapper.getRoleAuthorityByRoleId(role.getId());
        log.info(JacksonUtils.writeValueAsString(roleAuthorityList));
    }

    @Test
    void JwtTokenTest() {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        String sign = JWT.create().withIssuer("admin").withSubject("auth").sign(algorithm);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();

        DecodedJWT decodedJWT = jwtVerifier.verify(sign);
        String header = decodedJWT.getHeader();
        String token = decodedJWT.getToken();
        String issuer = decodedJWT.getIssuer();
        String subject = decodedJWT.getSubject();
        log.info("\n" + issuer + "\n" + subject);

        log.info("====\n" + header + "\n" + token);

    }

    @Test
    void JwtTokenUtils() {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("username", "admin");
        hashMap.put("dy", bCryptPasswordEncoder.encode("123456"));
        String token = JwtTokenUtils.tokenBuilder()
                .issuer("LoginApi:login")
                .subject("Authentication" + bCryptPasswordEncoder.encode("123456"))
                .IssuedAt(new Date())
                .ExpiresAt(new Date(2022, 04, 21, 15, 35))
                .audience("admin", "user", "test")
                .JWTId("1")
                .secret("auth/role")
                .claim("claimK", "claimV")
                .payloadClaims(hashMap)
                .build();
        log.info("\n" + token);


        hashMap.put("dy", bCryptPasswordEncoder.encode("123456"));
        String token1 = JwtTokenUtils.tokenBuilder()
                .issuer("LoginApi:login")
                .subject("Authentication" + bCryptPasswordEncoder.encode("123456"))
                .ExpiresAt(new Date(2022, 04, 21, 15, 35))
                .audience("admin", "user", "test")
                .JWTId("1")
                .secret("auth/role")
                .claim("claimK", "claimV")
                .payloadClaims(hashMap)
                .build();
        log.info("\n" + token1);

        JWTVerifier jwtVerifier = JwtTokenUtils.verifierBuilder().claim("claimK", "claimV").secret("auth/role").build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        String username = claims.get("username").asString();
        log.info(username + "\n");


        List<String> audience = decodedJWT.getAudience();

        log.info(JacksonUtils.writeValueAsString(audience));

    }

    @Test
    void bypassword() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String sourceString ="123456";
        String encode = bCryptPasswordEncoder.encode(sourceString);
        log.info(bCryptPasswordEncoder.encode(sourceString));
        log.info(bCryptPasswordEncoder.encode(sourceString));
        log.info(bCryptPasswordEncoder.encode(sourceString));

    }

    @Test
    void tokenTest(){
        long currentTimeMillis = System.currentTimeMillis();

        log.info(currentTimeMillis+"");

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("username", "admin");
        String token1 = JwtTokenUtils.tokenBuilder()
                .issuer("LoginApi:login"+UUID.randomUUID().toString().split("-")[0])
                .IssuedAt(new Date())
                .subject("Authentication" )
                .ExpiresAt(new Date(2022, 04, 21, 15, 35))
                .audience("admin", "user", "test")
                .JWTId(UUID.randomUUID().toString())
                .secret("auth/role")
                .claim("claimK", "claimV")
                .payloadClaims(hashMap)
                .build();
        String token11 = JwtTokenUtils.tokenBuilder()
                .issuer("LoginApi:login")
                .IssuedAt(new Date())
                .JWTId(UUID.randomUUID().toString().split("-")[0])
                .secret("auth/role")
                .claim("claimK", "claimV")
                .payloadClaims(hashMap)
                .build();

        log.info("\n"+token1+"\n"+token11+"\n"+UUID.randomUUID().toString().split("-")[0]);
    }
}
