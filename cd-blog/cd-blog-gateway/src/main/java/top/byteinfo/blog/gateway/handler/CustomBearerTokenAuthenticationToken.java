package top.byteinfo.blog.gateway.handler;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.util.Assert;

import java.util.Collections;

/**
 * modified from BearerTokenAuthenticationToken
 * org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
 */
public class CustomBearerTokenAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 550L;
    private final String token;

    public CustomBearerTokenAuthenticationToken(String token) {
        super(Collections.emptyList());
        Assert.hasText(token, "token cannot be empty");
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public Object getCredentials() {
        return this.getToken();
    }

    public Object getPrincipal() {
        return this.getToken();
    }
}
