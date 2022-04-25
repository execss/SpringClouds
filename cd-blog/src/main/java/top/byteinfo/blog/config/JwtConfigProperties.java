package top.byteinfo.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfigProperties {
    private String aesKey;
    private String tokenKey;
}
