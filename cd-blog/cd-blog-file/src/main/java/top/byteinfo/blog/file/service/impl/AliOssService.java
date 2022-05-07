package top.byteinfo.blog.file.service.impl;

import org.springframework.stereotype.Service;

@Service
public class AliOssService extends AbsFileService{

    @Override
    protected String fileType() {
        return null;
    }
}
