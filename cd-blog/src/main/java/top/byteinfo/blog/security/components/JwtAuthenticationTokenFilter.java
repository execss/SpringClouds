package top.byteinfo.blog.security.components;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import top.byteinfo.blog.config.JwtConfigProperties;
import top.byteinfo.blog.model.bo.UserAuthBOUserDetails;
import top.byteinfo.blog.util.JwtTokenUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    @Qualifier("handlerExceptionResolver")
    HandlerExceptionResolver handlerExceptionResolver;


    @Autowired
    private JwtConfigProperties jwtKeys;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


        String requestURI1 = request.getRequestURI();

        if (request.getRequestURI().startsWith("/auth") || request.getRequestURI().endsWith("/") || request.getRequestURI().equals("/favicon.ico")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (request.getHeader("Authorization") == null) {
            return;
        }

        String token = request.getHeader("Authorization").split(" ")[1];


        JWTVerifier jwtVerifier = JwtTokenUtils.verifierBuilder().secret(jwtKeys.getTokenKey()).claim("K", "V").build();
        DecodedJWT verify = null;
        try {
            verify = jwtVerifier.verify(token);
            logger.info("token check ok");
        } catch (Exception e) {
            logger.error("token check failed");
            filterChain.doFilter(request, response);
            return;
        }
        List<String> audience = verify.getAudience();

        String username = "admin";
        UserAuthBOUserDetails userAuthBOUserDetails = UserAuthBOUserDetails.builder()
                .username(username)
                .enabled(true)
                .authorities(audience.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()))
                .build();
        SecurityContextHolder
                .getContext()
                .setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                userAuthBOUserDetails, null, userAuthBOUserDetails.getAuthorities()
                        )
                );
        filterChain.doFilter(request, response);


    }

//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        filterChain.doFilter(servletRequest,servletResponse);
//
//    }
}
