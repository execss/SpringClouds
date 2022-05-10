package top.byteinfo.blog.x.feign;

import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import top.byteinfo.blog.common.core.model.result.Result;
import top.byteinfo.blog.mbg.entity.OssFileInfo;

@FeignClient(name = "ServiceNameConstants.SEARCH_SERVICE")
public interface SearchService {

    @PostMapping("/search/picture")
    Result<OssFileInfo> upload(@Param("file") String file);
}
