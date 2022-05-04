package top.byteinfo.blog.gateway.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * modified from ServerBearerTokenAuthenticationConverter
 * org.springframework.security.oauth2.server.resource.web.server.ServerBearerTokenAuthenticationConverter;
 */
@Slf4j
public class CustomBearerTokenAuthenticationConverter implements ServerAuthenticationConverter {
    private static final Pattern authorizationPattern = Pattern.compile("^Bearer (?<token>[a-zA-Z0-9-._~+/]+=*)$",
            Pattern.CASE_INSENSITIVE);
    private String bearerTokenHeaderName = HttpHeaders.AUTHORIZATION;

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {

        return Mono.fromCallable(() -> {
            return this.token(exchange.getRequest());
        }).map((token) -> {
            if (token.isEmpty()) {
                throw new RuntimeException("token.isEmpty()");
            } else {
                return new CustomBearerTokenAuthenticationToken(token);
            }
        });
    }

    private String token(ServerHttpRequest request) {
        String authorizationHeaderToken = getFromAuthorizationHeader(request.getHeaders());

        try {
            return request.getHeaders().getValuesAsList("Authorization").get(0).replace("Bearer", "").trim();
        } catch (RuntimeException e) {
            log.warn("to better", e);
            return null;
        } catch (Exception e) {
            log.error(" attention error", e);
            return null;
        }

    }

    private String getFromAuthorizationHeader(HttpHeaders httpHeaders) {
        String authorization = httpHeaders.getFirst(this.bearerTokenHeaderName);
        Matcher matcher = authorizationPattern.matcher(authorization);
        return matcher.group("token");

    }
}
