package top.byteinfo.blog.x;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CdBlogXApplication {
    public static void main(String[] args) {
        SpringApplication.run(CdBlogXApplication.class, args);

    }

}
