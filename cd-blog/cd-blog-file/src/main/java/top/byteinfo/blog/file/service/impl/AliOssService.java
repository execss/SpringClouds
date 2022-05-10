package top.byteinfo.blog.file.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.byteinfo.blog.file.config.OssConfig;
import top.byteinfo.blog.file.model.ObjectInfo;

import java.io.InputStream;

@Slf4j
@Service
public class AliOssService extends AbsFileService {

    @Autowired
    private OssConfig ossConfig;

    @Override
    protected String fileType() {
        return null;
    }

    @Override
    protected ObjectInfo uploadFile(MultipartFile file) {

        //TODO
        String objectName = "MultipartFile/" + file.getOriginalFilename();

        OSS ossClient = null;
        try (InputStream inputStream = file.getInputStream()) {
            ossClient = new OSSClientBuilder().build(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
            PutObjectRequest putObjectRequest = new PutObjectRequest(ossConfig.getBucketName(), objectName, inputStream);
            ossClient.putObject(putObjectRequest);


        } catch (Exception e) {

            throw new RuntimeException(e);
        } finally {
            ossClient.shutdown();
        }


        String url = ossConfig.getBucketName() + "." + ossConfig.getEndpoint() + "." + objectName;
        return ObjectInfo.builder().objectPath(objectName).objectUrl(url).build();
    }

    @Override
    protected ObjectInfo uploadFile(InputStream inputStream, String fileType) {

        //TODO
        String objectName = "MultipartFile/" + "inputStream01." + fileType;

        OSS ossClient = new OSSClientBuilder().build(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
        PutObjectRequest putObjectRequest = new PutObjectRequest(ossConfig.getBucketName(), objectName, inputStream);


        try {
            ossClient.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            ossClient.shutdown();
        }


        return null;
    }
}
