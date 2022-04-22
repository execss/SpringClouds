package top.byteinfo.blog.file.security.componets;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import top.byteinfo.blog.common.core.model.bo.UserAuthBOUserDetails;
import top.byteinfo.blog.common.core.util.JwtTokenUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    @Qualifier("handlerExceptionResolver")
    HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


        String requestURI1 = request.getRequestURI();

        if (request.getRequestURI().startsWith("/auth") || request.getRequestURI().endsWith("/") || request.getRequestURI().equals("/favicon.ico")) {
            filterChain.doFilter(request, response);
            return;
        }


        String token = request.getHeader("Authorization").split(" ")[1];


        JWTVerifier jwtVerifier = JwtTokenUtils.verifierBuilder().secret("auth/role").claim("claimK","claimV").build();
        DecodedJWT verify=null;
        try {
             verify = jwtVerifier.verify(token);

        }catch (Exception e){
            logger.error("token check failed");
            filterChain.doFilter(request, response);
            return;
        }
        List<String> audience = verify.getAudience();
        Map<String, Claim> claims = verify.getClaims();
        Claim username = claims.get("username");
        String s = username.asString();

        // choose one
        String requestURI = request.getRequestURI();

        UserAuthBOUserDetails userAuthBOUserDetails = UserAuthBOUserDetails.builder()
                .username(verify.getClaims().get("username").asString())
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
