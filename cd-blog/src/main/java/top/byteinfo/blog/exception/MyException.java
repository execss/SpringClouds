package top.byteinfo.blog.exception;

import lombok.Data;

@Data
public class MyException extends RuntimeException{
    private final String message;
}
