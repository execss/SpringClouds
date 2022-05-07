package top.byteinfo.blog.x.feign;

import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import top.byteinfo.blog.common.core.model.result.Result;
import top.byteinfo.blog.mbg.entity.OssFileInfo;
import top.byteinfo.blog.x.feign.fallback.FileServiceFallbackFactory;

@FeignClient(name = "ServiceNameConstants.SEARCH_SERVICE", fallbackFactory = FileServiceFallbackFactory.class, decode404 = true)
public interface FileService {

    @PostMapping("/files/picture")
    Result<OssFileInfo> upload(@Param("file") MultipartFile file);
}
