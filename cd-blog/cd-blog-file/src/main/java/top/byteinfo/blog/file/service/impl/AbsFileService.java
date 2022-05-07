package top.byteinfo.blog.file.service.impl;

import org.springframework.web.multipart.MultipartFile;
import top.byteinfo.blog.common.core.model.result.Result;
import top.byteinfo.blog.file.service.IFileService;
import top.byteinfo.blog.mbg.entity.OssFileInfo;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
public abstract class AbsFileService implements IFileService {

    @Override
    public Result<OssFileInfo> upload(MultipartFile file) throws Exception {

        OssFileInfo ossFileInfo = OssFileInfo.builder()
                .id(UUID.randomUUID().toString().replace("-", ""))
                .filename(file.getOriginalFilename())
                .contenttype(file.getContentType())
                .isimg(Objects.requireNonNull(file.getContentType()).startsWith("image/"))
                .size(file.getSize())
                .path("")
                .url("")
                .source("")
                .createtime(new Date())
                .updatetime(new Date())
                .build();

      return   Result.<OssFileInfo>builder()
                .data(
                        OssFileInfo.builder()
                                .id(UUID.randomUUID().toString())
                                .build()
                ).build();
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
//    protected abstract ObjectInfo uploadFile(MultipartFile file);
}
