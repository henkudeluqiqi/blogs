package org.king2.blogs.exception;


import org.king2.blogs.result.SystemResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

/*================================================================
说明：全局异常处理

作者          时间             注释
鹿七        2018.7.26	      创建
==================================================================*/
@ControllerAdvice
public class GlobalExceptionHandler {


    private static final Logger Logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * ----------------------------------------------------------------- 功能：处理全局异常
     * <p>
     * 返回：SystemResult 异常结果信息
     * -------------------------------------------------------------------
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public SystemResult errorHandler(Exception e) {
        // 打印栈堆信息
        e.printStackTrace();
        return SystemResult.build(500, "程序内部异常,请稍后再试");
    }

    /**
     * -----------------------------------------------------------------
     * 功能：处理数据校验异常
     * <p>
     * 返回：SystemResult 异常结果信息
     * -------------------------------------------------------------------
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public SystemResult dataException(BindException bind) {
        // 返回错误信息
        return SystemResult.build(100, bind.getFieldError().getDefaultMessage());
    }

    /**
     * -----------------------------------------------------------------
     * 功能：处理加了@RequestBody数据校验异常
     * <p>
     * 返回：SystemResult 异常结果信息
     * -------------------------------------------------------------------
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public SystemResult MethodArgumentNotValidException(MethodArgumentNotValidException dataValid) {
        // 返回错误信息
        dataValid.printStackTrace();
        return SystemResult.build(100, dataValid.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 处理请求单个参数不满足校验规则的异常信息
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public SystemResult constraintViolationExceptionHandler(HttpServletRequest request,
                                                            ConstraintViolationException exception) {
        // 执行校验，获得校验结果
        Set<ConstraintViolation<?>> validResult = exception.getConstraintViolations();
        // 返回错误信息
        return SystemResult.build(100, validResult.iterator().next().getMessage());
    }

    /**
     * 解析异常信息
     *
     * @param t
     * @return
     */
    public static String getTrace(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
    }
}
