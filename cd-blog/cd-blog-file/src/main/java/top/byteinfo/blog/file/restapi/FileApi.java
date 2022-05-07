package top.byteinfo.blog.file.restapi;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.byteinfo.blog.common.core.model.result.Result;
import top.byteinfo.blog.file.service.IFileService;
import top.byteinfo.blog.mbg.entity.OssFileInfo;

@RestController
@RequestMapping("/files")
public class FileApi {

    @Autowired
    IFileService fileService;

    @PostMapping("/picture")
    public Result<OssFileInfo> upload(@Param("file") MultipartFile file, @Param("pa1") String pa1) throws Exception {

        return fileService.upload(file);
    }

}
