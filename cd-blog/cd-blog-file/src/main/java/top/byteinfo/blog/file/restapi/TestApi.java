package top.byteinfo.blog.file.restapi;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.byteinfo.blog.common.core.model.result.Result;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class TestApi {

    private static String endpoint = "oss-cn-guangzhou.aliyuncs.com";
    private static String accessKeyId = "LTAI5tJCKM3fKrh1Na8YBTLF";
    private static String accessKeySecret = "J3ZK4c3jlQEexyXhXwc304cCE0kHKc";
    private static String bucketName = "oss--files";

    private static String OSSKey = "test";

    @GetMapping("/tS")
    Result<?> TestSecurity() {
        return Result.ok("test ok");
    }

    @PostMapping("/upload")
    Result<?> TestUploadFile(@RequestBody MultipartFile file) throws IOException {
        List<String> stringList = Files.lines(Paths.get("C:\\Intel\\AccessKey.csv"))
                .map(s -> s.split(",")[1])
                .collect(Collectors.toList());
        OSS ossClient = new OSSClientBuilder().build(endpoint, stringList.get(0), stringList.get(1));

        InputStream inputStream = file.getInputStream();
        String contentType = file.getContentType();
        String name = file.getName();
        String originalFilename = file.getOriginalFilename();
        boolean found = false;
        try {
            // 创建PutObject请求。
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, OSSKey + originalFilename, inputStream);
            found = ossClient.doesObjectExist(bucketName, OSSKey + originalFilename);
            ossClient.shutdown();
        } catch (Exception e) {
            log.error("OSS", e);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return Result.ok("ok", found);
    }



}
