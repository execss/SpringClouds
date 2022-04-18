package top.byteinfo.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.byteinfo.blog.JacksonUtils;
import top.byteinfo.blog.common.redis.redisTemplate.RedisClient;

@RestController
public class HomeApi {
    @Autowired
    RedisClient redisClient;
//    @Autowired
//    RedisRepository redisRepository;

    @GetMapping("")
    String defaultPath() {
//        redisRepository.set("redisRepository_defaultPath", "test_value_1");
        redisClient.set("redisClient_testPath_1", "test_value_2");
        return "test ok!";
    }


//    @GetMapping("/test")
//    String testPath() {
//        Object redisRepository_defaultPath = redisRepository.get("redisRepository_defaultPath");
//        String redisRepository_defaultPath_String = JacksonUtils.convertValue(redisRepository_defaultPath, String.class);
//        return redisRepository_defaultPath_String + "------test ok!";
//    }

    @GetMapping("/test1")
    String testPath_1() {

        Object redisClient_testPath_1 = redisClient.get("redisClient_testPath_1");
        String redisClient_testPath_1_String = JacksonUtils.convertValue(redisClient_testPath_1, String.class);
        return redisClient_testPath_1_String + "-----test ok!";
    }
}
