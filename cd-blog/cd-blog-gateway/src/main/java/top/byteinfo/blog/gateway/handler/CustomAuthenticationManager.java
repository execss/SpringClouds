package top.byteinfo.blog.gateway.handler;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zlt
 * @date 2019/10/6
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
public class CustomAuthenticationManager implements ReactiveAuthenticationManager {


    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(authentication)
                .filter(a -> a instanceof CustomBearerTokenAuthenticationToken)
                .cast(CustomBearerTokenAuthenticationToken.class)
                .map(CustomBearerTokenAuthenticationToken::getToken)
                .flatMap((accessTokenValue -> {

//                    OAuth2AccessToken accessToken = tokenStore.readAccessToken(accessTokenValue);
//                    if (accessToken == null) {
//                        return Mono.error(new InvalidTokenException("Invalid access token: " + accessTokenValue));
//                    } else if (accessToken.isExpired()) {
//                        tokenStore.removeAccessToken(accessToken);
//                        return Mono.error(new InvalidTokenException("Access token expired: " + accessTokenValue));
//                    }
//
//                    OAuth2Authentication result = tokenStore.readAuthentication(accessToken);
//                    if (result == null) {
//                        return Mono.error(new InvalidTokenException("Invalid access token: " + accessTokenValue));
//                    }
                    List<String> audience =new ArrayList<>();
                    audience.add("admin");
                    audience.add("user");
                    UserDetails userDetails = UserAuthBOUserDetails.builder()
                            .username("username")
                            .enabled(true)
                            .authorities(audience.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()))
                            .build();
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    return Mono.just(authenticationToken);
                }))
                .cast(Authentication.class);
    }
}
