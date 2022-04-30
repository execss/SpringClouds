package top.byteinfo.blog.x.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import top.byteinfo.blog.x.service.BlogInfoService;

@RestController
public class BlogInfoApi {
    @Autowired
    private BlogInfoService blogInfoService;

}
