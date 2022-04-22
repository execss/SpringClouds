package top.byteinfo.blog.restApi;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.byteinfo.blog.exception.MyException;
import top.byteinfo.blog.model.result.Result;
@Log4j2
@RestControllerAdvice
public class ApiAdviceHandler {
    @ExceptionHandler(MyException.class)
    public Result<?> tokenVerifyExceptionHandler(MyException e) {
        return Result.fail(e.getMessage());
    }
}
