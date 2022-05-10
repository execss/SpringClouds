package top.byteinfo.blog.file.service.impl;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import top.byteinfo.blog.common.core.model.result.Result;
import top.byteinfo.blog.file.model.ObjectInfo;
import top.byteinfo.blog.file.service.IFileService;
import top.byteinfo.blog.mbg.entity.OssFileInfo;

import java.io.InputStream;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public abstract class AbsFileService implements IFileService {

    @Override
    public Result<OssFileInfo> uploadStream(InputStream inputStream, String fileType) throws Exception {

        uploadFile(inputStream, fileType);

        return Result.<OssFileInfo>builder()
                .data(
                        OssFileInfo.builder()
                                .id(UUID.randomUUID().toString())
                                .build()
                ).build();
    }

    @Override
    public Result<OssFileInfo> uploadMultipartFile(MultipartFile file) throws Exception {

        ObjectInfo objectInfo = uploadFile(file);

        String contentType = file.getContentType();
        String name = file.getName();
        Resource resource = file.getResource();
        long size = file.getSize();


//        // name type size
//        OssFileInfo ossFileInfo = OssFileInfo.builder()
//                .id(UUID.randomUUID().toString().replace("-", ""))
//                .filename(file.getOriginalFilename())
//                .contenttype(file.getContentType())
//                .isimg(Objects.requireNonNull(file.getContentType()).startsWith("image/"))
//                .size(file.getSize())
//                .path(objectInfo.getObjectPath())//
//                .url(objectInfo.getObjectPath())//可访问的url
//                .source("")
//                .createtime(new Date())
//                .updatetime(new Date())
//                .build();

        return Result.<OssFileInfo>builder()
                .data(
                        OssFileInfo.builder()
                                .id(UUID.randomUUID().toString())
                                .filename(file.getOriginalFilename())
                                .isimg(Objects.requireNonNull(file.getContentType()).startsWith("image/"))
                                .contenttype(file.getContentType())
                                .url(objectInfo.getObjectPath())
                                .path(objectInfo.getObjectPath())
                                .source("")
                                .size(file.getSize())
                                .createtime(new Date())
                                .updatetime(new Date())
                                .build())
                .code(200)
                .flag(true)
                .message("")
                .build();

//        return null;
    }


    /**
     * 文件来源
     *
     * @return
     */
    protected abstract String fileType();

    /**
     * 上传文件
     *
     * @param file
     */
    protected abstract ObjectInfo uploadFile(MultipartFile file);

    protected abstract ObjectInfo uploadFile(InputStream inputStream, String fileType);
}
