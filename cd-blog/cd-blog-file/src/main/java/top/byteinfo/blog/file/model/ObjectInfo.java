package top.byteinfo.blog.file.model;

import lombok.Builder;
import lombok.Getter;

/**
 * @author zlt
 * @date 2021/2/11
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@Getter
@Builder
public class ObjectInfo {
    /**
     * 对象查看路径
     */
    private String objectUrl;
    /**
     * 对象保存路径
     */
    private String objectPath;
}
