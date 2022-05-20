package top.byteinfo.cd.auth.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result<T> {
    private T datas;
    private int resp_code;

    private String resp_msg;
}
