//package top.byteinfo.blog;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.Claim;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.time.DateUtils;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
//import top.byteinfo.blog.mbg.entity.Role;
//import top.byteinfo.blog.mbg.entity.RoleAuthority;
//import top.byteinfo.blog.mbg.entity.UserAuth;
//import top.byteinfo.blog.mbg.mapper.RoleAuthorityMapper;
//import top.byteinfo.blog.mbg.mapper.RoleMapper;
//import top.byteinfo.blog.mbg.mapper.UserAuthMapper;
//import top.byteinfo.blog.util.JacksonUtils;
//import top.byteinfo.blog.util.JwtTokenUtils;
//
//import javax.annotation.Resource;
//import javax.crypto.Cipher;
//import javax.crypto.spec.SecretKeySpec;
//import java.util.*;
//
//@Slf4j
//@SpringBootTest
//class CdBlogApplicationTests {
//
//    @Resource
//    UserAuthMapper userAuthMapper;
//
//    @Resource
//    RoleMapper roleMapper;
//    @Resource
//    RoleAuthorityMapper roleAuthorityMapper;
//
//    @Test
//    void contextLoads() {
//        //TODO source code
////        Arrays.stream(new String[]{"1-2-3", "12-12"})/*.map(i->{
////                    String[] split = i.split("-");
////                    return new HashMap(split[0].codePointAt(1),split[1].codePointAt(1));
////                }
////        )*/.collect(Collectors.toMap(p -> p.split("")[0]
////                , p -> p
////        ));
//        UserAuth userAuth = userAuthMapper.getUserAuthByName("admin");
//        log.info(JacksonUtils.writeValueAsString(userAuth));
//        Role role = roleMapper.getRoleByAuthId(userAuth.getId());
//        log.info(JacksonUtils.writeValueAsString(role));
//        List<RoleAuthority> roleAuthorityList = roleAuthorityMapper.getRoleAuthorityByRoleId(role.getId());
//        log.info(JacksonUtils.writeValueAsString(roleAuthorityList));
//    }
//
//    @Test
//    void JwtTokenTest() {
//        Algorithm algorithm = Algorithm.HMAC256("secret");
//        String sign = JWT.create().withIssuer("admin").withSubject("auth").sign(algorithm);
//        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
//
//        DecodedJWT decodedJWT = jwtVerifier.verify(sign);
//        String header = decodedJWT.getHeader();
//        String token = decodedJWT.getToken();
//        String issuer = decodedJWT.getIssuer();
//        String subject = decodedJWT.getSubject();
//        log.info("\n" + issuer + "\n" + subject);
//
//        log.info("====\n" + header + "\n" + token);
//
//    }
//
//    @Test
//    void JwtTokenUtils() {
//
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//
//
//        HashMap<Object, Object> hashMap = new HashMap<>();
//        hashMap.put("username", "admin");
//        hashMap.put("dy", bCryptPasswordEncoder.encode("123456"));
//        String token = JwtTokenUtils.tokenBuilder()
//                .issuer("LoginApi:login")
//                .subject("Authentication" + bCryptPasswordEncoder.encode("123456"))
//                .IssuedAt(new Date())
//                .ExpiresAt(new Date(2022, 04, 21, 15, 35))
//                .audience("admin", "user", "test")
//                .JWTId("1")
//                .secret("auth/role")
//                .claim("claimK", "claimV")
//                .payloadClaims(hashMap)
//                .build();
//        log.info("\n" + token);
//
//
//        hashMap.put("dy", bCryptPasswordEncoder.encode("123456"));
//        String token1 = JwtTokenUtils.tokenBuilder()
//                .issuer("LoginApi:login")
//                .subject("Authentication" + bCryptPasswordEncoder.encode("123456"))
//                .ExpiresAt(new Date(2022, 04, 21, 15, 35))
//                .audience("admin", "user", "test")
//                .JWTId("1")
//                .secret("auth/role")
//                .claim("claimK", "claimV")
//                .payloadClaims(hashMap)
//                .build();
//        log.info("\n" + token1);
//
//        JWTVerifier jwtVerifier = JwtTokenUtils.verifierBuilder().claim("claimK", "claimV").secret("auth/role").build();
//        DecodedJWT decodedJWT = jwtVerifier.verify(token);
//        Map<String, Claim> claims = decodedJWT.getClaims();
//        String username = claims.get("username").asString();
//        log.info(username + "\n");
//
//
//        List<String> audience = decodedJWT.getAudience();
//
//        log.info(JacksonUtils.writeValueAsString(audience));
//
//    }
//
//    @Test
//    void bypassword() {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String sourceString = "123456";
//        String encode = bCryptPasswordEncoder.encode(sourceString);
//        log.info(encode);
//
//
//    }
//
//    @Test
//    void tokenTest() {
//        long currentTimeMillis = System.currentTimeMillis();
//
//        log.info(currentTimeMillis + "");
//
//        HashMap<Object, Object> hashMap = new HashMap<>();
//        hashMap.put("username", "admin");
//        String token1 = JwtTokenUtils.tokenBuilder()
//                .issuer("LoginApi:login" + UUID.randomUUID().toString().split("-")[0])
//                .IssuedAt(new Date())
//                .subject("Authentication")
//                .ExpiresAt(new Date(2022, 04, 21, 15, 35))
//                .audience("admin", "user", "test")
//                .JWTId(UUID.randomUUID().toString())
//                .secret("auth/role")
//                .claim("claimK", "claimV")
//                .payloadClaims(hashMap)
//                .build();
//        String token11 = JwtTokenUtils.tokenBuilder()
//                .issuer("auth")
//                .ExpiresAt(DateUtils.addDays(new Date(), 1))
//                .JWTId(UUID.randomUUID().toString().split("-")[0])
//                .secret("a/r'bidfghopSP'EHOgj")
//                .claim("K", "V")
//                .payloadClaims(hashMap)
//                .build();
//
//        Algorithm algorithm = Algorithm.HMAC256("radhadfrtysjkrtahjertaheryahghbadfh");
//
//        String sign = JWT.create().withIssuer("auth")
//                .withExpiresAt(DateUtils.addDays(new Date(), 1))
//                .withJWTId(UUID.randomUUID().toString().split("-")[0])
//                .sign(algorithm);
//
//        log.info("\n" + token1 + "\n" + token11 + "\n" + "\n" + UUID.randomUUID().toString().split("-")[0] + "\n" + sign);
//    }
//
//    @Test
//    void dateUtils() {
//        Date date = DateUtils.addDays(new Date(), 1);
//        log.info(date.toString());
//        log.info(new Date().toString());
//
//    }
//
//    @Test
//    void pageTest() {
//
//        /**
//         * 原生Sql
//         * LIMIT num_limit OFFSET num_offset;
//         */
////        select uid from t_blog LIMIT 5 OFFSET 1;  查询5条记录 偏移1条记录
//
//
//        /**
//         * 开始分页
//         *
//         * @param pageNum  页码
//         * @param pageSize 每页显示数量
//         * static <E> Page<E> startPage(int pageNum, int pageSize)
//         */
////        PageHelper.startPage(2,1);
//        //todo num_limit= pageSize  ** num_offset= pageNum*pageSize
//
//    }
//
//    @Test
//    void AES() throws Exception {
//        String encode = "";
//
//        {
//            String sSrc = "rawsrawsrawsraws";
//            String sKey = "secret1234567890";
//
//            byte[] raw = sKey.getBytes("utf-8");
//            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
//            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
//            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
//
//            String s = new String(encrypted);
//            encode = new BASE64Encoder().encode(encrypted);
//            log.info(s);
//
//            log.info(encode);
//        }
//        String sKey = "secret1234567890";
//        String sSrc = encode;
//        byte[] raw = sKey.getBytes("utf-8");
//
//        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
//        byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);//先用base64解密
//        byte[] original = cipher.doFinal(encrypted1);
//        String originalString = new String(original, "utf-8");
//
//        long currentTimeMillis = System.currentTimeMillis();
//
//        log.info(currentTimeMillis + "");
//
//    }
//
//    @Test
//    void JwtTokenWithAES() throws Exception {
//        //TODO auth step 1
//        String JwtSecret = "radhadfrtysjkrtahjertaheryahghbadfh";
//        String aesKey = "secret1234567890";//TODO
//        Algorithm algorithm = Algorithm.HMAC256(JwtSecret);
//        String sign = JWT.create().withIssuer("auth")
//                .withExpiresAt(DateUtils.addDays(new Date(), 1))
//                .withClaim("K", "V")
//                .withAudience("admin", "user")
//                .withJWTId(UUID.randomUUID().toString().split("-")[0])
//                .sign(algorithm);
//        log.info(sign);
//
//        String aesEncodeToken = JwtTokenUtils.AesEncode(sign, aesKey);
//        log.info(aesEncodeToken);
//
//        String aesDecodeToken = JwtTokenUtils.AesDecode(aesEncodeToken, aesKey);
//        log.info(aesDecodeToken);
//        if (aesDecodeToken.equals(sign)) {
//            log.info("successful");
//        }else {
//            log.error("fail");
//        }
//        String s=new String("aesDecodeToken");
//
//        if ("aesDecodeToken" == s) {
//            log.info("ok");
//        }else {
//            log.error("fail");
//        }
////        s.startsWith("")
//
////        String aesSrc = "rawsrawsrawsraws";//TODO
//
////        Cipher.getInstance("AES/ECB/PKCS5Padding").init(Cipher.ENCRYPT_MODE, new SecretKeySpec(aesKey.getBytes("utf-8"), "AES"));
////        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
////        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(aesKey.getBytes("utf-8"), "AES"));
////        byte[] encrypted = cipher.doFinal(aesSrc.getBytes("utf-8"));
////        log.info(new BASE64Encoder().encode(encrypted));
////
////
////        String sKey = "secret1234567890";
////        String sSrc = new BASE64Encoder().encode(encrypted);
////        byte[] raw = sKey.getBytes("utf-8");
////
////        Cipher cipherde = Cipher.getInstance("AES/ECB/PKCS5Padding");
////        cipherde.init(Cipher.DECRYPT_MODE, new SecretKeySpec(raw, "AES"));
////        byte[] original = cipherde.doFinal(new BASE64Decoder().decodeBuffer(sSrc));
////        String originalString = new String(original, "utf-8");
////        log.info(originalString);
////        log.info(new BASE64Decoder().decodeBuffer("eyJhdWQiOlsiYWRtaW4iLCJ1c2VyIl0sImlzcyI6ImF1dGgiLCJLIjoiViIsImV4cCI6MTY1MDk2ODk3NSwianRpIjoiYTBmOGJkZmEifQ").toString());
//
//    }
//
//
//
//
//}
