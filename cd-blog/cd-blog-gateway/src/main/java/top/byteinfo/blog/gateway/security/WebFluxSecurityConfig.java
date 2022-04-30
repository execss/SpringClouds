package top.byteinfo.blog.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import top.byteinfo.blog.gateway.handler.CustomAuthenticationManager;
import top.byteinfo.blog.gateway.handler.CustomBearerTokenAuthenticationConverter;

@Configuration
public class WebFluxSecurityConfig {


    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        ReactiveAuthenticationManager customAuthenticationManager = new CustomAuthenticationManager();
        CustomBearerTokenAuthenticationConverter tokenAuthenticationConverter = new CustomBearerTokenAuthenticationConverter();
        AuthenticationWebFilter oauth2Filter = new AuthenticationWebFilter(customAuthenticationManager);
        oauth2Filter.setServerAuthenticationConverter(tokenAuthenticationConverter);

        http.addFilterAt(oauth2Filter, SecurityWebFiltersOrder.AUTHENTICATION);
        ServerHttpSecurity.AuthorizeExchangeSpec authorizeExchange = http.authorizeExchange();
        authorizeExchange
                .pathMatchers("/auth/login").permitAll()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .anyExchange().permitAll()
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .headers().cache().disable();

        return http.build();
    }
}
