package top.byteinfo.blog.file.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.byteinfo.blog.common.core.model.result.Result;
import top.byteinfo.blog.common.redis.redisTemplate.RedisClient;
import top.byteinfo.blog.file.service.IFileService;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/files")
public class FileApi {

    @Autowired
    private RedisClient redisClient;
    @Autowired
    IFileService fileService;

    @PostMapping("/binary")
    public Result<?> upload(HttpServletRequest httpServletRequest) throws Exception {
        ServletInputStream inputStream = httpServletRequest.getInputStream();

        String header = httpServletRequest.getHeader("Content-Type");
        String fileType = header.replace("application/", "");
        String s = inputStream.toString();
//        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Intel\\name.pdf");
//
//        byte[] b = new byte[1024];
//        int len;
//        while ((len = inputStream.read(b)) != -1) {
//            fileOutputStream.write(b, 0, len);
//        }
        fileService.uploadStream(inputStream,fileType);

        return null;
    }

    /**
     * @param file
     * @return
     */
    @PostMapping("/picture")
    public Result<?> upload(MultipartFile file) throws Exception {

        fileService.uploadMultipartFile(file);


        return null;
    }

    ;
}
