//package top.byteinfo.blog.restApi;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import top.byteinfo.blog.mbg.entity.UserAuth;
//import top.byteinfo.blog.mbg.mapper.UserAuthMapper;
//import top.byteinfo.blog.util.JacksonUtils;
//import top.byteinfo.blog.common.redis.redisTemplate.RedisClient;
//
//import javax.annotation.Resource;
//import java.util.Date;
//
//@RestController
//public class HomeApi {
//    private static final Logger log = LoggerFactory.getLogger(HomeApi.class);
//    @Autowired
//    RedisClient redisClient;
//
//    @Resource
//    private UserAuthMapper userAuthMapper;
////    @Autowired
////    RedisRepository redisRepository;
//
//    @GetMapping("")
//    String defaultPath() {
////        redisRepository.set("redisRepository_defaultPath", "test_value_1");
//        redisClient.set("redisClient_testPath_1", "test_value_2");
//        return "test ok!";
//    }
//
//
////    @GetMapping("/test")
////    String testPath() {
////        Object redisRepository_defaultPath = redisRepository.get("redisRepository_defaultPath");
////        String redisRepository_defaultPath_String = JacksonUtils.convertValue(redisRepository_defaultPath, String.class);
////        return redisRepository_defaultPath_String + "------test ok!";
////    }
//
//    @GetMapping("/test1")
//    String testPath_1() {
//
//        Object redisClient_testPath_1 = redisClient.get("redisClient_testPath_1");
//        String redisClient_testPath_1_String = JacksonUtils.convertValue(redisClient_testPath_1, String.class);
//        return redisClient_testPath_1_String + "-----test ok!";
//    }
//
//    @GetMapping("/import")
//    String importSQLOnlone() {
//        UserAuth userAuth = UserAuth.builder()
//                .id(1)
//                .uuid("")
//                .username("admin")
//                .password("")
//                .createTime(new Date())
//                .updateTime(new Date())
//                .lastLoginTime(new Date())
//                .build();
//
//
//
//        int insert = userAuthMapper.insert(userAuth);
//        log.info("result:"+insert);
//        return "result:"+insert;
////        return null;
//    }
//
//    @GetMapping("/update")
//    String updateSQLOnlone() {
//        UserAuth userAuth = UserAuth.builder()
//                .id(1)
//                .uuid("")
//                .username("admin")
//                .password("")
//                .createTime(new Date())
//                .updateTime(new Date())
//                .lastLoginTime(new Date())
//                .build();
//
//        int insert = userAuthMapper.updateByPrimaryKey(userAuth);
//        log.info("result:" + insert);
//        return "result:" + insert;
//    }
//}
