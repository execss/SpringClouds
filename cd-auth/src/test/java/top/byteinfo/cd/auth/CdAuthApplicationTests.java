package top.byteinfo.cd.auth;

import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CdAuthApplicationTests {
    private TokenGranter tokenGranter;

    @Test
    void contextLoads() {
    }


    class TokenGranter {

    }

    @NoArgsConstructor
    public class CompositeTokenGranter {
        List<TokenGranter> tokenGranters;
        public OAuth2AccessToken grant() {
            for (TokenGranter granter : tokenGranters) {
                OAuth2AccessToken grant = new OAuth2AccessToken();
                if (grant != null) {
                    return grant;
                }
            }
            return null;
        }
    }

    @Bean
    @ConditionalOnMissingBean
    public TokenGranter Test() {

        if (tokenGranter == null) {

            new TokenGranter() {
                private CompositeTokenGranter delegate;

                public OAuth2AccessToken grant() {
                    if (delegate == null) {
                        delegate = new CompositeTokenGranter();
                    }
                    return delegate.grant();
                }
            };
        }

        return tokenGranter;


    }


    Integer getInteger(){

        List<Integer> list =new ArrayList<>();
        for (Integer integer : list) {
            return integer;
        }
        return null;
    }


}
