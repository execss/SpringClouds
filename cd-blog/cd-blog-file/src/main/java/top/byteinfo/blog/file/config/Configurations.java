package top.byteinfo.blog.file.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class Configurations {

    @Bean
    @PostConstruct
    public OssConfig getOssConfig() {

        String[] split;
        try (
                Stream<String> in = Files.lines(Paths.get("C:\\Intel\\AccessKey.csv"))
        ) {
            List<String> stringList = in.collect(Collectors.toList());
            split = stringList.get(1).split(",");
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return OssConfig.builder()
                .endpoint("oss-cn-guangzhou.aliyuncs.com")
                .accessKeyId(split[0])
                .accessKeySecret(split[1])
                .bucketName("oss--files")
                .build();
    }
}
