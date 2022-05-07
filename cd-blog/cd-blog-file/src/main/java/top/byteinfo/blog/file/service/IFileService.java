package top.byteinfo.blog.file.service;

import org.springframework.web.multipart.MultipartFile;
import top.byteinfo.blog.common.core.model.result.Result;
import top.byteinfo.blog.mbg.entity.OssFileInfo;

public interface IFileService {
    Result<OssFileInfo> upload(MultipartFile file ) throws Exception;

//    PageResult<FileInfo> findList(Map<String, Object> params);
//
//    void delete(String id);
//
//    void out(String id, OutputStream os);
}
