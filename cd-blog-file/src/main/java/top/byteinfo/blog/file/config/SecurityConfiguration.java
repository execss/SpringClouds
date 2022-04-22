package top.byteinfo.blog.file.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.byteinfo.blog.file.security.componets.AuthenticationEntryPointAt;
import top.byteinfo.blog.file.security.componets.JwtAuthenticationTokenFilter;

@Configuration
public class SecurityConfiguration {
//    @Bean
//    AccessDeniedHandlerPoint getAccessDeniedHandlerPoint() {
//        return new AccessDeniedHandlerPoint();
//    }

    @Bean
    AuthenticationEntryPointAt getAuthenticationEntryPointAt() {
        return new AuthenticationEntryPointAt();
    }

//    @Bean
//    AuthenticationFailHandler AuthenticationFailHandler() {
//        return new AuthenticationFailHandler();
//    }


    @Bean
    JwtAuthenticationTokenFilter getJwtAuthenticationTokenFilter(){
        return new  JwtAuthenticationTokenFilter();
    }
}
