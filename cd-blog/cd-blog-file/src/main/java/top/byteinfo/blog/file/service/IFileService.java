package top.byteinfo.blog.file.service;

import org.springframework.web.multipart.MultipartFile;
import top.byteinfo.blog.common.core.model.result.Result;
import top.byteinfo.blog.mbg.entity.OssFileInfo;

import java.io.InputStream;

public interface IFileService {
    Result<OssFileInfo> uploadStream(InputStream inputStream,String fileType) throws Exception;
    Result<OssFileInfo> uploadMultipartFile(MultipartFile file) throws Exception;

//    PageResult<FileInfo> findList(Map<String, Object> params);
//
//    void delete(String id);
//
//    void out(String id, OutputStream os);
}
