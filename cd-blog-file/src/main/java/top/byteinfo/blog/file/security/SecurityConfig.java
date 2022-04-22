package top.byteinfo.blog.file.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.byteinfo.blog.file.security.componets.AuthenticationEntryPointAt;
import top.byteinfo.blog.file.security.componets.JwtAuthenticationTokenFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationEntryPointAt authenticationEntryPoint;
    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
//    @Autowired
//    private AuthenticationFailHandler authenticationFailHandler;
//
//    @Autowired
//    private AccessDeniedHandlerPoint accessDeniedHandler;

//    @Bean
//    public FilterRegistrationBean registrationBean(UsernamePasswordAuthenticationFilter filter) {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
//        registrationBean.setEnabled(false);
//        return registrationBean;
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity auth) throws Exception {

        auth.cors()
                .and()
                        .csrf().disable()
                        .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                // from nblog
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .sessionManagement().disable()
                .authorizeRequests()
                .antMatchers(
                        "/admin/**",
                        "/auth/**",
                        "/token",
                        "/**"
                ).permitAll()
                .antMatchers("/doc.html",
                "/swagger-ui/*"
                ).permitAll().anyRequest().authenticated();

        auth.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 禁用缓存 TODO read apidoc
        auth.headers().cacheControl();
//        super.configure(auth);
    }
}
