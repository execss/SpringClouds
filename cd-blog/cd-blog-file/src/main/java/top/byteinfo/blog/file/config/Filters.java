//package top.byteinfo.blog.file.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import top.byteinfo.blog.common.redis.redisTemplate.RedisClient;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//@Slf4j
//@Component
//@WebFilter("/**")
//public class Filters implements Filter {
//
//    @Autowired
//    private RedisClient redisClient;
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
////        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
////        ObjectMapper mapper = new ObjectMapper();
////        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
////        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
////        jackson2JsonRedisSerializer.setObjectMapper(mapper);
//
//
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        ServletInputStream inputStream = httpServletRequest.getInputStream();
//
////        log.error(Jackson.toString(httpServletRequest));
////        log.error(Jackson.toString(inputStream));
//
//
//
//
////        RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
////        redisTemplate.setConnectionFactory(factory);
//////        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//////        ObjectMapper mapper = new ObjectMapper();
//////        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//////        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//////        jackson2JsonRedisSerializer.setObjectMapper(mapper);
////        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
////        // key采用String的序列化方式
////        redisTemplate.setKeySerializer(stringRedisSerializer);
////        // hash的key也采用String的序列化方式
////        redisTemplate.setHashKeySerializer(stringRedisSerializer);
////        // value序列化方式采用jackson
////        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
////        // hash的value序列化方式采用jackson
////        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
////        redisTemplate.afterPropertiesSet();
//
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//}
