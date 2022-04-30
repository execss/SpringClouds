package top.byteinfo.blog.common.core.util;

import com.alibaba.fastjson.JSON;

public class Fjson {


    public static byte[] toBytes(Object o) {
        return JSON.toJSONBytes(o);
    }
}
