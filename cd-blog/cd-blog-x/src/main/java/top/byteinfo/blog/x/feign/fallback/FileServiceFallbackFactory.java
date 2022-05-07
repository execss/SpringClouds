package top.byteinfo.blog.x.feign.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import top.byteinfo.blog.common.core.model.result.Result;
import top.byteinfo.blog.mbg.entity.OssFileInfo;
import top.byteinfo.blog.x.feign.FileService;
@Slf4j
public class FileServiceFallbackFactory implements FallbackFactory<FileService> {
    @Override
    public FileService create(Throwable cause) {
        return  (pa1)->{
            log.error("error",pa1);
            return Result.<OssFileInfo>builder().data(new OssFileInfo()).build();
        };
    }
}
