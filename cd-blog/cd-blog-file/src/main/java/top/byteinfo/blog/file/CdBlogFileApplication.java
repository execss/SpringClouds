package top.byteinfo.blog.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CdBlogFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(CdBlogFileApplication.class, args);
    }

}
