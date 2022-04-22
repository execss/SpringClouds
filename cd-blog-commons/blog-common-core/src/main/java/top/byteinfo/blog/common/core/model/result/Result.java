package top.byteinfo.blog.common.core.model.result;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result<T> {

    private Boolean flag ;
    private Integer code;
    /**
     * 200
     *
     * 400 Bad Request
     * 401 Unauthorized
     * 403 Forbidden
     * 404 Not Found
     * 405 Method Not Allowed
     * 406
     * 407
     *
     *
     * 500 Internal Server Error
     * 501  Not Implemented
     * 502 Bad Gateway
     * 503 Service Unavailable
     *
     *
     *
     */
    private String message;
    private T data;



    public static Result<?> ok(String message){
        return Result.builder().flag(true).code(200).message(message).data(null).build();
    }
    public static <T> Result<?> ok(String message, T data){
        return Result.builder().flag(true).code(200).message(message).data(data).build();
    }

    public static Result<?> fail(String message){

        return Result.builder().flag(false).code(401).message(message).data(null).build();
    }
    public static <T> Result<?> fail(String message,T data){

        return Result.builder().flag(false).code(401).message(message).data(data).build();
    }



}
