package top.byteinfo.blog.x.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {

    @Bean(value = "api2")
    public Docket api2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .description("cd-blog-x RESTful APIs")
                        .termsOfServiceUrl("byteinfo.top")
                        .version("1.0")
                        .build()
                ).groupName("2.X")
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.byteinfo.blog.x.restapi"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
