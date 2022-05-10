package top.byteinfo.blog.file.config;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
//@Configuration
//@ConfigurationProperties("oss.ali")
public class OssConfig {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

}
